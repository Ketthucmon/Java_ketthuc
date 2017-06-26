
package Process;


public class BangTheLoai {
  private String MaTL; 
  private String TenTL ; 
  private String LuaTuoi;
  public BangTheLoai(String MaTL,String TenTL,String LuaTuoi)
  {
      this.MaTL=MaTL;
      this.TenTL=TenTL;
      this.LuaTuoi=LuaTuoi;
      
  }
   public String getTenTL()
  {
      return TenTL;
  }
  public String getMaTL()
  {
      return MaTL;
  }
  public String getLuaTuoi()
  {
      return LuaTuoi;
  }
}
