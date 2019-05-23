package projpa.GameLogic.Trackers;

import java.io.Serializable;
import projpa.GameLogic.GameConstants;

/**
 * HealthTracker
 */
public class HealthTracker extends GameConstants implements Serializable{
    
    private int healthstate;

    public HealthTracker(int value){
        
        if(value >=1 || value <= getStartHealth())
            this.healthstate = value;
        this.healthstate = 8;
    }

    public int getHullstate() {
        return healthstate;
    }

    public void setHullstate(int dstate) {
        if (dstate > 12)
            this.healthstate = 12;
        else
            this.healthstate = dstate;
    }
    
    public void Hullhit(){
        this.healthstate = this.healthstate - 1;
        
    }    
    
    
}