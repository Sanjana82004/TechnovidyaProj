package university.management.system;

import com.sun.net.httpserver.*;
import org.json.JSONObject;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import io.github.cdimascio.dotenv.Dotenv;

public class GoogleOAuth {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String CLIENT_ID = dotenv.get("GOOGLE_CLIENT_ID");
    private static final String CLIENT_SECRET = dotenv.get("GOOGLE_CLIENT_SECRET");
    private static final String REDIRECT_URI = "http://localhost:8080/callback";
    private static final String TOKEN_ENDPOINT = "https://oauth2.googleapis.com/token";
    private static final String USERINFO_ENDPOINT = "https://www.googleapis.com/oauth2/v2/userinfo";

    public static GoogleUser startLoginFlow() throws Exception {
        String authUrl = "https://accounts.google.com/o/oauth2/v2/auth?" +
                "response_type=code&" +
                "client_id=" + URLEncoder.encode(CLIENT_ID, "UTF-8") + "&" +
                "redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8") + "&" +
                "scope=" + URLEncoder.encode("https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email", "UTF-8") + "&" +
                "access_type=offline";

        final GoogleUser[] user = new GoogleUser[1];

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/callback", exchange -> {
            try {
                String query = exchange.getRequestURI().getQuery();
                String code = query.split("code=")[1].split("&")[0];

                String response = "Login successful! You can close this window.";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

                user[0] = fetchAccessToken(code);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                server.stop(0);
                synchronized (user) {
                    user.notify();
                }
            }
        });
        server.start();

        Desktop.getDesktop().browse(new URI(authUrl));

        synchronized (user) {
            user.wait(); // wait until login is complete
        }

        return user[0];
    }

    private static GoogleUser fetchAccessToken(String code) throws Exception {
        String params = "code=" + URLEncoder.encode(code, "UTF-8") +
                "&client_id=" + URLEncoder.encode(CLIENT_ID, "UTF-8") +
                "&client_secret=" + URLEncoder.encode(CLIENT_SECRET, "UTF-8") +
                "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8") +
                "&grant_type=authorization_code";

        URL url = new URL(TOKEN_ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(params.getBytes(StandardCharsets.UTF_8));
        }

        InputStream is = conn.getInputStream();
        String response = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        JSONObject json = new JSONObject(response);
        String accessToken = json.getString("access_token");

        return fetchUserInfo(accessToken);
    }

    private static GoogleUser fetchUserInfo(String token) throws Exception {
        URL url = new URL(USERINFO_ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "Bearer " + token);

        InputStream is = conn.getInputStream();
        String response = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        JSONObject json = new JSONObject(response);

        return new GoogleUser(
                json.optString("name"),
                json.optString("email")
               
        );
    }
}
