package hp.mnhp;


import Model.User;
import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Styles;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Main implements Initializable {
    @FXML
    VBox vbox;
    @FXML
    Button idbtn, classBtn, cbnvBtn, hsBtn;
    @FXML
    Text txt1, txt2, txt3;

    @FXML
    void onClickexit() {
//        List<Window> open = (List<Window>) Stage.getWindows().stream();
//        for (Window w : open) {
        Platform.exit();
//        }
    }

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

    @FXML
    void onclassBtnClick() throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("DSLop.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Quản lý Lớp");
            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(new CupertinoLight().getUserAgentStylesheet());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onkidsBtnClick() throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("HS.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Quản lý Trẻ");
            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(new CupertinoLight().getUserAgentStylesheet());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void onidbtnClick() throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("myInfo.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Quản lý Trẻ");
            stage.setScene(new Scene(root));
            stage.getScene().getStylesheets().add(new CupertinoLight().getUserAgentStylesheet());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idbtn.getStyleClass().addAll(
                Styles.MEDIUM, Styles.ROUNDED, Styles.BUTTON_OUTLINED, Styles.SUCCESS
        );
        classBtn.getStyleClass().addAll(
                Styles.ROUNDED, Styles.BUTTON_ICON, Styles.BUTTON_OUTLINED
        );
        hsBtn.getStyleClass().addAll(
                Styles.ROUNDED, Styles.BUTTON_ICON, Styles.BUTTON_OUTLINED
        );
        cbnvBtn.getStyleClass().addAll(
                Styles.ROUNDED, Styles.BUTTON_ICON, Styles.BUTTON_OUTLINED
        );

        Image image = new Image(getClass().getResourceAsStream("UI/profile-user.png"), idbtn.getWidth(), idbtn.getHeight(), false, true);
        BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(idbtn.getWidth(), idbtn.getHeight(), true, true, true, false));
        ImageView imgView = new ImageView(image);
        imgView.setFitWidth(30);
        imgView.setFitHeight(30);
        idbtn.setText(User.Hoten);
        idbtn.setGraphic(imgView);
        //Creating a graphic (image)
        Image img = new Image(getClass().getResourceAsStream("UI/lecture-room.png"));
        imgView = new ImageView(img);
        imgView.setFitWidth(150);
        imgView.setFitHeight(150);
        classBtn.setGraphic(imgView);
        img = new Image(getClass().getResourceAsStream("UI/cbnv.png"));
        imgView = new ImageView(img);
        imgView.setFitWidth(150);
        imgView.setFitHeight(150);
        cbnvBtn.setGraphic(imgView);
        img = new Image(getClass().getResourceAsStream("UI/chase.png"));
        imgView = new ImageView(img);
        imgView.setFitWidth(150);
        imgView.setFitHeight(150);
        hsBtn.setGraphic(imgView);
        txt1.getStyleClass().addAll(Styles.TEXT, Styles.ACCENT);
        txt2.getStyleClass().addAll(Styles.TEXT, Styles.ACCENT);
        txt3.getStyleClass().addAll(Styles.TEXT, Styles.ACCENT);
        vbox.sceneProperty().addListener((obs, oldScene, newScene) -> {
            Platform.runLater(() -> {
                Stage stage = (Stage) newScene.getWindow();
                stage.setOnCloseRequest(e -> {
                    Platform.exit();
                    System.out.println("Program Stopped");
                });
            });
        });
    }
}
