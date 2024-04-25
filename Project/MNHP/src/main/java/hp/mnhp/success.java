package hp.mnhp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class success {
    @FXML
    GridPane info;
    @FXML
    Button okbtn;


    @FXML
    public void okButton() {
        Stage stage = (Stage) info.getScene().getWindow();
        stage.close();
    }

}
