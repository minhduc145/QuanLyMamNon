package hp.mnhp;

import DAO.LopDao;
import DAO.imgFotBtn;
import Model.CBNVModule;
import Model.LopModel;
import atlantafx.base.controls.Notification;
import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Styles;
import atlantafx.base.util.Animations;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.ListViewSkin;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LopChitiet implements Initializable {
    LopDao ldao = new LopDao();
    List<LopModel> dslop = ldao.getDSLop();
    @FXML
    AnchorPane ap;
    @FXML
    ListView<LopModel> list;
    @FXML
    Button suaBtn, luuBtn, huyBtn, sBtn;
    @FXML
    TextField search;
    @FXML
    Text tenlop, sl1, sl2;
    @FXML
    TabPane t;


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
    void onClickexit() {
        Platform.exit();
    }

    @FXML
    void search() {
        if (search.getText() != null || !search.getText().toString().isEmpty()) {
            List<LopModel> dstk = new ArrayList<>();
            for (LopModel scb : dslop) {
                if (scb.getTenLop().toLowerCase().contains(search.getText().trim().toLowerCase())) {
                    dstk.add(scb);
                }
            }
            list.getItems().setAll(dstk);
        } else {
            list.getItems().setAll(dslop);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ap.sceneProperty().addListener((obs, oldScene, newScene) -> {
            Platform.runLater(() -> {
                Stage stage = (Stage) newScene.getWindow();
                stage.setOnCloseRequest(e -> {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("DSLop.fxml"));
                        Stage stage1 = new Stage();
                        stage1.setTitle("Quản lý Lớp");
                        stage1.setScene(new Scene(root));
                        stage1.getScene().getStylesheets().add(new CupertinoLight().getUserAgentStylesheet());
                        stage1.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    ap.getScene().getWindow().hide();
                });
            });
        });
        t.getStyleClass().add(Styles.BORDERED);
        search.getStyleClass().addAll(
                Styles.ROUNDED
        );
        Image image = new Image(getClass().getResourceAsStream("UI/loupe.png"), sBtn.getWidth(), sBtn.getHeight(), false, true);
        sBtn.setGraphic(new imgFotBtn().getImg(sBtn, image, 20, 20));
        sBtn.getStyleClass().addAll(
                Styles.ROUNDED, Styles.BUTTON_ICON
        );
        search.setPromptText("Tìm trong " + dslop.size() + " Lớp học");
        huyBtn.getStyleClass().add(Styles.DANGER);
        luuBtn.getStyleClass().add(Styles.SUCCESS);
        suaBtn.setVisible(false);
        list.getItems().setAll(dslop);
        list.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                                   @Override
                                   public void handle(javafx.scene.input.MouseEvent event) {
                                       LopModel lop = list.getSelectionModel().getSelectedItem();
                                       if (lop != null) {
                                           tenlop.setText(lop.getTenLop());
                                           sl1.setText(String.valueOf(lop.getSotre()));
                                           sl2.setText(String.valueOf(lop.getDsGVCN().size()));
                                       }
                                   }
                               }
        );

    }

}
