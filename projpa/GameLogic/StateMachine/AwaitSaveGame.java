package projpa.GameLogic.StateMachine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import projpa.GameLogic.GameData;

/**
 * AwaitSaveGame
 */
public class AwaitSaveGame extends StateAdapter implements Serializable{

    private int wasSaved;
    
    public AwaitSaveGame(GameData game){
        super(game);
        wasSaved = 0;
    }

    @Override
    public IStates saveGame() {

        try {
                
            //test
            FileOutputStream f = new FileOutputStream(new File("savegame.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            
            // allow to see if game was already saved or not
            wasSaved = 1;
            
            // Write objects to file
            o.writeObject(this.game);
			
            o.close();
                
        } catch (IOException e) {
            
            e.printStackTrace();
                    
        }
            //this.game.setStateOfTheGame(new AwaitCrewPhaseActions(this.game));
            
            //old
            return new AwaitCrewPhaseActions(this.game);
            
    }
    
    @Override
    public IStates saveGame(String input) {

        try {
                
            //test
            FileOutputStream f = new FileOutputStream(new File(input));
            ObjectOutputStream o = new ObjectOutputStream(f);
            
            // allow to see if game was already saved or not
            wasSaved = 1;
            
            // Write objects to file
            o.writeObject(this.game);
			
            o.close();
                
        } catch (IOException e) {
            
            e.printStackTrace();
                    
        }
            //this.game.setStateOfTheGame(new AwaitCrewPhaseActions(this.game));
            
            //old
            return new AwaitCrewPhaseActions(this.game);
            
    }

    @Override
    public IStates goBack() {
        wasSaved = 0;
        return new AwaitCrewPhaseActions(this.game);
    }
    
    @Override
    public Boolean wasSaved() {
        if (wasSaved == 1)
            return true;
        else
            return false;
    }
    
    
    
}