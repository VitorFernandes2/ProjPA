package projpa.Iu.Graphic.Panes;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
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
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.scene.Node;

import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import projpa.GameLogic.Alien;
import projpa.GameLogic.General.General;

public class ActionPane extends StackPane implements Constants, PropertyChangeListener {

    private GameLogic game;
    private BorderPane paneLayout;
    private Image imgExit;
    private Image imgNext;
    private Image imgSavecost;
    private Image imgSave;
    private GreenButton btnSave;
    private GreenButton btnSavecost;
    private RedButton btnExit;
    private DefaultButton btnNext;
    private HBox bottomBox;
    private TabPane tabLayout;
    private VBox firstTabImage;
    private Label firstTabLabel;
    private ImageView firstTabIcon;
    private Tab firstTab;
    private VBox secondTabImage;
    private Label secondTabLabel;
    private ImageView secondTabIcon;
    private Tab secondTab;
    private VBox leftBox;
    private ImageView pointsIcon;
    private Label lblPoints;
    private ImageView apIcon;
    private Label lblAp;
    private ImageView ipIcon;
    private Label lblIp;
    private HBox topRightBox;
    private HBox topLeftBox;
    private ArrayList<Label> journeyLabels;
    private Region region;
    private VBox rightBox;
    private final int maxnumberofshownalien = 15;
    private ArrayList<Label> alienslabel;
    private ArrayList<String> menu;
    private Label healthTracker;
    private Label HullTracker;
    private ImageView healthIcon;
    private ImageView hullIcon;
    private VBox secondCrewVBox;
    private VBox firstCrewVBox;
    private ComboBox<Integer> chooseRoomMove1;
    private ComboBox<Integer> chooseRoomMove2;
    private ArrayList<VBox> getarrayindicators;
    private ArrayList<ImageView> sealedroomarray;
    private ArrayList<ImageView> particledisperserarray;
    private ArrayList<ImageView> organidetonatorarray;
    private ImageView playerimage;

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
        imgSavecost = new Image(getClass().getResourceAsStream("..\\Images\\options.png"));
        
        btnSave = new GreenButton("Save", imgSave, 20, 20, 120, 40);
        btnSavecost = new GreenButton("", imgSavecost, 20, 20, 40, 40);
        btnExit = new RedButton("Exit", imgExit, 20, 20, 120, 40);
        btnNext = new DefaultButton("Next", imgNext, 20, 20, 120, 40);

