package hp.mnhp;

import DAO.DbHelper;
import DAO.LopDao;
import DAO.linhtinhDao;
import Model.LopModel;
import Model.hsModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class chuyenLop implements Initializable {
    protected static LopModel lopht = null;

    public static void setLopht(LopModel l) {
        lopht = l;
    }

    @FXML
    ComboBox<LopModel> dslop;
    @FXML
    Button ok;
    @FXML
    Text tenlopht;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tenlopht.setText(lopht.getTenLop());
        linhtinhDao lt = new linhtinhDao();
        lt.load();
        dslop.getItems().setAll(lt.dsl);
        dslop.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LopModel>() {
            @Override
            public void changed(ObservableValue<? extends LopModel> observable, LopModel oldValue, LopModel newValue) {
                if (!newValue.getId().equals("null") || newValue.getId() != null) {
                    ok.setDisable(false);
                }
            }
        });
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                int i = 0;
                try {
                    Connection cn = (DbHelper.getInstance()).getConnection();
                    String SQL = "UPDATE [dbo].[Tre]\n" +
                            "   SET [idLop] = ?" +
                            " WHERE idLop = ?";
                    PreparedStatement stmt = cn.prepareStatement(SQL);
                    stmt.setString(1, dslop.getSelectionModel().getSelectedItem().getId());
                    stmt.setString(2, lopht.getId());
                    i = stmt.executeUpdate();
                    ok.getScene().getWindow().hide();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
