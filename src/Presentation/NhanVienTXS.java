
package Presentation;


import Data.*;
import Process.*;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;


public class NhanVienTXS extends javax.swing.JFrame {
Connection conn=null;
PreparedStatement pst=null;
ResultSet rs=null;
      
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
     /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGioiTinh = new javax.swing.ButtonGroup();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        txtHo = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtNamSinh = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBNhanVien = new javax.swing.JTable();
        RBNam = new javax.swing.JRadioButton();
        RBNu = new javax.swing.JRadioButton();
        CBChucVu = new javax.swing.JComboBox<>();
        BtnLoad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nhân Viên");

        jLabel1.setText("User ID:");

        jLabel2.setText("Pass:");

        jLabel3.setText("Ho NV:");

        jLabel4.setText("Ten NV:");

        jLabel5.setText("NamSinh:");

        jLabel6.setText("Giới Tinh:");

        jLabel7.setText("Chức vụ:");

        jLabel8.setText("THÔNG TIN NHÂN VIÊN");

        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThoat.setText("THOÁT");

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

        btgGioiTinh.add(RBNam);
        RBNam.setText("Nam");

        btgGioiTinh.add(RBNu);
        RBNu.setText("Nữ");

        CBChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBChucVuActionPerformed(evt);
            }
        });

        BtnLoad.setText("LOAD");
        BtnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel1))
                                                .addGap(28, 28, 28))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(27, 27, 27)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtPass, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtHo, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNamSinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(RBNam)
                                                .addGap(18, 18, 18)
                                                .addComponent(RBNu))
                                            .addComponent(CBChucVu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtUser)))
                                    .addComponent(jLabel5))
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BtnLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThoat)
                                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jLabel8)))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel8)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(RBNam)
                                    .addComponent(RBNu))
                                .addGap(19, 19, 19))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(CBChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoa)
                            .addComponent(btnSua))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnThoat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnLoad)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String User=txtUser.getText();
        String Pass=txtPass.getText();
        String Ho=txtHo.getText();
        String Ten=txtTen.getText();
        int NamSinh=Integer.parseInt(txtNamSinh.getText());
        int GioiTinh=0;
        if(btgGioiTinh.getSelection().equals(RBNam.getModel()))
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
     
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void CBChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBChucVuActionPerformed
        
    }//GEN-LAST:event_CBChucVuActionPerformed

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

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String xoa="DELETE FROM nhanvien WHERE idnv='"+txtUser.getText()+"'";
        truyvan.executeSQlQuery(xoa,"Deleted");
        
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        ThucHienQuery truyvan=new ThucHienQuery();
        String User=txtUser.getText();
        String Pass=txtPass.getText();
        String Ho=txtHo.getText();
        String Ten=txtTen.getText();
        int NamSinh=Integer.parseInt(txtNamSinh.getText());
        int GioiTinh=0;
        if(btgGioiTinh.getSelection().equals(RBNam.getModel()))
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
        
    }//GEN-LAST:event_btnSuaActionPerformed

    private void BtnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoadActionPerformed
       Show_BangNV_In_JTable();
    }//GEN-LAST:event_BtnLoadActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVienTXS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienTXS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienTXS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienTXS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVienTXS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnLoad;
    private javax.swing.JComboBox<String> CBChucVu;
    private javax.swing.JRadioButton RBNam;
    private javax.swing.JRadioButton RBNu;
    private javax.swing.JTable TBNhanVien;
    private javax.swing.ButtonGroup btgGioiTinh;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}