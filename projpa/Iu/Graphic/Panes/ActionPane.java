package projpa.Iu.Graphic.Panes;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import projpa.GameLogic.GameLogic;
import projpa.Iu.Graphic.Buttons.DefaultButton;
import projpa.Iu.Graphic.Buttons.GreenButton;
import projpa.Iu.Graphic.Buttons.RedButton;
import projpa.Iu.Graphic.Constants;
import projpa.Iu.Graphic.DeathStarLabel;
import projpa.Iu.Graphic.ShipGps;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Circle;
import projpa.GameLogic.Alien;

public class ActionPane extends StackPane implements Constants, PropertyChangeListener {

    private GameLogic game;
    private BorderPane paneLayout;
    private Image imgExit;
    Image imgNext;
    Image imgSave;
    GreenButton btnSave;
    RedButton btnExit;
    DefaultButton btnNext;
    HBox bottomBox;
    TabPane tabLayout;
    VBox firstTabImage;
    Label firstTabLabel;
    ImageView firstTabIcon;
    Tab firstTab;
    VBox secondTabImage;
    Label secondTabLabel;
    ImageView secondTabIcon;
    Tab secondTab;
    VBox leftBox;
    ImageView pointsIcon;
    Label lblPoints;
    ImageView apIcon;
    Label lblAp;
    ImageView ipIcon;
    Label lblIp;
    HBox topRightBox;
    HBox topLeftBox;
    ArrayList<Label> journeyLabels;
    Region region;
    private VBox rightBox;
    private final int maxnumberofshownalien = 20;
    private ArrayList<Label> alienslabel;
    ArrayList<String> menu;
    Label healthTracker;
    Label HullTracker;
    ImageView healthIcon;
    ImageView hullIcon;

    public ActionPane(GameLogic game) {
        this.game = game;
        this.game.addPropertyChangeListener(this);
        setupComponents();
        propertyChange(null);
    }

    private void setupComponents() {

        this.getChildren().add(ActionPane());

    }

