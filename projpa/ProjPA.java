package projpa;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import projpa.GameLogic.GameData;
import projpa.GameLogic.GameLogic;
import projpa.GameLogic.StateMachine.AwaitBeginning;
import projpa.GameLogic.StateMachine.AwaitPeekRoom;
import projpa.GameLogic.StateMachine.GameOver;
import projpa.GameLogic.StateMachine.IStates;
import projpa.Iu.Graphic.GraphicInterface;
import projpa.Iu.Text.TextInterface;

public class ProjPA extends Application {

    /**
     * This is the main function where we will run all the interfaces
     * @param args the command line arguments
     */

    public static void main(String[] args) {
       
        if (args.length == 1 && args[0].equals("-console")){ // make console work
        
            GameLogic game = new GameLogic(new AwaitBeginning());
            TextInterface gameInterface = new TextInterface(game);

            gameInterface.run();
            
        }
        else
            launch(args);

        System.exit(0);
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GameLogic game = new GameLogic(new AwaitPeekRoom(new GameData()));
        GraphicInterface gameInterface = new GraphicInterface(primaryStage, game);
        gameInterface.run();

    }

    @Override
    public void init() throws Exception {
        super.init();
        Font.loadFont(ProjPA.class.getResource("Iu/Graphic/Fonts/Death Star.otf").openStream(), 12);
    }
}
