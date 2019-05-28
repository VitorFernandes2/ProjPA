package projpa.Iu.Graphic.Panes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import projpa.GameLogic.GameLogic;
import projpa.Iu.Graphic.Constants;

public class EndGamePane extends StackPane implements Constants,PropertyChangeListener{

    private GameLogic game;
    
    public EndGamePane(GameLogic Game) {
        this.game = Game;
        this.game.addPropertyChangeListener(this);
        setupComponents();
        propertyChange(null);
    }
    
    private void setupComponents() {

        ObservableList firstStackPaneList = this.getChildren();
        firstStackPaneList.add(interfacea(this));

    }
    
    public VBox interfacea(StackPane myStackPane){
    
        VBox paneLayoutendgame= new VBox();
    
        Button btnyes = new Button();
        btnyes.getStyleClass().add("GreenButton");

        Button btnno = new Button();
        btnno.getStyleClass().add("RedButton");


        Label lblChooseUserName = new Label("Game Over - Play Again");
        lblChooseUserName.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblChooseUserName.setTextFill(Color.web("#ffffff"));


        HBox backHboxChooseUsernameScene = new HBox(lblChooseUserName, btnyes, btnno);
        backHboxChooseUsernameScene.setAlignment(Pos.CENTER);
        backHboxChooseUsernameScene.setSpacing(10);


        btnyes.setOnMouseClicked(e -> {
            
        });

        btnno.setOnMouseClicked(e -> {
            
        });

        return paneLayoutendgame;
        
    }
    
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
    
}
