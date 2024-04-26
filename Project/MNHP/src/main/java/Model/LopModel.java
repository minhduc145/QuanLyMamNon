package Model;

import DAO.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LopModel {
    private List<TreModel> dstre = new ArrayList<>();
    private String id = "Khong";
    private List<GVCN> dsGVCN = dsGVCN();
    private String TenLop = "Khong";
    private int sotre;

    private class GVCN {
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

    List<GVCN> dsGVCN() {
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "SELECT idCBGV,HoTen from CBGV where idLop = ?";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dsGVCN.add(new GVCN(rs.getString("idCBGV"), rs.getString("HoTen")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    public String getGVCN() {
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
