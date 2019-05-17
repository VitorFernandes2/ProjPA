package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import projpa.GameLogic.GameData;

/**
 * AwaitThirdTokenFirstCrewMember
 */
public class AwaitThirdTokenFirstCrewMember extends StateAdapter implements Serializable{

    public AwaitThirdTokenFirstCrewMember(GameData game){
        super(game);
    }

    @Override
    public IStates placeFirstCrewMember(int room) {
     
        // place a crew member in a selected room if possible
        if(room >= 1 || room <= 12){
        
        game.chooseCrewMemberRoom(room,0);

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