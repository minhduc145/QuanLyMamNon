package Model;

import java.util.ArrayList;
import java.util.List;

public class LopModel {
    private List<TreModel> dstre = new ArrayList<>();
    private String id;
    private String TenLop;

    public LopModel(String id, String quyen) {
        this.id = id;
        TenLop = quyen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String tenLop) {
        TenLop = tenLop;
    }

    @Override
    public String toString() {
        return TenLop;
    }
}