    private StackPane ActionPane(){

        /*#########################################################################*/
        /*##                             Bottom Panel                            ##*/
        /*#########################################################################*/

        imgExit = new Image(getClass().getResourceAsStream("..\\Images\\exit.png"));
        imgNext = new Image(getClass().getResourceAsStream("..\\Images\\next.png"));
        imgSave = new Image(getClass().getResourceAsStream("..\\Images\\save.png"));

        btnSave = new GreenButton("Save", imgSave, 20, 20, 120, 40);
        btnExit = new RedButton("Exit", imgExit, 20, 20, 120, 40);
        btnNext = new DefaultButton("Next", imgNext, 20, 20, 120, 40);

        bottomBox = new HBox(btnSave, btnNext, btnExit);
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(5, 10, 5, 10));
        bottomBox.getStyleClass().add("topBox");

        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);

        /*#########################################################################*/
        /*##                             Left Panel                              ##*/
        /*#########################################################################*/

        tabLayout = new TabPane();
        tabLayout.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        firstTabImage = new VBox();
        firstTabLabel = new Label("Crew 1");

        firstTabLabel.setTextFill(Color.web("#ffffff"));

        firstTabIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\doctor.png")));
        firstTabIcon.setFitWidth(50);
        firstTabIcon.setFitHeight(50);

        firstTabImage.getChildren().addAll(firstTabIcon, firstTabLabel);

        firstTab = new Tab();
        firstTab.setGraphic(firstTabImage);
        tabLayout.getTabs().add(firstTab);

        secondTabImage = new VBox();
        secondTabLabel = new Label("Crew 2");

        secondTabLabel.setTextFill(Color.web("#ffffff"));

        secondTabIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\redShirt.png")));
        secondTabIcon.setFitWidth(50);
        secondTabIcon.setFitHeight(50);

        secondTabImage.getChildren().addAll(secondTabIcon, secondTabLabel);

        secondTab = new Tab();
        secondTab.setGraphic(secondTabImage);
        tabLayout.getTabs().add(secondTab);

        tabLayout.getSelectionModel().select(0);

        leftBox = new VBox(tabLayout);
        leftBox.getStyleClass().add("rightBox");


        leftBox.setAlignment(Pos.TOP_CENTER);


        /*#########################################################################*/
        /*##                              Top Panel                              ##*/
        /*#########################################################################*/


        pointsIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\points.png")));
        pointsIcon.setFitWidth(20);
        pointsIcon.setFitHeight(20);

        lblPoints = new Label(": " + this.game.getUserPoints(), pointsIcon);
        lblPoints.setTextFill(Color.web("#ffffff"));
        lblPoints.getStyleClass().add("PointsLabel");

        apIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\action.png")));
        apIcon.setFitWidth(20);
        apIcon.setFitHeight(20);
        lblAp = new Label(": " + this.game.getActionPoints(), apIcon);
        lblAp.setTextFill(Color.web("#ffffff"));
        lblAp.getStyleClass().add("PointsLabel");

        ipIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\influence.png")));
        ipIcon.setFitWidth(20);
        ipIcon.setFitHeight(20);
        lblIp = new Label(": " + this.game.getInspirationPoints(), ipIcon);
        lblIp.setTextFill(Color.web("#ffffff"));
        lblIp.getStyleClass().add("PointsLabel");

        healthIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\health.png")));
        healthIcon.setFitWidth(20);
        healthIcon.setFitHeight(20);
        healthTracker = new Label(": " + this.game.getHealthTrackerHealth(), healthIcon);
        healthTracker.setTextFill(Color.web("#ffffff"));
        healthTracker.getStyleClass().add("PointsLabel");

        hullIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\hull.png")));
        hullIcon.setFitWidth(20);
        hullIcon.setFitHeight(20);
        HullTracker = new Label(": " + this.game.getHullState(), hullIcon);
        HullTracker.setTextFill(Color.web("#ffffff"));
        HullTracker.getStyleClass().add("PointsLabel");

        topRightBox = new HBox();
        topRightBox.getChildren().addAll(HullTracker, healthTracker, lblPoints, lblAp, lblIp);
        topRightBox.setAlignment(Pos.CENTER_RIGHT);
        topRightBox.setSpacing(10);

        topLeftBox = new HBox();
        topLeftBox.getChildren().addAll();
        topLeftBox.setAlignment(Pos.CENTER_LEFT);

        journeyLabels = new ArrayList<>();

        for (int i = 0; i < 15; i++) {

            journeyLabels.add(new Label(this.game.getJourneyValue(i)));
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

        region = new Region();
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

        rightBox = new VBox();
        rightBox.setPadding(new Insets(5, 10, 5, 10));
        

        menu = new ArrayList<String>();
        alienslabel = new ArrayList<Label>();
        
        for (int i = 0; i< maxnumberofshownalien; i++){ // TEMP -> REVERRR
            
            if(i==0){
                //alienslabel.add(new Label("List Of Aliens:                          "));
                alienslabel.add(new DeathStarLabel("List Of Aliens: ", 20));
                alienslabel.get(i).setVisible(true);
            }
            else{
                alienslabel.add(new Label("Alien1:  "));
                alienslabel.get(i).setTextFill(Color.web("#ffffff"));
                alienslabel.get(i).getStyleClass().add("AliensLabel");
                alienslabel.get(i).setVisible(false);
            }
        }
        
        rightBox.getChildren().addAll(alienslabel);
        rightBox.setSpacing(20);
        rightBox.getStyleClass().add("rightBox");
        rightBox.setAlignment(Pos.TOP_LEFT);

        /*#########################################################################*/
        /*##                              Center Panel                           ##*/
        /*#########################################################################*/

        Image MainshipImage = new Image(getClass().getResourceAsStream("..\\Images\\Spaceship_comrooms.png"));

        Pane MainshipImageView = new Pane ();
        ImageView MainshipImageView2 = new ImageView(MainshipImage);
        // temp -> fit the image in the pain, subject to change
        // main image
        MainshipImageView2.setFitHeight(1300);
        MainshipImageView2.setPreserveRatio(true);


        double panewidth = MainshipImageView.getWidth();
        //width
        MainshipImageView2.setY(HEIGHT /2 - 650); // temp
        MainshipImageView2.setX(WIDTH/2 - 745); // temp


        // background ships animations

        Image Randomship1img = new Image(getClass().getResourceAsStream("..\\Images\\shiprandom1.png"));
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


        Circle test = new Circle();
        test.setRadius(1);
        test.setFill(Color.GREEN);
        
        // beta
        
        double xcenter=((WIDTH- 377)/2) - 29 ;
        double ycenter= ((HEIGHT - 84)/2)- 7 ;
        
        test.setCenterX(xcenter + 120);
        test.setCenterY(ycenter + 150);
        
        //test
        
        ShipGps pdata = new ShipGps(xcenter,ycenter) ;
        
        Image playerimagem = new Image(getClass().getResourceAsStream("..\\Images\\playerstand.png"));
        ImageView playerimage = new ImageView(playerimagem);
        playerimage.setFitHeight(20);
        playerimage.setPreserveRatio(true);
        
        ArrayList<Double> cordstart = new ArrayList<>(); // temp
        cordstart = pdata.getlocationcords(1);
        playerimage.setX(cordstart.get(0));
        playerimage.setY(cordstart.get(1));
        
        double tempxa = cordstart.get(0); double tempya = cordstart.get(1);
        cordstart.clear();
        cordstart = pdata.getlocationcords(8);
        
        
        double rotaionplayer = pdata.calculatedegree(tempxa, tempya, cordstart.get(0), cordstart.get(1));
        playerimage.setRotate(rotaionplayer * 100);
        
        
        cordstart.clear();
        cordstart = pdata.getcords(1,8);
        TranslateTransition playertranstion = new TranslateTransition(Duration.seconds(3),playerimage);
        
        playertranstion.setToX(cordstart.get(0));
        playertranstion.setToY(cordstart.get(1));
        playertranstion.setCycleCount(Animation.INDEFINITE);
        playertranstion.setAutoReverse(true);
        playertranstion.play();
        

        
        // end beta

        // adicionas of people in pane
        MainshipImageView.getChildren().add(Randomship1imgview);
        MainshipImageView.getChildren().add(MainshipImageView2);
        MainshipImageView.getChildren().add(test);
        MainshipImageView.getChildren().add(playerimage);
        
        VBox navyBox = new VBox();
        navyBox.getChildren().addAll();

        BorderPane centerBox = new BorderPane();
        centerBox.setCenter(MainshipImageView);
        centerBox.getStyleClass().add("centerBox");
        centerBox.setPadding(new Insets(10, 20, 10, 20));

        /*#########################################################################*/
        /*##                              Main Panel                             ##*/
        /*#########################################################################*/

        paneLayout = new BorderPane();
        paneLayout.setCenter(centerBox);
        paneLayout.setBottom(bottomBox);
        paneLayout.setLeft(leftBox);
        paneLayout.setTop(topBox);
        paneLayout.setRight(rightBox);
        paneLayout.getStyleClass().add("ChooseVBox");

        paneLayout.setBackground(new Background(new BackgroundFill(Color.web("#606060"),
                CornerRadii.EMPTY, Insets.EMPTY)));


        /*#########################################################################*/
        /*##                            Action Scene                             ##*/
        /*#########################################################################*/


        btnExit.setOnMouseClicked(e -> {
            this.getChildren().add(popupFunctionScene(this));
        });

        StackPane actionStackPane = new StackPane(paneLayout);
        actionStackPane.setAlignment(Pos.CENTER);
        
        btnNext.setOnAction(e -> {
            this.game.nextTurn();
            updaterightbox();
            refreshJourneyTracker();
            
        }); // next phase
        
        btnSave.setOnAction(e -> this.game.SaveGame());// save
        
        
        
        return actionStackPane;
        
    }

    private VBox popupFunctionScene(StackPane myStackPane){

        /*#########################################################################*/
        /*##                             Exit Popup                              ##*/
        /*#########################################################################*/

        Image btnExitPopupImg = new Image(getClass().getResourceAsStream("..\\Images\\cancel.png"));
        Image btnAcceptPopupImg = new Image(getClass().getResourceAsStream("..\\Images\\accept.png"));
        RedButton btnCancelPopup = new RedButton("No", btnExitPopupImg , 20, 20, 90, 40);
        GreenButton btnAcceptPopup = new GreenButton("Yes", btnAcceptPopupImg, 20 ,20, 90,40);
        DeathStarLabel lblExitPopup = new DeathStarLabel("Did you want play again?", 38);

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
        btnCancelPopup.setOnMouseClicked(e -> {
            myStackPane.getChildren().remove(exitPopup);
            this.game.endGame2();
        });
        btnAcceptPopup.setOnMouseClicked(event -> {
            this.game.newGame();
            myStackPane.getChildren().remove(exitPopup);
        });

        return exitPopup;

    }

    private void refreshJourneyTracker(){
        if (this.game.inAwaitCrewPhaseActions() || this.game.inAwaitRestPhaseActions()){
            for (int i = 0; i < 15; i++) {
                journeyLabels.get(i).getStyleClass().remove("journeyLabelsCenter");
                journeyLabels.get(i).getStyleClass().remove("journeyLabelsCenterActive");
                journeyLabels.get(i).getStyleClass().add("journeyLabelsCenter");
            }
            journeyLabels.get(0).getStyleClass().add("journeyLabelsLeft");
            journeyLabels.get(14).getStyleClass().add("journeyLabelsRight");
            journeyLabels.get(this.game.getJourneyState()).getStyleClass().add("journeyLabelsCenterActive");
        }
    }

    private void refreshLeftPanel(){

        DeathStarLabel Crew1Name = new DeathStarLabel(this.game.getCrewmember1Name(), 20);
        Crew1Name.setPadding(new Insets(20, 0, 0,0));
        DeathStarLabel Crew2Name = new DeathStarLabel(this.game.getCrewmember2Name(), 20);
        Crew2Name.setPadding(new Insets(20, 0, 0,0));

        DeathStarLabel LCrew1Local = new DeathStarLabel("Room:  " + this.game.getCrewmember1RoomName(), 15);
        DeathStarLabel LCrew2Local = new DeathStarLabel("Room:  " + this.game.getCrewmember2RoomName(), 15);

        DeathStarLabel ACrew1Local = new DeathStarLabel("Atack:  " + this.game.getCrewMember1Atack(), 15);
        DeathStarLabel ACrew2Local = new DeathStarLabel("Atack:  " + this.game.getCrewMember2Atack(), 15);

        DeathStarLabel MCrew1Local = new DeathStarLabel("Movement:  " + this.game.getCrewMember1Move(), 15);
        DeathStarLabel MCrew2Local = new DeathStarLabel("Movement:  " + this.game.getCrewMember2Move(), 15);

        DefaultButton btnMoveCrew1 = new DefaultButton("Move", new Image(getClass().getResourceAsStream("..\\Images\\move.png")), 20 , 20, 120, 40);
        DefaultButton btnAtackCrew1 = new DefaultButton("Atack", new Image(getClass().getResourceAsStream("..\\Images\\sword.png")), 20 , 20, 120, 40);
        DefaultButton btnHealCrew1 = new DefaultButton("Heal", new Image(getClass().getResourceAsStream("..\\Images\\potion.png")), 20 , 20, 120, 40);
        DefaultButton btnSacrificeCrew1 = new DefaultButton("Sacrifice", new Image(getClass().getResourceAsStream("..\\Images\\death.png")), 20 , 20, 120, 40);

        VBox firstCrewVBox = new VBox();
        firstCrewVBox.getChildren().addAll(btnMoveCrew1, btnAtackCrew1, btnHealCrew1, btnSacrificeCrew1);
        firstCrewVBox.setAlignment(Pos.CENTER);
        firstCrewVBox.setSpacing(20);

        DefaultButton btnMoveCrew2 = new DefaultButton("Move", new Image(getClass().getResourceAsStream("..\\Images\\move.png")), 20 , 20, 120, 40);
        DefaultButton btnAtackCrew2 = new DefaultButton("Atack", new Image(getClass().getResourceAsStream("..\\Images\\sword.png")), 20 , 20, 120, 40);
        DefaultButton btnHealCrew2 = new DefaultButton("Heal", new Image(getClass().getResourceAsStream("..\\Images\\potion.png")), 20 , 20, 120, 40);
        DefaultButton btnSacrificeCrew2 = new DefaultButton("Sacrifice", new Image(getClass().getResourceAsStream("..\\Images\\death.png")), 20 , 20, 120, 40);

        if (!this.game.getCrewmember1Name().equals("Doctor"))
            btnHealCrew1.setDisable(true);
        if (!this.game.getCrewmember2Name().equals("Doctor"))
            btnHealCrew2.setDisable(true);

        if (!this.game.getCrewmember1Name().equals("Red Shirt"))
            btnSacrificeCrew1.setDisable(true);
        if (!this.game.getCrewmember2Name().equals("Red Shirt"))
            btnSacrificeCrew2.setDisable(true);

        VBox secondCrewVBox = new VBox();
        secondCrewVBox.getChildren().addAll(btnMoveCrew2, btnAtackCrew2, btnHealCrew2, btnSacrificeCrew2);
        secondCrewVBox.setAlignment(Pos.CENTER);
        secondCrewVBox.setSpacing(20);

        VBox firstTabVbox = new VBox();
        firstTabVbox.getChildren().addAll(Crew1Name, LCrew1Local, ACrew1Local, MCrew1Local, firstCrewVBox);
        firstTabVbox.setAlignment(Pos.TOP_CENTER);
        firstTabVbox.setPadding(new Insets(0,0,0,5));
        firstTabVbox.setSpacing(20);

        VBox secondTabVbox = new VBox();
        secondTabVbox.getChildren().addAll(Crew2Name, LCrew2Local, ACrew2Local, MCrew2Local, secondCrewVBox);
        secondTabVbox.setAlignment(Pos.TOP_CENTER);
        secondTabVbox.setSpacing(20);
        secondTabVbox.setPadding(new Insets(0,0,0,5));

        secondTab.setContent(secondTabVbox);
        firstTab.setContent(firstTabVbox);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (this.game.inAwaitCrewPhaseActions() || this.game.inAwaitRestPhaseActions()){
            refreshJourneyTracker();
            refreshLeftPanel();
        }

        setVisible(this.game.inAwaitCrewPhaseActions() || this.game.inAwaitRestPhaseActions());
        updaterightbox();

    }

    private void updaterightbox() {
        
        List<Alien> returnalien = new ArrayList<Alien>(); 
        returnalien = this.game.getalienarraycopy();
        
        for (int j = 1; j < maxnumberofshownalien ; j++){
            alienslabel.get(j).setVisible(false);
        }
        
        for (int i = 0; i < returnalien.size() ; i++){ // update alien screen
            
            if(i < maxnumberofshownalien - 1)
            
            {
            alienslabel.get(i+1).setText("Alien  " + (i+1) + "  in:  " + returnalien.get(i).getRoom().getName());
            alienslabel.get(i+1).setVisible(true);
            
            }
        }
        
        //lblPoints.setText(": " + this.game.getPoints()); // point screen
        lblPoints.setText(": " + this.game.getJourneyState());
        
        lblIp.setText(": " + this.game.getInspirationPoints()); // inspirationpoint screen
        
        lblAp.setText(": " + this.game.getActionPoints()); // action point screen
        
        
        for (int i = 0; i < 15; i++) { // Jouney tracker update
                   
            journeyLabels.get(i).setText(this.game.getJourneyValue(i));

        }
        
        
        
        
    }
}
