package projpa;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import projpa.GameLogic.GameLogic;
import projpa.Iu.Graphic.GraphicInterface;
import projpa.Iu.Text.TextInterface;

public class ProjPA extends Application {

    /**
     * This is the main function where we will run all the interfaces
     * @param args the command line arguments
     */

    public static void main(String[] args) {

//        GameLogic game = new GameLogic();
//        TextInterface gameInterface = new TextInterface(game);
//
//        gameInterface.run();
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GameLogic game = new GameLogic();
        GraphicInterface gameInterface = new GraphicInterface(primaryStage, game);
        gameInterface.run();

    }

    @Override
    public void init() throws Exception {
        super.init();
        Font.loadFont(ProjPA.class.getResource("Iu/Graphic/Fonts/Death Star.otf").openStream(), 12);
    }
}
