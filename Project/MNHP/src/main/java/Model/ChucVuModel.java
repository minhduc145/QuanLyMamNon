package Model;

import lombok.Getter;
import lombok.Setter;


public class ChucVuModel {
    private String id;
    private String ChucVu;

    public ChucVuModel() {
        super();
    }

    public ChucVuModel(String id, String chucVu) {
        this.id = id;
        ChucVu = chucVu;
    }

    public String getId() {
        return id;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    @Override
    public String toString() {
        return ChucVu;
    }
}
