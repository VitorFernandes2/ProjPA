package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * AwaitThirdTokenFirstCrewMember
 */
public class AwaitThirdTokenFirstCrewMember extends StateAdapter implements Serializable{

    public AwaitThirdTokenFirstCrewMember(GameLogic game){
        super(game);
    }

    public AwaitThirdTokenFirstCrewMember(){
        super();
    }


    @Override
    public IStates placeFirstCrewMember(int room) {
     
        // place a crew member in a selected room if possible
        if(room >= 1 || room <= 12){
        
        game.getUser().getCrewMembers()[0].chooseRoom(room);
        
        return new AwaitThirdTokenSecondCrewMember(this.game);
        
        }
        else
            return this;
        
    }

    @Override
    public IStates goBack() {
        return new AwaitCrewMembersSelection(this.game);
    }

}