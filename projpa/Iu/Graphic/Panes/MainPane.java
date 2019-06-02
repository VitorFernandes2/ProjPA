package projpa.Iu.Graphic.Panes;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import projpa.GameLogic.GameLogic;
import projpa.Iu.Graphic.Buttons.DefaultButton;
import projpa.Iu.Graphic.Buttons.GreenButton;
import projpa.Iu.Graphic.Buttons.RedButton;
import projpa.Iu.Graphic.Constants;
import projpa.Iu.Graphic.DeathStarLabel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import javafx.stage.FileChooser;

public class MainPane extends StackPane implements Constants, PropertyChangeListener {

    private GameLogic game;
    private Image imgPlay;
    private Image imgExitMain;
    private Image imgScoreMain;
    private Image imgOptionsMain;
    private Image imgLoadGameMain;
    private VBox boxLineLayout;
    private GreenButton btnPlay;
    private DefaultButton btnChargeGame;
    private DefaultButton btnSeeScore;
    private DefaultButton btnOptions;
    private RedButton btnExitMain;
    private DeathStarLabel lblTitle;
    private DeathStarLabel lblTitle2;
    private CrewMembersChoicePane crewMembersChoicePane;
    private CrewMember1ChooseRoom crewMember1ChooseRoom;
    private CrewMember2ChooseRoom crewMember2ChooseRoom;
    private EndGamePane endgamepane;
    private ActionPane actionPane;
    private UsernamePane usernamePane;

    public MainPane(GameLogic game) {
        this.game = game;
        this.game.addPropertyChangeListener(this);
        //this.game.endGame2();
        setupComponents();
        propertyChange(null);
    }

    private void setupComponents(){

        this.setPrefHeight(HEIGHT);
        this.setPrefWidth(WIDTH);

        imgPlay = new Image(getClass().getResourceAsStream("..\\" + PLAY_IMAGE));
        imgExitMain = new Image(getClass().getResourceAsStream("..\\" + EXIT_IMAGE));
        imgScoreMain = new Image(getClass().getResourceAsStream("..\\" + SCORE_IMAGE));
        imgOptionsMain = new Image(getClass().getResourceAsStream("..\\" + OPTIONS_IMAGE));
        imgLoadGameMain = new Image(getClass().getResourceAsStream("..\\" + LOAD_IMAGE));

        boxLineLayout = new VBox();

        btnPlay = new GreenButton("Play", imgPlay, 20, 20, 180, 40);
        btnChargeGame = new DefaultButton("Load Game", imgLoadGameMain, 20, 20, 180, 40);
        btnSeeScore = new DefaultButton("Score", imgScoreMain, 20, 20, 180, 40);
        btnOptions = new DefaultButton("Options", imgOptionsMain, 20, 20, 180, 40);
        btnExitMain = new RedButton("Exit", imgExitMain, 20, 20, 180, 40);

        lblTitle = new DeathStarLabel("Destination", 70);
        lblTitle2 = new DeathStarLabel("Earth", 70);

        boxLineLayout.getChildren().addAll(lblTitle, lblTitle2, btnPlay, btnChargeGame, btnSeeScore, btnOptions,btnExitMain);
        boxLineLayout.setSpacing(20);
        boxLineLayout.getStyleClass().add("MainBox");
        boxLineLayout.setAlignment(Pos.CENTER);

        crewMembersChoicePane = new CrewMembersChoicePane(this.game);
        crewMember1ChooseRoom = new CrewMember1ChooseRoom(this.game);
        crewMember2ChooseRoom = new CrewMember2ChooseRoom(this.game);
        endgamepane = new EndGamePane(this.game);
        actionPane = new ActionPane(this.game);
        usernamePane = new UsernamePane(this.game);

        ObservableList firstStackPaneList = this.getChildren();
        firstStackPaneList.addAll(boxLineLayout, usernamePane,crewMembersChoicePane, crewMember1ChooseRoom, crewMember2ChooseRoom, actionPane , endgamepane);
        btnExitMain.setOnMouseClicked(e ->{
            firstStackPaneList.add(popupFunction(this));
        });
        btnOptions.setOnMouseClicked(e -> {
            firstStackPaneList.add(OptionsFunction(this));
        });
        btnSeeScore.setOnMouseClicked(e -> {
            firstStackPaneList.add(ShowScoreFunction(this));
        });
        btnPlay.setOnMouseClicked(e -> {
            this.game.startGame();
        });
        btnChargeGame.setOnAction(e -> {
            
            firstStackPaneList.add(LoadFunction(this));
            
            //this.game.LoadGame(); 
            //this.game.startGame();
        });
        
        

    }
    
    /*#########################################################################*/
    /*##                             Load Game Scene                         ##*/
    /*#########################################################################*/

    private VBox LoadFunction(StackPane myStackPane){

        VBox MainlayoutVbox = new VBox();

        HBox Optionsbutns = new HBox();

        DeathStarLabel lblScoreTitle = new DeathStarLabel("Are you sure you want to load?", 58);

        Image loadyesimg = new Image(getClass().getResourceAsStream("..\\" + ACCEPT_IMAGE));
        GreenButton loadyesbtn = new GreenButton("Yes", loadyesimg, 20, 20, 120, 40);

        Image loadnoimg = new Image(getClass().getResourceAsStream("..\\" + CANCEL_IMAGE));
        RedButton loadnobtn = new RedButton("No", loadnoimg, 20, 20, 120, 40);
        
        Image loadcostimg = new Image(getClass().getResourceAsStream("..\\" + LOAD_IMAGE));
        DefaultButton loadcostbtn = new DefaultButton("Open", loadcostimg, 20, 20, 120, 40);
        

        Optionsbutns.setSpacing(20);
        Optionsbutns.setAlignment(Pos.CENTER);
        Optionsbutns.getChildren().addAll(loadyesbtn, loadnobtn,loadcostbtn);
        MainlayoutVbox.getChildren().addAll(lblScoreTitle, Optionsbutns);
        MainlayoutVbox.setAlignment(Pos.CENTER);
        MainlayoutVbox.setSpacing(20);
        MainlayoutVbox.getStyleClass().add("ChooseVBox");

        loadnobtn.setOnAction(e-> {
            myStackPane.getChildren().remove(MainlayoutVbox);

        });
        
        loadcostbtn.setOnAction(e-> {
            myStackPane.getChildren().remove(MainlayoutVbox);
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("..\\"));
            File selectedFile = fc.showOpenDialog(null);
            
            if (selectedFile!= null){
                boolean returns = this.game.LoadGame(selectedFile.getAbsolutePath()); 
                if(returns == true)
                    this.game.startGame();
            }
            else{
                System.out.println("File isnt valid");
            }
        });

