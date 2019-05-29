package projpa.Iu.Graphic.Panes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.application.Platform;
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
import projpa.Iu.Graphic.Buttons.GreenButton;
import projpa.Iu.Graphic.Buttons.RedButton;
import projpa.Iu.Graphic.Constants;
import static projpa.Iu.Graphic.Constants.ACCEPT_IMAGE;
import static projpa.Iu.Graphic.Constants.CANCEL_IMAGE;

public class EndGamePane extends StackPane implements Constants,PropertyChangeListener{

    private GameLogic game;
    Label scorelabel;
    
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
    
    public BorderPane interfacea(StackPane myStackPane){
    
        BorderPane paneLayoutRoomChoice = new BorderPane();
        
        VBox paneLayoutendgame= new VBox();
    
        Image loadyesimg = new Image(getClass().getResourceAsStream("..\\" + ACCEPT_IMAGE));
        GreenButton btnyes = new GreenButton("Yes", loadyesimg, 20, 20, 120, 40);

        Image loadnoimg = new Image(getClass().getResourceAsStream("..\\" + CANCEL_IMAGE));
        RedButton btnno = new RedButton("No", loadnoimg, 20, 20, 120, 40);

        Label lblChooseUserName = new Label("Game Over - Play Again");
        lblChooseUserName.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblChooseUserName.setTextFill(Color.web("#ffffff"));
        lblChooseUserName.setAlignment(Pos.CENTER);
        

        scorelabel = new Label("Score: " + this.game.getPoints());
        scorelabel.setFont(Font.font("Death Star", FontWeight.MEDIUM, 28));
        scorelabel.setTextFill(Color.web("#ffffff"));
        scorelabel.setAlignment(Pos.CENTER);
        
        
        HBox backHboxChooseUsernameScene = new HBox(btnyes, btnno);
        backHboxChooseUsernameScene.setAlignment(Pos.CENTER);
        backHboxChooseUsernameScene.setSpacing(10);

        paneLayoutendgame.getChildren().addAll(lblChooseUserName,scorelabel,backHboxChooseUsernameScene);
        paneLayoutendgame.setAlignment(Pos.CENTER);
        
        VBox mainVboxUsernameScene = new VBox(paneLayoutendgame);
        mainVboxUsernameScene.setAlignment(Pos.CENTER);
        mainVboxUsernameScene.setSpacing(20);
        mainVboxUsernameScene.getStyleClass().add("ChooseVBox");
        
        
        btnyes.setOnMouseClicked(e -> {
            this.game.newGame();
        });

        btnno.setOnMouseClicked(e -> {
            this.game.startGame2();
            this.setVisible(false);
        });
        
        paneLayoutRoomChoice.setCenter(paneLayoutendgame);
        
        this.getChildren().add(mainVboxUsernameScene);


        return paneLayoutRoomChoice;
        
    }
    
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

       if(this.game.inGameOverState())
            scorelabel.setText("Score: " + this.game.getPoints()); 
        
       setVisible(this.game.inGameOverState());
       
    }
    
}
