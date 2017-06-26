
package Data;

import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ThucHienQuery {
Connection conn=null;   
 public void executeSQlQuery(String query, String message)
   {
       Connect con = new Connect();
       conn=con.ConnectDB();
       Statement st;
       try{
           st = conn.createStatement();
           if((st.executeUpdate(query)) == 1)
           {
               JOptionPane.showMessageDialog(null, "Dữ liệu "+message+" thành công!");
           }else{
               JOptionPane.showMessageDialog(null, "Data Not "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }    
}
