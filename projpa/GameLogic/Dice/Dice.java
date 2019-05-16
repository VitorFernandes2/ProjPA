package projpa.GameLogic.Dice;

import java.io.Serializable;
import java.util.Random;

/**
 * This class implements the functionality of the dice
 */
public class Dice implements Serializable{

    public int roll(int min, int max){

        if(min > max)
            return 0;

        int rollValue = 0;
        Random generator = new Random();

        rollValue += generator.nextInt(max) + min;

        return rollValue;
    
    }
    
}