package projpa.GameLogic.Trackers;

import java.io.Serializable;
import projpa.GameLogic.GameConstants;

/**
 * HullTracker
 */
public class HullTracker extends GameConstants implements Serializable{

    private int hullstate;

    public HullTracker(int value){
        if(value >=1 || value <= getStartHull())
            this.hullstate = value;
        this.hullstate = 8;
    }

    public int getHullstate() {
        return hullstate;
    }

    public void setHullstate(int hullstate) {
        this.hullstate = hullstate;
    }

    public void addHullHealth(){
        this.hullstate++;
    }
    
    public void Hullhit(){
        this.hullstate = this.hullstate - 1;
        
    }

}