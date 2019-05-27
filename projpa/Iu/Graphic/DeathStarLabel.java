package projpa.Iu.Graphic;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DeathStarLabel extends Label {

    public DeathStarLabel(int size) {
        this.setFont(Font.font("Death Star", FontWeight.MEDIUM, size));
        this.setTextFill(Color.web("#ffffff"));
    }

    public DeathStarLabel(String text, int size) {
        super(text);
        this.setFont(Font.font("Death Star", FontWeight.MEDIUM, size));
        this.setTextFill(Color.web("#ffffff"));
    }

    public DeathStarLabel(String text, Node graphic, int size) {
        super(text, graphic);
        this.setFont(Font.font("Death Star", FontWeight.MEDIUM, size));
        this.setTextFill(Color.web("#ffffff"));
    }
}
