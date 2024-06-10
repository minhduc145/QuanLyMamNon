package hp.mnhp;

import DAO.DbHelper;
import Model.CBNVModule;
import Model.ChamCongModel;
import DAO.chuyenNgay;
import Model.hsModel;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ChamCong1 implements Initializable {
    List<ChamCongModel> dscb = new ArrayList<>();
    Integer thangchon = null;
    Integer ngaychon = null;
    Integer namchon = null;
    @FXML
    TableView<ChamCongModel> tab;
    @FXML
    ComboBox<datepicker> thang;
    @FXML
    ComboBox<Integer> nam;


    void napNgay(int month, int year) {
        tab.getItems().clear();
        tab.getColumns().clear();
        TableColumn a1 = new TableColumn("ID");
        a1.setPrefWidth(50);
        TableColumn b1 = new TableColumn("Họ Tên");
        b1.setPrefWidth(150);
        a1.setCellValueFactory(new PropertyValueFactory<ChamCongModel, String>("id"));
        b1.setCellValueFactory(new PropertyValueFactory<ChamCongModel, String>("ten"));
        tab.getColumns().add(a1);
        tab.getColumns().add(b1);
        for (int i = 1; i <= chuyenNgay.getDays(month, year); i++) {
            TableColumn a = new TableColumn(String.valueOf(i));
            TableColumn b = new TableColumn(chuyenNgay.getThu(i, month, year));
            a.setPrefWidth(40);
            b.setPrefWidth(40);
            a.getColumns().add(b);
            a.setId("select" + i);
            b.setId("select" + i);
            tab.getColumns().add(a);
        }
        for (TableColumn s : tab.getColumns()) {
            if (s.getId() != null && s.getId().contains("select")) {
                TableColumn b = (TableColumn) s.getColumns().get(0);
                if (b.getText().equals("CN") || ((TableColumn) b).getText().equals("T7")) {
//                    b.setStyle("-fx-background-color: red ;");
                } else {
                    b.setCellValueFactory(new PropertyValueFactory<ChamCongModel, CheckBox>(s.getId()));
                }
            }
        }
        TableColumn a = new TableColumn("Tổng");
        a.setPrefWidth(100);
        tab.getColumns().add(a);
    }

    List<ChamCongModel> napCB() {
        dscb = new ArrayList<>();
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "SELECT CBNV.idCBNV, CBNV.HoTen FROM CBNV LEFT OUTER JOIN TaiKhoan ON CBNV.idCBNV = TaiKhoan.idCBNV";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ChamCongModel cb = new ChamCongModel();
                cb.setId(rs.getString("idcbnv"));
                cb.setTen(rs.getString(2));
                dscb.add(cb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dscb;
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
        int namvaosomnhat = 2000;
        try {
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "select top 1 year(Ngayvaolam) from CBNV order by year(NgayVaoLam) asc ";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                namvaosomnhat = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = namvaosomnhat; i <= localDate.getYear(); i++) {
            nam.getItems().add(i);
        }
        nam.getSelectionModel().select((Integer) localDate.getYear());
        napNgay(month, year);
        tab.getItems().setAll(napCB());
        thangchon = month;
        namchon = year;
        thang.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<datepicker>() {
            @Override
            public void changed(ObservableValue<? extends datepicker> observable, datepicker oldValue, datepicker newValue) {
                thangchon = newValue.getThang();
                napNgay(thangchon, namchon);
                tab.getItems().setAll(napCB());
            }
        });
        nam.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                namchon = newValue;
                napNgay(thangchon, namchon);
                tab.getItems().setAll(napCB());
            }
        });

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
