package Model;

public class danhhieuModel {
    private String id;
    private String danhhieu;
    private String nam;
    private String iddh;

    public danhhieuModel(String id, String name) {
        iddh = id;
        danhhieu = name;
    }

    public danhhieuModel() {
    }

    public String getIddh() {
        return iddh;
    }

    public void setIddh(String iddh) {
        this.iddh = iddh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDanhhieu() {
        return danhhieu;
    }

    public void setDanhhieu(String danhhieu) {
        this.danhhieu = danhhieu;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    @Override
    public String toString() {
        return danhhieu;
    }
}
