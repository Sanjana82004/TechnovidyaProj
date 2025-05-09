/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import university.management.system.GoogleOAuth;
public class Login extends JFrame implements ActionListener {
    JButton Login, cancel;
    JTextField tfusername, tfpassword;
    Login(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 20);
        add(lblusername);
         tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 20);
        add(tfusername);
        JLabel lblpassword = new JLabel("password");
        lblpassword.setBounds(40, 70, 100, 20);
        add(lblpassword);
          tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 20);
        add(tfpassword);
         Login = new JButton("Login");
        Login.setBounds(40, 140, 120, 30);
        Login.setBackground(Color.BLACK);
        Login.setForeground(Color.WHITE);
        Login.addActionListener(this);
        Login.setFont(new Font("Tahoman",Font.BOLD, 16));
        add(Login);
        
JButton googleLoginButton = new JButton("Login with Google");
googleLoginButton.setBounds(40, 180, 260, 30); // add bounds
googleLoginButton.setBackground(Color.BLACK);
googleLoginButton.setForeground(Color.WHITE);
googleLoginButton.setFont(new Font("Tahoma", Font.BOLD, 14));

googleLoginButton.addActionListener(e -> {
    try {
        GoogleUser user = GoogleOAuth.startLoginFlow();

        if (user != null) {
            String email = user.getEmail();
            Conn c = new Conn();
            String query = "SELECT * FROM login WHERE username = '" + email + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Welcome, " + user.getName());
                setVisible(false);
                new Project(); // open main dashboard
            } else {
                // Optionally register the user automatically:
                String insert = "INSERT INTO login (username, password) VALUES ('" + email + "', 'oauth')";
                c.s.executeUpdate(insert);
                JOptionPane.showMessageDialog(null, "First time login. Account created for: " + email);
                setVisible(false);
                new Project();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Google login failed.");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
});
add(googleLoginButton); // make sure to add the button to frame


        
         cancel = new JButton("cancel");
        cancel.setBounds(180, 140, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
         cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoman",Font.BOLD, 16));
        add(cancel);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);
        setSize(600, 300);
        setLocation(500, 250);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == Login){
            String username = tfusername.getText();
            String password = tfpassword.getText();
            String  query = "select * from login where username='" + username+"' and password='"+password+"'";
            try{
             Conn c = new Conn() ;  
          ResultSet rs =   c.s.executeQuery(query);
          if(rs.next()){
             setVisible(false) ;
             new Project();
          }
          else{
            JOptionPane.showMessageDialog(null,"Invalid username or password");
            setVisible(false);
          }
        } catch(Exception e){
            e.printStackTrace();
        }
        }
        else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }
    public static void main(String args[]){
        new Login();
   }
}
