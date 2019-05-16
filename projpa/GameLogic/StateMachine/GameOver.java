package projpa.GameLogic.StateMachine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.HashMap;
import projpa.GameLogic.GameLogic;

/**
 * GameOver
 */
public class GameOver extends StateAdapter implements Serializable{

    public GameOver(GameLogic game){
        super(game);
    }

    @Override
    public IStates StartNewBegin(){

        return new AwaitBeginning();
    
    }
    
}