package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * AwaitBeginning
 */
public class AwaitBeginning extends StateAdapter implements Serializable{

    public AwaitBeginning(){
        super();
    }
    
    @Override
    public IStates InputBeginning(GameLogic game){
        
        this.game = game;
        return new AwaitCrewMembersSelection(this.game);
        
    }
    
}