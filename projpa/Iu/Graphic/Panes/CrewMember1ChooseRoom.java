package projpa.Iu.Graphic.Panes;

import static com.sun.glass.ui.Cursor.setVisible;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import projpa.GameLogic.GameLogic;
import projpa.Iu.Graphic.Constants;

public class CrewMember1ChooseRoom extends StackPane implements Constants,PropertyChangeListener{

    private GameLogic game;
    
    public CrewMember1ChooseRoom(GameLogic Game) {
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

        Label lblChooseRooms = new Label("Choose Crew Member 1 Room");
        lblChooseRooms.setFont(Font.font("Death Star", FontWeight.MEDIUM, 58));
        lblChooseRooms.setTextFill(Color.web("#ffffff"));

        ImageView ChooseRoomsUndo = new ImageView(new Image(getClass().getResourceAsStream("..\\Images\\undo.png")));
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
                       
                        game.placeFirstCrewMember(0);
                        
                    }
                });

        RoomsImgHbox.get(1).addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                       
                        game.placeFirstCrewMember(1);
                                     
                    }
                });

        RoomsImgHbox.get(2).addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                       
                        game.placeFirstCrewMember(2);
                        
                    }
                });

        RoomsImgHbox.get(3).addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                       
                        game.placeFirstCrewMember(3);
                                     
                    }
                });
        
        
        RoomsImgHbox.get(4).addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                       
                        game.placeFirstCrewMember(4);
                        
                    }
                });

        RoomsImgHbox.get(5).addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                       
                        game.placeFirstCrewMember(5);
                                     
                    }
                });
        RoomsImgHbox.get(6).addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                       
                        game.placeFirstCrewMember(6);
                        
                    }
                });

        RoomsImgHbox.get(7).addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                       
                        game.placeFirstCrewMember(7);
                                     
                    }
                });
        RoomsImgHbox.get(8).addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                       
                        game.placeFirstCrewMember(8);
                        
                    }
                });

        RoomsImgHbox.get(9).addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                       
                        game.placeFirstCrewMember(9);
                                     
                    }
                });
        RoomsImgHbox.get(10).addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                       
                        game.placeFirstCrewMember(0);
                        
                    }
                });

        RoomsImgHbox.get(11).addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                       
                        game.placeFirstCrewMember(11);
                                     
                    }
                });  

        paneLayoutRoomChoice.setCenter(mainVboxRoomChoice);
        
        return paneLayoutRoomChoice;
        
    }
    
    
    
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
             

            setVisible(this.game.inAwaitThirdTokenFirstCrewMember());
            
        
        
        
    }
}
