package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * AwaitThirdTokenSecondCrewMember
 */
public class AwaitThirdTokenSecondCrewMember extends StateAdapter implements Serializable{

    public AwaitThirdTokenSecondCrewMember(GameLogic game){
        super(game);
    }

    @Override
    public IStates placeSecondCrewMember(int room) {
        
        // place a crew member in a selected room if possible
        if(room >= 1 || room <= 12){
        
            game.getUser().getCrewMembers()[1].chooseRoom(room);
            
            this.game.addTurn();
            this.game.spawnAliens();

            if(this.game.getJourneyTracker().getJourneyTurn().equals("R") )
                return new AwaitRestPhaseActions(this.game);
            else
                return new AwaitCrewPhaseActions(this.game);
        
        }
        else
            return this;
        
        
    }

    @Override
    public IStates goBack() {
        return new AwaitThirdTokenFirstCrewMember(this.game);
    }
    
}