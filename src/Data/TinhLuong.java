
package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class TinhLuong {
Connection conn=null;
ResultSet rs=null;
   public int SoLan(int Thang,int Nam,String MaID){
       Connect con = new Connect();
        conn=con.ConnectDB();
        Statement st;
        ResultSet rs;
       int solan=0;
       String query="select idnv,count(*) from diemdanh where MONTH(ngayvang)="+Thang+" and YEAR(ngayvang)="+Nam+" and idnv='"+MaID+"' GROUP BY idnv ";
       try{
        st=conn.createStatement();
        rs=st.executeQuery(query);
       
        while(rs.next())
        {
            solan=rs.getInt("count(*)");
        }
        }catch(Exception e){
            e.printStackTrace();
        }
       return solan;
   }
   public float HSL(String MaID){
       float hs=0;
        Connect con = new Connect();
        conn=con.ConnectDB();
        Statement st;
        ResultSet rs;
        String query="select * from chucvu inner join nhanvien on chucvu.macv=nhanvien.macv where idnv='"+MaID+"'";
        try{
        st=conn.createStatement();
        rs=st.executeQuery(query);
       
        while(rs.next())
        {
            hs=rs.getFloat("HeSoLuong");
        }
        }catch(Exception e){
            e.printStackTrace();
        }
       return hs;
   }
   public int PhuCapCV(String MaID){
       int hs=0;
        Connect con = new Connect();
        conn=con.ConnectDB();
        Statement st;
        ResultSet rs;
        String query="select * from chucvu inner join nhanvien on chucvu.macv=nhanvien.macv where idnv='"+MaID+"'";
        try{
        st=conn.createStatement();
        rs=st.executeQuery(query);
       
        while(rs.next())
        {
            hs=rs.getInt("phucapvc");
        }
        }catch(Exception e){
            e.printStackTrace();
        }
       return hs;
   }
   public String TenNV(String MaID){
       String TenNV="";
        Connect con = new Connect();
        conn=con.ConnectDB();
        Statement st;
        ResultSet rs;
        String query="select * from nhanvien where idnv='"+MaID+"'";
        try{
        st=conn.createStatement();
        rs=st.executeQuery(query);
       
        while(rs.next())
        {
            TenNV=rs.getString("tennv");
        }
        }catch(Exception e){
            e.printStackTrace();
        }
       return TenNV;
   }
}
