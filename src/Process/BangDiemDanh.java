package Process;


public class BangDiemDanh {
  private String MaID; 
  private String TenID ; 
  private String NgayVang;
  private String LyDo;
  public  BangDiemDanh(String MaID,String TenID,String NgayVang,String LyDo){
      this.MaID=MaID;
      this.TenID=TenID;
      this.NgayVang=NgayVang;
      this.LyDo=LyDo;
  }
  public String getMaID()
  {
      return MaID;
  }
  public String getTenID()
  {
      return TenID;
  }
   public String getNgayVang()
  {
      return NgayVang;
  }
    public String getLyDo()
  {
      return LyDo;
  }
  
}
