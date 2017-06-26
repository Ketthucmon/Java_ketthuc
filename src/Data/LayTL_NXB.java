
package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class LayTL_NXB {
Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;
  public String LayMaTL(String tentl){
     String matl="";
     try{
            Statement st;
            ResultSet rs;
            Connect con = new Connect();
            conn=con.ConnectDB();
            String sql="Select matl from theloai where tentl='"+tentl+"'"; 
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next())
            {
                matl=rs.getString("matl");     
            }
        }catch(Exception e){
            e.printStackTrace();
        }
     return matl;
 }     
  public String LayNXB(String tenNXB){
     String maNXB="";
     try{
            Statement st;
            ResultSet rs;
            Connect con = new Connect();
            conn=con.ConnectDB();
            String sql="Select * from NXB where tenNXB='"+tenNXB+"'"; 
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next())
            {
                maNXB=rs.getString("maNXB");     
            }
        }catch(Exception e){
            e.printStackTrace();
        }
     return maNXB;
 }   
}
