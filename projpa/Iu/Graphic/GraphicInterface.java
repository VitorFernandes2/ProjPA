package projpa.Iu.Graphic;

import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projpa.GameLogic.GameLogic;

public class GraphicInterface implements Constants{

    private Stage primaryStage;
    private GameLogic game;

    public GraphicInterface(Stage primaryStage, GameLogic game) {
        this.primaryStage = primaryStage;
        this.game = game;
    }

    public void run(){
        primaryStage.setTitle("Destination Earth");

        //music temp -> CHANGE LOCATION

        Media sound = new Media(getClass().getResource("Images\\space.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        Media soundgame = new Media(getClass().getResource("Images\\gameplaymusicship.mp3").toExternalForm());
        MediaPlayer mediaPlayergame = new MediaPlayer(soundgame);
        mediaPlayergame.setCycleCount(MediaPlayer.INDEFINITE);

        int width = (int) Screen.getPrimary().getBounds().getWidth();
        int height = (int) Screen.getPrimary().getBounds().getHeight();

        PaneOrganizer paneOrganizer = new PaneOrganizer(this.game);
        Scene scene = new Scene(paneOrganizer.getRoot());
        scene.getStylesheets().add(GraphicInterface.class.getResource("Css\\main.css").toExternalForm());
        Image imageCursor = new Image(getClass().getResourceAsStream("Images\\rocket.png"));
        scene.setCursor(new ImageCursor(imageCursor));

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Images\\icon.png")));

        //Minimum Dimensions
        primaryStage.setMinHeight(height);
        primaryStage.setMinWidth(width);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private VBox popupFunctionScene(Stage primaryStage,Scene mainScene, StackPane myStackPane){

        /*#########################################################################*/
        /*##                             Exit Popup                              ##*/
        /*#########################################################################*/

        Image btnExitPopupImg = new Image(getClass().getResourceAsStream("Images\\cancel.png"));
        ImageView imgVwBtnExitPopup = new ImageView(btnExitPopupImg);
        imgVwBtnExitPopup.setFitHeight(20);
        imgVwBtnExitPopup.setFitWidth(20);

        Image btnAcceptPopupImg = new Image(getClass().getResourceAsStream("Images\\accept.png"));
        ImageView imgVwBtnAcceptPopup = new ImageView(btnAcceptPopupImg);
        imgVwBtnAcceptPopup.setFitHeight(20);
        imgVwBtnAcceptPopup.setFitWidth(20);

        Button btnCancelPopup = new Button("No", imgVwBtnExitPopup);
        btnCancelPopup.getStyleClass().add("RedButton");
        btnCancelPopup.setPrefWidth(90);
        btnCancelPopup.setPrefHeight(40);

        Button btnAcceptPopup = new Button("Yes", imgVwBtnAcceptPopup);
        btnAcceptPopup.getStyleClass().add("GreenButton");
        btnAcceptPopup.setPrefWidth(90);
        btnAcceptPopup.setPrefHeight(40);

        Label lblExitPopup = new Label("Did You Really Want To Go?");
        lblExitPopup.setFont(Font.font("Death Star", FontWeight.MEDIUM, 38));
        lblExitPopup.setTextFill(Color.web("#ffffff"));

        HBox topOfPopup = new HBox(lblExitPopup);
        topOfPopup.setSpacing(20);
        topOfPopup.setAlignment(Pos.CENTER);

        HBox bottomOfPopup = new HBox(btnAcceptPopup, btnCancelPopup);
        bottomOfPopup.setAlignment(Pos.CENTER);
        bottomOfPopup.setSpacing(20);

        VBox exitPopup = new VBox();
        exitPopup.getChildren().addAll(topOfPopup, bottomOfPopup);
        exitPopup.getStyleClass().add("PopUp");
        exitPopup.setSpacing(20);
        exitPopup.setAlignment(Pos.CENTER);
        exitPopup.setMaxSize(600, 200);
        btnCancelPopup.setOnMouseClicked(e -> myStackPane.getChildren().remove(exitPopup));
        btnAcceptPopup.setOnMouseClicked(event -> {
            primaryStage.setScene(mainScene);
            myStackPane.getChildren().remove(exitPopup);
        });

        return exitPopup;

    }

}
