package DAO;

import Model.ChucVuModel;
import Model.LopModel;
import Model.QuyenModel;
import Model.TrangThaiModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class linhtinhDao {
    public List<QuyenModel> dsq = new ArrayList<>();
    public List<ChucVuModel> dschv = new ArrayList<>();
    public List<LopModel> dsl = new ArrayList<>();

    public List<TrangThaiModel> dstt = new ArrayList<>();

    public void getdsQuyen() {

        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "SELECT * from QuyenMuc";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dsq.add(new QuyenModel(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getdsChucvu() {
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "SELECT * from ChucVu";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dschv.add(new ChucVuModel(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getdsTrT() {
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "SELECT * from TinhTrangNV";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dstt.add(new TrangThaiModel(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getdsLop() {
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "SELECT * from Lop";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dsl.add(new LopModel(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        dsl.add(new LopModel(null, "Không"));
        getdsLop();
        getdsQuyen();
        getdsTrT();
        getdsChucvu();
    }
}
