package projpa.GameLogic.Traps;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * ParticleDisperser
 */
public class ParticleDisperser extends Trap implements Serializable{

    
    public ParticleDisperser(GameLogic game){

        super(game);
        
    }

    @Override
    public String getName() {
        return "ParticleDisperser";
    }

    @Override
    public int destroyAliens() {
        
        if (game.getCrewMember1Room().ReturnName().equals(this.getRoom().ReturnName())){ // if crewmemebr is in 1 room
            
            game.HealthTrackertozero();
            return -1;
        }
            
        if (game.getCrewMember2Room().ReturnName().equals(this.getRoom().ReturnName())){ // if crewmemebr is in 1 room
            
            game.HealthTrackertozero();
            return -1;
        }
        
        int existe = 0;
        for (int i = 0; i < game.getAliens().size() ; i++){
            
            if (this.getTrapRoom().ReturnName().equals(game.getAliens().get(i).getRoom().ReturnName())){
                
                game.getAliens().remove(i); // removes the alien
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