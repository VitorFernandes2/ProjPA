/* 
################################
#### Projeto de PA 2018/2019 ###
################################
 */
package projpa.GameLogic.Trackers;

import java.io.Serializable;
import projpa.GameLogic.GameConstants;

/**
 *
 * @author Joao Coelho
 */
public class InspirationPoints extends GameConstants implements Serializable{
    
    private int InspirationPoints;

    public InspirationPoints(int value){
        
        this.InspirationPoints = 0;

        if(value >=1 && value <= getMaxSize())
            this.InspirationPoints = value;        
        
    }
    
    public InspirationPoints(){
        
        this.InspirationPoints = 0;
    }

    public int getInspstate() {
        return InspirationPoints;
    }

    public void setInspstate(int dstate) {
        this.InspirationPoints = dstate;
    }
    
    public void addoneInspPoint(){
        
        if (InspirationPoints >= 12)
            this.InspirationPoints = 12;
        else
            this.InspirationPoints = this.InspirationPoints + 1;
        
    }
    
}
