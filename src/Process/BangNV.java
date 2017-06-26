package Process;

public class BangNV {
  private String User; 
  private String Pass ; 
  private String HoTenNV;
  private int NamSinh;
  private int Gioitinh;
  private String ChucVu;
  public BangNV(String User,String Pass,String HoTenNV,int NamSinh,int Gioitinh,String ChucVu)
  {
      this.User=User;
      this.Pass=Pass;
      this.HoTenNV=HoTenNV;
      this.NamSinh=NamSinh;
      this.Gioitinh=Gioitinh;
      this.ChucVu=ChucVu;
  }

      public String getUser()
  {
      return User;
  }
  public String getPass()
  {
      return Pass;
  }
  public String getHoTen()
  {
      return HoTenNV;
  }
  public int getNamSinh()
  {
      return NamSinh;
  }
  public String getGioitinh()
  {
      String xuat="";
      if(Gioitinh==0) xuat="Nữ"; 
      else xuat="Nam";
      return xuat;
  }
  public String getChucVu()
  {
      return ChucVu;
  }
}

