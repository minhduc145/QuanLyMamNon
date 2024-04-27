package hp.mnhp;

import DAO.*;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HS implements Initializable {
    List<hsModel> ds = new ArrayList<>();
    List<danhhieuModel> tempdh = new ArrayList<>();
    hsDao hdao = new hsDao();
    linhtinhDao linhtinh = new linhtinhDao();
    int lastIndex;
    danhhieuModel temp;
    hsModel temp1;
    @FXML
    Text gvcn;
    @FXML
    Button suaBtn, luuBtn, huyBtn, sBtn, m1, p1;
    @FXML
    ListView<hsModel> list;
    @FXML
    TableView<danhhieuModel> listdh;
    @FXML
    TableView<phModel> phtable;
    @FXML
    TextField search, hoten, noisinh, dc, nnh, ename;
    @FXML
    ComboBox<String> gt;
    @FXML
    ComboBox<String> tt;
    @FXML
    ComboBox<LopModel> lopds;
    @FXML
    ComboBox<danhhieuModel> dhds;
    @FXML
    DatePicker ngs;
    @FXML
    TableColumn col1, col2, phcol1, phcol2, phcol3, phcol4, phcol5;

    void setTable1(List<danhhieuModel> ds) {
        col1.setCellValueFactory(new PropertyValueFactory<danhhieuModel, String>("nam"));
        col2.setCellValueFactory(new PropertyValueFactory<danhhieuModel, String>("danhhieu"));
        listdh.getItems().setAll(ds);
    }

    void setTable2(List<phModel> ds) {
        for (phModel d : ds) {
            System.out.println(d.getHoten());
        }
        phcol1.setCellValueFactory(new PropertyValueFactory<phModel, String>("hoten"));
        phcol2.setCellValueFactory(new PropertyValueFactory<phModel, String>("vaitro"));
        phcol3.setCellValueFactory(new PropertyValueFactory<phModel, String>("sdt"));
        phcol4.setCellValueFactory(new PropertyValueFactory<phModel, String>("diachi"));
        phcol5.setCellValueFactory(new PropertyValueFactory<phModel, String>("nghe"));
        phtable.getItems().setAll(ds);
    }

    void setField(hsModel hs) {
        linhtinh.load();
        gvcn.setText(hs.getGvcnString());
        hoten.setText(hs.getHoten());
        noisinh.setText(hs.getNoisinh());
        dc.setText(hs.getDiachi());
        nnh.setText(hs.getNamnhaphoc());
        ngs.setValue(hs.getNgaysinh());
        suaBtn.setDisable(true);
        if (User.idQuyen.equals("0")) {
            suaBtn.setDisable(false);

        } else {
            for (LopModel.GVCN e : hs.getGvcn()) {
                if (e != null && (User.idCBNV.equals(e.getId()))) {
                    if (!luuBtn.isVisible()) {
                        suaBtn.setVisible(true);
                        suaBtn.setDisable(false);
                        break;
                    }
                }
            }
        }
        gt.getItems().setAll("Nam", "Nữ");
        if (hs.isLanam()) {
            gt.setValue("Nam");
        } else {
            gt.setValue("Nữ");
        }
        lopds.getItems().setAll(linhtinh.dsl);
        for (LopModel lop : linhtinh.dsl) {
            if (hs.getIdLop() != null && hs.getIdLop().equals(lop.getId())) {
                lopds.setValue(lop);
                break;
            }
            lopds.setValue(lopds.getItems().get(0));

        }

        tt.getItems().setAll("Đang theo học", "Không đang theo học");
        if (hs.isDangtheohoc() == true) {
            tt.setValue("Đang theo học");
        } else {
            tt.setValue("Không đang theo học");
        }
        search.setPromptText("Tìm trong " + ds.size() + " Học sinh");
        setTable1(hs.getDanhhieu());
        dhds.getItems().setAll(linhtinh.dsdh);
        setTable2(hs.getPh());

    }

    void select() {
        hsModel hs = list.getItems().get(lastIndex);
        if (hs != null) {
            list.getSelectionModel().select(lastIndex);
            list.getFocusModel().focus(lastIndex);
            setField(hs);
            setTable1(hs.getDanhhieu());
            setTable2(hs.getPh());
        } else {
            hsModel hs1 = new hsModel();
            setField(hs1);
        }
    }

    void reload() {
        ds = hdao.getdshs();
        list.getItems().setAll(ds);

    }

    @FXML
    void editbtnnotshow() {
        if (suaBtn != null) {
            suaBtn.setVisible(false);

        }
    }

    @FXML
    void editbtnshow() {
        if (suaBtn != null) {
            suaBtn.setVisible(true);

        }
    }

    @FXML
    void xoa1() {
        if (!listdh.getSelectionModel().isEmpty() && !listdh.getSelectionModel().getSelectedItems().isEmpty()) {
            hsModel hs = list.getSelectionModel().getSelectedItem();
            danhhieuModel e = listdh.getSelectionModel().getSelectedItem();
            temp = e;
            temp1 = hs;
            listdh.getItems().remove(e);
        }

    }

    @FXML
    void xoa1(hsModel hs, danhhieuModel e) throws Exception {


        Connection cn = (DbHelper.getInstance()).getConnection();
        String SQL = "delete from XepLoai where idTre = ? and year(NamHoc) = ?";
        PreparedStatement stmt = cn.prepareStatement(SQL);
        stmt.setString(1, hs.getId());
        stmt.setString(2, e.getNam());
        stmt.executeUpdate();


    }


    @FXML
    void them1() {
        if (!ename.getText().isEmpty() && !dhds.getSelectionModel().isEmpty()) {
            danhhieuModel dhm = new danhhieuModel();
            dhm.setId(list.getItems().get(lastIndex).getId());
            dhm.setIddh(dhds.getSelectionModel().getSelectedItem().getIddh());
            dhm.setDanhhieu(dhds.getSelectionModel().getSelectedItem().getDanhhieu());
            dhm.setNam(ename.getText());
            tempdh.add(dhm);
            col1.setCellValueFactory(new PropertyValueFactory<danhhieuModel, String>("nam"));
            col2.setSortable(false);
            col2.setCellValueFactory(new PropertyValueFactory<danhhieuModel, String>("danhhieu"));
            listdh.getItems().add(dhm);
        }
    }

    boolean them1(List<danhhieuModel> ds) throws Exception {
        boolean i = true;

        for (danhhieuModel d : ds) {
            for (char ch : d.getNam().toCharArray()) {
                if (Character.isLetter(ch)) {
                    throw new Exception();
                }
            }
            Connection cn = (DbHelper.getInstance()).getConnection();
            String SQL = "insert Xeploai values(?,?,?)";
            PreparedStatement stmt = cn.prepareStatement(SQL);
            stmt.setString(1, d.getIddh());
            stmt.setString(2, d.getId());
            stmt.setString(3, d.getNam());
            if (stmt.executeUpdate() != 1) {
                return false;
            }

        }
        return i;
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

    void setEditable(boolean i) {
        hoten.setEditable(i);
        noisinh.setEditable(i);
        dc.setEditable(i);
        nnh.setEditable(i);
        ename.setVisible(i);
        dhds.setVisible(i);
        m1.setVisible(i);
        p1.setVisible(i);

    }

    @FXML
    void onClickhuyBtn() {
        huyBtn.setVisible(false);
        luuBtn.setVisible(false);
        suaBtn.setVisible(true);
        setEditable(false);
        reload();
        select();
    }

    @FXML
    void onClickexit() {
        Platform.exit();
    }

    @FXML
    void onClickluuBtn() {
        boolean ch = false;

        try {
            if (temp != null && temp1 != null) {
                xoa1(temp1, temp);
                temp1 = null;
                temp = null;
                ch = true;
            }
            if (!tempdh.isEmpty()) {
                if (them1(tempdh)) ch = true;
                else throw new Exception();
                tempdh.clear();
            }
            hsModel hs = list.getSelectionModel().getSelectedItem();
            ch = hdao.upHs(hs.getId(), hoten.getText(), lopds.getSelectionModel().getSelectedItem().getId(), dc.getText(), nnh.getText(), gt.getSelectionModel().getSelectedItem().equals("Nam"), tt.getSelectionModel().getSelectedItem().equals("Đang theo học"), ngs.getValue(), noisinh.getText());
            if (ch == true) {
                AlertMessage.infoBox(null, "Thông báo", "Cập nhật thành công");
                reload();
                select();
                setEditable(false);
                huyBtn.setVisible(false);
                luuBtn.setVisible(false);
                suaBtn.setVisible(true);
            }

        } catch (Exception e) {
            AlertMessage.erBox(null, "Thông báo", "Cập nhật không thành công");
            reload();
            select();
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
        huyBtn.getStyleClass().add(Styles.DANGER);
        luuBtn.getStyleClass().add(Styles.SUCCESS);
        ds = hdao.getdshs();
        list.getItems().setAll(ds);
        search.getStyleClass().addAll(
                Styles.ROUNDED
        );
        sBtn.getStyleClass().addAll(
                Styles.ROUNDED, Styles.BUTTON_ICON
        );
        Image image = new Image(getClass().getResourceAsStream("UI/loupe.png"), sBtn.getWidth(), sBtn.getHeight(), false, true);
        sBtn.setGraphic(new imgFotBtn().getImg(sBtn, image, 20, 20));
        setEditable(false);
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                lastIndex = list.getSelectionModel().getSelectedIndex();
                select();

            }
        });
    }
}
