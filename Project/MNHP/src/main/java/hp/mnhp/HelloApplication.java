package hp.mnhp;

import atlantafx.base.theme.CupertinoLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class HelloApplication extends Application {
    void handlerCloserq(WindowEvent event) {
        System.out.println("AAAAAAAAAAAA");
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(new CupertinoLight().getUserAgentStylesheet());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            handlerCloserq(event);
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}