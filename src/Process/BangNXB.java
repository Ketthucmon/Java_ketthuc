
package Process;


public class BangNXB {
  private String MaNXB; 
  private String TenNXB ; 
  private String DiaChi;
  private String SDT;
  public BangNXB(String MaNXB,String TenNXB,String DiaChi,String SDT){
      this.MaNXB=MaNXB;
      this.TenNXB=TenNXB;
      this.DiaChi=DiaChi;
      this.SDT=SDT;
  }
  public String getTenNXB()
  {
      return TenNXB;
  }
  public String getMaNXB()
  {
      return MaNXB;
  }
  public String getDiaChi()
  {
      return DiaChi;
  }
  public String getSDT()
  {
      return SDT;
  }
}
