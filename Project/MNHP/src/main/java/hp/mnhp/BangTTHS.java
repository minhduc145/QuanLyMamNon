package hp.mnhp;

import DAO.DbHelper;
import DAO.linhtinhDao;
import Model.LopModel;
import Model.danhhieuModel;
import Model.hsModel;
import Model.phModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class BangTTHS implements Initializable {
    linhtinhDao linhtinh = new linhtinhDao();

    @FXML
    ComboBox<String> tt, gt;
    @FXML
    ComboBox<LopModel> lopds;
    @FXML
    ComboBox<danhhieuModel> dhds;
    @FXML
    Button luuBtn, huyBtn, m1, p1, add2, del2;
    @FXML
    DatePicker ngs;
    @FXML
    TableColumn col1, col2, phcol1, phcol2, phcol3, phcol4, phcol5;
    @FXML
    TableView<danhhieuModel> listdh;
    @FXML
    TableView<phModel> phtable;
    @FXML
    TextField hoten, noisinh, dc, nnh, ename, inputten, inputvt, inputsdt, inputdc, inputnn;

    @FXML
    void xoa1() {
        if (!listdh.getSelectionModel().isEmpty()) {
            danhhieuModel e = listdh.getSelectionModel().getSelectedItem();
            listdh.getItems().remove(e);
        }

    }

    @FXML
    void them1() {
        danhhieuModel dhm = new danhhieuModel();
        dhm.setIddh(dhds.getSelectionModel().getSelectedItem().getIddh());
        dhm.setDanhhieu(dhds.getSelectionModel().getSelectedItem().getDanhhieu());
        dhm.setNam(ename.getText());
        col1.setCellValueFactory(new PropertyValueFactory<danhhieuModel, String>("nam"));
        col2.setSortable(false);
        col2.setCellValueFactory(new PropertyValueFactory<danhhieuModel, String>("danhhieu"));
        listdh.getItems().add(dhm);
    }

    @FXML
    void them2() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Xác nhận Thêm");
        alert.showAndWait();
        if (alert.getResult().getButtonData().isDefaultButton()) {
            if (!inputten.getText().isEmpty() && !inputvt.getText().isEmpty() && !inputsdt.getText().isEmpty() && !inputnn.getText().isEmpty() && !inputdc.getText().isEmpty()) {
                phModel ph = new phModel();
                ph.setHoten(inputten.getText());
                ph.setVaitro(inputvt.getText());
                ph.setSdt(inputsdt.getText());
                ph.setNghe(inputnn.getText());
                ph.setDiachi(inputdc.getText());
                phcol1.setCellValueFactory(new PropertyValueFactory<phModel, String>("hoten"));
                phcol2.setCellValueFactory(new PropertyValueFactory<phModel, String>("vaitro"));
                phcol3.setCellValueFactory(new PropertyValueFactory<phModel, String>("sdt"));
                phcol4.setCellValueFactory(new PropertyValueFactory<phModel, String>("diachi"));
                phcol5.setCellValueFactory(new PropertyValueFactory<phModel, String>("nghe"));
                phtable.getItems().add(ph);
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Điền đầy đủ các trường");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void xoa2() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Xác nhận xóa");
        alert.showAndWait();
        if (alert.getResult().getButtonData().isDefaultButton()) {
            if (phtable.getSelectionModel().getSelectedItem() != null) {
                phModel ph = phtable.getSelectionModel().getSelectedItem();
                phtable.getItems().remove(ph);
            }
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        linhtinh.load();
        hsModel newHs = new hsModel();
        tt.getItems().setAll("Đang theo học", "Không đang theo học");
        gt.getItems().setAll("Nam", "Nữ");
        lopds.getItems().setAll(linhtinh.dsl);
        dhds.getItems().setAll(linhtinh.dsdh);
        ngs.setConverter(linhtinh.datePickerFormatter(ngs));
        newHs.setHoten(hoten.getText());
        newHs.setNoisinh(noisinh.getText());
    }
}
