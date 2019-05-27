package projpa.Iu.Graphic.Buttons;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import projpa.Iu.Graphic.ImageView.ImageViewIcon;

public class RedButton extends Button {

    private ImageView imgVw;

    public RedButton(String Name, Image image, double widthImg, double heightImg, double width, double height) {

        super(Name);

        if (image != null){

            imgVw = new ImageViewIcon(image, heightImg, widthImg);
            this.setGraphic(imgVw);
            this.getStyleClass().add("RedButton");

        }

        this.setPrefWidth(width);
        this.setPrefHeight(height);

    }
}
