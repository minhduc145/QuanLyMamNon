package hp.mnhp;

import DAO.CBNVDao;
import DAO.imgFotBtn;
import Model.CBNVModule;
import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Styles;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HS implements Initializable {
    @FXML
    Button suaBtn, luuBtn, huyBtn, sBtn;
    @FXML
    ListView<CBNVModule> list;
    @FXML
    TextField search;

    @FXML
    void onClicklogout() {
        List<Window> open = Stage.getWindows();
        try {
            for (Window w : open) {
                Stage s = (Stage) w;
                s.close();
                for (Window w1 : open) {
                    Stage s1 = (Stage) w1;
                    s1.close();
                }
            }
        } catch (Exception e) {
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Hello");
            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(new CupertinoLight().getUserAgentStylesheet());
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void setEditable(boolean i) {
//        hoten.setEditable(i);
//        noisinh.setEditable(i);
//        tn.setEditable(i);
//        dc.setEditable(i);
//        sdt.setEditable(i);
//        email.setEditable(i);
//        cccd.setEditable(i);
//        date.setEditable(i);
//        bd.setEditable(i);
//        editpw.setEditable(i);
//        tdhv.setEditable(i);
//        hsl.setEditable(i);
    }

    @FXML
    void onClickhuyBtn() {
        huyBtn.setVisible(false);
        luuBtn.setVisible(false);
        suaBtn.setVisible(true);
        setEditable(false);
    }

    @FXML
    void onClickexit() {
//        List<Window> open = (List<Window>) Stage.getWindows().stream();
//        for (Window w : open) {
        Platform.exit();
//        }
    }

    @FXML
    void onClickluuBtn() {

//        CBNVModule c = list.getSelectionModel().getSelectedItem();
//        boolean i = cbdao.updateCBNV(c.getIdCBNV(), quyen.getSelectionModel().getSelectedItem().getId(), editpw.getText(), hoten.getText(), cv.getSelectionModel().getSelectedItem().getId(), noisinh.getText(), tn.getText(), hsl.getText(), dc.getText(), sdt.getText(), email.getText(), date.getValue(), cccd.getText(), tt.getSelectionModel().getSelectedItem().getId(), gt.getSelectionModel().getSelectedItem().toString(), lopcn.getSelectionModel().getSelectedItem().getId(), bd.getValue(), tdhv.getText());
//        if (i) {
//            ds = new CBNVDao().getDSCB();
//            list.getItems().setAll(ds);
//            setEditable(false);
//            huyBtn.setVisible(false);
//            luuBtn.setVisible(false);
//            AlertMessage.infoBox(null, "Thông báo", "Cập nhật thành công");
//            suaBtn.setVisible(true);
//        } else {
//            AlertMessage.erBox(null, "Thông báo", "Cập nhật không thành công");
//            setEditable(true);
//            huyBtn.setVisible(true);
//            luuBtn.setVisible(true);
//        }


    }

    @FXML
    void onClicksuaBtn() {
        huyBtn.setVisible(true);
        luuBtn.setVisible(true);
        suaBtn.setVisible(false);
        setEditable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search.getStyleClass().addAll(
                Styles.ROUNDED
        );
        sBtn.getStyleClass().addAll(
                Styles.ROUNDED, Styles.BUTTON_ICON
        );
        Image image = new Image(getClass().getResourceAsStream("UI/loupe.png"), sBtn.getWidth(), sBtn.getHeight(), false, true);
        sBtn.setGraphic(new imgFotBtn().getImg(sBtn, image, 20, 20));
    }
}
