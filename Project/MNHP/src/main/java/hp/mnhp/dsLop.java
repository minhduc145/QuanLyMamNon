package hp.mnhp;


import DAO.LineNumbersCellFactory;
import DAO.LopDao;
import DAO.imgFotBtn;
import Model.CBNVModule;
import Model.LopModel;
import Model.User;
import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Styles;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class dsLop implements Initializable {
    LopDao ldao = new LopDao();
    @FXML
    AnchorPane ap;
    @FXML
    Button sBtn;
    @FXML
    TextField search;
    @FXML
    TableView<LopModel> table;
    @FXML
    TableColumn col1, col2, col3, col4;
    @FXML
    Hyperlink chitiet;


    void setTable(List<LopModel> ds) {
        col1.setSortable(false);
        col1.setCellFactory(new LineNumbersCellFactory<>());
        col2.setCellValueFactory(new PropertyValueFactory<LopModel, String>("TenLop"));
        col3.setCellValueFactory(new PropertyValueFactory<LopModel, String>("sotre"));
        col4.setCellValueFactory(new PropertyValueFactory<LopModel, String>("tenGVCN"));
        table.getItems().setAll(ds);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<LopModel> ds = ldao.getDSLop();
        sBtn.getStyleClass().addAll(Styles.ROUNDED, Styles.BUTTON_ICON);
        Image image = new Image(getClass().getResourceAsStream("UI/loupe.png"), sBtn.getWidth(), sBtn.getHeight(), false, true);
        sBtn.setGraphic(new imgFotBtn().getImg(sBtn, image, 20, 20));
        search.getStyleClass().addAll(Styles.ROUNDED);
        search.setPromptText("Tìm trong " + ds.size() + " Lớp học");
        setTable(ds);
        sBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if (search.getText() != null || !search.getText().toString().isEmpty()) {
                    List<LopModel> dstk = new ArrayList<>();
                    for (LopModel scb : ds) {
                        if (scb.getTenLop().toLowerCase().contains(search.getText().trim().toLowerCase())) {
                            dstk.add(scb);
                        }
                    }
                    setTable(dstk);
                } else {
                    setTable(ds);
                }
            }
        });

        chitiet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Lop.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Quản lý Lớp");
                    stage.setScene(new Scene(root));
                    stage.getScene().getStylesheets().add(new CupertinoLight().getUserAgentStylesheet());
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ap.getScene().getWindow().hide();
            }
        });
    }
}

