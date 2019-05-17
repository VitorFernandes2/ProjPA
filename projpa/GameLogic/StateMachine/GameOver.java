package projpa.GameLogic.StateMachine;

import java.io.Serializable;

import projpa.GameLogic.GameData;

/**
 * GameOver
 */
public class GameOver extends StateAdapter implements Serializable{

    public GameOver(GameData game){
        super(game);
    }

    @Override
    public IStates StartNewBegin(){

        return new AwaitBeginning();
    
    }
    
}