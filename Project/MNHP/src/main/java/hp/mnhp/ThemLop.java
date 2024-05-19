package hp.mnhp;

import DAO.DbHelper;
import DAO.LopDao;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class ThemLop implements Initializable {
    @FXML
    AnchorPane ap;
    @FXML
    Button luubtn;
    @FXML
    TextField txt;
    @FXML
    Text id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (!txt.getText().isEmpty()) {
                    String newid = "";
                    String[] splited = txt.getText().toString().split("\\s+");
                    for (String s : splited) {
                        if (s.substring(0, 1).chars().allMatch(Character::isDigit)) {
                            newid += s;
                        } else {
                            newid += s.substring(0, 1);
                        }
                    }
                    id.setText(newid.toLowerCase());
                } else id.setText(null);
            }
        });
        luubtn.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                try {
                    new LopDao().ThemLop(id.getText().toString(), txt.getText().toString());
                    ap.getScene().getWindow().hide();
                } catch (Exception e) {
                    if (e.getMessage().contains("Violation of PRIMARY KEY")) {
                        AlertMessage.erBox("Thử với tên khác", "Loi", "Lớp đã tồn tại (trùng mã lớp)");
                    } else {
                        AlertMessage.erBox("Thử với tên khác", "Loi", "Loi them");
                    }
                }
            }
        });
    }
}
