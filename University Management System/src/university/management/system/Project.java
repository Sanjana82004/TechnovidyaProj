/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package university.management.system;

import javax.swing.*;import java.awt.*;
import java.awt.event.*;
public class Project extends JFrame implements ActionListener  {
    Project(){
           setSize(1540, 850);
           ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        JMenuBar mb = new JMenuBar();
        JMenu newInformation = new JMenu("new Information");
        newInformation.setForeground(Color.BLUE);
        mb.add(newInformation);
        
        JMenuItem facultyInfo = new JMenuItem("new faculty Information");
        facultyInfo.setBackground(Color.WHITE);
        facultyInfo.addActionListener(this);
        newInformation.add(facultyInfo);
        
        JMenuItem studentInfo = new JMenuItem("new student Information");
        studentInfo.setBackground(Color.WHITE);
        studentInfo.addActionListener(this);
        newInformation.add(studentInfo);
        
        JMenu details = new JMenu("view Details");
        details.setForeground(Color.RED);
        mb.add(details);
        
         JMenuItem facultydetails = new JMenuItem("view faculty details");
       facultydetails.setBackground(Color.WHITE);
       facultydetails.addActionListener(this);
       details.add(facultydetails);
       
          JMenuItem studentdetails = new JMenuItem("view student details");
        studentdetails.setBackground(Color.WHITE);
        studentdetails.addActionListener(this);
        details.add(studentdetails);  
        
         JMenu leave = new JMenu("Apply Leave");
        leave.setForeground(Color.BLUE);
        mb.add(leave);
        
         JMenuItem facultyleave = new JMenuItem("faculty Leave");
       facultyleave.setBackground(Color.WHITE);
       facultyleave.addActionListener(this);
       leave.add(facultyleave);
       
          JMenuItem studentleave = new JMenuItem("student Leave");
        studentleave.setBackground(Color.WHITE);
        studentleave.addActionListener(this);
        leave.add(studentleave);  
        
         JMenu leaveDetails = new JMenu("Leave Details");
        leaveDetails.setForeground(Color.RED);
        mb.add(leaveDetails);
        
         JMenuItem facultyleavedetails = new JMenuItem("faculty Leave Details");
       facultyleavedetails.setBackground(Color.WHITE);
        facultyleavedetails.addActionListener(this);
       leaveDetails.add(facultyleavedetails);
       
          JMenuItem studentleavedetails = new JMenuItem("student Leave Details");
        studentleavedetails.setBackground(Color.WHITE);
         studentleavedetails.addActionListener(this);
        leaveDetails.add(studentleavedetails); 
//        
       JMenu exam = new JMenu("Examination");
        exam.setForeground(Color.BLUE);
        mb.add(exam);
        
         JMenuItem examinationdetails = new JMenuItem("Examination Result");
       examinationdetails.setBackground(Color.WHITE);
        examinationdetails.addActionListener(this);
       exam.add(examinationdetails);
       
          JMenuItem entermarks = new JMenuItem("Enter Marks");
        entermarks.setBackground(Color.WHITE);
         entermarks.addActionListener(this);
        exam.add(entermarks); 
        
        JMenu updateInfo = new JMenu(" update details ");
        updateInfo.setForeground(Color.RED);
        mb.add(updateInfo);
        
        JMenuItem updatefacultyinfo = new JMenuItem("update faculty details");
        updatefacultyinfo.setBackground(Color.WHITE);
        updatefacultyinfo.addActionListener(this);
        updateInfo.add(updatefacultyinfo); 
        
        JMenuItem updatestudentinfo = new JMenuItem("update student details");
        updatestudentinfo.setBackground(Color.WHITE);
        updatestudentinfo.addActionListener(this);
        updateInfo.add(updatestudentinfo); 
        
        JMenu fee = new JMenu(" fee details ");
        fee.setForeground(Color.BLUE);
        mb.add(fee);
        JMenuItem feestructure = new JMenuItem("fee structure");
        feestructure.setBackground(Color.WHITE);
        feestructure.addActionListener(this);
        fee.add(feestructure); 
        JMenuItem feeform = new JMenuItem("student fee form");
        feeform.setBackground(Color.WHITE);
        feeform.addActionListener(this);
        fee.add(feeform);
        
         JMenu utility = new JMenu("utility ");
        utility.setForeground(Color.RED);
        mb.add(utility);
        JMenuItem notepad = new JMenuItem("notepad");
        notepad.setBackground(Color.WHITE);
        notepad.addActionListener(this);
        utility.add(notepad); 
        JMenuItem calculator = new JMenuItem("calculator");
        calculator.setBackground(Color.WHITE);
        calculator.addActionListener(this);
       utility.add(calculator);
       
       JMenu about = new JMenu("About");
       about.setForeground(Color.BLUE);
       mb.add(about);
       
       JMenuItem ab = new JMenuItem("About");
       ab.setBackground(Color.BLUE);
       ab.addActionListener(this);
       about.add(ab);
       
        JMenu exit = new JMenu("Exit ");
        exit.setForeground(Color.BLUE);
        mb.add(exit);
        JMenuItem ex = new JMenuItem("Exit");
        ex.setBackground(Color.WHITE);
        ex.addActionListener(this);
        exit.add(ex); 
        
        setJMenuBar(mb);
       
           setVisible(true);
    }
    public void actionPerformed (ActionEvent ae){
        String msg = ae.getActionCommand();
        if(msg.equals("Exit")){
           setVisible(false); 
        }
        else if(msg.equals("calculator")){
            try{
              Runtime.getRuntime().exec("calc.exe");
            } catch(Exception e){
               
            }
        } else if(msg.equals("notepad")){
            try{
                Runtime.getRuntime().exec("notepad.exe");
            } catch(Exception e){
               e.printStackTrace();
            }
        } else if(msg.equals("new faculty Information")){
            new AddTeacher();
        } else if(msg.equals("new student Information")){
            new AddStudent();
        } else if(msg.equals("view faculty details")){

          new TeacherDetails();
        }else if(msg.equals("view student details")){
            new studentDetails();
        
    }else if(msg.equals("faculty Leave")){
            new TeacherLeave();
    }else if(msg.equals("student Leave")){
            new studentLeave();
    }else if(msg.equals("faculty Leave Details")){
            new TeacherleaveDetails();
    }else if(msg.equals("student Leave Details")){
            new studentleaveDetails();
    }else if(msg.equals("update faculty details")){
            new UpdateTeacher();
    }else if(msg.equals("update student details")){
            new UpdateStudent();
    }else if(msg.equals("Enter Marks")){
            new EnterMarks();
    } else if(msg.equals("Examination Result")){
            new ExaminationDetails();
    }else if(msg.equals("fee structure")){
            new FeeStructure();
    }else if(msg.equals("About")){
            new About();
    } else if(msg.equals("student fee form")){
            new StudentFeeForm();
    }
    }
       
   public static void main(String args[]){
       new Project();
   }
}

