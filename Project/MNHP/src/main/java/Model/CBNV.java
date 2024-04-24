package Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@lombok.ToString
@Builder
public class CBNV {
    private String idCBNV;
    private String Hoten;
    private String idChucVu;
    private String ChucVu;
    private String NoiSinh;
    private String QueQuan;
    private String DiaChiTT;
    private String SDT;
    private String Email;
    private Date NgayVaoLam;
    private String soCCCD;
    private String idTinhTrang;
    private String TinhTrang;

    public CBNV(String idCBNV, String hoten, String idChucVu, String chucVu, String noiSinh, String queQuan, String diaChiTT, String SDT, String email, Date ngayVaoLam, String soCCCD, String idTinhTrang, String tinhTrang) {
        this.idCBNV = idCBNV;
        Hoten = hoten;
        this.idChucVu = idChucVu;
        ChucVu = chucVu;
        NoiSinh = noiSinh;
        QueQuan = queQuan;
        DiaChiTT = diaChiTT;
        this.SDT = SDT;
        Email = email;
        NgayVaoLam = ngayVaoLam;
        this.soCCCD = soCCCD;
        this.idTinhTrang = idTinhTrang;
        TinhTrang = tinhTrang;
    }

    public String getIdCBNV() {
        return idCBNV;
    }

    public String getHoten() {
        return Hoten;
    }

    public String getIdChucVu() {
        return idChucVu;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public String getNoiSinh() {
        return NoiSinh;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public String getDiaChiTT() {
        return DiaChiTT;
    }

    public String getSDT() {
        return SDT;
    }

    public String getEmail() {
        return Email;
    }

    public Date getNgayVaoLam() {
        return NgayVaoLam;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public String getIdTinhTrang() {
        return idTinhTrang;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }
}
