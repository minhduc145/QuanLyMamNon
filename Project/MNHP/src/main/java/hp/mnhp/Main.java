package hp.mnhp;

import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Styles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable {
    @FXML
    Button classBtn, cbnvBtn, hsBtn;
    @FXML
    Text txt1, txt2, txt3;

    @FXML
    void oncbnvBtnClick() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CBNV.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Quản lý CBNV");
            stage.setScene(new Scene(root));
            // stage.setMaximized(true);
            stage.getScene().getStylesheets().add(new CupertinoLight().getUserAgentStylesheet());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Creating a graphic (image)
        Image img = new Image(getClass().getResourceAsStream("UI/lecture-room.png"));
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(150);
        imgView.setFitHeight(150);
        classBtn.setGraphic(imgView);
        img = new Image(getClass().getResourceAsStream("UI/cbnv.png"));
        imgView = new ImageView(img);
        imgView.setFitWidth(150);
        imgView.setFitHeight(150);
        cbnvBtn.setGraphic(imgView);
        img = new Image(getClass().getResourceAsStream("UI/baby-boy.png"));
        imgView = new ImageView(img);
        imgView.setFitWidth(150);
        imgView.setFitHeight(150);
        hsBtn.setGraphic(imgView);
        txt1.getStyleClass().addAll(Styles.TEXT, Styles.ACCENT);
        txt2.getStyleClass().addAll(Styles.TEXT, Styles.ACCENT);
        txt3.getStyleClass().addAll(Styles.TEXT, Styles.ACCENT);

    }
}
