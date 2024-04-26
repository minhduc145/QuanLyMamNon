package hp.mnhp;

import DAO.DbHelper;
import Model.User;
import atlantafx.base.theme.CupertinoLight;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    boolean i = false;

    @FXML
    Parent root;
    @FXML
    Button login_loginBtn;
    @FXML
    TextField login_username;
    @FXML
    TextField login_password;
    @FXML
    ProgressBar p;

    @FXML
    void onClick() {
        p.setVisible(true);
        String usn = login_username.getText().toString();
        String psw = login_password.getText().toString();
        i = DbHelper.getInstance().DangNhap(usn, psw);


    }

    @FXML
    protected void startProcess() {

        // Create a background Task
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                p.setVisible(true);
                Thread.sleep(1000); // Pause briefly

                onClick();
                return null;
            }
        };
        task.setOnFailed(wse -> {
            wse.getSource().getException().printStackTrace();

        });
        task.setOnSucceeded(wse -> {
            if (i) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Main");
                    stage.setScene(new Scene(root));
                    // stage.setMaximized(true);
                    stage.getScene().getStylesheets().add(new CupertinoLight().getUserAgentStylesheet());
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                login_loginBtn.getScene().getWindow().hide();
            } else {
                p.setVisible(false);
                try {
                    root = FXMLLoader.load(getClass().getResource("loi.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("");
                    stage.setScene(new Scene(root, 261, 130));
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.getScene().getStylesheets().add(new CupertinoLight().getUserAgentStylesheet());
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        p.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}