        loadyesbtn.setOnAction(e-> {
            myStackPane.getChildren().remove(MainlayoutVbox);
            this.game.LoadGame(); 
            this.game.startGame();
        });
        
        return MainlayoutVbox;

    }
    

    /*#########################################################################*/
    /*##                             Exit Popup                              ##*/
    /*#########################################################################*/

    private VBox popupFunction(StackPane myStackPane){

        Image btnExitPopupImg = new Image(getClass().getResourceAsStream("..\\" + CANCEL_IMAGE));
        Image btnAcceptPopupImg = new Image(getClass().getResourceAsStream("..\\" + ACCEPT_IMAGE));
        RedButton btnCancelPopup = new RedButton("No", btnExitPopupImg, 20, 20, 90, 40);
        GreenButton btnAcceptPopup = new GreenButton("Yes", btnAcceptPopupImg, 20, 20, 90, 40);
        btnAcceptPopup.setOnMouseClicked(event -> Platform.exit());

        DeathStarLabel lblExitPopup = new DeathStarLabel("Do You Really Want To Go?", 38);

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

    /*#########################################################################*/
    /*##                              Options Scene                          ##*/
    /*#########################################################################*/

    private VBox OptionsFunction(StackPane myStackPane){

        HBox Options1Line = new HBox();
        DeathStarLabel lblOptionsTitle = new DeathStarLabel("Options", 58);

        Options1Line.getChildren().addAll(lblOptionsTitle);
        Options1Line.setAlignment(Pos.CENTER);
        Options1Line.setSpacing(20);

        HBox Options2Line = new HBox();

        DeathStarLabel lblChangeJourneyTrackerTitle = new DeathStarLabel("Change Journey Tracker", 20);

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

        DeathStarLabel lblChangeHullTrackerTitle = new DeathStarLabel("Change Hull Tracker", 20);

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

        DeathStarLabel lblChangeHealthTrackerTitle = new DeathStarLabel("Change Health Tracker", 20);

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

        Image OptionsUndo = new Image(getClass().getResourceAsStream("..\\" + UNDO_IMAGE));
        Image OptionsSave = new Image(getClass().getResourceAsStream("..\\" + SAVE_IMAGE));

        DefaultButton btnTurnBackOptions = new DefaultButton("Go Back", OptionsUndo, 20 , 20, 120, 40);

        GreenButton btnTurSaveOptions = new GreenButton("Save", OptionsSave, 20 , 20, 90, 40);

        Options8Line.getChildren().addAll(btnTurnBackOptions, btnTurSaveOptions);
        Options8Line.setAlignment(Pos.CENTER);
        Options8Line.setSpacing(20);

        VBox OptionsSceneVBox = new VBox();
        OptionsSceneVBox.getChildren().addAll(Options1Line, Options2Line, Options3Line,
                Options4Line, Options5Line, Options6Line, Options7Line, Options8Line);
        OptionsSceneVBox.getStyleClass().add("ChooseVBox");
        OptionsSceneVBox.setSpacing(20);
        OptionsSceneVBox.setAlignment(Pos.CENTER);

        btnTurnBackOptions.setOnMouseClicked(e -> {
            myStackPane.getChildren().remove(OptionsSceneVBox);
        });

        btnTurSaveOptions.setOnMouseClicked(e -> {
            this.game.setHealthTrackerValue(valueFactory2.getValue());
            System.out.println(this.game.getHealthTrackerHealth());
            this.game.setHullTrackerValue(valueFactory.getValue());

            for (int i = 0; i < 15; i++) {
                if (!this.game.validateJourneyTrackerOption(OptionsText.get(i).getText()))
                    this.game.changeJourney(i, OptionsText.get(i).getText());
            }
            myStackPane.getChildren().remove(OptionsSceneVBox);
        });

        return OptionsSceneVBox;

    }

    /*#########################################################################*/
    /*##                             Score Scene                          ##*/
    /*#########################################################################*/

    private VBox ShowScoreFunction(StackPane myStackPane){

        VBox scoreSceneMainVbox = new VBox();
        HBox scoreTitleHbox = new HBox();
        HBox turnBackScoreHBox = new HBox();
        VBox playersScorebox = new VBox();
        Label lblsPlayersScore = new Label();
        DeathStarLabel lblScoreTitle = new DeathStarLabel("Top Players", 58);

        Image scoreSceneUndo = new Image(getClass().getResourceAsStream("..\\" + UNDO_IMAGE));
        DefaultButton btnTurnBackscoreScene = new DefaultButton("Go Back", scoreSceneUndo, 20, 20, 120, 40);

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

        btnTurnBackscoreScene.setOnMouseClicked(e -> {
            myStackPane.getChildren().remove(scoreSceneMainVbox);
        });

        return scoreSceneMainVbox;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}