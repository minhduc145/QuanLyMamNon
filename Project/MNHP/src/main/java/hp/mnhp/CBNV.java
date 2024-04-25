package hp.mnhp;

import DAO.CBNVDao;
import DAO.linhtinhDao;
import Model.User;
import Model.*;
import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Styles;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class CBNV implements Initializable {
    linhtinhDao linhtinh = new linhtinhDao();
    CBNVDao cbdao = new CBNVDao();
    List<CBNVModule> ds = new ArrayList<>();

    @FXML
    ListView<CBNVModule> list;
    @FXML
    Text id;
    @FXML
    TextField hsl, tdhv, search, hoten, tn, noisinh, dc, sdt, email, cccd, editpw;
    @FXML
    ComboBox<String> gt;
    @FXML
    ComboBox<QuyenModel> quyen;
    @FXML
    ComboBox<ChucVuModel> cv;
    @FXML
    ComboBox<TrangThaiModel> tt;
    @FXML
    ComboBox<LopModel> lopcn;
    @FXML
    ImageView img;
    @FXML
    Button suaBtn, luuBtn, huyBtn, sBtn;

    @FXML
    DatePicker date, bd;

    void setEditable(boolean i) {
        hoten.setEditable(i);
        noisinh.setEditable(i);
        tn.setEditable(i);
        dc.setEditable(i);
        sdt.setEditable(i);
        email.setEditable(i);
        cccd.setEditable(i);
        date.setEditable(i);
        bd.setEditable(i);
        editpw.setEditable(i);
        tdhv.setEditable(i);
        hsl.setEditable(i);
    }

    @FXML
    void onClickhuyBtn() {
        huyBtn.setVisible(false);
        luuBtn.setVisible(false);
        suaBtn.setVisible(true);
        setEditable(false);
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
    void onClickexit() {
//        List<Window> open = (List<Window>) Stage.getWindows().stream();
//        for (Window w : open) {
        Platform.exit();
//        }
    }

    @FXML
    void onClickluuBtn() {
        suaBtn.setVisible(true);
        CBNVModule c = list.getSelectionModel().getSelectedItem();
        boolean i = cbdao.updateCBNV(c.getIdCBNV(), quyen.getSelectionModel().getSelectedItem().getId(), editpw.getText(), hoten.getText(), cv.getSelectionModel().getSelectedItem().getId(), noisinh.getText(), tn.getText(), hsl.getText(), dc.getText(), sdt.getText(), email.getText(), date.getValue(), cccd.getText(), tt.getSelectionModel().getSelectedItem().getId(), gt.getSelectionModel().getSelectedItem().toString(), lopcn.getSelectionModel().getSelectedItem().getId(), bd.getValue(), tdhv.getText());
        if (i) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("success.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.getScene().getStylesheets().add(new CupertinoLight().getUserAgentStylesheet());
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ds = new CBNVDao().getDSCB();
            list.getItems().setAll(ds);
            setEditable(false);
            huyBtn.setVisible(false);
            luuBtn.setVisible(false);
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("loi.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.getScene().getStylesheets().add(new CupertinoLight().getUserAgentStylesheet());
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setEditable(true);
            huyBtn.setVisible(true);
            luuBtn.setVisible(true);
        }


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
        ds = cbdao.getDSCB();
        search.setPromptText("Tìm trong " + ds.size() + " CBNV.");
        huyBtn.getStyleClass().add(Styles.DANGER);
        luuBtn.getStyleClass().add(Styles.SUCCESS);
        suaBtn.setVisible(false);
        linhtinh.load();
        gt.getItems().add("Nam");
        gt.getItems().add("Nữ");
        quyen.getItems().setAll(linhtinh.dsq);
        tt.getItems().setAll(linhtinh.dstt);
        cv.getItems().setAll(linhtinh.dschv);
        lopcn.getItems().setAll(linhtinh.dsl);
        list.getItems().setAll(ds);

        ListViewSkin<CBNVModule> skin = new ListViewSkin<>(list);
        List<String> link = new ArrayList<>();
        link.add("UI/teacher.png");
        link.add("UI/teacher1.png");
        link.add("UI/teacher2.png");
        link.add("UI/teacher3.png");
        list.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                CBNVModule cb = list.getSelectionModel().getSelectedItem();
                int random = 0 + (int) ((3 - 0 + 1) * Math.random());
                String url = link.get(random);
                Image a = new Image(getClass().getResourceAsStream(url));
                img.setImage(a);
                if (User.idCBNV.equals(cb.getIdCBNV()) || User.idQuyen.equals("0")) {
                    if (!luuBtn.isVisible()) {
                        suaBtn.setVisible(true);
                        suaBtn.setDisable(false);
                    }
                    editpw.setText(cb.getMatKhau());
                } else {
                    suaBtn.setDisable(true);
                    editpw.setText(null);
                }
                if (cb.isGTNam()) {
                    gt.setValue("Nam");
                } else {
                    gt.setValue("Nữ");
                }

                for (QuyenModel q : linhtinh.dsq) {
                    if (cb.getIdQuyen() != null && cb.getIdQuyen().equals(q.getId())) {
                        quyen.setValue(q);
                        break;
                    }
                }

//                lopcn.setText("Khong");

                for (LopModel lop : linhtinh.dsl) {

                    if (cb.getIdLop() != null && cb.getIdLop().equals(lop.getId())) {
                        lopcn.setValue(lop);
                        break;
                    }
                    lopcn.setValue(lopcn.getItems().get(0));

                }

                for (TrangThaiModel trt : linhtinh.dstt) {
                    if (cb.getIdTinhTrang() != null && cb.getIdTinhTrang().equals(trt.getId())) {
                        tt.setValue(trt);
                        break;
                    }
                }

                for (ChucVuModel c : linhtinh.dschv) {
                    if (cb.getIdChucVu() != null && cb.getIdChucVu().equals(c.getId())) {
                        cv.setValue(c);
                        break;
                    }
                }
                hsl.setText(Double.toString(cb.getHsl()));
                tdhv.setText(cb.getTDHV());
                date.setValue(cb.getNgayVaoLam());
                bd.setValue(cb.getNgaySinh());
                id.setText(cb.getIdCBNV());
                hoten.setText(cb.getHoten());
                noisinh.setText(cb.getNoiSinh());
                tn.setText(Double.toString(cb.getPctn()));
                dc.setText(cb.getDiaChiTT());
                sdt.setText(cb.getSDT());
                email.setText(cb.getEmail());
                cccd.setText(cb.getSoCCCD());
            }
        });
        sBtn.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if (search.getText() != null || !search.getText().toString().isEmpty()) {
                    List<CBNVModule> dstk = new ArrayList<>();
                    for (CBNVModule scb : ds) {
                        if (scb.getHoten().toLowerCase().contains(search.getText().trim().toLowerCase())) {
                            dstk.add(scb);
                        }
                    }
                    list.getItems().setAll(dstk);
                } else {
                    list.getItems().setAll(ds);
                }


            }
        });
    }
}
