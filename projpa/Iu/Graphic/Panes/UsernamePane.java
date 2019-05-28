package projpa.Iu.Graphic.Panes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import projpa.GameLogic.GameLogic;
import projpa.Iu.Graphic.Constants;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UsernamePane extends StackPane implements Constants, PropertyChangeListener {

    private GameLogic game;

    public UsernamePane(GameLogic game) {
        this.game = game;
        this.game.addPropertyChangeListener(this);
        setupComponents();
        propertyChange(null);
    }

    private void setupComponents(){
        Image imgReset = new Image(getClass().getResourceAsStream("..\\Images\\trash.png"));
        ImageView imgVwReset = new ImageView(imgReset);
        imgVwReset.setFitWidth(20);
        imgVwReset.setFitHeight(20);

        ImageView ChooseUsernameSceneUndo = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\undo.png")));
        ChooseUsernameSceneUndo.setFitHeight(20);
        ChooseUsernameSceneUndo.setFitWidth(20);

        Button btnTurnBackUserNameScene = new Button("Go Back", ChooseUsernameSceneUndo);
        btnTurnBackUserNameScene.getStyleClass().add("DefaultButton");

        Image imgPlayUsername = new Image(getClass().getResourceAsStream("..\\Images\\play-button-inside-a-circle.png"));
        ImageView imgVwPlayUsername = new ImageView(imgPlayUsername);
        imgVwPlayUsername.setFitHeight(20);
        imgVwPlayUsername.setFitWidth(20);

        Button btnSubmitName = new Button();
        btnSubmitName.setGraphic(imgVwPlayUsername);
        btnSubmitName.getStyleClass().add("GreenButton");
        btnSubmitName.setDisable(true);

        Button btnResetUsername = new Button();
        btnResetUsername.setGraphic(imgVwReset);
        btnResetUsername.getStyleClass().add("RedButton");

        TextField txtAreaUserName = new TextField();
        txtAreaUserName.getStyleClass().add("TextArea");
        txtAreaUserName.setPrefHeight(30);
        txtAreaUserName.setPrefWidth(210);
        txtAreaUserName.setFont(Font.font("Death Star", FontWeight.MEDIUM, 15));

        Label lblChooseUserName = new Label("Choose Your Username");
        lblChooseUserName.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblChooseUserName.setTextFill(Color.web("#ffffff"));

        HBox topHboxChooseUsernameScene = new HBox(lblChooseUserName);
        topHboxChooseUsernameScene.setAlignment(Pos.CENTER);

        HBox backHboxChooseUsernameScene = new HBox(txtAreaUserName, btnResetUsername, btnSubmitName, btnTurnBackUserNameScene);
        backHboxChooseUsernameScene.setAlignment(Pos.CENTER);
        backHboxChooseUsernameScene.setSpacing(10);

        VBox mainVboxUsernameScene = new VBox(topHboxChooseUsernameScene, backHboxChooseUsernameScene);
        mainVboxUsernameScene.setAlignment(Pos.CENTER);
        mainVboxUsernameScene.setSpacing(20);
        mainVboxUsernameScene.getStyleClass().add("ChooseVBox");

        btnTurnBackUserNameScene.setOnMouseClicked(e -> {
            this.game.endGame();
        });

        btnSubmitName.setOnMouseClicked(e -> {
            this.game.newUser(txtAreaUserName.getText());
            this.game.Inputbegining();
        });

        btnResetUsername.setOnMouseClicked(e -> {
            txtAreaUserName.clear();
            btnSubmitName.setDisable(true);
        });

        txtAreaUserName.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 10 ? change : null));

        txtAreaUserName.setOnKeyReleased(e -> {
            if (txtAreaUserName.getLength() > 0){
                btnSubmitName.setDisable(false);
            }
            else{
                btnSubmitName.setDisable(true);
            }
        });

        this.getChildren().add(mainVboxUsernameScene);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setVisible(this.game.inAwaitBeginning());
    }
}
