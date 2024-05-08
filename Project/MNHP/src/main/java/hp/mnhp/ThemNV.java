package hp.mnhp;

import DAO.DbHelper;
import DAO.linhtinhDao;
import Model.ChucVuModel;
import Model.hsModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThemNV implements Initializable {
    linhtinhDao linhtinhDao = new linhtinhDao();
    ChucVuModel c;
    @FXML
    AnchorPane ap;
    @FXML
    Button luu;
    @FXML
    ComboBox<ChucVuModel> chucvu;
    @FXML
    TextField hoten, mk;
    @FXML
    Text tentk;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        linhtinhDao.load();
        chucvu.getItems().setAll(linhtinhDao.dschv);
        chucvu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ChucVuModel>() {
            @Override
            public void changed(ObservableValue<? extends ChucVuModel> observable, ChucVuModel oldValue, ChucVuModel newValue) {
                String id = null;
                c = chucvu.getValue();
                try {
                    Connection cn = (DbHelper.getInstance()).getConnection();
                    String SQL = "select max(idCBNV) from CBNV where idCBNV like '" + c.getId() + "%'";
                    PreparedStatement stmt = cn.prepareStatement(SQL);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        id = rs.getString(1);
                    }
                } catch (Exception e) {
                }
                if (id != null) {
                    int so = Integer.parseInt(id.replaceAll("[^0-9]", "")) + 1;
                    id = c.getId() + String.valueOf(so);
                } else {
                    id = c.getId() + "1";
                }
                tentk.setText(id);
            }
        });

        luu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                try {
                    Connection cn = (DbHelper.getInstance()).getConnection();
                    String SQL = "INSERT INTO [dbo].[CBNV] ([idCBNV] ,[idChucVu], [HoTen]) VALUES(? ,?, ?)";
                    PreparedStatement stmt = cn.prepareStatement(SQL);
                    stmt.setString(1, tentk.getText());
                    stmt.setString(2, c.getId());
                    stmt.setString(3, hoten.getText());
                    stmt.executeUpdate();
                    SQL = "insert into taikhoan(idcbnv, matkhau) values (?,?)";
                    stmt = cn.prepareStatement(SQL);
                    stmt.setString(1,tentk.getText());
                    stmt.setString(2,mk.getText());

                    stmt.executeUpdate();
                    ap.getScene().getWindow().hide();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


        });
    }
}
