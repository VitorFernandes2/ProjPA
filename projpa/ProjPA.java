package projpa;
import projpa.GameLogic.GameLogic;
import projpa.Iu.Text.TextInterface;

public class ProjPA {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        GameLogic game = new GameLogic();
        TextInterface gameInterface = new TextInterface(game);
    
        gameInterface.run();        
        
    }
    
}
