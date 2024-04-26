package DAO;

import Model.CBNVModule;
import Model.LopModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LopDao {
    public List<LopModel> getDSLop() {
        List<LopModel> ds = new ArrayList<>();
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "SELECT Lop.idLop, Lop.TenLop,  COUNT(Tre.idTre) AS SoTre\n" +
                    "FROM     Lop left JOIN\n" +
                    "                  Tre ON Lop.idLop = Tre.idLop \n" +
                    "Group by Lop.idLop, Lop.TenLop";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LopModel lop = new LopModel(rs.getString(1), rs.getString(2));
                lop.setSotre(rs.getInt(3));
                ds.add(lop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
