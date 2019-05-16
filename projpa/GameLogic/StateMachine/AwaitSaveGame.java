package projpa.GameLogic.StateMachine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * AwaitSaveGame
 */
public class AwaitSaveGame extends StateAdapter implements Serializable{

    private int wassaved;
    
    public AwaitSaveGame(GameLogic game){
        super(game);
        wassaved = 0;
    }

    @Override
    public IStates saveGame() {
        

        try {
                
            //test
            FileOutputStream f = new FileOutputStream(new File("savegame.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            
            // allow to see if game was already saved or not
            wassaved = 1;
            
            // Write objects to file
            o.writeObject(this.game);
			
            o.close();
                
        } catch (IOException e) {
            
            e.printStackTrace();
                    
        }
            this.game.setStateOfTheGame(new AwaitCrewPhaseActions(this.game));
            
            //old
            return new AwaitCrewPhaseActions(this.game);
            
    }

    @Override
    public IStates goBack() {
        wassaved = 0;
        return new AwaitCrewPhaseActions(this.game);
    }
    
    @Override
    public Boolean wassaved() {
        if (wassaved == 1)
            return true;
        else
            return false;
    }
    
    
    
}