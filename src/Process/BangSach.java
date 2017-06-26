package Process;

public class BangSach {
  private String MaSach; 
  private String TenSach ; 
  private byte[] AnhSach;
  private String TacGia;
  private int Gia;
  private int SoLuong;
  private String MaTL;
  private String MaNXB;
  public BangSach(String MaSach,String TenSach,byte[] AnhSach,String TacGia,int Gia,int SoLuong,String MaTL,String MaNXB)
  {
      this.MaSach=MaSach;
      this.TenSach=TenSach;
      this.AnhSach=AnhSach;
      this.TacGia=TacGia;
      this.Gia=Gia;
      this.SoLuong=SoLuong;
       this.MaTL=MaTL;
        this.MaNXB=MaNXB;
  }

      public String getMaSach()
  {
      return MaSach;
  }
  public String getTenSach()
  {
      return TenSach;
  }
  public byte[] getAnhSach()
  {
      return AnhSach;
  }
   public String getTacGia()
  {
      return TacGia;
  }
  public int getGia()
  {
      return Gia;
  }
   public int getSoLuong()
  {
      return SoLuong;
  }
   public String getMaTL()
  {
      return MaTL;
  }
  public String getMaNXB()
  {
      return MaNXB;
  }
}

