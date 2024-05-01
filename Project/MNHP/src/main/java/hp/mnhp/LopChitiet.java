package hp.mnhp;

import DAO.DbHelper;
import DAO.LineNumbersCellFactory;
import DAO.LopDao;
import DAO.imgFotBtn;
import Model.CBNVModule;
import Model.LopModel;
import Model.hsModel;
import atlantafx.base.controls.Notification;
import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Styles;
import atlantafx.base.util.Animations;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.ListViewSkin;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
    TableView<hsModel> hstab;
    @FXML
    TableColumn stths, hths, nshs, gths, sttgv, htgv, tkgv, nsgv;
    @FXML
    TableView<CBNVModule> gvtab;


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
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LopModel>() {
            @Override
            public void changed(ObservableValue<? extends LopModel> observable, LopModel oldValue, LopModel newValue) {
                LopModel lop = list.getSelectionModel().getSelectedItem();
                if (lop != null) {
                    tenlop.setText(lop.getTenLop());
                    sl1.setText(String.valueOf(lop.getSotre()));
                    sl2.setText(String.valueOf(lop.getDsGVCN().size()));

                    List<hsModel> hsl = new ArrayList<>();
                    try {
                        Connection cn = (DbHelper.getInstance()).getConnection();
                        String SQL = "SELECT Tre.*\n" +
                                "FROM     Tre\n" +
                                "where idLop = ?";
                        PreparedStatement stmt = cn.prepareStatement(SQL);
                        stmt.setString(1, lop.getId());
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                            hsModel h = new hsModel();
                            h.setHoten(rs.getString("hoten"));
                            h.setLanam(rs.getBoolean("lanam"));
                            if (rs.getDate("ngaysinh") != null) {
                                LocalDate newDate2 = rs.getDate("ngaysinh").toLocalDate();
                                h.setNgaysinh(newDate2);
                            }
                            hsl.add(h);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    stths.setSortable(false);
                    stths.setCellFactory(new LineNumbersCellFactory<>());

                    hths.setCellValueFactory(new PropertyValueFactory<hsModel, String>("hoten"));
                    nshs.setCellValueFactory(new PropertyValueFactory<hsModel, String>("ngaysinh"));
                    gths.setCellValueFactory(new PropertyValueFactory<hsModel, String>("lanam"));
                    hstab.getItems().setAll(hsl);

                }

                List<CBNVModule> dsgv = new ArrayList<>();
                try {
                    Connection cn = (DbHelper.getInstance()).getConnection();
                    String SQL = "SELECT *\n" +
                            "FROM     CBNV\n" +
                            "where idLop = ?";
                    PreparedStatement stmt = cn.prepareStatement(SQL);
                    stmt.setString(1, lop.getId());
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        CBNVModule h = new CBNVModule();
                        h.setHoten(rs.getString("hoten"));
                        h.setIdCBNV(rs.getString("idcbnv"));
                        if (rs.getDate("ngaysinh") != null) {
                            LocalDate newDate2 = rs.getDate("ngaysinh").toLocalDate();
                            h.setNgaySinh(newDate2);
                        }
                        dsgv.add(h);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sttgv.setSortable(false);
                sttgv.setCellFactory(new LineNumbersCellFactory<>());
                htgv.setCellValueFactory(new PropertyValueFactory<CBNVModule, String>("hoten"));
                nsgv.setCellValueFactory(new PropertyValueFactory<CBNVModule, String>("NgaySinh"));
                tkgv.setCellValueFactory(new PropertyValueFactory<CBNVModule, String>("idCBNV"));
                gvtab.getItems().setAll(dsgv);
            }
        });

    }

}
