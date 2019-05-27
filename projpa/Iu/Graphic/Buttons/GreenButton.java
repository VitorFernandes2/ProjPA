package projpa.Iu.Graphic.Buttons;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import projpa.Iu.Graphic.ImageView.ImageViewIcon;

public class GreenButton extends Button{

    private ImageView imgVw;

    public GreenButton(String Name, Image image, double widthImg, double heightImg, double width, double height) {

        super(Name);

        if (image != null){

            imgVw = new ImageViewIcon(image, heightImg, widthImg);
            this.setGraphic(imgVw);
            this.getStyleClass().add("GreenButton");

        }

        this.setPrefWidth(width);
        this.setPrefHeight(height);

    }

}
