/* 
################################
#### Projeto de PA 2018/2019 ###
################################
 */
package projpa.GameLogic;

import java.io.Serializable;

/**
 *
 * @author a21270909
 */
public class GameConstants implements Serializable{
    
    // MOVE LATE AND BECOME ABSTRACT
    
    private final int Journeysize = 15;
    private final int startHealth = 8;
    private final int startHull = 8;
    private final int maxSize = 12;
    private final int Startinspiration = 0;
    
    int returnJourneysize (){
        
        return Journeysize;
        
    }
    
    public int getStartHealth() {
        return startHealth;
    }

    public int getStartHull() {
        return startHull;
    }

    public int getMaxSize(){
        return maxSize;
    }
    
    public int getStartinspiration(){
        return Startinspiration;
    }
    
}
