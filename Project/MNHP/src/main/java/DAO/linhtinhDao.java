package DAO;

import Model.danhhieuModel;
import Model.ChucVuModel;
import Model.LopModel;
import Model.QuyenModel;
import Model.TrangThaiModel;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class linhtinhDao {
    public List<danhhieuModel> dsdh = new ArrayList<>();
    public List<QuyenModel> dsq = new ArrayList<>();
    public List<ChucVuModel> dschv = new ArrayList<>();
    public List<LopModel> dsl = new ArrayList<>();

    public List<TrangThaiModel> dstt = new ArrayList<>();

    public void getdsDanhHieu() {
        dsdh.clear();
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "SELECT * from danhhieu";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                danhhieuModel dh = new danhhieuModel();
                dh.setIddh(rs.getString("iddanhhieu"));
                dh.setDanhhieu(rs.getString("danhhieu"));
                dsdh.add(dh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getdsQuyen() {
        dsq.clear();
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
        dschv.clear();
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
        dstt.clear();
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
        getdsDanhHieu();
        getdsLop();
        getdsQuyen();
        getdsTrT();
        getdsChucvu();
    }

    public StringConverter<LocalDate> datePickerFormatter(DatePicker ngs) {
        StringConverter<LocalDate> d = new StringConverter<LocalDate>() {
            String pattern = "dd/MM/yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                ngs.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        return d;
    }
}
