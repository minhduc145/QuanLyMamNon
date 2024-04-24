package Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Setter
@Getter
@ToString
public class User extends CBNV {
    public User(String idCBNV, String hoten, String idChucVu, String chucVu, String noiSinh, String queQuan, String diaChiTT, String SDT, String email, Date ngayVaoLam, String soCCCD, String idTinhTrang, String tinhTrang) {
        super(idCBNV, hoten, idChucVu, chucVu, noiSinh, queQuan, diaChiTT, SDT, email, ngayVaoLam, soCCCD, idTinhTrang, tinhTrang);
    }

}
