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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
import javafx.scene.shape.Circle;

public class ActionPane extends StackPane implements Constants, PropertyChangeListener {

    private GameLogic game;

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

        Image imgExit = new Image(getClass().getResourceAsStream("..\\Images\\exit.png"));
        Image imgNext = new Image(getClass().getResourceAsStream("..\\Images\\next.png"));
        Image imgSave = new Image(getClass().getResourceAsStream("..\\Images\\save.png"));

        GreenButton btnSave = new GreenButton("Save", imgSave, 20, 20, 120, 40);
        RedButton btnExit = new RedButton("Exit", imgExit, 20, 20, 120, 40);
        DefaultButton btnNext = new DefaultButton("Next", imgNext, 20, 20, 120, 40);

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

        ImageView firstTabIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\doctor.png")));
        firstTabIcon.setFitWidth(50);
        firstTabIcon.setFitHeight(50);

        firstTabImage.getChildren().addAll(firstTabIcon, firstTabLabel);

        Tab firstTab = new Tab();
        firstTab.setGraphic(firstTabImage);
        tabLayout.getTabs().add(firstTab);

        VBox secondTabImage = new VBox();
        Label secondTabLabel = new Label("Red Shirt");

        secondTabLabel.setTextFill(Color.web("#ffffff"));

        ImageView secondTabIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\redShirt.png")));
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


        ImageView pointsIcon = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\points.png")));
        pointsIcon.setFitWidth(20);
        pointsIcon.setFitHeight(20);

        Label lblPoints = new Label(": xxx", pointsIcon);

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

        HBox topRightBox = new HBox();
        topRightBox.getChildren().addAll(lblPoints, lblAp, lblIp);
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
            System.out.println(this.game.getJourneyState());
            if (this.game.getJourneyState() + 1 == i)
                journeyLabels.get(i).getStyleClass().add("journeyLabelsCenterActive");
            else
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
        /*##                            Action Scene                             ##*/
        /*#########################################################################*/

        btnNext.setOnMouseClicked(e -> {
            game.nextTurn();
            for (int i = 0; i < 15; i++) {
                if (game.getJourneyState() + 1 == i)
                    journeyLabels.get(i).getStyleClass().add("journeyLabelsCenterActive");
                else
                    journeyLabels.get(i).getStyleClass().add("journeyLabelsCenter");
            }
            journeyLabels.get(0).getStyleClass().add("journeyLabelsLeft");
            journeyLabels.get(14).getStyleClass().add("journeyLabelsRight");
        });

        btnExit.setOnMouseClicked(e -> {
            this.getChildren().add(popupFunctionScene(this));
        });

        StackPane actionStackPane = new StackPane(paneLayout);
        actionStackPane.setAlignment(Pos.CENTER);
        
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        setVisible(this.game.inAwaitCrewPhaseActions());

    }
}
