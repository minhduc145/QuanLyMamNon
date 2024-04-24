package hp.mnhp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Loi {
    @FXML
    AnchorPane eStage;
    @FXML
    Button okbtn;


    @FXML
    public void closestg() {
        Stage stage = (Stage) eStage.getScene().getWindow();
        stage.close();
    }

}
