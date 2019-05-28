package projpa.Iu.Graphic.Panes;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import projpa.Iu.Graphic.Constants;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CrewMembersChoicePane extends StackPane implements Constants, PropertyChangeListener {

    private GameLogic game;
    private ArrayList<String> CrewNames;

    public CrewMembersChoicePane(GameLogic game) {
        this.game = game;
        this.game.addPropertyChangeListener(this);
        this.CrewNames = new ArrayList<>();
        setupComponents();
        propertyChange(null);
    }

    private void setupComponents() {

        ObservableList firstStackPaneList = this.getChildren();
        firstStackPaneList.add(CrewMember1ChooseRoom());

    }

    private BorderPane CrewMember1ChooseRoom(){

        /*#########################################################################*/
        /*##                     Choose CrewMember 1 Scene                       ##*/
        /*#########################################################################*/

        BorderPane paneLayoutCrewMembersChoice = new BorderPane();
        VBox mainVboxCrewMembersChoice = new VBox();

        ArrayList<Image> CardsImage = new ArrayList<>();

        for (int i = 0; i < 12; i++) {

            switch (i){
                case 0:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\Captain.png")));
                    break;
                case 1:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\Commander.png")));
                    break;
                case 2:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\CommsOfficer.png")));
                    break;
                case 3:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\Doctor.png")));
                    break;
                case 4:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\Engineer.png")));
                    break;
                case 5:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\MoralOfficer.png")));
                    break;
                case 6:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\NavigationOfficer.png")));
                    break;
                case 7:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\RedShirt.png")));
                    break;
                case 8:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\ScienceOfficer.png")));
                    break;
                case 9:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\SecurityOfficer.png")));
                    break;
                case 10:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\ShuttlePilot.png")));
                    break;
                case 11:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\TransporterChief.png")));
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

        ArrayList<String> names = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            switch (i){
                case 0:
                    names.add("Captain");
                    break;
                case 1:
                    names.add("Commander");
                    break;
                case 2:
                    names.add("Comm's Officer");
                    break;
                case 3:
                    names.add("Doctor");
                    break;
                case 4:
                    names.add("Engineer");
                    break;
                case 5:
                    names.add("Moral Officer");
                    break;
                case 6:
                    names.add("Navigation Officer");
                    break;
                case 7:
                    names.add("Red Shirt");
                    break;
                case 8:
                    names.add("Science Officer");
                    break;
                case 9:
                    names.add("Security Officer");
                    break;
                case 10:
                    names.add("Shuttle Pilot");
                    break;
                case 11:
                    names.add("Transporter Chief");
                    break;
            }
        }

        this.CrewNames.clear();
        for (int i = 0; i < 12; i++) {
            CrewCardImgHbox.add(new HBox());
            CrewCardImgHbox.get(i).getChildren().add(CardsImageView.get(i));
            CrewCardImgHbox.get(i).getStyleClass().add("ImageView");
            String str = names.get(i);
            CrewCardImgHbox.get(i).setOnMouseClicked(e -> {
                this.CrewNames.add(str);
                ObservableList firstStackPaneList = this.getChildren();
                firstStackPaneList.add(CrewMember2ChooseRoom());
            });
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

        Label lblChooseCrewMembers = new Label("Choose Crew Member 1");
        lblChooseCrewMembers.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblChooseCrewMembers.setTextFill(Color.web("#ffffff"));

        ImageView ChooseCardUndo = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\undo.png")));
        ChooseCardUndo.setFitHeight(20);
        ChooseCardUndo.setFitWidth(20);

        Button btnTurnBackChooseCrewMember = new Button("Go Back", ChooseCardUndo);
        btnTurnBackChooseCrewMember.getStyleClass().add("DefaultButton");

        Image imgDice = new Image(getClass().getResourceAsStream("..\\Images\\dice.png"));
        GreenButton randomButton = new GreenButton(null, imgDice, 20 ,20, 40 ,40);

        HBox turnBackChooseCard = new HBox();
        turnBackChooseCard.setSpacing(20);
        turnBackChooseCard.setAlignment(Pos.CENTER);
        btnTurnBackChooseCrewMember.setPadding(new Insets(10));

        turnBackChooseCard.getChildren().addAll(lblChooseCrewMembers, btnTurnBackChooseCrewMember, randomButton);
        turnBackChooseCard.setAlignment(Pos.CENTER);
        turnBackChooseCard.setPadding(new Insets(0, 10, 0, 10));

        mainVboxCrewMembersChoice.getChildren().setAll(turnBackChooseCard, firstLineCrewMembersHbox, secondLineCrewMembersHbox, thirdLineCrewMembersHbox);
        mainVboxCrewMembersChoice.setAlignment(Pos.CENTER);
        mainVboxCrewMembersChoice.setSpacing(20);

        mainVboxCrewMembersChoice.getStyleClass().add("ChooseVBox");

        paneLayoutCrewMembersChoice.setCenter(mainVboxCrewMembersChoice);

        btnTurnBackChooseCrewMember.setOnMouseClicked(e -> {
            this.game.goBack();
        });

        randomButton.setOnMouseClicked(e -> {
            this.game.selectrandomCrewMembers();
        });

        return paneLayoutCrewMembersChoice;

    }

    private BorderPane CrewMember2ChooseRoom(){

        /*#########################################################################*/
        /*##                     Choose CrewMember 2 Scene                       ##*/
        /*#########################################################################*/

        BorderPane paneLayoutCrewMembersChoice = new BorderPane();
        VBox mainVboxCrewMembersChoice = new VBox();

        ArrayList<Image> CardsImage = new ArrayList<>();

        for (int i = 0; i < 12; i++) {

            switch (i){
                case 0:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\Captain.png")));
                    break;
                case 1:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\Commander.png")));
                    break;
                case 2:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\CommsOfficer.png")));
                    break;
                case 3:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\Doctor.png")));
                    break;
                case 4:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\Engineer.png")));
                    break;
                case 5:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\MoralOfficer.png")));
                    break;
                case 6:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\NavigationOfficer.png")));
                    break;
                case 7:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\RedShirt.png")));
                    break;
                case 8:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\ScienceOfficer.png")));
                    break;
                case 9:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\SecurityOfficer.png")));
                    break;
                case 10:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\ShuttlePilot.png")));
                    break;
                case 11:
                    CardsImage.add(new Image(getClass().getResourceAsStream("..\\Images\\Cards\\TransporterChief.png")));
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

        ArrayList<String> names = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            switch (i){
                case 0:
                    names.add("Captain");
                    break;
                case 1:
                    names.add("Commander");
                    break;
                case 2:
                    names.add("Comm's Officer");
                    break;
                case 3:
                    names.add("Doctor");
                    break;
                case 4:
                    names.add("Engineer");
                    break;
                case 5:
                    names.add("Moral Officer");
                    break;
                case 6:
                    names.add("Navigation Officer");
                    break;
                case 7:
                    names.add("Red Shirt");
                    break;
                case 8:
                    names.add("Science Officer");
                    break;
                case 9:
                    names.add("Security Officer");
                    break;
                case 10:
                    names.add("Shuttle Pilot");
                    break;
                case 11:
                    names.add("Transporter Chief");
                    break;
            }
        }

        for (int i = 0; i < 12; i++) {
            CrewCardImgHbox.add(new HBox());
            CrewCardImgHbox.get(i).getChildren().add(CardsImageView.get(i));
            CrewCardImgHbox.get(i).getStyleClass().add("ImageView");
            String str = names.get(i);
            CrewCardImgHbox.get(i).setOnMouseClicked(e -> {
                this.CrewNames.add(str);
                if (!this.CrewNames.get(0).equals(this.CrewNames.get(1)))
                    this.game.selectCrewMembers(this.CrewNames.get(0), this.CrewNames.get(1));
                else
                    this.CrewNames.remove(1);
            });
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

        Label lblChooseCrewMembers = new Label("Choose Crew Member 2");
        lblChooseCrewMembers.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblChooseCrewMembers.setTextFill(Color.web("#ffffff"));

        ImageView ChooseCardUndo = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\undo.png")));
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

        btnTurnBackChooseCrewMember.setOnMouseClicked(e -> {
            this.game.goBack();
        });

        return paneLayoutCrewMembersChoice;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setVisible(this.game.inAwaitCrewMembersSelection());
    }

}
