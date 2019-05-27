package projpa.Iu.Graphic.Buttons;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class GreenButton extends Button{

    public GreenButton(String Name, ImageView image) {

        super(Name);

        if (image != null){

            image.setFitHeight(15);
            image.setFitWidth(15);

            this.setGraphic(image);

            this.getStyleClass().add("GreenButton");

        }

        this.setMinHeight(30);
        this.setMinWidth(50);

    }

}