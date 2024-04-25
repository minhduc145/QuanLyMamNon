package Model;

import lombok.Builder;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Setter
@Builder
public class CBNVModule {
    private String idCBNV;
    private String Hoten;
    private String idChucVu;
    private String NoiSinh;
    private String QueQuan;
    private String DiaChiTT;
    private String SDT = "null";
    private String Email = "null";
    private LocalDate NgayVaoLam;
    private LocalDate NgaySinh;
    private String soCCCD = "null";
    private String idTinhTrang;
    private boolean GTNam;
    private String idLop;
    private String MatKhau;
    private String idQuyen;
    private String TDHV = "null";

    public String getTDHV() {
        return TDHV;
    }

    public void setTDHV(String TDHV) {
        this.TDHV = TDHV;
    }

    public CBNVModule(String idCBNV, String hoten, String idChucVu, String noiSinh, String queQuan, String diaChiTT, String SDT, String email, LocalDate ngayVaoLam, LocalDate ngaySinh, String soCCCD, String idTinhTrang, boolean GTNam, String idLop, String matKhau, String idQuyen, String TDHV) {
        this.idCBNV = idCBNV;
        Hoten = hoten;
        this.idChucVu = idChucVu;
        NoiSinh = noiSinh;
        QueQuan = queQuan;
        DiaChiTT = diaChiTT;
        this.SDT = SDT;
        Email = email;
        NgayVaoLam = ngayVaoLam;
        NgaySinh = ngaySinh;
        this.soCCCD = soCCCD;
        this.idTinhTrang = idTinhTrang;
        this.GTNam = GTNam;
        this.idLop = idLop;
        MatKhau = matKhau;
        this.idQuyen = idQuyen;
        this.TDHV = TDHV;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        NgayVaoLam = ngayVaoLam;
    }

    public LocalDate getNgayVaoLam() {
        return NgayVaoLam;
    }

    public String getIdCBNV() {
        return idCBNV;
    }

    public void setIdCBNV(String idCBNV) {
        this.idCBNV = idCBNV;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getIdChucVu() {
        return idChucVu;
    }

    public void setIdChucVu(String idChucVu) {
        this.idChucVu = idChucVu;
    }

    public String getNoiSinh() {
        return NoiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        NoiSinh = noiSinh;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String queQuan) {
        QueQuan = queQuan;
    }

    public String getDiaChiTT() {
        return DiaChiTT;
    }

    public void setDiaChiTT(String diaChiTT) {
        DiaChiTT = diaChiTT;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getIdTinhTrang() {
        return idTinhTrang;
    }

    public void setIdTinhTrang(String idTinhTrang) {
        this.idTinhTrang = idTinhTrang;
    }

    public boolean isGTNam() {
        return GTNam;
    }

    public void setGTNam(boolean GTNam) {
        this.GTNam = GTNam;
    }

    public String getIdLop() {
        return idLop;
    }

    public void setIdLop(String idLop) {
        this.idLop = idLop;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getIdQuyen() {
        return idQuyen;

    }

    public void setIdQuyen(String idQuyen) {
        this.idQuyen = idQuyen;
    }

    public String getGioiTinh() {
        if (isGTNam())
            return "Nam";
        return "Nữ";
    }


    public CBNVModule() {
        super();
    }

    public String getStringNgayVaoLam() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(NgayVaoLam);
        return date;
    }

    public LocalDate getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        NgaySinh = ngaySinh;
    }

    @Override
    public String toString() {
        return Hoten;
    }
}
