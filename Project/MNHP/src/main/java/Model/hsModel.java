package Model;

import java.time.LocalDate;
import java.util.List;

public class hsModel {
    private String id;
    private String idLop;
    private String hoten;
    private String noisinh;
    private LocalDate ngaysinh;
    private String diachi;
    private String namnhaphoc;
    private boolean lanam;
    private boolean dangtheohoc = false;
    private List<LopModel.GVCN> gvcn;
    private String gvcnString;
    private List<phModel> ph;
    private List<danhhieuModel> danhhieu;

    public hsModel() {
        super();
    }

    public List<danhhieuModel> getDanhhieu() {
        return danhhieu;
    }

    public void setDanhhieu(List<danhhieuModel> danhhieu) {
        this.danhhieu = danhhieu;
    }

    public String getGvcnString() {
        return gvcnString;
    }

    public void setGvcnString(String gvcnString) {
        this.gvcnString = gvcnString;
    }

    public String getIdLop() {
        return idLop;
    }

    public void setIdLop(String idLop) {
        this.idLop = idLop;
    }

    public List<phModel> getPh() {
        return ph;
    }

    public void setPh(List<phModel> ph) {
        this.ph = ph;
    }

    public String getNamnhaphoc() {
        return namnhaphoc;
    }

    public void setNamnhaphoc(String namnhaphoc) {
        this.namnhaphoc = namnhaphoc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNoisinh() {
        return noisinh;
    }

    public void setNoisinh(String noisinh) {
        this.noisinh = noisinh;
    }

    public LocalDate getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(LocalDate ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public boolean isLanam() {
        return lanam;
    }

    public void setLanam(boolean lanam) {
        this.lanam = lanam;
    }

    public boolean isDangtheohoc() {
        return dangtheohoc;
    }

    public void setDangtheohoc(boolean dangtheohoc) {
        this.dangtheohoc = dangtheohoc;
    }

    public String getStringGV() {
        String s = "";
        for (phModel p : ph) {
            s += p.getHoten() + "; ";
        }
        return s;
    }

    public List<LopModel.GVCN> getGvcn() {
        return gvcn;
    }

    public void setGvcn(List<LopModel.GVCN> gvcn) {
        this.gvcn = gvcn;
    }

    @Override
    public String toString() {
        return hoten;
    }
}
