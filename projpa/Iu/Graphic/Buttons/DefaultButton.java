package projpa.Iu.Graphic.Buttons;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class DefaultButton extends Button {

    public DefaultButton(String Name, ImageView image) {

        super(Name);

        if (image != null){

            image.setFitHeight(15);
            image.setFitWidth(15);

            this.setGraphic(image);

            this.getStyleClass().add("DefaultButton");

        }

        this.setMinHeight(30);
        this.setMinWidth(50);

    }

}
