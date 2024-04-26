package DAO;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class imgFotBtn {
    public ImageView getImg(Button sBtn, Image image, float width, float height) {
        BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(sBtn.getWidth(), sBtn.getHeight(), true, true, true, false));
        ImageView imgView = new ImageView(image);
        imgView.setFitWidth(width);
        imgView.setFitHeight(height);
        return imgView;
    }
}
