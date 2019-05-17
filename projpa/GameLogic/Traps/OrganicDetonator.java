package projpa.GameLogic.Traps;

import java.io.Serializable;
import projpa.GameLogic.GameData;

/**
 * OrganicDetonator
 */
public class OrganicDetonator extends Trap implements Serializable{

    public OrganicDetonator(GameData game){
        
        super(game);
        
    }

    @Override
    public String getName() {
        return "OrganicDetonator";
    }

    @Override
    public int destroyAliens() {
        
        for (int i = 0; i < game.getSizeOfAliens() ; i++){
            
            if (this.TrapRoomNameIsEqual(this.game.getAlienRoomNameByPosition(i))){
                
                game.removeAlienByPosition(i); // removes the alien
                game.addOneInspPoint(); // add one inspiration point
                return -1;
                
            }         
        }  
        return 0;
    }
    
}
