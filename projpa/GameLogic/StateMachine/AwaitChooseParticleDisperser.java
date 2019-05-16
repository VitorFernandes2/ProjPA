package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * AwaitChooseParticleDisperser
 */
public class AwaitChooseParticleDisperser extends StateAdapter implements Serializable{

    public AwaitChooseParticleDisperser(GameLogic game){
        super(game);
        
    }

    public boolean existParticleDisperser(String Location){

        int exist=0;
        
        for(int i=0; i <= game.getUser().getTraps().size();i++){
            
            // verifica se existe organic detontor no sitio ou não
            if(this.game.getTrapName(i).equals("OrganicDetonator") && this.game.getTrapRoomName(i).equals(Location)) 
                exist++;
        }

        return exist > 0;

    }

    @Override
    public IStates detonateParticleDisperser(String particleDisperser) {
        
        //Particle disperser variable is the location of it 
        
        if(existParticleDisperser(particleDisperser)){

            game.Killallaliensin(particleDisperser);
            this.game.removeTrap(this.game.findParticleDisperserByRoom(particleDisperser));

             if ( this.game.getCrewMember1RoomName().equals(particleDisperser) || 
                this.game.getCrewMember2RoomName().equals(particleDisperser))
                return new GameOver(this.game);
            else
                return new AwaitCrewPhaseActions(this.game);

        }

        return new AwaitCrewPhaseActions(this.game); 

    }

    @Override
    public IStates goBack() {
        return new AwaitCrewPhaseActions(this.game);    
    }

}