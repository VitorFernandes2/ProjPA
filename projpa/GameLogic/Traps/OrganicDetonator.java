package projpa.GameLogic.Traps;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * OrganicDetonator
 */
public class OrganicDetonator extends Trap implements Serializable{

    public OrganicDetonator(GameLogic game){
        
        super(game);
        
    }

    @Override
    public String getName() {
        return "OrganicDetonator";
    }

    @Override
    public int destroyAliens() {
        
        for (int i = 0; i < game.getAliens().size() ; i++){
            
            if (this.getTrapRoom().ReturnName().equals(game.getAliens().get(i).getRoom().ReturnName())){
                
                game.getAliens().remove(i); // removes the alien
                game.getInspirationPoints().addoneInspPoint(); // add one inspiration point
                return -1;
                
            }         
        }  
        return 0;
    }
    
}
