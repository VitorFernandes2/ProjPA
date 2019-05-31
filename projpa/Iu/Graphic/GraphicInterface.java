package projpa.Iu.Graphic;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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

        Media sound = new Media(getClass().getResource(MAIN_MUSIC).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        PaneOrganizer paneOrganizer = new PaneOrganizer(this.game);
        Scene scene = new Scene(paneOrganizer.getRoot());
        scene.getStylesheets().add(GraphicInterface.class.getResource("Css\\main.css").toExternalForm());
        Image imageCursor = new Image(getClass().getResourceAsStream("Images\\rocket.png"));
        scene.setCursor(new ImageCursor(imageCursor));

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Images\\icon.png")));

        //Minimum Dimensions
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMinWidth(WIDTH);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
