package Model;

import DAO.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LopModel {
    private List<TreModel> dstre = new ArrayList<>();
    public String id = "Khong";
    private List<GVCN> dsGVCN = new ArrayList<>();
    private String TenLop = "Khong";
    private int sotre;
    private String tenGVCN = "Không";

    public static class GVCN {
        private String Hoten;
        private String id;

        public GVCN(String id, String hoten) {
            this.id = id;
            this.Hoten = hoten;
        }

        public String getHoten() {
            return Hoten;
        }

        public void setHoten(String hoten) {
            Hoten = hoten;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public LopModel(String id, String tenLop) {
        this.id = id;
        TenLop = tenLop;
    }

    List<TreModel> dstre() {
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "SELECT * from Tre where idLop = ?";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dstre;
    }


    public void setTenGVCN(String tenGVCN) {
        this.tenGVCN = tenGVCN;
    }

    public void setDsGVCN(List<GVCN> ds) {
        dsGVCN = ds;
    }

    public List<GVCN> getDsGVCN() {
        return dsGVCN;
    }


    public int getSotre() {
        return sotre;
    }

    public void setSotre(int sotre) {
        this.sotre = sotre;
    }

    public List<TreModel> getDstre() {
        return dstre;
    }

    public void setDstre(List<TreModel> dstre) {
        this.dstre = dstre;
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


    public String getIdGVCN() {
        String rs = "";
        for (LopModel.GVCN gv : dsGVCN) {
            rs += gv.getId();
            rs += "/";
        }
        return rs;
    }


    public String getTenGVCN() {
        String rs = "";
        for (LopModel.GVCN gv : dsGVCN) {
            rs += gv.getHoten();
            rs += "; ";
        }
        return rs;
    }

    @Override
    public String toString() {
        return TenLop;
    }
}
