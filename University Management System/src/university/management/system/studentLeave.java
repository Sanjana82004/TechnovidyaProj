/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package university.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
public class studentLeave extends JFrame implements ActionListener
{
    Choice crollno, ctime;
    JDateChooser dcdate;
    JButton submit, cancel;
    studentLeave(){
        setSize(500,550);
        setLocation(550, 100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Apply leave (Student)");
        heading.setBounds(40,50, 300, 30 );
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);
        
        JLabel lblrollno = new JLabel("search by roll number");
        lblrollno.setBounds(60,100,200, 20 );
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblrollno);
        
        crollno = new Choice();
        crollno.setBounds(60, 130, 200,20);
        add(crollno);
        
        
        try{
           Conn c = new  Conn();
           ResultSet rs = c.s.executeQuery("select * from student");
           while(rs.next()){
               crollno.add(rs.getString("rollno"));
           }
        } catch(Exception e){
            e.printStackTrace();
        }
    
    
     JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60,180,200, 20 );
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lbldate);
        
        
        dcdate = new JDateChooser();
        dcdate.setBounds(70, 210,200, 25);
        add(dcdate);
        
         JLabel lbltime = new JLabel("Time Duration");
        lbltime.setBounds(60,260,200, 20 );
        lbltime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbltime);
        
        ctime = new Choice();
        ctime.setBounds(60, 290, 200,20);
        ctime.add("full day");
        ctime.add("half day");
        add(ctime);
        
         submit = new JButton("Submit");
        submit.setBounds(60, 350, 100, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoman",Font.BOLD, 15));
        add(submit);
        
         cancel = new JButton("cancel");
        cancel.setBounds(200, 350, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
       cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoman",Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
        
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== submit){
            String rollno = crollno.getSelectedItem();
            String date = ((JTextField) dcdate.getDateEditor().getUiComponent()). getText();
            String duration = ctime.getSelectedItem();
            
            String query = "insert into studentleave values('"+rollno+"','"+date+"','"+duration+"')";
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Leave confirmed");
                setVisible(false);
            } catch(Exception e){
                e.printStackTrace();
            }
        } else{
            setVisible(false);
        }
    }
    public static void main(String []args){
        new studentLeave();
    }
}
