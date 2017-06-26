/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Data.Connect;
import Data.LayMaCV;
import Data.LayTL_NXB;
import Data.ThucHienQuery;
import Data.TinhLuong;
import Process.BangChucVu;
import Process.BangDiemDanh;
import Process.BangLuong;
import Process.BangNV;
import Process.BangNXB;
import Process.BangSach;
import Process.BangTheLoai;
import Process.HienIcon;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author K-P-P-K
 */
public class WelcomeAdmin extends javax.swing.JFrame {

Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;

    public WelcomeAdmin() {
        initComponents();
        Pn_TT.removeAll();
        Pn_TT.repaint();
        Pn_TT.revalidate();
        
        Pn_TT.add(Pn_GioiThieu);
        Pn_TT.repaint();
        Pn_TT.revalidate();
    }
    public ArrayList<BangNV> BangNVList()
    {   
         String User; 
         String Pass ; 
         String HoTenNV;
         int NamSinh;
         int Gioitinh;
         String ChucVu;
        ArrayList<BangNV> BangNVList=new ArrayList<BangNV>();
        Connect con = new Connect();
        conn=con.ConnectDB();
        String query="SELECT * from nhanvien INNER JOIN chucvu on nhanvien.macv=chucvu.macv";
        Statement st;
        ResultSet rs;
        BangNV Bang;
        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            while(rs.next())
            {
               User=rs.getString("idnv");
               Pass=rs.getString("pass");
               HoTenNV=rs.getString("honv")+" "+rs.getString("tennv");
               NamSinh=rs.getInt("namsinh");
               Gioitinh=rs.getInt("gioitinh");
               ChucVu=rs.getString("tencv");
               Bang = new BangNV(User,Pass,HoTenNV,NamSinh,Gioitinh,ChucVu);
               BangNVList.add(Bang);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return BangNVList;
    }
    public void Show_BangNV_In_JTable()
    {
       ArrayList<BangNV> list=BangNVList();
      DefaultTableModel model = new DefaultTableModel();
      model.setColumnIdentifiers(new Object[]{"User","Pass","HoTen","NamSinh","GioiTinh","Chucvu"});
      Object[] row=new Object[6];
        for(int i=0;i<list.size();i++)
        {
            row[0]=list.get(i).getUser();
            row[1]=list.get(i).getPass();
            row[2]=list.get(i).getHoTen();
            row[3]=list.get(i).getNamSinh();
            row[4]=list.get(i).getGioitinh();
            row[5]=list.get(i).getChucVu();
            model.addRow(row);
        }
    TBNhanVien.setModel(model);
    }
    public void HienCombo(){
        try{
            Statement st;
            ResultSet rs;
            Connect con = new Connect();
            conn=con.ConnectDB();
            String sql="Select * from chucvu"; 
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next())
            {
                String tencv=rs.getString("tencv");
                CBChucVu.addItem(tencv);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<BangSach> BangSachList()
    {   
        String MaSach; 
        String TenSach ; 
        byte[] AnhSach;
        String TacGia;
        int Gia;
        int SoLuong;
        String MaTL;
        String MaNXB;
         
        ArrayList<BangSach> BangSachList=new ArrayList<BangSach>();
        Connect con = new Connect();
        conn=con.ConnectDB();
        String query="SELECT * from bangsach INNER JOIN theloai on bangsach.MaTL=theloai.MaTL INNER JOIN nxb on bangsach.MaNXB=nxb.MaNXB";
        Statement st;
        ResultSet rs;
        BangSach Bang;
        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            while(rs.next())
            {
               MaSach=rs.getString("masach");
               TenSach=rs.getString("tensach");
               AnhSach=rs.getBytes("AnhSach");
               TacGia=rs.getString("tacgia");
               Gia=rs.getInt("gia");
               SoLuong=rs.getInt("soluong");
               MaTL=rs.getString("Tentl");
               MaNXB=rs.getString("TenNXB");
               Bang = new BangSach(MaSach,TenSach,AnhSach,TacGia,Gia,SoLuong,MaTL,MaNXB);
               BangSachList.add(Bang);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return BangSachList;
    }
    public void hienTL()
       {
           try{
            Statement st;
            ResultSet rs;
            Connect con = new Connect();
            conn=con.ConnectDB();
            String sql="Select * from theloai"; 
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next())
            {
                String tentl=rs.getString("tentl");
                CBTheLoai.addItem(tentl);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
       }
    public void hienNXB()
       {
           try{
            Statement st;
            ResultSet rs;
            Connect con = new Connect();
            conn=con.ConnectDB();
            String sql="Select * from nxb"; 
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next())
            {
                String tennxb=rs.getString("TenNXB");
                CBNXB.addItem(tennxb);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
       }
    public ImageIcon InAnh(String LinkAnh,byte[] pic){
      ImageIcon MyImage=null;
      if(LinkAnh!=null){
          MyImage=new ImageIcon(LinkAnh);
      }else{
          MyImage=new ImageIcon(pic);
      }
      Image img=MyImage.getImage();
      Image newImg=img.getScaledInstance(lblAnh.getWidth(),lblAnh.getHeight(),Image.SCALE_SMOOTH);
      ImageIcon anh=new ImageIcon(newImg);   
      return anh;
      }
    public void HienBang()
  {     
        ArrayList<BangSach> list = BangSachList();
        
        String[] columnName = {"Mã Sách","Tên Sách","Ảnh Sách","Tác Giả","Giá","Số Lượng","Thể Loại","Nhà Xuất Bản"};
        Object[][] rows = new Object[list.size()][8];
        for(int i = 0; i < list.size(); i++){
            rows[i][0] = list.get(i).getMaSach();
            rows[i][1] = list.get(i).getTenSach();
            rows[i][2] = list.get(i).getAnhSach();
            if(list.get(i).getAnhSach() != null){
                
              ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getAnhSach()).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH) );      
                
            rows[i][2] = image;
            }
            else{
                rows[i][2] = null;
            }
            rows[i][3] = list.get(i).getTacGia();
            rows[i][4] = list.get(i).getGia();
            rows[i][5] = list.get(i).getSoLuong();
            rows[i][6] = list.get(i).getMaTL();
            rows[i][7] = list.get(i).getMaNXB();
            
            
            
        }
        
        HienIcon model = new HienIcon(rows, columnName);
        BangSach.setModel(model);
        BangSach.setRowHeight(80);
        BangSach.getColumnModel().getColumn(2).setPreferredWidth(80);
    }
        public ArrayList<BangNV> BangNVList1(String Tim)
    {   
         String User; 
         String Pass ; 
         String HoTenNV;
         int NamSinh;
         int Gioitinh;
         int TimGT=2;
        if (Tim.equals("Nam")==true)TimGT=1; 
        if (Tim.equals("Nữ")==true) TimGT=0; 
         String ChucVu;
        ArrayList<BangNV> BangNVList1=new ArrayList<BangNV>();
        Connect con = new Connect();
        conn=con.ConnectDB();
        String query="SELECT * from nhanvien INNER JOIN chucvu on nhanvien.macv=chucvu.macv WHERE idnv Like '%"+Tim+"%' or honv Like '%"+Tim+"%' or tennv Like '%"+Tim+"%' or namsinh Like '%"+Tim+"%' or chucvu.macv Like '%"+Tim+"%' or gioitinh Like '%"+TimGT+"%' or tencv Like '%"+Tim+"%'  or phucapvc Like '%"+Tim+"%'";
        Statement st;
        ResultSet rs;
        BangNV Bang;
        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            while(rs.next())
            {
               User=rs.getString("idnv");
               Pass=rs.getString("pass");
               HoTenNV=rs.getString("honv")+" "+rs.getString("tennv");
               NamSinh=rs.getInt("namsinh");
               Gioitinh=rs.getInt("gioitinh");
               ChucVu=rs.getString("tencv");
               Bang = new BangNV(User,Pass,HoTenNV,NamSinh,Gioitinh,ChucVu);
               BangNVList1.add(Bang);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return BangNVList1;
    }   
    public void Show_BangNV_In_JTable1()            
    {
      ArrayList<BangNV> list=BangNVList1(txtTim.getText());
       DefaultTableModel model = new DefaultTableModel();
      model.setColumnIdentifiers(new Object[]{"User","Pass","HoTen","NamSinh","Gioitinh","ChucVu"});
      Object[] row=new Object[6];
        for(int i=0;i<list.size();i++)
        {
            row[0]=list.get(i).getUser();
            row[1]=list.get(i).getPass();
            row[2]=list.get(i).getHoTen();
            row[3]=list.get(i).getNamSinh();
            row[4]=list.get(i).getGioitinh();
            row[5]=list.get(i).getChucVu();
        model.addRow(row);
        }
     TBNhanVien.setModel(model);
    }
    public ArrayList<BangTheLoai> BangTheLoaiList(){
        String MaTL;
        String TenTL;
        String LuaTuoi;
        ArrayList<BangTheLoai> BangTheLoaiList=new ArrayList<BangTheLoai>();
        Connect con = new Connect();
        conn=con.ConnectDB();
        String query="SELECT * from TheLoai";
        Statement st;
        ResultSet rs;
        BangTheLoai Bang;
        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            while(rs.next())
            {
              MaTL=rs.getString("matl");
              TenTL=rs.getString("tentl");
              LuaTuoi=rs.getString("luatuoi");
              Bang = new BangTheLoai(MaTL,TenTL,LuaTuoi);
              BangTheLoaiList.add(Bang);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return BangTheLoaiList;
    }
    public ArrayList<BangChucVu> BangChucVuList(){
        String MaCV;
        String TenCV;
        int PhuCapCV;
        int HeSoLuong;
        ArrayList<BangChucVu> BangChucVuList=new ArrayList<BangChucVu>();
        Connect con = new Connect();
        conn=con.ConnectDB();
        String query="SELECT * from chucvu";
        Statement st;
        ResultSet rs;
        BangChucVu Bang;
        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            while(rs.next())
            {
              MaCV=rs.getString("macv");
              TenCV=rs.getString("tencv");
              PhuCapCV=rs.getInt("phucapvc");
              HeSoLuong=rs.getInt("HeSoLuong");
              Bang = new BangChucVu(MaCV,TenCV,PhuCapCV,HeSoLuong);
              BangChucVuList.add(Bang);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return BangChucVuList;
    }
    public ArrayList<BangNXB> BangNXBList(){
        String MaNXB;
        String TenNXB;
        String DiaChi;
        String SDT;
        ArrayList<BangNXB> BangNXBList=new ArrayList<BangNXB>();
        Connect con = new Connect();
        conn=con.ConnectDB();
        String query="SELECT * from NXB";
        Statement st;
        ResultSet rs;
        BangNXB Bang;
        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            while(rs.next())
            {
              MaNXB=rs.getString("MaNXB");
              TenNXB=rs.getString("TenNXB");
              DiaChi=rs.getString("DiaChi");
              SDT=rs.getString("SoDT");
              Bang = new BangNXB(MaNXB,TenNXB,DiaChi,SDT);
              BangNXBList.add(Bang);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return BangNXBList;
    }
    public ArrayList<BangDiemDanh> BangDiemDanhList(){
        String MaID;
        String TenID;
        String NgayVang;
        String LyDo;
        ArrayList<BangDiemDanh> BangDiemDanhList=new ArrayList<BangDiemDanh>();
        Connect con = new Connect();
        conn=con.ConnectDB();
        String query="SELECT * from DiemDanh inner join nhanvien on DiemDanh.idnv=nhanvien.idnv";
        Statement st;
        ResultSet rs;
        BangDiemDanh Bang;
        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            while(rs.next())
            {
              MaID=rs.getString("diemdanh.idnv");
              TenID=rs.getString("tennv");
              NgayVang=rs.getString("NgayVang");
              LyDo=rs.getString("lydo");
              Bang = new BangDiemDanh(MaID,TenID,NgayVang,LyDo);
              BangDiemDanhList.add(Bang);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return BangDiemDanhList;
    }
   public ArrayList<BangLuong> BangLuongList(){
        String MaChi; 
        String ID ; 
        String TenID;
        String NgayChi;
        int Thang;
        int Nam;
        float Luong;
        ArrayList<BangLuong> BangLuongList=new ArrayList<BangLuong>();
        Connect con = new Connect();
        conn=con.ConnectDB();
        String query="SELECT * from phieuchi";
        Statement st;
        ResultSet rs;
        BangLuong Bang;
        TinhLuong lay=new TinhLuong();
        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            while(rs.next())
            {
              MaChi=rs.getString("machi");
              ID=rs.getString("idnv");
              NgayChi=rs.getString("ngaychi");
              Thang=rs.getInt("Thang");
              Nam=rs.getInt("Nam");
              Luong=(float)((30-lay.SoLan(Thang, Nam, ID))*2000*lay.HSL(ID))/30+lay.PhuCapCV(ID);
              TenID=lay.TenNV(ID);
              Bang = new BangLuong(MaChi,ID,TenID,NgayChi,Thang,Nam,Luong);
              BangLuongList.add(Bang);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return BangLuongList;
    } 
    public void Show_BangTL()
    {
       ArrayList<BangTheLoai> list=BangTheLoaiList();
      DefaultTableModel model = new DefaultTableModel();
      model.setColumnIdentifiers(new Object[]{"MaTL","TenTL","LuaTuoi"});
      Object[] row=new Object[3];
        for(int i=0;i<list.size();i++)
        {
            row[0]=list.get(i).getMaTL();
            row[1]=list.get(i).getTenTL();
            row[2]=list.get(i).getLuaTuoi();
            model.addRow(row);
        }
    TBTheLoai.setModel(model);
    }
       public void Show_BangCV()
    {
       ArrayList<BangChucVu> list=BangChucVuList();
      DefaultTableModel model = new DefaultTableModel();
      model.setColumnIdentifiers(new Object[]{"MaCV","TenCV","PhuCapCV","HeSoLuong"});
      Object[] row=new Object[4];
        for(int i=0;i<list.size();i++)
        {
            row[0]=list.get(i).getMaCV();
            row[1]=list.get(i).getTenCV();
            row[2]=list.get(i).getPhuCapCV();
            row[3]=list.get(i).getHeSoLuong();
            model.addRow(row);
        }
    TBChucVu.setModel(model);
    }
          public void Show_BangNXB()
    {
       ArrayList<BangNXB> list=BangNXBList();
      DefaultTableModel model = new DefaultTableModel();
      model.setColumnIdentifiers(new Object[]{"MaNXB","TenNXB","DiaChi","SDT"});
      Object[] row=new Object[4];
        for(int i=0;i<list.size();i++)
        {
            row[0]=list.get(i).getMaNXB();
            row[1]=list.get(i).getTenNXB();
            row[2]=list.get(i).getDiaChi();
            row[3]=list.get(i).getSDT();
            model.addRow(row);
        }
    TBNXB.setModel(model);
    }
    public void Show_BangDiemDanh()
    {
       ArrayList<BangDiemDanh> list=BangDiemDanhList();
      DefaultTableModel model = new DefaultTableModel();
      model.setColumnIdentifiers(new Object[]{"idnv","TenNV","ngayvang","lydo"});
      Object[] row=new Object[4];
        for(int i=0;i<list.size();i++)
        {
            row[0]=list.get(i).getMaID();
            row[1]=list.get(i).getTenID();
            row[2]=list.get(i).getNgayVang();
            row[3]=list.get(i).getLyDo();
            model.addRow(row);
        }
    TBDiemDanh.setModel(model);
    }
     public void Show_BangLuong()
    {
       ArrayList<BangLuong> list=BangLuongList();
      DefaultTableModel model = new DefaultTableModel();
      model.setColumnIdentifiers(new Object[]{"MaChi","ID","TenID","NgayChi","Thang","Nam","Luong"});
      Object[] row=new Object[8];
        for(int i=0;i<list.size();i++)
        {
            row[0]=list.get(i).getMaChi();
            row[1]=list.get(i).getID();
            row[2]=list.get(i).getTenID();
            row[3]=list.get(i).getNgayChi();
            row[4]=list.get(i).getThang();
            row[5]=list.get(i).getNam();
            row[6]=list.get(i).getLuong();
            model.addRow(row);
        }
    TBLuong.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGioitinh = new javax.swing.ButtonGroup();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        Pn_Dau = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        MnTT_GioiThieu = new javax.swing.JButton();
        MnTT_Sach = new javax.swing.JButton();
        MnTT_NV = new javax.swing.JButton();
        MnTT_TheLoai = new javax.swing.JButton();
        MnTT_CV = new javax.swing.JButton();
        MnTT_NXB = new javax.swing.JButton();
        MnDiemDanh = new javax.swing.JButton();
        MnLuong = new javax.swing.JButton();
        Pn_TT = new javax.swing.JPanel();
        Pn_TTNV = new javax.swing.JPanel();
        txtTen = new javax.swing.JTextField();
        btnXoaNV = new javax.swing.JButton();
        txtNamSinh = new javax.swing.JTextField();
        btnSuaNV = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBNhanVien = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        RBNam = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        RBNu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        CBChucVu = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        BtnLoadNV = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnThemNV = new javax.swing.JButton();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        txtHo = new javax.swing.JTextField();
        btnTimNV = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        Pn_TTSach = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        BangSach = new javax.swing.JTable();
        lblAnh = new javax.swing.JLabel();
        btnThemSach = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnSuaSach = new javax.swing.JButton();
        txtMaSach = new javax.swing.JTextField();
        btnXoaSach = new javax.swing.JButton();
        txtTenSach = new javax.swing.JTextField();
        btnLoadSach = new javax.swing.JButton();
        txtAnhSach = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTacgia = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        CBTheLoai = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        CBNXB = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        btnchon = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        Pn_GioiThieu = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        Pn_TTTheLoai = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TBTheLoai = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtMaTL = new javax.swing.JTextField();
        txtTenTL = new javax.swing.JTextField();
        txtLuaTuoi = new javax.swing.JTextField();
        btnThemTL = new javax.swing.JButton();
        btnXoaTL = new javax.swing.JButton();
        btnSuaTL = new javax.swing.JButton();
        btnLoadTL = new javax.swing.JButton();
        Pn_TTNXB = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TBNXB = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtMaNXB = new javax.swing.JTextField();
        txtTenNXB = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnThemNXB = new javax.swing.JButton();
        btnXoaNXB = new javax.swing.JButton();
        btnSuaNXB = new javax.swing.JButton();
        btnLoadNXB = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        Pn_TTChucVu = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TBChucVu = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtMaCV = new javax.swing.JTextField();
        txtTenCV = new javax.swing.JTextField();
        txtPhuCapCV = new javax.swing.JTextField();
        btnThemCv = new javax.swing.JButton();
        btnXoaCV = new javax.swing.JButton();
        btnSuaCV = new javax.swing.JButton();
        btnLoadCV = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        txtHeSoLuong = new javax.swing.JTextField();
        Pn_Luong = new javax.swing.JPanel();
        btnXoaLuong = new javax.swing.JButton();
        btnSuaLuong = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        NgayChi = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TBLuong = new javax.swing.JTable();
        jLabel36 = new javax.swing.JLabel();
        BtnLoadLuong = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        btnThemLuong = new javax.swing.JButton();
        txtIDChi = new javax.swing.JTextField();
        txtNgayChi = new javax.swing.JTextField();
        txtThang = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtIDLuong = new javax.swing.JTextField();
        txtNam = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtTenNVLuong = new javax.swing.JTextField();
        Pn_DiemDanh = new javax.swing.JPanel();
        btnXoaDiemDanh = new javax.swing.JButton();
        btnSuaDiemDanh = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        TBDiemDanh = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        BtnLoadDiemDanh1 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        btnThemDiemDanh1 = new javax.swing.JButton();
        txtIDDiemDanh = new javax.swing.JTextField();
        txtNgayVang = new javax.swing.JTextField();
        txtLyDo = new javax.swing.JTextField();
        btnTimDiemDanh1 = new javax.swing.JButton();
        txtTimDiemDanh1 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtTenDiemDanh = new javax.swing.JTextField();

        jLabel33.setText("jLabel33");

        jScrollPane8.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        MnTT_GioiThieu.setText("GioiThieu");
        MnTT_GioiThieu.setFocusable(false);
        MnTT_GioiThieu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MnTT_GioiThieu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MnTT_GioiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTT_GioiThieuActionPerformed(evt);
            }
        });
        jToolBar1.add(MnTT_GioiThieu);

        MnTT_Sach.setText("ThongTinSach");
        MnTT_Sach.setFocusable(false);
        MnTT_Sach.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MnTT_Sach.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MnTT_Sach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTT_SachActionPerformed(evt);
            }
        });
        jToolBar1.add(MnTT_Sach);

        MnTT_NV.setText("ThongTinNhanVien");
        MnTT_NV.setFocusable(false);
        MnTT_NV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MnTT_NV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MnTT_NV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTT_NVActionPerformed(evt);
            }
        });
        jToolBar1.add(MnTT_NV);

        MnTT_TheLoai.setText("Thể Loại");
        MnTT_TheLoai.setFocusable(false);
        MnTT_TheLoai.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MnTT_TheLoai.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MnTT_TheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTT_TheLoaiActionPerformed(evt);
            }
        });
        jToolBar1.add(MnTT_TheLoai);

        MnTT_CV.setText("Chức Vụ");
        MnTT_CV.setFocusable(false);
        MnTT_CV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MnTT_CV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MnTT_CV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTT_CVActionPerformed(evt);
            }
        });
        jToolBar1.add(MnTT_CV);

        MnTT_NXB.setText("NXB");
        MnTT_NXB.setFocusable(false);
        MnTT_NXB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MnTT_NXB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MnTT_NXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTT_NXBActionPerformed(evt);
            }
        });
        jToolBar1.add(MnTT_NXB);

        MnDiemDanh.setText("Điểm Danh");
        MnDiemDanh.setFocusable(false);
        MnDiemDanh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MnDiemDanh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MnDiemDanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiemDanhActionPerformed(evt);
            }
        });
        jToolBar1.add(MnDiemDanh);

        MnLuong.setText("Lương");
        MnLuong.setFocusable(false);
        MnLuong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MnLuong.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MnLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLuongActionPerformed(evt);
            }
        });
        jToolBar1.add(MnLuong);

        javax.swing.GroupLayout Pn_DauLayout = new javax.swing.GroupLayout(Pn_Dau);
        Pn_Dau.setLayout(Pn_DauLayout);
        Pn_DauLayout.setHorizontalGroup(
            Pn_DauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Pn_DauLayout.setVerticalGroup(
            Pn_DauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Pn_DauLayout.createSequentialGroup()
                .addGap(0, 98, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Pn_TT.setBackground(new java.awt.Color(255, 255, 255));

        btnXoaNV.setText("XÓA");
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });

        btnSuaNV.setText("SỬA");
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });

        jLabel1.setText("User ID:");

        jLabel2.setText("Pass:");

        TBNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User", "Pass", "HoTen", "NamSinh", "GioiTinh", "Chucvu"
            }
        ));
        TBNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBNhanVien);

        jLabel4.setText("Ho NV:");

        jLabel5.setText("Ten NV:");

        btgGioitinh.add(RBNam);
        RBNam.setText("Nam");

        jLabel6.setText("NamSinh:");

        btgGioitinh.add(RBNu);
        RBNu.setText("Nữ");

        jLabel7.setText("Giới Tinh:");

        CBChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBChucVuActionPerformed(evt);
            }
        });

        jLabel8.setText("Chức vụ:");

        BtnLoadNV.setText("LOAD");
        BtnLoadNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoadNVActionPerformed(evt);
            }
        });

        jLabel9.setText("THÔNG TIN NHÂN VIÊN");

        btnThemNV.setText("THÊM");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnTimNV.setText("Tìm");
        btnTimNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Pn_TTNVLayout = new javax.swing.GroupLayout(Pn_TTNV);
        Pn_TTNV.setLayout(Pn_TTNVLayout);
        Pn_TTNVLayout.setHorizontalGroup(
            Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_TTNVLayout.createSequentialGroup()
                .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pn_TTNVLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Pn_TTNVLayout.createSequentialGroup()
                                .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Pn_TTNVLayout.createSequentialGroup()
                                        .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(Pn_TTNVLayout.createSequentialGroup()
                                                .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel1))
                                                .addGap(28, 28, 28))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Pn_TTNVLayout.createSequentialGroup()
                                                .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(27, 27, 27)))
                                        .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtPass, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtHo, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNamSinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Pn_TTNVLayout.createSequentialGroup()
                                                .addComponent(RBNam)
                                                .addGap(18, 18, 18)
                                                .addComponent(RBNu))
                                            .addComponent(CBChucVu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtUser)))
                                    .addComponent(jLabel6))
                                .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Pn_TTNVLayout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnXoaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnThemNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(31, 31, 31)
                                        .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnSuaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                            .addComponent(BtnLoadNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Pn_TTNVLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnTimNV)
                                        .addGap(16, 16, 16))))))
                    .addGroup(Pn_TTNVLayout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jLabel9)))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        Pn_TTNVLayout.setVerticalGroup(
            Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_TTNVLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel9)
                .addGap(30, 30, 30)
                .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Pn_TTNVLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(17, 17, 17)
                        .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Pn_TTNVLayout.createSequentialGroup()
                                .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(RBNam)
                                    .addComponent(RBNu))
                                .addGap(19, 19, 19))
                            .addGroup(Pn_TTNVLayout.createSequentialGroup()
                                .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)))
                        .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(CBChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Pn_TTNVLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaNV)
                            .addComponent(btnSuaNV))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemNV)
                            .addComponent(BtnLoadNV))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Pn_TTNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTimNV)
                            .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(292, Short.MAX_VALUE))
        );

        BangSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sách", "Tên Sách", "Ảnh Sách", "Tác Giả", "Giá", "Số Lượng", "Mã Thể Loại", "Mã NXB"
            }
        ));
        BangSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BangSachMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(BangSach);

        lblAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnThemSach.setText("Thêm");
        btnThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSachActionPerformed(evt);
            }
        });

        jLabel10.setText("NXB");

        jLabel11.setText("Link");

        btnSuaSach.setText("Sửa");
        btnSuaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSachActionPerformed(evt);
            }
        });

        btnXoaSach.setText("Xóa");
        btnXoaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSachActionPerformed(evt);
            }
        });

        btnLoadSach.setText("Load");
        btnLoadSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadSachActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã Sách");

        jLabel12.setText("Tên Sách");

        jLabel13.setText("Ảnh Sách");

        jLabel14.setText("Tác Giả");

        jLabel15.setText("Giá");

        jLabel16.setText("Số Lượng");

        btnchon.setText("Chọn");
        btnchon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchonActionPerformed(evt);
            }
        });

        jLabel17.setText("Thể Loại");

        javax.swing.GroupLayout Pn_TTSachLayout = new javax.swing.GroupLayout(Pn_TTSach);
        Pn_TTSach.setLayout(Pn_TTSachLayout);
        Pn_TTSachLayout.setHorizontalGroup(
            Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_TTSachLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSL, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGia, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTacgia, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaSach, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenSach, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CBTheLoai, 0, 137, Short.MAX_VALUE)
                    .addComponent(CBNXB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pn_TTSachLayout.createSequentialGroup()
                        .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoaSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThemSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSuaSach, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addComponent(btnLoadSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(87, 87, 87))
                    .addGroup(Pn_TTSachLayout.createSequentialGroup()
                        .addComponent(txtAnhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnchon)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jScrollPane2)
        );
        Pn_TTSachLayout.setVerticalGroup(
            Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_TTSachLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Pn_TTSachLayout.createSequentialGroup()
                        .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemSach)
                            .addComponent(btnSuaSach)
                            .addComponent(jLabel3)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnXoaSach)
                                .addComponent(btnLoadSach))
                            .addGroup(Pn_TTSachLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtTacgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Pn_TTSachLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtAnhSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnchon, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(12, 12, 12)
                .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(Pn_TTSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(CBNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jLabel18.setText("Xin Chao");

        javax.swing.GroupLayout Pn_GioiThieuLayout = new javax.swing.GroupLayout(Pn_GioiThieu);
        Pn_GioiThieu.setLayout(Pn_GioiThieuLayout);
        Pn_GioiThieuLayout.setHorizontalGroup(
            Pn_GioiThieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_GioiThieuLayout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(jLabel18)
                .addContainerGap(401, Short.MAX_VALUE))
        );
        Pn_GioiThieuLayout.setVerticalGroup(
            Pn_GioiThieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_GioiThieuLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel18)
                .addContainerGap(409, Short.MAX_VALUE))
        );

        Pn_TTTheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pn_TTTheLoaiMouseClicked(evt);
            }
        });

        jLabel19.setText("Thông Tin Bảng Thể loại");

        TBTheLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaTL", "TenTL", "LuaTuoi"
            }
        ));
        TBTheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBTheLoaiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TBTheLoai);

        jLabel20.setText("MaTL:");

        jLabel21.setText("TenTL:");

        jLabel22.setText("LuaTuoi:");

        btnThemTL.setText("Thêm");
        btnThemTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTLActionPerformed(evt);
            }
        });

        btnXoaTL.setText("Xóa");
        btnXoaTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTLActionPerformed(evt);
            }
        });

        btnSuaTL.setText("Sửa");
        btnSuaTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTLActionPerformed(evt);
            }
        });

        btnLoadTL.setText("Load");
        btnLoadTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadTLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Pn_TTTheLoaiLayout = new javax.swing.GroupLayout(Pn_TTTheLoai);
        Pn_TTTheLoai.setLayout(Pn_TTTheLoaiLayout);
        Pn_TTTheLoaiLayout.setHorizontalGroup(
            Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_TTTheLoaiLayout.createSequentialGroup()
                .addGroup(Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pn_TTTheLoaiLayout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel19))
                    .addGroup(Pn_TTTheLoaiLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Pn_TTTheLoaiLayout.createSequentialGroup()
                                .addGroup(Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Pn_TTTheLoaiLayout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addGap(24, 24, 24)
                                        .addComponent(txtLuaTuoi))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Pn_TTTheLoaiLayout.createSequentialGroup()
                                        .addGroup(Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Pn_TTTheLoaiLayout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addGap(18, 18, 18))
                                            .addGroup(Pn_TTTheLoaiLayout.createSequentialGroup()
                                                .addComponent(jLabel21)
                                                .addGap(14, 14, 14)))
                                        .addGap(18, 18, 18)
                                        .addGroup(Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTenTL, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaTL, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(81, 81, 81)
                                .addGroup(Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnThemTL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnXoaTL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnLoadTL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSuaTL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(164, Short.MAX_VALUE))
        );
        Pn_TTTheLoaiLayout.setVerticalGroup(
            Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_TTTheLoaiLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtMaTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemTL)
                    .addComponent(btnSuaTL))
                .addGroup(Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pn_TTTheLoaiLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtTenTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtLuaTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Pn_TTTheLoaiLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(Pn_TTTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaTL)
                            .addComponent(btnLoadTL))))
                .addContainerGap(307, Short.MAX_VALUE))
        );

        jLabel23.setText("Thông Tin Bảng Nhà Xuất Bản");

        TBNXB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaNXB", "TenNXB", "DiaChi", "SDT"
            }
        ));
        TBNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBNXBMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TBNXB);

        jLabel24.setText("MaNXB:");

        jLabel25.setText("TenNXB:");

        jLabel26.setText("DiaChi:");

        btnThemNXB.setText("Thêm");
        btnThemNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNXBActionPerformed(evt);
            }
        });

        btnXoaNXB.setText("Xóa");
        btnXoaNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNXBActionPerformed(evt);
            }
        });

        btnSuaNXB.setText("Sửa");
        btnSuaNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNXBActionPerformed(evt);
            }
        });

        btnLoadNXB.setText("Load");
        btnLoadNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadNXBActionPerformed(evt);
            }
        });

        jLabel31.setText("SDT:");

        javax.swing.GroupLayout Pn_TTNXBLayout = new javax.swing.GroupLayout(Pn_TTNXB);
        Pn_TTNXB.setLayout(Pn_TTNXBLayout);
        Pn_TTNXBLayout.setHorizontalGroup(
            Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_TTNXBLayout.createSequentialGroup()
                .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pn_TTNXBLayout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel23))
                    .addGroup(Pn_TTNXBLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Pn_TTNXBLayout.createSequentialGroup()
                                .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel25))
                                        .addComponent(jLabel26))
                                    .addGroup(Pn_TTNXBLayout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addGap(7, 7, 7)))
                                .addGap(32, 32, 32)
                                .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDiaChi)
                                    .addComponent(txtTenNXB, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(txtMaNXB, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(txtSDT))
                                .addGap(81, 81, 81)
                                .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnThemNXB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnXoaNXB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnLoadNXB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSuaNXB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        Pn_TTNXBLayout.setVerticalGroup(
            Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_TTNXBLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtMaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNXB)
                    .addComponent(btnSuaNXB))
                .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pn_TTNXBLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtTenNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Pn_TTNXBLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaNXB)
                            .addComponent(btnLoadNXB))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Pn_TTNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jLabel27.setText("Thông Tin Bảng Chức Vụ");

        TBChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaCV", "TenCV", "PhuCapCV", "HeSoLuong"
            }
        ));
        TBChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBChucVuMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TBChucVu);

        jLabel28.setText("MaCV:");

        jLabel29.setText("TenCV:");

        jLabel30.setText("PhuCapCV:");

        btnThemCv.setText("Thêm");
        btnThemCv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCvActionPerformed(evt);
            }
        });

        btnXoaCV.setText("Xóa");
        btnXoaCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCVActionPerformed(evt);
            }
        });

        btnSuaCV.setText("Sửa");
        btnSuaCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCVActionPerformed(evt);
            }
        });

        btnLoadCV.setText("Load");
        btnLoadCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadCVActionPerformed(evt);
            }
        });

        jLabel32.setText("HeSoLuong:");

        javax.swing.GroupLayout Pn_TTChucVuLayout = new javax.swing.GroupLayout(Pn_TTChucVu);
        Pn_TTChucVu.setLayout(Pn_TTChucVuLayout);
        Pn_TTChucVuLayout.setHorizontalGroup(
            Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_TTChucVuLayout.createSequentialGroup()
                .addGroup(Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pn_TTChucVuLayout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel27))
                    .addGroup(Pn_TTChucVuLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Pn_TTChucVuLayout.createSequentialGroup()
                                .addGroup(Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel32))
                                .addGap(18, 18, 18)
                                .addGroup(Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenCV, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(txtMaCV, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(txtPhuCapCV)
                                    .addComponent(txtHeSoLuong))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnThemCv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnXoaCV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnLoadCV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSuaCV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(Pn_TTChucVuLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        Pn_TTChucVuLayout.setVerticalGroup(
            Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_TTChucVuLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtMaCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemCv)
                    .addComponent(btnSuaCV))
                .addGroup(Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pn_TTChucVuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtTenCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txtPhuCapCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Pn_TTChucVuLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaCV)
                            .addComponent(btnLoadCV))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Pn_TTChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addContainerGap(150, Short.MAX_VALUE))
        );

        btnXoaLuong.setText("XÓA");
        btnXoaLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLuongActionPerformed(evt);
            }
        });

        btnSuaLuong.setText("SỬA");
        btnSuaLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLuongActionPerformed(evt);
            }
        });

        jLabel34.setText("MaChi:");

        NgayChi.setText("NgayChi:");

        TBLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaChi", "ID", "TenID", "Thang", "Nam", "Luong"
            }
        ));
        TBLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBLuongMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(TBLuong);

        jLabel36.setText("Thang:");

        BtnLoadLuong.setText("LOAD");
        BtnLoadLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoadLuongActionPerformed(evt);
            }
        });

        jLabel41.setText("THÔNG TIN NGÀY LƯƠNG  NHÂN VIÊN");

        btnThemLuong.setText("THÊM");
        btnThemLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLuongActionPerformed(evt);
            }
        });

        jLabel37.setText("Idnv:");

        jLabel35.setText("Nam:");

        jLabel44.setText("TenNV:");

        javax.swing.GroupLayout Pn_LuongLayout = new javax.swing.GroupLayout(Pn_Luong);
        Pn_Luong.setLayout(Pn_LuongLayout);
        Pn_LuongLayout.setHorizontalGroup(
            Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_LuongLayout.createSequentialGroup()
                .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pn_LuongLayout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jLabel41))
                    .addGroup(Pn_LuongLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Pn_LuongLayout.createSequentialGroup()
                                .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel36)
                                        .addComponent(jLabel34)
                                        .addComponent(jLabel37)
                                        .addComponent(jLabel35)
                                        .addComponent(NgayChi))
                                    .addComponent(jLabel44))
                                .addGap(28, 28, 28)
                                .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNam)
                                    .addComponent(txtNgayChi, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(txtThang)
                                    .addComponent(txtIDChi, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtIDLuong, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTenNVLuong))
                                .addGap(76, 76, 76)
                                .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnXoaLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnThemLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(31, 31, 31)
                                .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSuaLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                    .addComponent(BtnLoadLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        Pn_LuongLayout.setVerticalGroup(
            Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_LuongLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel41)
                .addGap(30, 30, 30)
                .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtIDChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(12, 12, 12)
                .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Pn_LuongLayout.createSequentialGroup()
                        .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaLuong)
                            .addComponent(btnSuaLuong))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemLuong)
                            .addComponent(BtnLoadLuong))
                        .addGap(27, 27, 27))
                    .addGroup(Pn_LuongLayout.createSequentialGroup()
                        .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(txtTenNVLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgayChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NgayChi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(Pn_LuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        btnXoaDiemDanh.setText("XÓA");
        btnXoaDiemDanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDiemDanhActionPerformed(evt);
            }
        });

        btnSuaDiemDanh.setText("SỬA");
        btnSuaDiemDanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDiemDanhActionPerformed(evt);
            }
        });

        jLabel38.setText("User ID:");

        jLabel39.setText("ngayvang:");

        TBDiemDanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idnv", "TenNV", "ngayvang", "LyDo"
            }
        ));
        TBDiemDanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBDiemDanhMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(TBDiemDanh);

        jLabel40.setText("LyDo:");

        BtnLoadDiemDanh1.setText("LOAD");
        BtnLoadDiemDanh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoadDiemDanh1ActionPerformed(evt);
            }
        });

        jLabel42.setText("THÔNG TIN NGÀY LÀM  NHÂN VIÊN");

        btnThemDiemDanh1.setText("THÊM");
        btnThemDiemDanh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDiemDanh1ActionPerformed(evt);
            }
        });

        txtIDDiemDanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDDiemDanhActionPerformed(evt);
            }
        });

        btnTimDiemDanh1.setText("Tìm");
        btnTimDiemDanh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimDiemDanh1ActionPerformed(evt);
            }
        });

        jLabel43.setText("TenNV:");

        javax.swing.GroupLayout Pn_DiemDanhLayout = new javax.swing.GroupLayout(Pn_DiemDanh);
        Pn_DiemDanh.setLayout(Pn_DiemDanhLayout);
        Pn_DiemDanhLayout.setHorizontalGroup(
            Pn_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Pn_DiemDanhLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimDiemDanh1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimDiemDanh1)
                .addGap(144, 144, 144))
            .addGroup(Pn_DiemDanhLayout.createSequentialGroup()
                .addGroup(Pn_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pn_DiemDanhLayout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jLabel42))
                    .addGroup(Pn_DiemDanhLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(Pn_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Pn_DiemDanhLayout.createSequentialGroup()
                                .addGroup(Pn_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel39)
                                    .addComponent(jLabel38)
                                    .addComponent(jLabel43))
                                .addGap(28, 28, 28)
                                .addGroup(Pn_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNgayVang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(txtLyDo, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIDDiemDanh)
                                    .addComponent(txtTenDiemDanh))
                                .addGap(76, 76, 76)
                                .addGroup(Pn_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnXoaDiemDanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnThemDiemDanh1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(31, 31, 31)
                                .addGroup(Pn_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSuaDiemDanh, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                    .addComponent(BtnLoadDiemDanh1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        Pn_DiemDanhLayout.setVerticalGroup(
            Pn_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pn_DiemDanhLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel42)
                .addGap(30, 30, 30)
                .addGroup(Pn_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtIDDiemDanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(Pn_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel43)
                    .addComponent(txtTenDiemDanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Pn_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaDiemDanh)
                    .addComponent(btnSuaDiemDanh)
                    .addComponent(txtNgayVang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Pn_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemDiemDanh1)
                    .addComponent(BtnLoadDiemDanh1)
                    .addComponent(txtLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addGap(55, 55, 55)
                .addGroup(Pn_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimDiemDanh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimDiemDanh1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Pn_TTLayout = new javax.swing.GroupLayout(Pn_TT);
        Pn_TT.setLayout(Pn_TTLayout);
        Pn_TTLayout.setHorizontalGroup(
            Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pn_TTNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Pn_TTSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_TTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Pn_GioiThieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_TTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Pn_TTTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_TTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Pn_TTNXB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(34, 34, 34)))
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_TTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Pn_TTChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(24, 24, 24)))
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_TTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Pn_Luong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_TTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Pn_DiemDanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        Pn_TTLayout.setVerticalGroup(
            Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pn_TTNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Pn_TTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Pn_TTSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_TTLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(Pn_GioiThieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_TTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Pn_TTTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(21, 21, 21)))
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_TTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Pn_TTNXB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(152, 152, 152)))
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_TTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Pn_TTChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(142, 142, 142)))
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_TTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Pn_Luong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(119, 119, 119)))
            .addGroup(Pn_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Pn_TTLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Pn_DiemDanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(119, 119, 119)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pn_Dau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Pn_TT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Pn_Dau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pn_TT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MnTT_NVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTT_NVActionPerformed
        Pn_TT.removeAll();
        Pn_TT.repaint();
        Pn_TT.revalidate();
        
        Pn_TT.add(Pn_TTNV);
        Pn_TT.repaint();
        Pn_TT.revalidate();
        
    }//GEN-LAST:event_MnTT_NVActionPerformed

    private void MnTT_SachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTT_SachActionPerformed
        Pn_TT.removeAll();
        Pn_TT.repaint();
        Pn_TT.revalidate();
        
        Pn_TT.add(Pn_TTSach);
        Pn_TT.repaint();
        Pn_TT.revalidate();
    }//GEN-LAST:event_MnTT_SachActionPerformed

    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String xoa="DELETE FROM nhanvien WHERE idnv='"+txtUser.getText()+"'";
        truyvan.executeSQlQuery(xoa,"Deleted");

    }//GEN-LAST:event_btnXoaNVActionPerformed

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String User=txtUser.getText();
        String Pass=txtPass.getText();
        String Ho=txtHo.getText();
        String Ten=txtTen.getText();
        int NamSinh=Integer.parseInt(txtNamSinh.getText());
        int GioiTinh=0;
        if(btgGioitinh.getSelection().equals(RBNam.getModel()))
        {
            GioiTinh=1;
        }else GioiTinh=0;
        LayMaCV lay= new LayMaCV();
        String ChucVu="";
        int k;
        for(k=0;k<=CBChucVu.getItemCount();k++)
        if(CBChucVu.getSelectedIndex()==k) ChucVu=CBChucVu.getItemAt(k);
        ChucVu=lay.LayChucVu(ChucVu);
        Connect con = new Connect();
        conn=con.ConnectDB();
        String Sua="UPDATE nhanvien SET pass='"+Pass+"',honv='"+Ho+"',tennv='"+Ten+"',namsinh="+NamSinh+",gioitinh="+GioiTinh+",macv='"+ChucVu+"'  WHERE idnv='"+User+"'";
        truyvan.executeSQlQuery(Sua,"Updated");

    }//GEN-LAST:event_btnSuaNVActionPerformed

    private void TBNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBNhanVienMouseClicked
        int i = TBNhanVien.getSelectedRow();
        TableModel model = TBNhanVien.getModel();

        // Display Slected Row In JTexteFields
        txtUser.setText(model.getValueAt(i,0).toString());
        txtPass.setText(model.getValueAt(i,1).toString());
        String HoTen=model.getValueAt(i,2).toString();
        int sokhoangtrang=0;
        char x;
        for(int j=0;j<HoTen.length();j++){
            x=HoTen.charAt(j);
            if(x==' '){
                sokhoangtrang++;
            }
        }
        if(sokhoangtrang==2) {
            txtHo.setText(HoTen.split(" ")[0]+" "+HoTen.split(" ")[1]);
            txtTen.setText(HoTen.split(" ")[2]);
        }else{
            txtHo.setText(HoTen.split(" ")[0]);
            txtTen.setText(HoTen.split(" ")[1]);
        }
        txtNamSinh.setText(model.getValueAt(i,3).toString());

        String GioiTinh=model.getValueAt(i,4).toString();
        if(GioiTinh.equals("Nam")==true) {
            RBNam.setSelected(true);
        }else  RBNu.setSelected(true);

        String ChucVu=model.getValueAt(i,5).toString();
        for(int k=0;k<CBChucVu.getItemCount();k++)
        if(CBChucVu.getItemAt(k).equals(ChucVu)==true) CBChucVu.setSelectedIndex(k);

    }//GEN-LAST:event_TBNhanVienMouseClicked

    private void CBChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBChucVuActionPerformed

    }//GEN-LAST:event_CBChucVuActionPerformed

    private void BtnLoadNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoadNVActionPerformed
        Show_BangNV_In_JTable();
        HienCombo();
    }//GEN-LAST:event_BtnLoadNVActionPerformed

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String User=txtUser.getText();
        String Pass=txtPass.getText();
        String Ho=txtHo.getText();
        String Ten=txtTen.getText();
        int NamSinh=Integer.parseInt(txtNamSinh.getText());
        int GioiTinh=0;
        if(btgGioitinh.getSelection().equals(RBNam.getModel()))
        {
            GioiTinh=1;
        }else GioiTinh=0;
        String ChucVu="";
        LayMaCV lay=new LayMaCV();
        int k;
        for(k=0;k<CBChucVu.getItemCount();k++)
        if(CBChucVu.getSelectedIndex()==k) ChucVu=CBChucVu.getItemAt(k);
        ChucVu=lay.LayChucVu(ChucVu);

        Connect con = new Connect();
        conn=con.ConnectDB();
        String Them="INSERT INTO nhanvien VALUES('"+User+"','"+Pass+"','"+Ho+"','"+Ten+"',"+NamSinh+","+GioiTinh+",'"+ChucVu+"')";
        truyvan.executeSQlQuery(Them,"Inserted");

    }//GEN-LAST:event_btnThemNVActionPerformed

    private void BangSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BangSachMouseClicked
        int i = BangSach.getSelectedRow();

        TableModel model = BangSach.getModel();
        txtMaSach.setText(model.getValueAt(i,0).toString());
        txtTenSach.setText(model.getValueAt(i,1).toString());
        lblAnh.setIcon(InAnh(null,BangSachList().get(i).getAnhSach()));
        txtTacgia.setText(model.getValueAt(i,3).toString());
        txtGia.setText(model.getValueAt(i,4).toString());
        txtSL.setText(model.getValueAt(i,5).toString());

        String TL =model.getValueAt(i,6).toString();

        for(int k=0;k<CBTheLoai.getItemCount();k++)
        if(CBTheLoai.getItemAt(k).equals(TL)==true) CBTheLoai.setSelectedIndex(k);
        Connect con = new Connect();
        conn=con.ConnectDB();
        String NXB =model.getValueAt(i,7).toString();
        for(int j=0;j<CBNXB.getItemCount();j++)
        if(CBNXB.getItemAt(j).equals(NXB)==true) CBNXB.setSelectedIndex(j);

    }//GEN-LAST:event_BangSachMouseClicked

    private void btnThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSachActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String MaSach=txtMaSach.getText();
        String TenSach =txtTenSach.getText();

        String AnhSach=txtAnhSach.getText();
        char kt1='\\';
        char kt='/';
        AnhSach=AnhSach.replace(kt1,kt);
        String TacGia=txtTacgia.getText();
        int Gia=Integer.parseInt(txtGia.getText());
        int SoLuong=Integer.parseInt(txtSL.getText());

        String MaTL="";
        LayTL_NXB lay=new LayTL_NXB();
        int k;
        for(k=0;k<CBTheLoai.getItemCount();k++)
        if(CBTheLoai.getSelectedIndex()==k) MaTL=CBTheLoai.getItemAt(k);
        MaTL=lay.LayMaTL(MaTL);

        String MaNXB="";
        LayTL_NXB lay1=new LayTL_NXB();
        int j;
        for(j=0;j<CBNXB.getItemCount();j++)
        if(CBNXB.getSelectedIndex()==j) MaNXB=CBNXB.getItemAt(j);
        MaNXB=lay1.LayNXB(MaNXB);

        Connect con = new Connect();
        conn=con.ConnectDB();
        String Them="INSERT INTO BangSach VALUES('"+MaSach+"','"+TenSach+"',LOAD_File('"+AnhSach+"'),'"+TacGia+"',"+Gia+","+SoLuong+",'"+MaTL+"','"+MaNXB+"')";
        truyvan.executeSQlQuery(Them,"Inserted");
        HienBang();
    }//GEN-LAST:event_btnThemSachActionPerformed

    private void btnSuaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSachActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String MaSach=txtMaSach.getText();
        String TenSach =txtTenSach.getText();

        String AnhSach=txtAnhSach.getText();
        char kt1='\\';
        char kt='/';
        AnhSach=AnhSach.replace(kt1,kt);
        String TacGia=txtTacgia.getText();
        int Gia=Integer.parseInt(txtGia.getText());
        int SoLuong=Integer.parseInt(txtSL.getText());

        String MaTL="";
        LayTL_NXB lay=new LayTL_NXB();
        int k;
        for(k=0;k<CBTheLoai.getItemCount();k++)
        if(CBTheLoai.getSelectedIndex()==k) MaTL=CBTheLoai.getItemAt(k);
        MaTL=lay.LayMaTL(MaTL);

        String MaNXB="";
        LayTL_NXB lay1=new LayTL_NXB();
        int j;
        for(j=0;j<CBNXB.getItemCount();j++)
        if(CBNXB.getSelectedIndex()==j) MaNXB=CBNXB.getItemAt(j);
        MaNXB=lay1.LayNXB(MaNXB);
        Connect con = new Connect();
        String Sua="";
        conn=con.ConnectDB();
        if (AnhSach.equals(Sua)==true){
            Sua="UPDATE BangSach SET TenSach='"+TenSach+"',tacgia='"+TacGia+"',gia="+Gia+",soluong="+SoLuong+",matl='"+MaTL+"',manxb='"+MaNXB+"' Where  MaSach='"+MaSach+"'";

        }else{
            Sua="UPDATE BangSach SET TenSach='"+TenSach+"',Anhsach=LOAD_File('"+AnhSach+"'),tacgia='"+TacGia+"',gia="+Gia+",soluong="+SoLuong+",matl='"+MaTL+"',manxb='"+MaNXB+"' Where  MaSach='"+MaSach+"'  ";
        }
        truyvan.executeSQlQuery(Sua,"Updated");
        HienBang();

    }//GEN-LAST:event_btnSuaSachActionPerformed

    private void btnXoaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSachActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String xoa="DELETE FROM BangSach WHERE masach='"+txtMaSach.getText()+"'";
        truyvan.executeSQlQuery(xoa,"Deleted");
        HienBang();
    }//GEN-LAST:event_btnXoaSachActionPerformed
    
    private void btnchonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchonActionPerformed
        JFileChooser chon=new JFileChooser();
        chon.showOpenDialog(null);
        File f=chon.getSelectedFile();
        String tenfile=f.getAbsolutePath();
        txtAnhSach.setText(tenfile);
        lblAnh.setIcon(InAnh(tenfile, null));
    }//GEN-LAST:event_btnchonActionPerformed

    private void btnLoadSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadSachActionPerformed
        HienBang();
        hienTL();
        hienNXB();
    }//GEN-LAST:event_btnLoadSachActionPerformed

    private void MnTT_GioiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTT_GioiThieuActionPerformed
        Pn_TT.removeAll();
        Pn_TT.repaint();
        Pn_TT.revalidate();
        
        Pn_TT.add(Pn_GioiThieu);
        Pn_TT.repaint();
        Pn_TT.revalidate();
    }//GEN-LAST:event_MnTT_GioiThieuActionPerformed

    private void btnTimNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimNVActionPerformed
        Show_BangNV_In_JTable1();
    }//GEN-LAST:event_btnTimNVActionPerformed

    private void MnTT_NXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTT_NXBActionPerformed
        Pn_TT.removeAll();
        Pn_TT.repaint();
        Pn_TT.revalidate();
        
        Pn_TT.add(Pn_TTNXB);
        Pn_TT.repaint();
        Pn_TT.revalidate();
    }//GEN-LAST:event_MnTT_NXBActionPerformed

    private void MnTT_TheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTT_TheLoaiActionPerformed
        Pn_TT.removeAll();
        Pn_TT.repaint();
        Pn_TT.revalidate();
        
        Pn_TT.add(Pn_TTTheLoai);
        Pn_TT.repaint();
        Pn_TT.revalidate();
    }//GEN-LAST:event_MnTT_TheLoaiActionPerformed

    private void MnTT_CVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTT_CVActionPerformed
        Pn_TT.removeAll();
        Pn_TT.repaint();
        Pn_TT.revalidate();
        
        Pn_TT.add(Pn_TTChucVu);
        Pn_TT.repaint();
        Pn_TT.revalidate();
    }//GEN-LAST:event_MnTT_CVActionPerformed

    private void btnLoadTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadTLActionPerformed
        Show_BangTL();
    }//GEN-LAST:event_btnLoadTLActionPerformed

    private void btnSuaTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTLActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String MaTL=txtMaTL.getText();
        String TenTL=txtTenTL.getText();
        String LuaTuoi=txtLuaTuoi.getText();
        Connect con = new Connect();
        conn=con.ConnectDB();
    
        conn=con.ConnectDB();
        String Sua="UPDATE TheLoai SET tentl='"+TenTL+"',LuaTuoi='"+LuaTuoi+"' where Matl='"+MaTL+"' ";
        truyvan.executeSQlQuery(Sua,"updated");
    }//GEN-LAST:event_btnSuaTLActionPerformed

    private void btnLoadNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadNXBActionPerformed
         Show_BangNXB();
    }//GEN-LAST:event_btnLoadNXBActionPerformed

    private void btnLoadCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadCVActionPerformed
       Show_BangCV();
    }//GEN-LAST:event_btnLoadCVActionPerformed

    private void TBNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBNXBMouseClicked
         int i = TBNXB.getSelectedRow();
        TableModel model = TBNXB.getModel();
        txtMaNXB.setText(model.getValueAt(i,0).toString());
        txtTenNXB.setText(model.getValueAt(i,1).toString());
        txtDiaChi.setText(model.getValueAt(i,2).toString());
        txtSDT.setText(model.getValueAt(i,3).toString());
    }//GEN-LAST:event_TBNXBMouseClicked

    private void Pn_TTTheLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pn_TTTheLoaiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Pn_TTTheLoaiMouseClicked

    private void TBTheLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBTheLoaiMouseClicked
        int i = TBTheLoai.getSelectedRow();
        TableModel model = TBTheLoai.getModel();
        txtMaTL.setText(model.getValueAt(i,0).toString());
        txtTenTL.setText(model.getValueAt(i,1).toString());
        txtLuaTuoi.setText(model.getValueAt(i,2).toString());
                
    }//GEN-LAST:event_TBTheLoaiMouseClicked

    private void TBChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBChucVuMouseClicked
         int i = TBChucVu.getSelectedRow();
        TableModel model = TBChucVu.getModel();
         txtMaCV.setText(model.getValueAt(i,0).toString());
        txtTenCV.setText(model.getValueAt(i,1).toString());
        txtPhuCapCV.setText(model.getValueAt(i,2).toString());
        txtHeSoLuong.setText(model.getValueAt(i,3).toString());
    }//GEN-LAST:event_TBChucVuMouseClicked

    private void btnThemCvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCvActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String MaCV=txtMaCV.getText();
        String TenCV=txtTenCV.getText();
        int HeSoLuong=Integer.parseInt(txtHeSoLuong.getText());
        int PhuCapCV=Integer.parseInt(txtPhuCapCV.getText());
        Connect con = new Connect();
        conn=con.ConnectDB();
        String Them="INSERT INTO ChucVu VALUES('"+MaCV+"','"+TenCV+"',"+PhuCapCV+","+HeSoLuong+")";
        truyvan.executeSQlQuery(Them,"Inserted");
    }//GEN-LAST:event_btnThemCvActionPerformed

    private void btnSuaCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCVActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String MaCV=txtMaCV.getText();
        String TenCV=txtTenCV.getText();
        int HeSoLuong=Integer.parseInt(txtHeSoLuong.getText());
        int PhuCapCV=Integer.parseInt(txtPhuCapCV.getText());
        Connect con = new Connect();
        conn=con.ConnectDB();
        String Sua="UPDATE chucvu SET tencv='"+TenCV+"',phucapvc="+PhuCapCV+",HeSoLuong="+HeSoLuong+" where MaCV='"+MaCV+"' ";
        truyvan.executeSQlQuery(Sua,"updated");
    }//GEN-LAST:event_btnSuaCVActionPerformed

    private void btnXoaCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCVActionPerformed
          ThucHienQuery truyvan=new ThucHienQuery();
        String xoa="DELETE FROM chucvu WHERE macv='"+txtMaCV.getText()+"'";
        truyvan.executeSQlQuery(xoa,"Deleted");
    }//GEN-LAST:event_btnXoaCVActionPerformed

    private void btnThemNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNXBActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String MaNXB=txtMaNXB.getText();
        String TenNXB=txtTenNXB.getText();
        String DiaChi=txtDiaChi.getText();
        String SoDT=txtSDT.getText();
        
        Connect con = new Connect();
        conn=con.ConnectDB();
        String Them="INSERT INTO NXB VALUES('"+MaNXB+"','"+TenNXB+"','"+DiaChi+"','"+SoDT+"')";
        truyvan.executeSQlQuery(Them,"Inserted");
    }//GEN-LAST:event_btnThemNXBActionPerformed

    private void btnXoaNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNXBActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String xoa="DELETE FROM NXB WHERE maNXB='"+txtMaNXB.getText()+"'";
        truyvan.executeSQlQuery(xoa,"Deleted");
    }//GEN-LAST:event_btnXoaNXBActionPerformed

    private void btnSuaNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNXBActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String MaNXB=txtMaNXB.getText();
        String TenNXB=txtTenNXB.getText();
        String DiaChi=txtDiaChi.getText();
        String SoDT=txtSDT.getText();
        
        Connect con = new Connect();
        conn=con.ConnectDB();
        String Sua="UPDATE NXB SET tennxb='"+TenNXB+"',DiaChi='"+DiaChi+"',SoDT='"+SoDT+"' where MaNXB='"+MaNXB+"' ";
        truyvan.executeSQlQuery(Sua,"updated");
    }//GEN-LAST:event_btnSuaNXBActionPerformed

    private void btnThemTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTLActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String MaTL=txtMaTL.getText();
        String TenTL=txtTenTL.getText();
        String LuaTuoi=txtLuaTuoi.getText();
      
        
        Connect con = new Connect();
        conn=con.ConnectDB();
        String Them="INSERT INTO TheLoai VALUES('"+MaTL+"','"+TenTL+"','"+LuaTuoi+"')";
        truyvan.executeSQlQuery(Them,"Inserted");
    }//GEN-LAST:event_btnThemTLActionPerformed

    private void btnXoaTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTLActionPerformed
          ThucHienQuery truyvan=new ThucHienQuery();
        String xoa="DELETE FROM theloai WHERE matl='"+txtMaTL.getText()+"'";
        truyvan.executeSQlQuery(xoa,"Deleted");
    }//GEN-LAST:event_btnXoaTLActionPerformed

    private void btnXoaLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLuongActionPerformed
         ThucHienQuery truyvan=new ThucHienQuery();
        String xoa="DELETE FROM phieuchi WHERE machi='"+txtIDChi.getText()+"'";
        truyvan.executeSQlQuery(xoa,"Deleted");
    }//GEN-LAST:event_btnXoaLuongActionPerformed

    private void btnSuaLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaLuongActionPerformed

    private void TBLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLuongMouseClicked
        int i = TBLuong.getSelectedRow();
        TableModel model = TBLuong.getModel();
        txtIDChi.setText(model.getValueAt(i,0).toString());
        txtIDLuong.setText(model.getValueAt(i,1).toString());
        txtTenNVLuong.setText(model.getValueAt(i,2).toString());
        txtNgayChi.setText(model.getValueAt(i,3).toString());
        txtThang.setText(model.getValueAt(i,4).toString());
        txtNam.setText(model.getValueAt(i,5).toString());
           
    }//GEN-LAST:event_TBLuongMouseClicked

    private void BtnLoadLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoadLuongActionPerformed
        Show_BangLuong();
    }//GEN-LAST:event_BtnLoadLuongActionPerformed

    private void btnThemLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLuongActionPerformed
       String MaChi=txtIDChi.getText();
       String ID=txtIDLuong.getText();
       String NgayChi=txtNgayChi.getText();
       int Thang=Integer.parseInt(txtThang.getText());
       int Nam=Integer.parseInt(txtNam.getText());
       ThucHienQuery truyvan=new ThucHienQuery();
       String Them="INSERT INTO TheLoai VALUES('"+MaChi+"','"+ID+"','"+NgayChi+"',"+Thang+","+Nam+")";
       truyvan.executeSQlQuery(Them,"Inserted");
    }//GEN-LAST:event_btnThemLuongActionPerformed

    private void btnXoaDiemDanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDiemDanhActionPerformed
         ThucHienQuery truyvan=new ThucHienQuery();
        String xoa="DELETE FROM diemdanh WHERE idnv='"+txtIDDiemDanh.getText()+"'";
        truyvan.executeSQlQuery(xoa,"Deleted");
    }//GEN-LAST:event_btnXoaDiemDanhActionPerformed

    private void btnSuaDiemDanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDiemDanhActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String MaID=txtIDDiemDanh.getText();
        String TenID=txtTenDiemDanh.getText();
        String NgayVang=txtNgayVang.getText();
        String LyDo=txtLyDo.getText();
        Connect con = new Connect();
        conn=con.ConnectDB();
        String Sua="UPDATE diemdanh SET ngayvang='"+NgayVang+"',lydo='"+LyDo+"'  WHERE idnv='"+MaID+"'";
        truyvan.executeSQlQuery(Sua,"Updated");
    }//GEN-LAST:event_btnSuaDiemDanhActionPerformed

    private void TBDiemDanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBDiemDanhMouseClicked
        int i = TBDiemDanh.getSelectedRow();
        TableModel model = TBDiemDanh.getModel();
        
         // Display Slected Row In JTexteFields
        txtIDDiemDanh.setText(model.getValueAt(i,0).toString());
        txtTenDiemDanh.setText(model.getValueAt(i,1).toString());
        txtNgayVang.setText(model.getValueAt(i,2).toString());
        txtLyDo.setText(model.getValueAt(i,3).toString());
        
        
    }//GEN-LAST:event_TBDiemDanhMouseClicked

    private void BtnLoadDiemDanh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoadDiemDanh1ActionPerformed
        Show_BangDiemDanh();
    }//GEN-LAST:event_BtnLoadDiemDanh1ActionPerformed

    private void btnThemDiemDanh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDiemDanh1ActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String MaID=txtIDDiemDanh.getText();
        String TenID=txtTenDiemDanh.getText();
        String NgayVang=txtNgayVang.getText();
        String LyDo=txtLyDo.getText();
        Connect con = new Connect();
        conn=con.ConnectDB();
        String Them="INSERT INTO diemdanh VALUES('"+MaID+"','"+NgayVang+"','"+LyDo+"')";
        truyvan.executeSQlQuery(Them,"Inserted");
    }//GEN-LAST:event_btnThemDiemDanh1ActionPerformed

    private void btnTimDiemDanh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimDiemDanh1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimDiemDanh1ActionPerformed

    private void txtIDDiemDanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDDiemDanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDDiemDanhActionPerformed

    private void MnDiemDanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiemDanhActionPerformed
              Pn_TT.removeAll();
        Pn_TT.repaint();
        Pn_TT.revalidate();
        
        Pn_TT.add(Pn_DiemDanh);
        Pn_TT.repaint();
        Pn_TT.revalidate();
    }//GEN-LAST:event_MnDiemDanhActionPerformed

    private void MnLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLuongActionPerformed
              Pn_TT.removeAll();
        Pn_TT.repaint();
        Pn_TT.revalidate();
        
        Pn_TT.add(Pn_Luong);
        Pn_TT.repaint();
        Pn_TT.revalidate();
    }//GEN-LAST:event_MnLuongActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WelcomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomeAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable BangSach;
    private javax.swing.JButton BtnLoadDiemDanh1;
    private javax.swing.JButton BtnLoadLuong;
    private javax.swing.JButton BtnLoadNV;
    private javax.swing.JComboBox<String> CBChucVu;
    private javax.swing.JComboBox<String> CBNXB;
    private javax.swing.JComboBox<String> CBTheLoai;
    private javax.swing.JButton MnDiemDanh;
    private javax.swing.JButton MnLuong;
    private javax.swing.JButton MnTT_CV;
    private javax.swing.JButton MnTT_GioiThieu;
    private javax.swing.JButton MnTT_NV;
    private javax.swing.JButton MnTT_NXB;
    private javax.swing.JButton MnTT_Sach;
    private javax.swing.JButton MnTT_TheLoai;
    private javax.swing.JLabel NgayChi;
    private javax.swing.JPanel Pn_Dau;
    private javax.swing.JPanel Pn_DiemDanh;
    private javax.swing.JPanel Pn_GioiThieu;
    private javax.swing.JPanel Pn_Luong;
    private javax.swing.JPanel Pn_TT;
    private javax.swing.JPanel Pn_TTChucVu;
    private javax.swing.JPanel Pn_TTNV;
    private javax.swing.JPanel Pn_TTNXB;
    private javax.swing.JPanel Pn_TTSach;
    private javax.swing.JPanel Pn_TTTheLoai;
    private javax.swing.JRadioButton RBNam;
    private javax.swing.JRadioButton RBNu;
    private javax.swing.JTable TBChucVu;
    private javax.swing.JTable TBDiemDanh;
    private javax.swing.JTable TBLuong;
    private javax.swing.JTable TBNXB;
    private javax.swing.JTable TBNhanVien;
    private javax.swing.JTable TBTheLoai;
    private javax.swing.ButtonGroup btgGioitinh;
    private javax.swing.JButton btnLoadCV;
    private javax.swing.JButton btnLoadNXB;
    private javax.swing.JButton btnLoadSach;
    private javax.swing.JButton btnLoadTL;
    private javax.swing.JButton btnSuaCV;
    private javax.swing.JButton btnSuaDiemDanh;
    private javax.swing.JButton btnSuaLuong;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnSuaNXB;
    private javax.swing.JButton btnSuaSach;
    private javax.swing.JButton btnSuaTL;
    private javax.swing.JButton btnThemCv;
    private javax.swing.JButton btnThemDiemDanh1;
    private javax.swing.JButton btnThemLuong;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnThemNXB;
    private javax.swing.JButton btnThemSach;
    private javax.swing.JButton btnThemTL;
    private javax.swing.JButton btnTimDiemDanh1;
    private javax.swing.JButton btnTimNV;
    private javax.swing.JButton btnXoaCV;
    private javax.swing.JButton btnXoaDiemDanh;
    private javax.swing.JButton btnXoaLuong;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.JButton btnXoaNXB;
    private javax.swing.JButton btnXoaSach;
    private javax.swing.JButton btnXoaTL;
    private javax.swing.JButton btnchon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JTextField txtAnhSach;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtHeSoLuong;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtIDChi;
    private javax.swing.JTextField txtIDDiemDanh;
    private javax.swing.JTextField txtIDLuong;
    private javax.swing.JTextField txtLuaTuoi;
    private javax.swing.JTextField txtLyDo;
    private javax.swing.JTextField txtMaCV;
    private javax.swing.JTextField txtMaNXB;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtMaTL;
    private javax.swing.JTextField txtNam;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtNgayChi;
    private javax.swing.JTextField txtNgayVang;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtPhuCapCV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTacgia;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenCV;
    private javax.swing.JTextField txtTenDiemDanh;
    private javax.swing.JTextField txtTenNVLuong;
    private javax.swing.JTextField txtTenNXB;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTenTL;
    private javax.swing.JTextField txtThang;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTimDiemDanh1;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
