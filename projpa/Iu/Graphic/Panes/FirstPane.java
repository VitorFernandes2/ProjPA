package projpa.Iu.Graphic.Panes;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import projpa.GameLogic.GameLogic;
import projpa.Iu.Graphic.Constants;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FirstPane extends VBox implements Constants, PropertyChangeListener {

    private GameLogic game;

    public FirstPane(GameLogic game) {
        this.game = game;
        this.game.addPropertyChangeListener(this);
        setupComponents();
        propertyChange(null);
    }

    private void setupComponents(){
        Image imgPlay = new Image(getClass().getResourceAsStream("Images\\play-button-inside-a-circle.png"));
        ImageView imgVwPlay = new ImageView(imgPlay);
        imgVwPlay.setFitHeight(20);
        imgVwPlay.setFitWidth(20);

        Image imgExitMain = new Image(getClass().getResourceAsStream("Images\\exit.png"));
        ImageView imgVwExit = new ImageView(imgExitMain);
        imgVwExit.setFitHeight(20);
        imgVwExit.setFitWidth(20);

        Image imgScoreMain = new Image(getClass().getResourceAsStream("Images\\score.png"));
        ImageView imgVwScoreMain = new ImageView(imgScoreMain);
        imgVwScoreMain.setFitHeight(20);
        imgVwScoreMain.setFitWidth(20);

        Image imgOptionsMain = new Image(getClass().getResourceAsStream("Images\\options.png"));
        ImageView imgVwOptionsMain = new ImageView(imgOptionsMain);
        imgVwOptionsMain.setFitHeight(20);
        imgVwOptionsMain.setFitWidth(20);

        Image imgLoadGameMain = new Image(getClass().getResourceAsStream("Images\\load.png"));
        ImageView imgVwLoadGameMain = new ImageView(imgLoadGameMain);
        imgVwLoadGameMain.setFitHeight(20);
        imgVwLoadGameMain.setFitWidth(20);

        VBox boxLineLayout = new VBox();

        Button btnPlay = new Button("Play",imgVwPlay);
        btnPlay.getStyleClass().add("GreenButton");
        btnPlay.setPrefWidth(180);
        btnPlay.setPrefHeight(40);

        Button btnChargeGame = new Button("Load Game", imgVwLoadGameMain);
        btnChargeGame.getStyleClass().add("DefaultButton");
        btnChargeGame.setPrefWidth(180);
        btnChargeGame.setPrefHeight(40);

        Button btnSeeScore = new Button("Score", imgVwScoreMain);
        btnSeeScore.getStyleClass().add("DefaultButton");
        btnSeeScore.setPrefWidth(180);
        btnSeeScore.setPrefHeight(40);

        Button btnOptions = new Button("Options", imgVwOptionsMain);
        btnOptions.getStyleClass().add("DefaultButton");
        btnOptions.setPrefWidth(180);
        btnOptions.setPrefHeight(40);

        Button btnExitMain = new Button("Exit", imgVwExit);
        btnExitMain.getStyleClass().add("RedButton");
        btnExitMain.setPrefWidth(180);
        btnExitMain.setPrefHeight(40);

        Label lblTitle = new Label("Destination");
        Label lblTitle2 = new Label("Earth");
        lblTitle.setFont(Font.font("Death Star", FontWeight.MEDIUM, 70));
        lblTitle.setTextFill(Color.web("#ffffff"));
        lblTitle2.setFont(Font.font("Death Star", FontWeight.MEDIUM, 70));
        lblTitle2.setTextFill(Color.web("#ffffff"));

        boxLineLayout.getChildren().addAll(lblTitle, lblTitle2, btnPlay, btnChargeGame, btnSeeScore, btnOptions,btnExitMain);
        boxLineLayout.setSpacing(20);
        boxLineLayout.getStyleClass().add("MainBox");
        boxLineLayout.setAlignment(Pos.CENTER);

        StackPane firstStackPane = new StackPane();
        ObservableList firstStackPaneList = firstStackPane.getChildren();
        firstStackPaneList.add(boxLineLayout);
        btnExitMain.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        firstStackPaneList.add(popupFunction(firstStackPane));
                    }
                });
    }

    private VBox popupFunction(StackPane myStackPane){

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
        btnAcceptPopup.setOnMouseClicked(event -> Platform.exit());

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

        return exitPopup;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setVisible(this.game.inAwaitBeginning());
    }
}
