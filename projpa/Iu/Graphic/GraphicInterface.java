package projpa.Iu.Graphic;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import projpa.GameLogic.GameLogic;
import projpa.Iu.Graphic.Buttons.DefaultButton;
import projpa.Iu.Graphic.Buttons.GreenButton;
import projpa.Iu.Graphic.Buttons.RedButton;

import java.util.ArrayList;

public class GraphicInterface {

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

        /*#########################################################################*/
        /*##                             Bottom Panel                            ##*/
        /*#########################################################################*/

        Image imgExit = new Image(getClass().getResourceAsStream("Images\\exit.png"));
        Image imgNext = new Image(getClass().getResourceAsStream("Images\\next.png"));
        Image imgSave = new Image(getClass().getResourceAsStream("Images\\save.png"));

        GreenButton btnSave = new GreenButton("Save", new ImageView(imgSave));
        RedButton btnExit = new RedButton("Exit", new ImageView(imgExit));
        DefaultButton btnNext = new DefaultButton("Next", new ImageView(imgNext));

        HBox bottomBox = new HBox(btnSave, btnNext, btnExit);
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(5, 10, 5, 10));
        bottomBox.getStyleClass().add("topBox");

        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);

        /*#########################################################################*/
        /*##                             Left Panel                              ##*/
        /*#########################################################################*/

        TabPane tabLayout = new TabPane();
        tabLayout.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        VBox firstTabImage = new VBox();
        Label firstTabLabel = new Label("Doctor");

        firstTabLabel.setTextFill(Color.web("#ffffff"));

        ImageView firstTabIcon = new ImageView(new Image(getClass().getResourceAsStream("Images\\doctor.png")));
        firstTabIcon.setFitWidth(50);
        firstTabIcon.setFitHeight(50);

        firstTabImage.getChildren().addAll(firstTabIcon, firstTabLabel);

        Tab firstTab = new Tab();
        firstTab.setGraphic(firstTabImage);
        tabLayout.getTabs().add(firstTab);

        VBox secondTabImage = new VBox();
        Label secondTabLabel = new Label("Red Shirt");

        secondTabLabel.setTextFill(Color.web("#ffffff"));

        ImageView secondTabIcon = new ImageView(new Image(getClass().getResourceAsStream("Images\\redShirt.png")));
        secondTabIcon.setFitWidth(50);
        secondTabIcon.setFitHeight(50);

        secondTabImage.getChildren().addAll(secondTabIcon, secondTabLabel);

        Tab secondTab = new Tab();
        secondTab.setGraphic(secondTabImage);
        tabLayout.getTabs().add(secondTab);

        VBox leftBox = new VBox(tabLayout);
        leftBox.getStyleClass().add("rightBox");

        leftBox.setAlignment(Pos.TOP_CENTER);

        /*#########################################################################*/
        /*##                              Top Panel                              ##*/
        /*#########################################################################*/


        ImageView pointsIcon = new ImageView(new Image(getClass().getResourceAsStream("Images\\points.png")));
        pointsIcon.setFitWidth(20);
        pointsIcon.setFitHeight(20);

        Label lblPoints = new Label(": xxx", pointsIcon);

        lblPoints.setTextFill(Color.web("#ffffff"));
        lblPoints.getStyleClass().add("PointsLabel");

        ImageView apIcon = new ImageView(new Image(getClass().getResourceAsStream("Images\\action.png")));
        apIcon.setFitWidth(20);
        apIcon.setFitHeight(20);

        Label lblAp = new Label(": xxx", apIcon);

        lblAp.setTextFill(Color.web("#ffffff"));
        lblAp.getStyleClass().add("PointsLabel");

        ImageView ipIcon = new ImageView(new Image(getClass().getResourceAsStream("Images\\influence.png")));
        ipIcon.setFitWidth(20);
        ipIcon.setFitHeight(20);

        Label lblIp = new Label(": xxx", ipIcon);

        lblIp.setTextFill(Color.web("#ffffff"));
        lblIp.getStyleClass().add("PointsLabel");

        HBox topRightBox = new HBox();
        topRightBox.getChildren().addAll(lblPoints, lblAp, lblIp);
        topRightBox.setAlignment(Pos.CENTER_RIGHT);
        topRightBox.setSpacing(10);

        HBox topLeftBox = new HBox();
        topLeftBox.getChildren().addAll();
        topLeftBox.setAlignment(Pos.CENTER_LEFT);

        ArrayList<Label> journeyLabels = new ArrayList<>();

        for (int i = 0; i < 15; i++) {

            journeyLabels.add(new Label("5A"));
            journeyLabels.get(i).setFont(Font.font("Death Star", FontWeight.MEDIUM, 12));
            journeyLabels.get(i).setTextFill(Color.web("#ffffff"));
            journeyLabels.get(i).getStyleClass().add("journeyLabelsCenter");
            journeyLabels.get(i).setAlignment(Pos.CENTER);
            journeyLabels.get(i).setPrefWidth(40);
            journeyLabels.get(i).setPrefHeight(30);
            topLeftBox.getChildren().add(journeyLabels.get(i));

        }

        journeyLabels.get(0).setText("S");
        journeyLabels.get(0).getStyleClass().add("journeyLabelsLeft");
        journeyLabels.get(14).setText("E");
        journeyLabels.get(14).getStyleClass().add("journeyLabelsRight");
        journeyLabels.get(1).getStyleClass().add("journeyLabelsCenterActive");

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        HBox topBox = new HBox();
        topBox.getChildren().addAll(topLeftBox, region, topRightBox);
        topBox.setSpacing(10);
        topBox.setPadding(new Insets(5, 10, 5, 10));
        topBox.getStyleClass().add("topBox");
        topBox.setAlignment(Pos.CENTER_RIGHT);

        /*#########################################################################*/
        /*##                             Right Panel                             ##*/
        /*#########################################################################*/

        VBox rightBox = new VBox();
        rightBox.setPadding(new Insets(5, 10, 5, 10));

        Label lbl1 = new Label("Alien1: Aquele sitio");
        Label lbl2 = new Label("Alien2: Aquele sitio");
        Label lbl3 = new Label("Alien3: Aquele sitio");
        Label lbl4 = new Label("Alien4: Aquele sitio");
        Label lbl5 = new Label("Alien5: Aquele sitio");

        lbl1.setTextFill(Color.web("#ffffff"));
        lbl2.setTextFill(Color.web("#ffffff"));
        lbl3.setTextFill(Color.web("#ffffff"));
        lbl4.setTextFill(Color.web("#ffffff"));
        lbl5.setTextFill(Color.web("#ffffff"));

        lbl1.getStyleClass().add("AliensLabel");
        lbl2.getStyleClass().add("AliensLabel");
        lbl3.getStyleClass().add("AliensLabel");
        lbl4.getStyleClass().add("AliensLabel");
        lbl5.getStyleClass().add("AliensLabel");

        rightBox.getChildren().addAll(lbl1, lbl2, lbl3, lbl4, lbl5);
        rightBox.getStyleClass().add("rightBox");
        rightBox.setAlignment(Pos.TOP_CENTER);

        /*#########################################################################*/
        /*##                              Center Panel                           ##*/
        /*#########################################################################*/

        Image MainshipImage = new Image(getClass().getResourceAsStream("Images\\Spaceship_comrooms.png"));

        Pane MainshipImageView = new Pane ();
        ImageView MainshipImageView2 = new ImageView(MainshipImage);
        // temp -> fit the image in the pain, subject to change
        // main image
        MainshipImageView2.setFitHeight(1300);
        MainshipImageView2.setPreserveRatio(true);
        MainshipImageView2.setY(-250);
        MainshipImageView2.setX(-80);

        // background ships animations

        Image Randomship1img = new Image(getClass().getResourceAsStream("Images\\shiprandom1.png"));
        ImageView Randomship1imgview = new ImageView(Randomship1img);
        // set off screen and correct size and rotation
        Randomship1imgview.setLayoutX(2000);
        Randomship1imgview.setLayoutY(1500);
        Randomship1imgview.setFitHeight(30);
        Randomship1imgview.setPreserveRatio(true);
        Randomship1imgview.setRotate(-50);

        TranslateTransition transtion = new TranslateTransition(Duration.seconds(8),Randomship1imgview);
        //transtion.setDuration(Duration.seconds(3));
        transtion.setToX(-2100);
        transtion.setToY(-1700);
        transtion.setCycleCount(Animation.INDEFINITE);
        transtion.play();


        // adicionas of people in pane
        MainshipImageView.getChildren().add(Randomship1imgview);
        MainshipImageView.getChildren().add(MainshipImageView2);

        VBox navyBox = new VBox();
        navyBox.getChildren().addAll();

        BorderPane centerBox = new BorderPane();
        centerBox.setCenter(MainshipImageView);
        centerBox.getStyleClass().add("centerBox");
        centerBox.setPadding(new Insets(10, 20, 10, 20));

        /*#########################################################################*/
        /*##                              Main Panel                             ##*/
        /*#########################################################################*/

        BorderPane paneLayout = new BorderPane();
        paneLayout.setCenter(centerBox);
        paneLayout.setBottom(bottomBox);
        paneLayout.setLeft(leftBox);
        paneLayout.setTop(topBox);
        paneLayout.setRight(rightBox);
        paneLayout.getStyleClass().add("ChooseVBox");

        paneLayout.setBackground(new Background(new BackgroundFill(Color.web("#606060"),
                CornerRadii.EMPTY, Insets.EMPTY)));

        /*#########################################################################*/
        /*##                             First Scene                             ##*/
        /*#########################################################################*/

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
                        firstStackPaneList.add(popupFunction(primaryStage, firstStackPane));
                    }
                });

        Scene firstScene = new Scene(firstStackPane, width, height);
        firstScene.getStylesheets().add(GraphicInterface.class.getResource("Css\\main.css").toExternalForm());
        Image imageCursor = new Image(getClass().getResourceAsStream("Images\\rocket.png"));
        firstScene.setCursor(new ImageCursor(imageCursor));


        /*#########################################################################*/
        /*##                            Action Scene                             ##*/
        /*#########################################################################*/

        StackPane actionStackPane = new StackPane(paneLayout);
        actionStackPane.setAlignment(Pos.CENTER);

        Scene ActionScene = new Scene(actionStackPane, width, height);
        ActionScene.getStylesheets().add(GraphicInterface.class.getResource("Css\\main.css").toExternalForm());
        ActionScene.setCursor(new ImageCursor(imageCursor));

        /*#########################################################################*/
        /*##                     Choose CrewMember Scene                         ##*/
        /*#########################################################################*/

        BorderPane paneLayoutCrewMembersChoice = new BorderPane();

        VBox mainVboxCrewMembersChoice = new VBox();

        ArrayList<Image> CardsImage = new ArrayList<>();

        for (int i = 0; i < 12; i++) {

            switch (i){
                case 0:
                    CardsImage.add(new Image(getClass().getResourceAsStream("Images\\Cards\\Captain.png")));
                    break;
                case 1:
                    CardsImage.add(new Image(getClass().getResourceAsStream("Images\\Cards\\Commander.png")));
                    break;
                case 2:
                    CardsImage.add(new Image(getClass().getResourceAsStream("Images\\Cards\\CommsOfficer.png")));
                    break;
                case 3:
                    CardsImage.add(new Image(getClass().getResourceAsStream("Images\\Cards\\Doctor.png")));
                    break;
                case 4:
                    CardsImage.add(new Image(getClass().getResourceAsStream("Images\\Cards\\Engineer.png")));
                    break;
                case 5:
                    CardsImage.add(new Image(getClass().getResourceAsStream("Images\\Cards\\MoralOfficer.png")));
                    break;
                case 6:
                    CardsImage.add(new Image(getClass().getResourceAsStream("Images\\Cards\\NavigationOfficer.png")));
                    break;
                case 7:
                    CardsImage.add(new Image(getClass().getResourceAsStream("Images\\Cards\\RedShirt.png")));
                    break;
                case 8:
                    CardsImage.add(new Image(getClass().getResourceAsStream("Images\\Cards\\ScienceOfficer.png")));
                    break;
                case 9:
                    CardsImage.add(new Image(getClass().getResourceAsStream("Images\\Cards\\SecurityOfficer.png")));
                    break;
                case 10:
                    CardsImage.add(new Image(getClass().getResourceAsStream("Images\\Cards\\ShuttlePilot.png")));
                    break;
                case 11:
                    CardsImage.add(new Image(getClass().getResourceAsStream("Images\\Cards\\TransporterChief.png")));
                    break;
            }

        }

        ArrayList<ImageView> CardsImageView = new ArrayList<>();

        for (int i = 0; i < 12; i++){
            CardsImageView.add(new ImageView(CardsImage.get(i)));
            CardsImageView.get(i).setFitWidth(125);
            CardsImageView.get(i).setFitHeight(180);
        }

        ArrayList<HBox> CrewCardImgHbox = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            CrewCardImgHbox.add(new HBox());
            CrewCardImgHbox.get(i).getChildren().add(CardsImageView.get(i));
            CrewCardImgHbox.get(i).getStyleClass().add("ImageView");

        }

        HBox firstLineCrewMembersHbox = new HBox(CrewCardImgHbox.get(0), CrewCardImgHbox.get(1) ,CrewCardImgHbox.get(2) ,CrewCardImgHbox.get(3));
        firstLineCrewMembersHbox.setSpacing(40);
        firstLineCrewMembersHbox.setAlignment(Pos.CENTER);

        HBox secondLineCrewMembersHbox = new HBox(CrewCardImgHbox.get(4), CrewCardImgHbox.get(5) ,CrewCardImgHbox.get(6) ,CrewCardImgHbox.get(7));
        secondLineCrewMembersHbox.setSpacing(40);
        secondLineCrewMembersHbox.setAlignment(Pos.CENTER);

        HBox thirdLineCrewMembersHbox = new HBox(CrewCardImgHbox.get(8), CrewCardImgHbox.get(9) ,CrewCardImgHbox.get(10) ,CrewCardImgHbox.get(11));
        thirdLineCrewMembersHbox.setSpacing(40);
        thirdLineCrewMembersHbox.setAlignment(Pos.CENTER);

        Label lblChooseCrewMembers = new Label("Choose Crew Members");
        lblChooseCrewMembers.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblChooseCrewMembers.setTextFill(Color.web("#ffffff"));

        ImageView ChooseCardUndo = new ImageView(new Image(getClass().getResourceAsStream("Images\\undo.png")));
        ChooseCardUndo.setFitHeight(20);
        ChooseCardUndo.setFitWidth(20);

        Button btnTurnBackChooseCrewMember = new Button("Go Back", ChooseCardUndo);
        btnTurnBackChooseCrewMember.getStyleClass().add("DefaultButton");

        HBox turnBackChooseCard = new HBox();
        turnBackChooseCard.setSpacing(20);
        turnBackChooseCard.setAlignment(Pos.CENTER);
        btnTurnBackChooseCrewMember.setPadding(new Insets(10));

        turnBackChooseCard.getChildren().addAll(lblChooseCrewMembers, btnTurnBackChooseCrewMember);
        turnBackChooseCard.setAlignment(Pos.CENTER);
        turnBackChooseCard.setPadding(new Insets(0, 10, 0, 10));

        mainVboxCrewMembersChoice.getChildren().setAll(turnBackChooseCard, firstLineCrewMembersHbox, secondLineCrewMembersHbox, thirdLineCrewMembersHbox);
        mainVboxCrewMembersChoice.setAlignment(Pos.CENTER);
        mainVboxCrewMembersChoice.setSpacing(20);

        mainVboxCrewMembersChoice.getStyleClass().add("ChooseVBox");

        paneLayoutCrewMembersChoice.setCenter(mainVboxCrewMembersChoice);
        Scene ChooseCrewMembersScene = new Scene(paneLayoutCrewMembersChoice, width, height);
        ChooseCrewMembersScene.getStylesheets().add(GraphicInterface.class.getResource("Css\\main.css").toExternalForm());
        ChooseCrewMembersScene.setCursor(new ImageCursor(imageCursor));

        /*#########################################################################*/
        /*##                             Username Scene                          ##*/
        /*#########################################################################*/

        Image imgReset = new Image(getClass().getResourceAsStream("Images\\trash.png"));
        ImageView imgVwReset = new ImageView(imgReset);
        imgVwReset.setFitWidth(20);
        imgVwReset.setFitHeight(20);

        ImageView ChooseUsernameSceneUndo = new ImageView(new Image(getClass().getResourceAsStream("Images\\undo.png")));
        ChooseUsernameSceneUndo.setFitHeight(20);
        ChooseUsernameSceneUndo.setFitWidth(20);

        Button btnTurnBackUserNameScene = new Button("Go Back", ChooseUsernameSceneUndo);
        btnTurnBackUserNameScene.getStyleClass().add("DefaultButton");

        Image imgPlayUsername = new Image(getClass().getResourceAsStream("Images\\play-button-inside-a-circle.png"));
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

        Scene userNameScene = new Scene(mainVboxUsernameScene, width, height);
        userNameScene.getStylesheets().add(GraphicInterface.class.getResource("Css\\main.css").toExternalForm());
        userNameScene.setCursor(new ImageCursor(imageCursor));

        /*#########################################################################*/
        /*##                             Score Scene                          ##*/
        /*#########################################################################*/

        VBox scoreSceneMainVbox = new VBox();
        HBox scoreTitleHbox = new HBox();
        HBox turnBackScoreHBox = new HBox();
        VBox playersScorebox = new VBox();
        Label lblsPlayersScore = new Label();
        Label lblScoreTitle = new Label("Top Players");
        lblScoreTitle.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblScoreTitle.setTextFill(Color.web("#ffffff"));

        ImageView scoreSceneUndo = new ImageView(new Image(getClass().getResourceAsStream("Images\\undo.png")));
        scoreSceneUndo.setFitHeight(20);
        scoreSceneUndo.setFitWidth(20);

        Button btnTurnBackscoreScene = new Button("Go Back", scoreSceneUndo);
        btnTurnBackscoreScene.getStyleClass().add("DefaultButton");

        String scoreStr = new String(this.game.getUserPontuation());
        lblsPlayersScore.setText(scoreStr);
        lblsPlayersScore.setFont(Font.font("Death Star", FontWeight.MEDIUM, 30));
        lblsPlayersScore.setPadding(new Insets(10,0,10,0));
        lblsPlayersScore.setTextFill(Color.web("#ffffff"));
        playersScorebox.getChildren().add(lblsPlayersScore);


        scoreTitleHbox.getChildren().addAll(lblScoreTitle);
        scoreTitleHbox.setSpacing(20);
        scoreTitleHbox.setAlignment(Pos.CENTER);
        turnBackScoreHBox.setSpacing(20);
        turnBackScoreHBox.setAlignment(Pos.CENTER);
        turnBackScoreHBox.getChildren().add(btnTurnBackscoreScene);
        playersScorebox.setAlignment(Pos.CENTER);
        scoreSceneMainVbox.getChildren().addAll(scoreTitleHbox, playersScorebox, turnBackScoreHBox);
        scoreSceneMainVbox.setAlignment(Pos.CENTER);
        scoreSceneMainVbox.setSpacing(20);
        scoreSceneMainVbox.getStyleClass().add("ChooseVBox");

        Scene scoreScene = new Scene(scoreSceneMainVbox, width, height);
        scoreScene.getStylesheets().add(GraphicInterface.class.getResource("Css\\main.css").toExternalForm());
        scoreScene.setCursor(new ImageCursor(imageCursor));

        /*#########################################################################*/
        /*##                              Options Scene                          ##*/
        /*#########################################################################*/

        HBox Options1Line = new HBox();

        Label lblOptionsTitle = new Label("Options");
        lblOptionsTitle.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblOptionsTitle.setTextFill(Color.web("#ffffff"));

        Options1Line.getChildren().addAll(lblOptionsTitle);
        Options1Line.setAlignment(Pos.CENTER);
        Options1Line.setSpacing(20);

        HBox Options2Line = new HBox();

        Label lblChangeJourneyTrackerTitle = new Label("Change Journey Tracker");
        lblChangeJourneyTrackerTitle.setFont(Font.font("Death Star", FontWeight.MEDIUM, 20));
        lblChangeJourneyTrackerTitle.setTextFill(Color.web("#ffffff"));

        Options2Line.getChildren().addAll(lblChangeJourneyTrackerTitle);
        Options2Line.setAlignment(Pos.CENTER);
        Options2Line.setSpacing(20);

        HBox Options3Line = new HBox();

        ArrayList<TextField> OptionsText = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            OptionsText.add(new TextField());
            OptionsText.get(i).getStyleClass().add("TextArea2");
            OptionsText.get(i).setAlignment(Pos.CENTER);
            OptionsText.get(i).setText(this.game.getJourneyValue(i));
            OptionsText.get(i).setPrefWidth(80);
            OptionsText.get(i).setFont(Font.font("Death Star", FontWeight.MEDIUM, 15));
            Options3Line.getChildren().addAll(OptionsText.get(i));
        }

        OptionsText.get(0).setDisable(true);
        OptionsText.get(0).getStyleClass().add("TextArea3");
        OptionsText.get(14).setDisable(true);
        OptionsText.get(14).getStyleClass().add("TextArea4");

        Options3Line.setAlignment(Pos.CENTER);

        HBox Options4Line = new HBox();

        Label lblChangeHullTrackerTitle = new Label("Change Hull Tracker");
        lblChangeHullTrackerTitle.setFont(Font.font("Death Star", FontWeight.MEDIUM, 20));
        lblChangeHullTrackerTitle.setTextFill(Color.web("#ffffff"));

        Options4Line.getChildren().addAll(lblChangeHullTrackerTitle);
        Options4Line.setAlignment(Pos.CENTER);
        Options4Line.setSpacing(20);

        HBox Options5Line = new HBox();

        Spinner<Integer> HullTrackerSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, this.game.getHullState());
        HullTrackerSpinner.setValueFactory(valueFactory);
        HullTrackerSpinner.getStyleClass().add("spinner");

        Options5Line.getChildren().addAll(HullTrackerSpinner);
        Options5Line.setAlignment(Pos.CENTER);
        Options5Line.setSpacing(20);

        HBox Options6Line = new HBox();

        Label lblChangeHealthTrackerTitle = new Label("Change Health Tracker");
        lblChangeHealthTrackerTitle.setFont(Font.font("Death Star", FontWeight.MEDIUM, 20));
        lblChangeHealthTrackerTitle.setTextFill(Color.web("#ffffff"));

        Options6Line.getChildren().addAll(lblChangeHealthTrackerTitle);
        Options6Line.setAlignment(Pos.CENTER);
        Options6Line.setSpacing(20);

        HBox Options7Line = new HBox();

        Spinner<Integer> HealthTrackerSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, this.game.getHealthTrackerHealth());
        HealthTrackerSpinner.setValueFactory(valueFactory2);
        HealthTrackerSpinner.getStyleClass().add("spinner");

        Options7Line.getChildren().addAll(HealthTrackerSpinner);
        Options7Line.setAlignment(Pos.CENTER);
        Options7Line.setSpacing(20);

        HBox Options8Line = new HBox();

        ImageView OptionsUndo = new ImageView(new Image(getClass().getResourceAsStream("Images\\undo.png")));
        OptionsUndo.setFitHeight(20);
        OptionsUndo.setFitWidth(20);

        ImageView OptionsSave = new ImageView(new Image(getClass().getResourceAsStream("Images\\save.png")));
        OptionsSave.setFitHeight(20);
        OptionsSave.setFitWidth(20);

        Button btnTurnBackOptions = new Button("Go Back", OptionsUndo);
        btnTurnBackOptions.getStyleClass().add("DefaultButton");

        Button btnTurSaveOptions = new Button("Save", OptionsSave);
        btnTurSaveOptions.getStyleClass().add("GreenButton");

        Options8Line.getChildren().addAll(btnTurnBackOptions, btnTurSaveOptions);
        Options8Line.setAlignment(Pos.CENTER);
        Options8Line.setSpacing(20);

        VBox OptionsSceneVBox = new VBox();
        OptionsSceneVBox.getChildren().addAll(Options1Line, Options2Line, Options3Line,
                Options4Line, Options5Line, Options6Line, Options7Line, Options8Line);
        OptionsSceneVBox.getStyleClass().add("ChooseVBox");
        OptionsSceneVBox.setSpacing(20);
        OptionsSceneVBox.setAlignment(Pos.CENTER);

        Scene OptionsScene = new Scene(OptionsSceneVBox, width, height);
        OptionsScene.getStylesheets().add(GraphicInterface.class.getResource("Css\\main.css").toExternalForm());
        OptionsScene.setCursor(new ImageCursor(imageCursor));

        /*#########################################################################*/
        /*##                            Choose Room Scene                        ##*/
        /*#########################################################################*/

        BorderPane paneLayoutRoomChoice = new BorderPane();

        VBox mainVboxRoomChoice = new VBox();

        ArrayList<Image> RoomImage = new ArrayList<>();

        for (int i = 0; i < 12; i++) {

            switch (i){
                case 0:
                    RoomImage.add(new Image(getClass().getResourceAsStream("Images\\Rooms\\Room1.png")));
                    break;
                case 1:
                    RoomImage.add(new Image(getClass().getResourceAsStream("Images\\Rooms\\Room2.png")));
                    break;
                case 2:
                    RoomImage.add(new Image(getClass().getResourceAsStream("Images\\Rooms\\Room3.png")));
                    break;
                case 3:
                    RoomImage.add(new Image(getClass().getResourceAsStream("Images\\Rooms\\Room4.png")));
                    break;
                case 4:
                    RoomImage.add(new Image(getClass().getResourceAsStream("Images\\Rooms\\Room5.png")));
                    break;
                case 5:
                    RoomImage.add(new Image(getClass().getResourceAsStream("Images\\Rooms\\Room6.png")));
                    break;
                case 6:
                    RoomImage.add(new Image(getClass().getResourceAsStream("Images\\Rooms\\Room7.png")));
                    break;
                case 7:
                    RoomImage.add(new Image(getClass().getResourceAsStream("Images\\Rooms\\Room8.png")));
                    break;
                case 8:
                    RoomImage.add(new Image(getClass().getResourceAsStream("Images\\Rooms\\Room9.png")));
                    break;
                case 9:
                    RoomImage.add(new Image(getClass().getResourceAsStream("Images\\Rooms\\Room10.png")));
                    break;
                case 10:
                    RoomImage.add(new Image(getClass().getResourceAsStream("Images\\Rooms\\Room11.png")));
                    break;
                case 11:
                    RoomImage.add(new Image(getClass().getResourceAsStream("Images\\Rooms\\Room12.png")));
                    break;
            }

        }

        ArrayList<ImageView> RoomsImageView = new ArrayList<>();

        for (int i = 0; i < 12; i++){
            RoomsImageView.add(new ImageView(RoomImage.get(i)));
            RoomsImageView.get(i).setFitWidth(125);
            RoomsImageView.get(i).setFitHeight(125);
        }

        ArrayList<HBox> RoomsImgHbox = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            RoomsImgHbox.add(new HBox());
            RoomsImgHbox.get(i).getChildren().add(RoomsImageView.get(i));
            RoomsImgHbox.get(i).getStyleClass().add("ImageView");

        }

        HBox firstLineRoomsHbox = new HBox(RoomsImgHbox.get(0), RoomsImgHbox.get(1) ,RoomsImgHbox.get(2) ,RoomsImgHbox.get(3));
        firstLineRoomsHbox.setSpacing(40);
        firstLineRoomsHbox.setAlignment(Pos.CENTER);

        HBox secondLineRoomssHbox = new HBox(RoomsImgHbox.get(4), RoomsImgHbox.get(5) ,RoomsImgHbox.get(6) ,RoomsImgHbox.get(7));
        secondLineRoomssHbox.setSpacing(40);
        secondLineRoomssHbox.setAlignment(Pos.CENTER);

        HBox thirdLineRoomsHbox = new HBox(RoomsImgHbox.get(8), RoomsImgHbox.get(9) ,RoomsImgHbox.get(10) ,RoomsImgHbox.get(11));
        thirdLineRoomsHbox.setSpacing(40);
        thirdLineRoomsHbox.setAlignment(Pos.CENTER);

        Label lblChooseRooms = new Label("Choose Crew Member 1 Room");
        lblChooseRooms.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblChooseRooms.setTextFill(Color.web("#ffffff"));

        ImageView ChooseRoomsUndo = new ImageView(new Image(getClass().getResourceAsStream("Images\\undo.png")));
        ChooseRoomsUndo.setFitHeight(20);
        ChooseRoomsUndo.setFitWidth(20);

        Button btnTurnBackChooseRoom = new Button("Go Back", ChooseRoomsUndo);
        btnTurnBackChooseRoom.getStyleClass().add("DefaultButton");

        HBox turnBackChooseRoom = new HBox();
        turnBackChooseRoom.setSpacing(20);
        turnBackChooseRoom.setAlignment(Pos.CENTER);
        btnTurnBackChooseRoom.setPadding(new Insets(10));

        turnBackChooseRoom.getChildren().addAll(lblChooseRooms, btnTurnBackChooseRoom);
        turnBackChooseRoom.setAlignment(Pos.CENTER);
        turnBackChooseRoom.setPadding(new Insets(0, 10, 0, 10));

        mainVboxRoomChoice.getChildren().setAll(turnBackChooseRoom, firstLineRoomsHbox, secondLineRoomssHbox, thirdLineRoomsHbox);
        mainVboxRoomChoice.setAlignment(Pos.CENTER);
        mainVboxRoomChoice.setSpacing(20);

        mainVboxRoomChoice.getStyleClass().add("ChooseVBox");

        RoomsImgHbox.get(0).addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        primaryStage.setScene(ActionScene);
                        primaryStage.setFullScreen(true);
                        mediaPlayer.pause();
                        mediaPlayergame.play();

                    }
                });



        paneLayoutRoomChoice.setCenter(mainVboxRoomChoice);
        Scene ChooseRoomsScene = new Scene(paneLayoutRoomChoice, width, height);
        ChooseRoomsScene.getStylesheets().add(GraphicInterface.class.getResource("Css\\main.css").toExternalForm());
        ChooseRoomsScene.setCursor(new ImageCursor(imageCursor));

        /*#########################################################################*/
        /*##                              Main Stage                             ##*/
        /*#########################################################################*/

        btnOptions.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(OptionsScene);
                primaryStage.setFullScreen(true);
            }
        });

        btnTurnBackOptions.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(firstScene);
                primaryStage.setFullScreen(true);
            }
        });

        btnTurnBackscoreScene.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(firstScene);
                primaryStage.setFullScreen(true);
            }
        });

        btnSeeScore.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(scoreScene);
                primaryStage.setFullScreen(true);
            }
        });

        btnPlay.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(userNameScene);
                primaryStage.setFullScreen(true);
            }
        });

        btnSubmitName.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(ChooseCrewMembersScene);
                primaryStage.setFullScreen(true);
            }
        });

        btnSubmitName.setOnMouseClicked(e -> {
            this.game.newUser(txtAreaUserName.getText());
            this.game.Inputbegining();
            txtAreaUserName.clear();
        });

        btnTurnBackChooseCrewMember.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(userNameScene);
                primaryStage.setFullScreen(true);
            }
        });

        btnTurnBackChooseRoom.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(ChooseCrewMembersScene);
                primaryStage.setFullScreen(true);
            }
        });

        btnTurnBackUserNameScene.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(firstScene);
                primaryStage.setFullScreen(true);
            }
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

        btnResetUsername.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                txtAreaUserName.clear();
                btnSubmitName.setDisable(true);
            }
        });

        btnTurnBackUserNameScene.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                txtAreaUserName.clear();
                btnSubmitName.setDisable(true);
            }
        });

        ObservableList actionStackPaneList = actionStackPane.getChildren();
        btnExit.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        actionStackPaneList.add(popupFunctionScene(primaryStage, firstScene, actionStackPane));
                    }
                });


        for (int i = 0; i < 12; i++) {
            CardsImageView.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setScene(ChooseRoomsScene);
                    primaryStage.setFullScreen(true);
                }
            });
        }

        btnTurnBackOptions.setOnMouseClicked(e -> {
            for (int i = 0; i < 15; i++) {
                OptionsText.get(i).setText(this.game.getJourneyValue(i));
            }
            valueFactory.setValue(this.game.getHullState());
            valueFactory2.setValue(this.game.getHealthTrackerHealth());
        });

        btnTurSaveOptions.setOnMouseClicked(e -> {
            this.game.setHealthTrackerValue(valueFactory2.getValue());
            this.game.setHullTrackerValue(valueFactory.getValue());

            for (int i = 0; i < 15; i++) {
                if (!this.game.validateJourneyTrackerOption(OptionsText.get(i).getText()))
                    this.game.changeJourney(i, OptionsText.get(i).getText());
            }

            for (int i = 0; i < 15; i++) {
                OptionsText.get(i).setText(this.game.getJourneyValue(i));
            }
            valueFactory.setValue(this.game.getHullState());
            valueFactory2.setValue(this.game.getHealthTrackerHealth());

            primaryStage.setScene(firstScene);
            primaryStage.setFullScreen(true);
        });

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Images\\icon.png")));

        //Minimum Dimensions
        primaryStage.setMinHeight(height);
        primaryStage.setMinWidth(width);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setScene(firstScene);
        primaryStage.show();

    }

    private VBox popupFunction(Stage primaryStage, StackPane myStackPane){

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
        btnAcceptPopup.setOnMouseClicked(event -> primaryStage.close());

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
