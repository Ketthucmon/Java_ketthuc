/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.*;
import javax.swing.*;


/**
 *
 * @author K-P-P-K
 */
public class Connect {
   public String ThongBao=""; 
   Connection conn=null;
  public static  Connection ConnectDB() 
  {
    try{
    Class.forName("com.mysql.jdbc.Driver");
    String url="jdbc:mysql://localhost:3306/sach";
    Connection conn=DriverManager.getConnection(url,"root","");
    return conn; 
    }
catch(Exception e){
     JOptionPane.showMessageDialog(null,e);
      return null;
}
   
  }

  
}
    
    