        bottomBox = new HBox();
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(5, 10, 5, 10));
        bottomBox.getStyleClass().add("topBox");

        HBox leftBottomBox = new HBox();
        leftBottomBox.setAlignment(Pos.CENTER_LEFT);

        DefaultButton sealRoom = new DefaultButton("Seal Room", new Image(getClass().getResourceAsStream("..\\Images\\seal.png")), 20, 20, 140, 40);
        DefaultButton ParticleDisperser = new DefaultButton("Place Particle Disperser", new Image(getClass().getResourceAsStream("..\\Images\\particle.png")), 20, 20, 260, 40);
        DefaultButton OrganicDetonator = new DefaultButton("Place Organic Detonator", new Image(getClass().getResourceAsStream("..\\Images\\seal.png")), 20, 20, 260, 40);
        DefaultButton Detonate = new DefaultButton("Detonate Particle Disperser", new Image(getClass().getResourceAsStream("..\\Images\\detonator.png")), 20, 20, 280, 40);

        leftBottomBox.getChildren().addAll(sealRoom, ParticleDisperser, OrganicDetonator, Detonate);
        leftBottomBox.setSpacing(10);

        sealRoom.setOnMouseClicked(e -> {
            disableMove();
            if (this.game.canSealRoom())
                sealRoom();
        });

        ParticleDisperser.setOnMouseClicked(e -> {
            disableMove();
            if (this.game.hasParticleDispersersToPlace())
                placeParticle(this);
        });

        OrganicDetonator.setOnMouseClicked(e -> {
            disableMove();
            if (this.game.hasOrganicDetonatorToPlace())
                placeOrganic(this);
        });

        Detonate.setOnMouseClicked(e -> {
            disableMove();
            if (this.game.hasParticleDisperserToExplode())
                detonate(this);
        });

        region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        HBox rigthBottomBox = new HBox(btnSave,btnSavecost, btnNext, btnExit);
        rigthBottomBox.setAlignment(Pos.CENTER_RIGHT);
        rigthBottomBox.setSpacing(10);
        bottomBox.getChildren().addAll(leftBottomBox, region,rigthBottomBox);

        bottomBox.setAlignment(Pos.CENTER);

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

        secondTabIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\doctor.png")));
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
        
        
        
        Image Randomship1img2 = new Image(getClass().getResourceAsStream("..\\Images\\8-bit-spaceship-6.png"));
        ImageView Randomship1imgview2 = new ImageView(Randomship1img2);
        // set off screen and correct size and rotation
        Randomship1imgview2.setLayoutX(-2000);
        Randomship1imgview2.setLayoutY(500);
        Randomship1imgview2.setFitHeight(50);
        Randomship1imgview2.setPreserveRatio(true);
        Randomship1imgview2.setRotate(86);

        TranslateTransition transtion2 = new TranslateTransition(Duration.seconds(12),Randomship1imgview2);
        //transtion.setDuration(Duration.seconds(3));
        transtion2.setToX(8000);
        transtion2.setToY(-500);
        transtion2.setCycleCount(Animation.INDEFINITE);
        transtion2.play();

        

        Circle test = new Circle(); // main image debug
        test.setRadius(1);
        test.setFill(Color.GREEN);
        test.setVisible(false);
        
        // beta
        
        double xcenter=((WIDTH- 377)/2) - 29 ;
        double ycenter= ((HEIGHT - 84)/2)- 7 ;
        
        test.setCenterX(xcenter);
        test.setCenterY(ycenter);
        
        //test
        
        ShipGps pdata = new ShipGps(xcenter,ycenter) ;
        
        Image playerimagem = new Image(getClass().getResourceAsStream("..\\Images\\playerstand.png"));
        playerimage = new ImageView(playerimagem);
        playerimage.setFitHeight(15);
        playerimage.setPreserveRatio(true);
        playerimage.setVisible(false);
        
        ArrayList<Double> cordstart = new ArrayList<>(); // temp
        cordstart = pdata.getlocationcords(1);
        //playerimage.setX(cordstart.get(0));
        //playerimage.setY(cordstart.get(1));
        
        double tempxa = cordstart.get(0); double tempya = cordstart.get(1);
        cordstart.clear();
        cordstart = pdata.getlocationcords(8);
        
        
        double rotaionplayer = pdata.calculatedegree(tempxa, tempya, cordstart.get(0), cordstart.get(1));
        //playerimage.setRotate(180 - (rotaionplayer * 100));
        
        
        cordstart.clear();
        cordstart = pdata.getcords(1,8);
        TranslateTransition playertranstion = new TranslateTransition(Duration.seconds(3),playerimage);
        
        playertranstion.setToX(cordstart.get(0));
        playertranstion.setToY(cordstart.get(1));
        playertranstion.setCycleCount(Animation.INDEFINITE);
        playertranstion.setAutoReverse(true);
        //playertranstion.play();
        
        getarrayindicators = new ArrayList<VBox>();
        getarrayindicators = pdata.setbackgroundstats(xcenter,ycenter);
        
        
        
        Image closeimagem = new Image(getClass().getResourceAsStream("..\\Images\\cancel2.png"));
        sealedroomarray = new ArrayList<ImageView>();
        
        
        for (int i=0 ; i < 12;i++){
            
            sealedroomarray.add(new ImageView(closeimagem));
            ArrayList<Double> returncloselocal = new ArrayList<Double>();
            returncloselocal = pdata.getlocationlocked(i+1);
            sealedroomarray.get(i).setFitHeight(20);
            sealedroomarray.get(i).setPreserveRatio(true);
            sealedroomarray.get(i).setLayoutX(returncloselocal.get(0));
            sealedroomarray.get(i).setLayoutY(returncloselocal.get(1));
            sealedroomarray.get(i).setVisible(false);
        }
        
        Image closeimagem2 = new Image(getClass().getResourceAsStream("..\\Images\\detonator3.png"));
        particledisperserarray = new ArrayList<ImageView>();
        
        for (int i=0 ; i < 12;i++){
            
            particledisperserarray.add(new ImageView(closeimagem2));
            ArrayList<Double> returncloselocal = new ArrayList<Double>();
            returncloselocal = pdata.getlocationlocked(i+1);
            particledisperserarray.get(i).setFitHeight(20);
            particledisperserarray.get(i).setPreserveRatio(true);
            particledisperserarray.get(i).setLayoutX(returncloselocal.get(0));
            particledisperserarray.get(i).setLayoutY(returncloselocal.get(1));
            particledisperserarray.get(i).setVisible(false);
        }
        
        Image closeimagem3 = new Image(getClass().getResourceAsStream("..\\Images\\detonator2.png"));
        organidetonatorarray = new ArrayList<ImageView>();
        
        for (int i=0 ; i < 12;i++){
            
            organidetonatorarray.add(new ImageView(closeimagem3));
            ArrayList<Double> returncloselocal = new ArrayList<Double>();
            returncloselocal = pdata.getlocationlocked(i+1);
            organidetonatorarray.get(i).setFitHeight(20);
            organidetonatorarray.get(i).setPreserveRatio(true);
            organidetonatorarray.get(i).setLayoutX(returncloselocal.get(0));
            organidetonatorarray.get(i).setLayoutY(returncloselocal.get(1));
            organidetonatorarray.get(i).setVisible(false);
        }
        
        // end beta

        // adicionas of people in pane
        MainshipImageView.getChildren().add(Randomship1imgview);
        MainshipImageView.getChildren().add(Randomship1imgview2);
        MainshipImageView.getChildren().add(MainshipImageView2);
        MainshipImageView.getChildren().add(test);
        MainshipImageView.getChildren().add(playerimage);
        MainshipImageView.getChildren().addAll(getarrayindicators);//test
        MainshipImageView.getChildren().addAll(sealedroomarray);
        MainshipImageView.getChildren().addAll(particledisperserarray);
        MainshipImageView.getChildren().addAll(organidetonatorarray);

       
       
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
            disableMove();
            this.getChildren().add(popupFunctionScene(this));
        });

        StackPane actionStackPane = new StackPane(paneLayout);
        actionStackPane.setAlignment(Pos.CENTER);
        
        btnNext.setOnAction(e -> {
            disableMove();
            this.game.nextTurn();
            updaterightbox();
            refreshJourneyTracker();
            
        }); // next phase
        
        btnSave.setOnAction(e -> {
            disableMove();
            this.game.saveGame();
            this.game.SaveGame();
            this.getChildren().add(popupsavegame(this));
            
        });// save
        
        btnSavecost.setOnAction(e -> {
            
            disableMove();
            this.game.saveGame();
            
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("..\\"));
            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fc.getExtensionFilters().add(extFilter);
            File selectedFile = fc.showSaveDialog(null);
            
            if (selectedFile!= null){
                this.game.SaveGame(selectedFile.getAbsolutePath()); 
                this.getChildren().add(popupsavegame(this));  
            }
            else{
                System.out.println("File isnt valid");
            }
        
        });
        
        firstTab.setOnSelectionChanged(e -> {
            disableMove();
        });

        secondTab.setOnSelectionChanged(e -> {
            disableMove();
        });

        return actionStackPane;
        
    }

    private void disableMove(){
        this.chooseRoomMove1.setVisible(false);
        this.chooseRoomMove2.setVisible(false);
    }

    private void sealRoom(){
        BorderPane paneLayoutRoomChoice = new BorderPane();

        VBox mainVboxRoomChoice = new VBox();

        ArrayList<Image> RoomImage = new ArrayList<>();

        for (int i = 0; i < 12; i++) {

            switch (i){
                case 0:
                    RoomImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Rooms\\Room1.png")));
                    break;
                case 1:
                    RoomImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Rooms\\Room2.png")));
                    break;
                case 2:
                    RoomImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Rooms\\Room3.png")));
                    break;
                case 3:
                    RoomImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Rooms\\Room4.png")));
                    break;
                case 4:
                    RoomImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Rooms\\Room5.png")));
                    break;
                case 5:
                    RoomImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Rooms\\Room6.png")));
                    break;
                case 6:
                    RoomImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Rooms\\Room7.png")));
                    break;
                case 7:
                    RoomImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Rooms\\Room8.png")));
                    break;
                case 8:
                    RoomImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Rooms\\Room9.png")));
                    break;
                case 9:
                    RoomImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Rooms\\Room10.png")));
                    break;
                case 10:
                    RoomImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Rooms\\Room11.png")));
                    break;
                case 11:
                    RoomImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Rooms\\Room12.png")));
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

        Label lblChooseRooms = new Label("Seal Room");
        lblChooseRooms.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblChooseRooms.setTextFill(Color.web("#ffffff"));

        ImageView ChooseRoomsUndo = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\undo.png")));
        ChooseRoomsUndo.setFitHeight(20);
        ChooseRoomsUndo.setFitWidth(20);

        mainVboxRoomChoice.getChildren().setAll(lblChooseRooms, firstLineRoomsHbox, secondLineRoomssHbox, thirdLineRoomsHbox);
        mainVboxRoomChoice.setAlignment(Pos.CENTER);
        mainVboxRoomChoice.setSpacing(20);

        mainVboxRoomChoice.getStyleClass().add("ChooseVBox");

        for (int i = 0; i < 12; i++) {
            if (this.game.roomsToSeal().contains(i))
                RoomsImgHbox.get(i).setVisible(false);

            final int j = i;
            RoomsImgHbox.get(i).setOnMouseClicked(e -> {
                this.game.sealRoom(j);
                this.game.sealRoom(j);
                this.getChildren().remove(paneLayoutRoomChoice);
            });

        }

        paneLayoutRoomChoice.setCenter(mainVboxRoomChoice);

        this.getChildren().add(paneLayoutRoomChoice);

    }

    private void placeParticle(StackPane pane){
        BorderPane paneLayoutRoomChoice = new BorderPane();

        VBox mainVboxRoomChoice = new VBox();

        ComboBox<Integer> chooseRoomMove1;
        ComboBox<Integer> chooseRoomMove2;

        chooseRoomMove1 = new ComboBox<>(FXCollections.observableArrayList(game.getUnplacedParticleDisperser()));
        chooseRoomMove2 = new ComboBox<>(FXCollections.observableArrayList(game.getAvailableTrapRooms()));

        chooseRoomMove2.setVisible(false);

        HBox firstLineRoomsHbox = new HBox(chooseRoomMove1);
        firstLineRoomsHbox.setSpacing(40);
        firstLineRoomsHbox.setAlignment(Pos.CENTER);

        HBox secondLineRoomssHbox = new HBox(chooseRoomMove2);
        secondLineRoomssHbox.setSpacing(40);
        secondLineRoomssHbox.setAlignment(Pos.CENTER);

        Label lblChooseRooms = new Label("Place Particle Disperser");
        lblChooseRooms.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblChooseRooms.setTextFill(Color.web("#ffffff"));

        mainVboxRoomChoice.getChildren().setAll(lblChooseRooms, firstLineRoomsHbox, secondLineRoomssHbox);
        mainVboxRoomChoice.setAlignment(Pos.CENTER);
        mainVboxRoomChoice.setSpacing(20);

        mainVboxRoomChoice.getStyleClass().add("ChooseVBox");

        chooseRoomMove1.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {

                boolean visibi = chooseRoomMove2.isVisible();

                if (visibi)
                    visibi = false;
                else
                    visibi = true;

                chooseRoomMove2.setVisible(visibi);
            }
        });

        chooseRoomMove2.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {

                game.setTrap(newValue, chooseRoomMove1.getValue());
                game.setTrap(newValue, chooseRoomMove1.getValue());

                pane.getChildren().remove(paneLayoutRoomChoice);

            }
        });

        paneLayoutRoomChoice.setCenter(mainVboxRoomChoice);

        this.getChildren().add(paneLayoutRoomChoice);

    }

    private void placeOrganic(StackPane pane){
        BorderPane paneLayoutRoomChoice = new BorderPane();

        VBox mainVboxRoomChoice = new VBox();

        ComboBox<Integer> chooseRoomMove1;
        ComboBox<Integer> chooseRoomMove2;

        chooseRoomMove1 = new ComboBox<>(FXCollections.observableArrayList(this.game.getUnplacedOrganicDetonaters()));
        chooseRoomMove2 = new ComboBox<>(FXCollections.observableArrayList(this.game.getAvailableTrapRooms()));

        chooseRoomMove2.setVisible(false);

        HBox firstLineRoomsHbox = new HBox(chooseRoomMove1);
        firstLineRoomsHbox.setSpacing(40);
        firstLineRoomsHbox.setAlignment(Pos.CENTER);

        HBox secondLineRoomssHbox = new HBox(chooseRoomMove2);
        secondLineRoomssHbox.setSpacing(40);
        secondLineRoomssHbox.setAlignment(Pos.CENTER);

        Label lblChooseRooms = new Label("Place  Organic  Detonator");
        lblChooseRooms.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblChooseRooms.setTextFill(Color.web("#ffffff"));

        mainVboxRoomChoice.getChildren().setAll(lblChooseRooms, firstLineRoomsHbox, secondLineRoomssHbox);
        mainVboxRoomChoice.setAlignment(Pos.CENTER);
        mainVboxRoomChoice.setSpacing(20);

        chooseRoomMove1.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {

                boolean visibi = chooseRoomMove2.isVisible();

                if (visibi)
                    visibi = false;
                else
                    visibi = true;

                chooseRoomMove2.setVisible(visibi);
            }
        });

        chooseRoomMove2.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {

                game.setTrap(newValue, chooseRoomMove1.getValue());
                game.setTrap(newValue, chooseRoomMove1.getValue());

                pane.getChildren().remove(paneLayoutRoomChoice);

            }
        });

        mainVboxRoomChoice.getStyleClass().add("ChooseVBox");
        paneLayoutRoomChoice.setCenter(mainVboxRoomChoice);

        this.getChildren().add(paneLayoutRoomChoice);

    }

    private void detonate(StackPane pane){
        BorderPane paneLayoutRoomChoice = new BorderPane();

        VBox mainVboxRoomChoice = new VBox();

        ComboBox<Integer> chooseRoomMove2;
        chooseRoomMove2 = new ComboBox<>(FXCollections.observableArrayList(this.game.getPlacedParticleDisperser2()));

        HBox firstLineRoomsHbox = new HBox(chooseRoomMove2);
        firstLineRoomsHbox.setSpacing(40);
        firstLineRoomsHbox.setAlignment(Pos.CENTER);

        Label lblChooseRooms = new Label("Detonate Particle Disperser");
        lblChooseRooms.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblChooseRooms.setTextFill(Color.web("#ffffff"));

        mainVboxRoomChoice.getChildren().setAll(lblChooseRooms, firstLineRoomsHbox);
        mainVboxRoomChoice.setAlignment(Pos.CENTER);
        mainVboxRoomChoice.setSpacing(20);

        mainVboxRoomChoice.getStyleClass().add("ChooseVBox");

        chooseRoomMove2.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {

                game.detonateParticleDisperser(newValue);
                pane.getChildren().remove(paneLayoutRoomChoice);

            }
        });

        paneLayoutRoomChoice.setCenter(mainVboxRoomChoice);

        this.getChildren().add(paneLayoutRoomChoice);

    }
    
    private HBox popupsavegame(StackPane myStackPane){

        /*#########################################################################*/
        /*##                        Save Game Popup                              ##*/
        /*#########################################################################*/

        DeathStarLabel lblExitPopup = new DeathStarLabel("Game Saved", 38);
        
        HBox topOfPopup = new HBox(lblExitPopup);
        topOfPopup.setSpacing(20);
        topOfPopup.setAlignment(Pos.CENTER);

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished( e -> myStackPane.getChildren().remove(topOfPopup) );
        delay.play();
        
        
        myStackPane.getChildren().remove(topOfPopup);
        
        return topOfPopup;

    }

    private VBox popupFunctionScene(StackPane myStackPane){

        /*#########################################################################*/
        /*##                             Exit Popup                              ##*/
        /*#########################################################################*/

        Image btnExitPopupImg = new Image(getClass().getResourceAsStream("..\\Images\\cancel.png"));
        Image btnAcceptPopupImg = new Image(getClass().getResourceAsStream("..\\Images\\accept.png"));
        RedButton btnCancelPopup = new RedButton("No", btnExitPopupImg , 20, 20, 90, 40);
        GreenButton btnAcceptPopup = new GreenButton("Yes", btnAcceptPopupImg, 20 ,20, 90,40);
        DeathStarLabel lblExitPopup = new DeathStarLabel("Do you want play again?", 38);

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
                journeyLabels.get(i).setText(this.game.getJourneyValue(i));
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
        DefaultButton btnreapairhull1 = new DefaultButton("Fix Hull", new Image(getClass().getResourceAsStream("..\\Images\\hullrepair.png")), 20 , 20, 120, 40);

        firstCrewVBox = new VBox();
        firstCrewVBox.getChildren().addAll(btnMoveCrew1, btnAtackCrew1, btnHealCrew1, btnSacrificeCrew1,btnreapairhull1);
        firstCrewVBox.setAlignment(Pos.CENTER);
        firstCrewVBox.setSpacing(20);

        DefaultButton btnMoveCrew2 = new DefaultButton("Move", new Image(getClass().getResourceAsStream("..\\Images\\move.png")), 20 , 20, 120, 40);
        DefaultButton btnAtackCrew2 = new DefaultButton("Atack", new Image(getClass().getResourceAsStream("..\\Images\\sword.png")), 20 , 20, 120, 40);
        DefaultButton btnHealCrew2 = new DefaultButton("Heal", new Image(getClass().getResourceAsStream("..\\Images\\potion.png")), 20 , 20, 120, 40);
        DefaultButton btnSacrificeCrew2 = new DefaultButton("Sacrifice", new Image(getClass().getResourceAsStream("..\\Images\\death.png")), 20 , 20, 120, 40);
        DefaultButton btnreapairhull2 = new DefaultButton("Fix Hull", new Image(getClass().getResourceAsStream("..\\Images\\hullrepair.png")), 20 , 20, 120, 40);

        if (!this.game.getCrewmember1Name().equals("Doctor"))
            btnHealCrew1.setDisable(true);
        if (!this.game.getCrewmember2Name().equals("Doctor"))
            btnHealCrew2.setDisable(true);
        
        if (!this.game.getCrewmember1Name().equals("Engineer"))
            btnreapairhull1.setDisable(true);
        if (!this.game.getCrewmember2Name().equals("Engineer"))
            btnreapairhull2.setDisable(true);

        if (!this.game.getCrewmember1Name().equals("Red Shirt"))
            btnSacrificeCrew1.setDisable(true);
        if (!this.game.getCrewmember2Name().equals("Red Shirt"))
            btnSacrificeCrew2.setDisable(true);

        secondCrewVBox = new VBox();
        secondCrewVBox.getChildren().addAll(btnMoveCrew2, btnAtackCrew2, btnHealCrew2, btnSacrificeCrew2,btnreapairhull2);
        secondCrewVBox.setAlignment(Pos.CENTER);
        secondCrewVBox.setSpacing(20);

        chooseRoomMove1 = new ComboBox<>();
        chooseRoomMove1.setVisible(false);

        chooseRoomMove2 = new ComboBox<>();
        chooseRoomMove2.setVisible(false);

        VBox firstTabVbox = new VBox();
        firstTabVbox.getChildren().addAll(Crew1Name, LCrew1Local, ACrew1Local, MCrew1Local, firstCrewVBox, chooseRoomMove1);
        firstTabVbox.setAlignment(Pos.TOP_CENTER);
        firstTabVbox.setPadding(new Insets(0,0,0,5));
        firstTabVbox.setSpacing(20);

        VBox secondTabVbox = new VBox();
        secondTabVbox.getChildren().addAll(Crew2Name, LCrew2Local, ACrew2Local, MCrew2Local, secondCrewVBox, chooseRoomMove2);
        secondTabVbox.setAlignment(Pos.TOP_CENTER);
        secondTabVbox.setSpacing(20);
        secondTabVbox.setPadding(new Insets(0,0,0,5));

        btnAtackCrew1.setOnMouseClicked(e -> {
            disableMove();
            ArrayList<Integer> attackresults;
            attackresults = this.game.atack(0);
            if(attackresults.size()==0)
                attackresults = this.game.atack(0);
            this.getChildren().add(Dicescreenresult(this,attackresults));
            this.updaterightbox();
            updateindicators();
        });

        btnAtackCrew2.setOnMouseClicked(e -> {
            disableMove();
            ArrayList<Integer> attackresults;
            attackresults = this.game.atack(1);
            if(attackresults.size()==0)
                attackresults = this.game.atack(1);
            this.getChildren().add(Dicescreenresult(this,attackresults));
            this.updaterightbox();
            updateindicators();
        });

        btnHealCrew1.setOnMouseClicked(e -> {
            disableMove();
            this.game.heal();
            this.updaterightbox();
        });

        btnHealCrew2.setOnMouseClicked(e -> {
            disableMove();
            this.game.heal();
            this.updaterightbox();
        });
        
        btnreapairhull1.setOnAction(e -> {
            disableMove();
            this.game.fixOneHull();
            this.updaterightbox();
        });
        
        btnreapairhull2.setOnAction(e -> {
            disableMove();
            this.game.fixOneHull();
            this.updaterightbox();
        });
        

        btnMoveCrew1.setOnMouseClicked(e -> {
            String oldlocation= this.game.getCrewmember1RoomName();
            disableMove();
            firstCrewVBox.getChildren().remove(chooseRoomMove1);
            chooseRoomMove1 = new ComboBox<>(FXCollections.observableArrayList(this.game.getNearAvailableRooms(0)));
            firstCrewVBox.getChildren().add(chooseRoomMove1);
            chooseRoomMove1.valueProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                    game.move(0, newValue);
                    game.move(0, newValue);
                    firstCrewVBox.getChildren().remove(chooseRoomMove1);
                    domoveanimationplayer(1,oldlocation);
                }
            });
        });

        btnMoveCrew2.setOnMouseClicked(e -> {
            String oldlocation= this.game.getCrewmember2RoomName();
            disableMove();
            secondCrewVBox.getChildren().remove(chooseRoomMove2);
            chooseRoomMove2 = new ComboBox<>(FXCollections.observableArrayList(this.game.getNearAvailableRooms(1)));
            secondCrewVBox.getChildren().add(chooseRoomMove2);
            chooseRoomMove2.valueProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                    game.move(1, newValue);
                    game.move(1, newValue);
                    secondCrewVBox.getChildren().remove(chooseRoomMove2);
                    domoveanimationplayer(2,oldlocation);
                }
            });
        });

        btnSacrificeCrew1.setOnMouseClicked(e -> {
            disableMove();
            this.game.redShirtSpecial();
            LCrew2Local.setVisible(false);
            this.firstCrewVBox.setDisable(true);
            updateindicators();
        });

        btnSacrificeCrew2.setOnMouseClicked(e -> {
            disableMove();
            this.game.redShirtSpecial();
            LCrew2Local.setVisible(false);
            this.secondCrewVBox.setDisable(true);
            updateindicators();
        });

        secondTab.setContent(secondTabVbox);
        firstTab.setContent(firstTabVbox);

    }
    
    private VBox Dicescreenresult(StackPane myStackPane,ArrayList<Integer> attackresults){

        /*#########################################################################*/
        /*##                        Dice Game Popup                              ##*/
        /*#########################################################################*/

        DeathStarLabel lblExitPopup = new DeathStarLabel("Dice", 38);
        VBox topOfPopup = new VBox();
        
        if (attackresults.size()==0){
            
                Label Dicevalue = new Label("Unable to do any action");
                Dicevalue.setFont(Font.font("Death Star", FontWeight.MEDIUM, 38));
                Dicevalue.setTextFill(Color.web("#ffffff"));
            
               
                topOfPopup.getChildren().addAll(lblExitPopup,Dicevalue);
                topOfPopup.setSpacing(20);
                topOfPopup.setAlignment(Pos.CENTER);

                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished( e -> myStackPane.getChildren().remove(topOfPopup) );
                delay.play();
                
                
        }
        else{
                Label Dicevalue = new Label("Dice result: " + attackresults.get(0) + " from " + attackresults.get(1) + " dices");
                Dicevalue.setFont(Font.font("Death Star", FontWeight.MEDIUM, 38));
                Dicevalue.setTextFill(Color.web("#ffffff"));

                Label DiceResult = new Label("Dice result");
                DiceResult.setFont(Font.font("Death Star", FontWeight.MEDIUM, 38));
                DiceResult.setTextFill(Color.web("#ffffff"));

                if (attackresults.get(2) == 1)
                    DiceResult.setText("The Crew Member as killed the alien");
                else if (attackresults.get(2) == 0)
                    DiceResult.setText("The Crew Member was unable to kill the alien");
                else
                    DiceResult.setText("There was no alien in the selected room");

                topOfPopup.getChildren().addAll(lblExitPopup,Dicevalue,DiceResult);
                topOfPopup.setSpacing(20);
                topOfPopup.setAlignment(Pos.CENTER);

                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished( e -> myStackPane.getChildren().remove(topOfPopup) );
                delay.play();
        }
        
        myStackPane.getChildren().remove(topOfPopup);
        
        return topOfPopup;

    }

    private BorderPane restPhaseLayout(){

        BorderPane restPhasePane = new BorderPane();
        VBox restPhaseMainVBox = new VBox();

        ImageView pointsIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\points.png")));
        pointsIcon.setFitWidth(20);
        pointsIcon.setFitHeight(20);

        Label lblPoints = new Label(": " + this.game.getUserPoints(), pointsIcon);
        lblPoints.setTextFill(Color.web("#ffffff"));
        lblPoints.getStyleClass().add("PointsLabel");

        ImageView apIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\action.png")));
        apIcon.setFitWidth(20);
        apIcon.setFitHeight(20);
        Label lblAp = new Label(": " + this.game.getActionPoints(), apIcon);
        lblAp.setTextFill(Color.web("#ffffff"));
        lblAp.getStyleClass().add("PointsLabel");

        ImageView ipIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\influence.png")));
        ipIcon.setFitWidth(20);
        ipIcon.setFitHeight(20);
        Label lblIp = new Label(": " + this.game.getInspirationPoints(), ipIcon);
        lblIp.setTextFill(Color.web("#ffffff"));
        lblIp.getStyleClass().add("PointsLabel");

        ImageView healthIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\health.png")));
        healthIcon.setFitWidth(20);
        healthIcon.setFitHeight(20);
        Label healthTracker = new Label(": " + this.game.getHealthTrackerHealth(), healthIcon);
        healthTracker.setTextFill(Color.web("#ffffff"));
        healthTracker.getStyleClass().add("PointsLabel");

        ImageView hullIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\hull.png")));
        hullIcon.setFitWidth(20);
        hullIcon.setFitHeight(20);
        Label HullTracker = new Label(": " + this.game.getHullState(), hullIcon);
        HullTracker.setTextFill(Color.web("#ffffff"));
        HullTracker.getStyleClass().add("PointsLabel");

        HBox topRightBox = new HBox();
        topRightBox.getChildren().addAll(HullTracker, healthTracker, lblPoints, lblAp, lblIp);
        topRightBox.setAlignment(Pos.CENTER_RIGHT);
        topRightBox.setSpacing(10);

        HBox topLeftBox = new HBox();
        topLeftBox.getChildren().addAll();
        topLeftBox.setAlignment(Pos.CENTER_LEFT);

        ArrayList<Label> journeyLabels = new ArrayList<>();

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
        journeyLabels.get(this.game.getJourneyState()).getStyleClass().add("journeyLabelsCenterActive");

        region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        HBox topBox = new HBox();
        topBox.getChildren().addAll(topLeftBox, region, topRightBox);
        topBox.setSpacing(10);
        topBox.setPadding(new Insets(5, 10, 5, 10));
        topBox.getStyleClass().add("topBox");
        topBox.setAlignment(Pos.CENTER_RIGHT);

        Image addHealthImg = new Image(getClass().getResourceAsStream("..\\Images\\addHealth.png"));
        Image addHullImg = new Image(getClass().getResourceAsStream("..\\Images\\addHealth.png"));
        Image addOrganicDetonater = new Image(getClass().getResourceAsStream("..\\Images\\addHealth.png"));
        Image addParticleDisperserImg = new Image(getClass().getResourceAsStream("..\\Images\\addHealth.png"));
        Image addMovementImg = new Image(getClass().getResourceAsStream("..\\Images\\addHealth.png"));
        Image addSealTokenImg = new Image(getClass().getResourceAsStream("..\\Images\\addHealth.png"));
        Image addGainAtackImg = new Image(getClass().getResourceAsStream("..\\Images\\addHealth.png"));
        Image addOneDiceImg = new Image(getClass().getResourceAsStream("..\\Images\\addHealth.png"));
        Image addOneParticleDisperserImg = new Image(getClass().getResourceAsStream("..\\Images\\addHealth.png"));
        Image nextImg = new Image(getClass().getResourceAsStream("..\\Images\\next.png"));

        DeathStarLabel titleRest = new DeathStarLabel("Rest Phase", 58);

        DefaultButton btnAddOneHealth = new DefaultButton("Add One Health Point", addHealthImg, 20 ,20, 320,40);
        DefaultButton btnAddOneHull = new DefaultButton("Add One Hull Point", addHullImg, 20 ,20, 320,40);
        DefaultButton btnAddOneOrganicDetonater = new DefaultButton("Add One Organic Detonator", addOrganicDetonater, 20 ,20, 320,40);
        DefaultButton btnAddOneParticleDisperser = new DefaultButton("Add One Particle Disperser", addOneParticleDisperserImg, 20 ,20, 320,40);
        DefaultButton btnAddOneMovement = new DefaultButton("Add One Movement Crew Member", addMovementImg, 20 ,20, 320,40);
        DefaultButton btnAddOneSealToken = new DefaultButton("Add One Seal Room Token", addSealTokenImg, 20 ,20, 320,40);
        DefaultButton btnAddOneGainAtack = new DefaultButton("Add One Atack CrewMember", addGainAtackImg, 20 ,20, 320,40);
        DefaultButton btnAddOneDice = new DefaultButton("Add One Dice Result", addOneDiceImg, 20 ,20, 320,40);
        DefaultButton btnNext = new DefaultButton("Next Turn", nextImg, 20 ,20, 320,40);

        restPhaseMainVBox.getChildren().addAll(titleRest, btnAddOneHealth, btnAddOneHull, btnAddOneOrganicDetonater, btnAddOneMovement, btnAddOneSealToken, btnAddOneGainAtack, btnAddOneDice, btnAddOneParticleDisperser, btnNext);
        restPhaseMainVBox.setAlignment(Pos.CENTER);
        restPhaseMainVBox.setSpacing(20);

        restPhasePane.setTop(topBox);
        restPhasePane.setCenter(restPhaseMainVBox);
        restPhasePane.getStyleClass().add("ChooseVBox");

        btnAddOneHealth.setOnMouseClicked(e -> {
            this.game.addonehealth();
            lblIp.setText(": " + this.game.getInspirationPoints());
            healthTracker.setText(": " + this.game.getHealthTrackerHealth());
        });
        btnAddOneHull.setOnMouseClicked(e -> {
            this.game.fixOneHull();
            lblIp.setText(": " + this.game.getInspirationPoints());
            HullTracker.setText(": " + this.game.getHullState());
        });
        btnAddOneOrganicDetonater.setOnMouseClicked(e -> {
            this.game.buildorganicdetonator();
            lblIp.setText(": " + this.game.getInspirationPoints());
        });
        btnAddOneMovement.setOnMouseClicked(e -> {
            chooseCrewMemberGainMovement();
            lblIp.setText(": " + this.game.getInspirationPoints());
        });
        btnAddOneSealToken.setOnMouseClicked(e -> {
            this.game.gainonesealedromtoken();
            lblIp.setText(": " + this.game.getInspirationPoints());
        });
        btnAddOneGainAtack.setOnMouseClicked(e -> {
            chooseCrewMemberGainAtack();
            lblIp.setText(": " + this.game.getInspirationPoints());
        });
        btnAddOneDice.setOnMouseClicked(e -> {
            this.game.addonethedice();
            lblIp.setText(": " + this.game.getInspirationPoints());
        });
        btnAddOneParticleDisperser.setOnMouseClicked(e -> {
            this.game.builoneparticleddispenser();
            lblIp.setText(": " + this.game.getInspirationPoints());
        });

        btnNext.setOnMouseClicked(e -> {
            this.game.nextTurn();
            this.getChildren().remove(restPhasePane);
        });

        return restPhasePane;

    }

    private void chooseCrewMemberGainAtack(){

        /*#########################################################################*/
        /*##                             Exit Popup                              ##*/
        /*#########################################################################*/

        Image btnExitPopupImg = new Image(getClass().getResourceAsStream("..\\Images\\user.png"));
        Image btnAcceptPopupImg = new Image(getClass().getResourceAsStream("..\\Images\\user.png"));
        RedButton btnCancelPopup = new RedButton("Crew Member 2", btnExitPopupImg , 20, 20, 180, 40);
        GreenButton btnAcceptPopup = new GreenButton("Crew Member 1", btnAcceptPopupImg, 20 ,20, 180,40);
        DeathStarLabel lblExitPopup = new DeathStarLabel("Who is the Crew Member?", 38);

        HBox topOfPopup = new HBox(lblExitPopup);
        topOfPopup.setSpacing(20);
        topOfPopup.setAlignment(Pos.CENTER);

        HBox bottomOfPopup = new HBox(btnAcceptPopup, btnCancelPopup);
        bottomOfPopup.setAlignment(Pos.CENTER);
        bottomOfPopup.setSpacing(20);

        VBox gainAtackPopup = new VBox();
        gainAtackPopup.getChildren().addAll(topOfPopup, bottomOfPopup);
        gainAtackPopup.getStyleClass().add("PopUp");
        gainAtackPopup.setSpacing(20);
        gainAtackPopup.setAlignment(Pos.CENTER);
        gainAtackPopup.setMaxSize(600, 200);

        BorderPane pane = new BorderPane();
        pane.setCenter(gainAtackPopup);

        this.getChildren().add(pane);

        btnCancelPopup.setOnMouseClicked(e -> {
            this.game.gainoneattack(1);
            this.getChildren().remove(pane);
        });
        btnAcceptPopup.setOnMouseClicked(event -> {
            this.game.gainoneattack(0);
            this.getChildren().remove(pane);
        });

    }

    private void chooseCrewMemberGainMovement(){

        /*#########################################################################*/
        /*##                             Exit Popup                              ##*/
        /*#########################################################################*/

        Image btnExitPopupImg = new Image(getClass().getResourceAsStream("..\\Images\\user.png"));
        Image btnAcceptPopupImg = new Image(getClass().getResourceAsStream("..\\Images\\user.png"));
        RedButton btnCancelPopup = new RedButton("Crew Member 2", btnExitPopupImg , 20, 20, 180, 40);
        GreenButton btnAcceptPopup = new GreenButton("Crew Member 1", btnAcceptPopupImg, 20 ,20, 180,40);
        DeathStarLabel lblExitPopup = new DeathStarLabel("Who is the Crew Member?", 38);

        HBox topOfPopup = new HBox(lblExitPopup);
        topOfPopup.setSpacing(20);
        topOfPopup.setAlignment(Pos.CENTER);

        HBox bottomOfPopup = new HBox(btnAcceptPopup, btnCancelPopup);
        bottomOfPopup.setAlignment(Pos.CENTER);
        bottomOfPopup.setSpacing(20);

        VBox gainMovementPopup = new VBox();
        gainMovementPopup.getChildren().addAll(topOfPopup, bottomOfPopup);
        gainMovementPopup.getStyleClass().add("PopUp");
        gainMovementPopup.setSpacing(20);
        gainMovementPopup.setAlignment(Pos.CENTER);
        gainMovementPopup.setMaxSize(600, 200);

        BorderPane pane = new BorderPane();
        pane.setCenter(gainMovementPopup);

        this.getChildren().add(pane);

        btnCancelPopup.setOnMouseClicked(e -> {
            this.game.addonemovement(1);
            this.getChildren().remove(pane);
        });
        btnAcceptPopup.setOnMouseClicked(event -> {
            this.game.addonemovement(0);
            this.getChildren().remove(pane);
        });

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (this.game.inAwaitCrewPhaseActions() || this.game.inAwaitRestPhaseActions()){

            refreshJourneyTracker();
            refreshLeftPanel();
            updateindicators();

        }

        if (this.game.inAwaitRestPhaseActions()) {
            this.getChildren().add(restPhaseLayout());
            refreshJourneyTracker();
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
        
        lblPoints.setText(": " + this.game.getPoints()); // point screen
        //lblPoints.setText(": " + this.game.getJourneyState());
        
        lblIp.setText(": " + this.game.getInspirationPoints()); // inspirationpoint screen
        
        lblAp.setText(": " + this.game.getActionPoints()); // action point screen

        HullTracker.setText(": " + this.game.getHullState());

        healthTracker.setText(": " + this.game.getHealthTrackerHealth());

        for (int i = 0; i < 15; i++) { // Jouney tracker update

            journeyLabels.get(i).setText(this.game.getJourneyValue(i));

        }
        
        
        
        
    }
    
    private void domoveanimationplayer(int crewmemebt, String oldlocation){
        
        String newlocation;
        
        if(crewmemebt == 1)
            newlocation = this.game.getCrewmember1RoomName();
        else
            newlocation = this.game.getCrewmember2RoomName();
                

        General converthelp = new General();
        int oldlocal = converthelp.convertRoomStrinToInt(oldlocation);
        int newlocal = converthelp.convertRoomStrinToInt(newlocation);
        
        ShipGps animhelp = new ShipGps();
        
        double xcenter=((WIDTH- 377)/2) - 29 ;
        double ycenter= ((HEIGHT - 84)/2)- 7 ;
        
        
        if(this.game.getActionPoints() >= 0)
            playerimage.setVisible(true);
        
        playerimage.setTranslateX(animhelp.getlocationcords(oldlocal).get(0) + xcenter);
        playerimage.setTranslateY(animhelp.getlocationcords(oldlocal).get(1) + ycenter);
        
        ArrayList<Double> cordstart = new ArrayList<>(); // temp
        
        cordstart = animhelp.getcords(oldlocal,newlocal);
        
        double rotaionplayer = animhelp.calculatedegree(animhelp.getlocationcords(oldlocal).get(0)
                                                        ,animhelp.getlocationcords(oldlocal).get(1)
                                                        ,animhelp.getlocationcords(newlocal).get(0)
                                                        ,animhelp.getlocationcords(newlocal).get(1));
        

        playerimage.setRotate((rotaionplayer * 100));
        
        
        TranslateTransition transtion = new TranslateTransition(Duration.seconds(2),playerimage);
        
        transtion.setByX(cordstart.get(0));
        transtion.setByY(cordstart.get(1));
        //transtion.setCycleCount(Animation.INDEFINITE);
        transtion.play();
        
        transtion.setOnFinished(e -> playerimage.setVisible(false));
        
        updateindicators();
    }
    
    private void updateindicators(){
    
        General converthelp = new General();
        int crew1location = converthelp.convertRoomStrinToInt(this.game.getCrewmember1RoomName()) - 1;
        int crew2location = converthelp.convertRoomStrinToInt(this.game.getCrewmember2RoomName()) - 1;
        

        for (int i = 0; i < 12; i++ ){ // user update
            
            int value = 0;
            if(crew1location == i) {value++;}
            if(crew2location == i) {value++;}

            
            Node nodeOut = getarrayindicators.get(i).getChildren().get(0);
            if(nodeOut instanceof HBox){
                for(Node nodeIn:((HBox)nodeOut).getChildren()){
                    if(nodeIn instanceof Label){
                        ((Label) nodeIn).setText(String.valueOf(value));
                    }
                }
            } 
        }
        
        List<Alien> returnalien = new ArrayList<Alien>(); 
        returnalien = this.game.getalienarraycopy();
        General funchelp = new General();
        
        
        for (int i = 0; i < 12; i++ ){ // alien update
            
            int value = 0;
            
            for (int j=0; j < returnalien.size();j++){
                
                int room = funchelp.convertRoomStrinToInt(returnalien.get(j).getRoom().getName()) - 1;
                
                if(room == i)
                {
                    value++;
                }
                 
            }
            Node nodeOut = getarrayindicators.get(i).getChildren().get(1);
            if(nodeOut instanceof HBox){
                for(Node nodeIn:((HBox)nodeOut).getChildren()){
                    if(nodeIn instanceof Label){
                        ((Label) nodeIn).setText(String.valueOf(value));
                    }
                }
            } 
        }
        
        // update sealed rooms
        for (int i = 0; i < 12; i++ ){  
            boolean returnaswer = this.game.seelockedroom(i);
            
            if (returnaswer == true)
                sealedroomarray.get(i).setVisible(true);
        }
        
        // update paticle disperser
        for (int i = 0; i < 12 ; i++){
            particledisperserarray.get(i).setVisible(false); 
        }

        ArrayList<Integer> placedOrganicDetonaters = new ArrayList<>();
        for (int i = 0; i < 12; i++ ){  
            placedOrganicDetonaters = this.game.getPlacedParticleDisperser();
            boolean returnaswer = false;
            
            for(int j = 0; j < placedOrganicDetonaters.size() ; j++){
                if(this.game.gettrapsclone().get(placedOrganicDetonaters.get(j)).getRoomName() ==  converthelp.convertIntToRoom(i + 1).getName())
                    returnaswer = true;

            }
                  
            if (returnaswer == true){
                particledisperserarray.get(i).setVisible(true);
            }
        }
        
        // update paticle disperser
        for (int i = 0; i < 12 ; i++){
            organidetonatorarray.get(i).setVisible(false); 
        }
        ArrayList<Integer> placedparticledispencer = new ArrayList<>();
        for (int i = 0; i < 12; i++ ){  
            placedparticledispencer = this.game.getPlacedorganicdetonator();
            boolean returnaswer = false;
            
            for(int j = 0; j < placedparticledispencer.size() ; j++){
                if(this.game.gettrapsclone().get(placedparticledispencer.get(j)).getRoomName() ==  converthelp.convertIntToRoom(i + 1).getName())
                    returnaswer = true;

            }
                  
            if (returnaswer == true){
                organidetonatorarray.get(i).setVisible(true);
            }
        }
        
        
    }
    
}
