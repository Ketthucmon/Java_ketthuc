
package Process;


public class BangLuong {
  private String MaChi; 
  private String ID ; 
  private String TenID;
  private String NgayChi;
  private int Thang;
  private int Nam;
  private float Luong;
  public BangLuong(String MaChi,String ID,String TenID,String NgayChi,int Thang,int Nam,float Luong){
      this.MaChi=MaChi;
      this.ID=ID;
      this.TenID=TenID;
      this.Thang=Thang;
      this.Nam=Nam;
      this.Luong=Luong;
      this.NgayChi=NgayChi;
    }
  public String getID()
  {
      return ID;
  }
  public String getMaChi()
  {
      return MaChi;
  }
   public String getNgayChi()
  {
      return NgayChi;
  }
   public String getTenID()
  {
      return TenID;
  }
   public int getThang(){
       return Thang;
   }
     public int getNam(){
       return Nam;
   }
     public float getLuong(){
         return Luong;
     }
   
}
