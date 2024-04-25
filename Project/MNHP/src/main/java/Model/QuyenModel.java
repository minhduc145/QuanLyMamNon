package Model;

public class QuyenModel {
    private String id;
    private String Quyen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuyen() {
        return Quyen;
    }

    public void setQuyen(String quyen) {
        Quyen = quyen;
    }

    public QuyenModel(String id, String quyen) {
        this.id = id;
        Quyen = quyen;
    }

    public QuyenModel() {
        super();
    }

    @Override
    public String toString() {
        return Quyen;
    }
}
