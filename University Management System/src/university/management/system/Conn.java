/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package university.management.system;

/**
 *
 * @author sanja
 */
import java.sql.*;
public class Conn {
  Connection c;  
  Statement s;
    Conn(){
        try{
        //  Class.forName("com.mysql.cj.jdbc.Driver") ; 
          c = DriverManager.getConnection("jdbc:mysql:///universitymanagementsystem","root", "1234");
           s = c.createStatement();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
