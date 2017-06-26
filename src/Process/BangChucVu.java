
package Process;

public class BangChucVu {
  private String MaCV; 
  private String TenCV ; 
  private int PhuCapCV;
  private int HeSoLuong;
  
  public BangChucVu(String MaCV,String TenCV,int PhuCapCV,int HeSoLuong)
  {
      this.MaCV=MaCV;
      this.TenCV=TenCV;
      this.PhuCapCV=PhuCapCV;
      this.HeSoLuong=HeSoLuong;
      
  }
   public String getTenCV()
  {
      return TenCV;
  }
  public String getMaCV()
  {
      return MaCV;
  }
  public int getPhuCapCV()
  {
      return PhuCapCV;
  }
  public int getHeSoLuong()
  {
      return HeSoLuong;
  }
}
