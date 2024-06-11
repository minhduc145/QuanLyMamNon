package hp.mnhp;

import DAO.DbHelper;
import Model.ChamCongModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class BangLuong implements Initializable {
    List<luongModel> dscb = new ArrayList<>();
    Integer thangchon = null;
    Integer ngaychon = null;
    Integer namchon = null;
    @FXML
    ComboBox<Integer> nam;
    @FXML
    ComboBox<datepicker> thang;
    @FXML
    TableView<luongModel> tab;
    @FXML
    TableColumn id, hoten, hs1, tt1, hs2, tt2, hs3, tt3, tongpc, tong1, bhxh, bhyt, bhtn, tong2;

    Integer taoBang() {
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "insert into BangLuong values(?)";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            stmt.setString(1, thangchon + "/" + namchon);
            int i = stmt.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println("Bang Luong da ton tai");
        }
        return 0;
    }

    private void napDL() {
        int i = taoBang();
        if (i == 0) {
            try {
                Connection cn = (DbHelper.getInstance()).getConnection();
                String SQL = "SELECT TaiKhoan.idQuyen, CBNV.*\n" +
                        "FROM CBNV INNER JOIN TaiKhoan ON CBNV.idCBNV = TaiKhoan.idCBNV\n" + " where idtinhtrang = 'day' and year(Ngayvaolam) <=?";
                PreparedStatement stmt = cn.prepareStatement(SQL);
                stmt.setInt(1, namchon);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    luongModel cb = new luongModel();
                    cb.setId(rs.getString("idcbnv"));
                    cb.setTen(rs.getString("HoTen"));
                    cb.setIdCV(rs.getString("idchucvu"));
                    cb.setIdQ(rs.getString("idquyen"));
                    cb.setHsl(rs.getFloat("HSL"));
                    cb.setPCTN(rs.getFloat("pctn"));
                    dscb.add(cb);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }

    }

    void napBang() {
        id.setCellValueFactory(new PropertyValueFactory<luongModel, String>("id"));
        hoten.setCellValueFactory(new PropertyValueFactory<luongModel, String>("ten"));
        hs1.setCellValueFactory(new PropertyValueFactory<luongModel, Float>("hsl"));
        hs2.setCellValueFactory(new PropertyValueFactory<luongModel, Float>("hscv"));
        hs3.setCellValueFactory(new PropertyValueFactory<luongModel, Float>("PCTN"));
        tt1.setCellValueFactory(new PropertyValueFactory<luongModel, String>("TThsl"));
        tt2.setCellValueFactory(new PropertyValueFactory<luongModel, String>("TThscv"));
        tt3.setCellValueFactory(new PropertyValueFactory<luongModel, String>("ttpctn"));
        tongpc.setCellValueFactory(new PropertyValueFactory<luongModel, String>("Tong1"));
        tong1.setCellValueFactory(new PropertyValueFactory<luongModel, String>("Tong2"));
        bhxh.setCellValueFactory(new PropertyValueFactory<luongModel, String>("bhxh"));
        bhyt.setCellValueFactory(new PropertyValueFactory<luongModel, String>("bhyt"));
        bhtn.setCellValueFactory(new PropertyValueFactory<luongModel, String>("bhtn"));
        tong2.setCellValueFactory(new PropertyValueFactory<luongModel, String>("total"));
        tab.getItems().setAll(dscb);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date curDate = new Date();
        LocalDate localDate = curDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        int day = localDate.getDayOfMonth();
        for (int i = 1; i <= 12; i++) {
            thang.getItems().add(new datepicker("Tháng " + i, i));
            if (i == month)
                thang.getSelectionModel().select(thang.getItems().get(i - 1));
        }
        int i = 0;
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "SELECT top 1 year(NgayVaoLam) from CBNV order by year(NgayVaoLam) asc";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i != 0) {
            for (int j = i; j <= year; j++) {
                nam.getItems().add(j);
            }
        }
        nam.getSelectionModel().select((Integer) localDate.getYear());
        thangchon = month;
        namchon = year;
        napDL();
        napBang();
        thang.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<datepicker>() {
            @Override
            public void changed(ObservableValue<? extends datepicker> observable, datepicker oldValue, datepicker newValue) {
                thangchon = newValue.getThang();
                napDL();

            }
        });
        nam.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                namchon = newValue;
                napDL();

            }
        });

    }


    public class luongModel {
        private String id;
        private String ten;
        String idCV;
        private Float hsl;
        private Float TThsl;
        private Float hscv;
        private Float PCTN;
        private Float TThscv;
        private Float Tong1;
        private Float Tong2;
        private Float bhxh;
        private Float bhyt;
        private Float bhtn;
        private Float tong3;
        private Float total;
        private String idQ;
        private Float ttpctn;

        public String getTtpctn() {
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###");
            ttpctn = (getHsl() + getHscv()) * getPCTN() / 100 * 1800000;
            return formatter.format(ttpctn);
        }

        public String getIdQ() {
            return idQ;
        }

        public void setIdQ(String idQ) {
            this.idQ = idQ;
        }

        public String getIdCV() {

            return idCV;
        }

        public void setIdCV(String idCV) {
            this.idCV = idCV;
        }

        public void tinhLuong() {

        }

        public luongModel() {

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTen() {
            return ten;
        }

        public void setTen(String ten) {
            this.ten = ten;
        }

        public Float getHsl() {
            return hsl;
        }

        public void setHsl(Float hsl) {
            this.hsl = hsl;
        }

        public String getTThsl() {
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###");
            setTThsl(hsl * 1800000);
            return formatter.format(hsl * 1800000);
        }

        public void setTThsl(Float TThsl) {
            this.TThsl = TThsl;
        }

        public Float getHscv() {
            if (idCV.equals("ht"))
                return Float.valueOf("0.5");
            else if (idCV.equals("pht"))
                return Float.valueOf("0.4");
            else if (idCV.equals("gv") && idQ.contains("TT"))
                return Float.valueOf("0.3");
            else return Float.valueOf("0.0");
        }

        public void setHscv(Float hscv) {
            this.hscv = hscv;
        }

        public Float getPCTN() {
            return PCTN;
        }

        public void setPCTN(Float PCTN) {
            this.PCTN = PCTN;
        }

        public String getTong1() {
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###");
            setTong1((getHscv() * 1800000) + ((getHsl() + getHscv()) * getPCTN() / 100 * 1800000));
            return formatter.format(Tong1);

        }

        public void setTThscv(Float TThscv) {
            this.TThscv = TThscv;
        }

        public String getTThscv() {
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###");
            TThscv = (getHscv() * 1800000);
            return formatter.format(TThscv);
        }

        public void setTong1(Float tong1) {
            Tong1 = tong1;
        }

        public String getTong2() {
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###");
            setTong2(TThsl + Tong1);
            return formatter.format(TThsl + Tong1);
        }

        public void setTong2(Float tong2) {
            Tong2 = tong2;
        }

        public String getBhxh() {
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###");
            return formatter.format(Tong2 * 0.08);
        }

        public void setBhxh(Float bhxh) {
            this.bhxh = bhxh;
        }

        public String getBhyt() {
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###");
            return formatter.format(Tong2 * 0.015);
        }

        public void setBhyt(Float bhyt) {
            this.bhyt = bhyt;
        }

        public String getBhtn() {
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###");
            return formatter.format(Tong2 * 0.01);
        }

        public void setBhtn(Float bhtn) {
            this.bhtn = bhtn;
        }

        public Float getTong3() {
            return tong3;
        }

        public void setTong3(Float tong3) {
            this.tong3 = tong3;
        }

        public String getTotal() {
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###");
            return formatter.format(Tong2 - (Tong2 * (0.01 + 0.08 + 0.015)));
        }

        public void setTotal(Float total) {
            this.total = total;
        }
    }

    class datepicker {
        private String tenThang;
        private Integer thang;

        public datepicker(String t, int thang) {
            tenThang = t;
            this.thang = thang;
        }

        public String getTenThang() {
            return tenThang;
        }

        public Integer getThang() {
            return thang;
        }

        public void setTenThang(String tenThang) {
            this.tenThang = tenThang;
        }

        public void setThang(Integer thang) {
            this.thang = thang;
        }

        @Override
        public String toString() {
            return tenThang;
        }
    }
}
