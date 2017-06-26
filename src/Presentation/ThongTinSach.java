package Presentation;

import Data.*;
import Process.*;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author K-P-P-K
 */
public class ThongTinSach extends javax.swing.JFrame {
Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;
    /**
     * Creates new form ThongTinSach
     */
    public ThongTinSach() {
        initComponents();
        HienBang();
        hienTL();
        hienNXB();
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
  /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        BangSach = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        txtTenSach = new javax.swing.JTextField();
        txtAnhSach = new javax.swing.JTextField();
        txtTacgia = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        txtSL = new javax.swing.JTextField();
        CBTheLoai = new javax.swing.JComboBox<>();
        CBNXB = new javax.swing.JComboBox<>();
        btnchon = new javax.swing.JButton();
        lblAnh = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jScrollPane1.setViewportView(BangSach);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThoat.setText("Thoát");

        jLabel1.setText("Mã Sách");

        jLabel2.setText("Tên Sách");

        jLabel3.setText("Ảnh Sách");

        jLabel4.setText("Tác Giả");

        jLabel5.setText("Giá");

        jLabel6.setText("Số Lượng");

        jLabel7.setText("Thể Loại");

        jLabel8.setText("NXB");

        btnchon.setText("Chọn");
        btnchon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchonActionPerformed(evt);
            }
        });

        lblAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Link");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSL, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGia, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTacgia, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaSach, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenSach, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CBTheLoai, 0, 137, Short.MAX_VALUE)
                    .addComponent(CBNXB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThoat))
                        .addGap(87, 87, 87))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtAnhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnchon)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua)
                            .addComponent(jLabel1)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnXoa)
                                .addComponent(btnThoat))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTacgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtAnhSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnchon, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(CBNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String xoa="DELETE FROM BangSach WHERE masach='"+txtMaSach.getText()+"'";
        truyvan.executeSQlQuery(xoa,"Deleted");
        HienBang();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
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
    }//GEN-LAST:event_btnThemActionPerformed

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

    private void btnchonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchonActionPerformed
        JFileChooser chon=new JFileChooser();
        chon.showOpenDialog(null);
        File f=chon.getSelectedFile();
        String tenfile=f.getAbsolutePath();
        txtAnhSach.setText(tenfile);
        lblAnh.setIcon(InAnh(tenfile, null));
    }//GEN-LAST:event_btnchonActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
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
           
    }//GEN-LAST:event_btnSuaActionPerformed

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
            java.util.logging.Logger.getLogger(ThongTinSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongTinSach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable BangSach;
    private javax.swing.JComboBox<String> CBNXB;
    private javax.swing.JComboBox<String> CBTheLoai;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnchon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JTextField txtAnhSach;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTacgia;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}
