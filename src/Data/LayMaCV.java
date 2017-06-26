
package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class LayMaCV {
Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;
 public String LayChucVu(String tencv){
     String macv="";
     try{
            Statement st;
            ResultSet rs;
            Connect con = new Connect();
            conn=con.ConnectDB();
            String sql="Select * from chucvu where tencv='"+tencv+"'"; 
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next())
            {
                macv=rs.getString("macv");     
            }
        }catch(Exception e){
            e.printStackTrace();
        }
     return macv;
 }   
}
