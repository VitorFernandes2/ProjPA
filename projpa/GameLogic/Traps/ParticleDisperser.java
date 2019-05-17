package projpa.GameLogic.Traps;

import java.io.Serializable;
import projpa.GameLogic.GameData;

/**
 * ParticleDisperser
 */
public class ParticleDisperser extends Trap implements Serializable{

    
    public ParticleDisperser(GameData game){

        super(game);
        
    }

    @Override
    public String getName() {
        return "ParticleDisperser";
    }

    @Override
    public int destroyAliens() {
        
        if (game.isCrewMember1SameRoomName(this.getRoomName())){ // if crewmemebr is in 1 room
            
            game.HealthTrackertozero();
            return -1;
        }
            
        if (game.isCrewMember2SameRoomName(this.getRoomName())){ // if crewmemebr is in 1 room
            
            game.HealthTrackertozero();
            return -1;

        }
        
        int existe = 0;
        for (int i = 0; i < game.getSizeOfAliens() ; i++){
            
            if (this.TrapRoomNameIsEqual(game.getAlienRoomNameByPosition(i))){
                
                game.removeAlienByPosition(i); // removes the alien
                i--;
                existe++;
                
            }

        }
        
        if(existe == 0){
            return 0;
        }
        else
            return -1;
        
    }
    
}