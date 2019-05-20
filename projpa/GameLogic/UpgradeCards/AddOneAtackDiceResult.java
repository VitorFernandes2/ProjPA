package projpa.GameLogic.UpgradeCards;

import java.io.Serializable;
import projpa.GameLogic.GameData;
import projpa.GameLogic.CrewMembers.CrewMember;
import projpa.GameLogic.MapRooms.RoomState;

/**
 * AddOneAtackDiceResult
 */
public class AddOneAtackDiceResult extends UpgradeCard implements Serializable{

    public AddOneAtackDiceResult(GameData game){
        super(game);
    }

    @Override
    public boolean upgradeFunction() {
        return false;
    }

    @Override
    public boolean closeRoomFunction(RoomState room) {
        return false;
    }

    @Override
    public boolean addOneHability(CrewMember crewMember) {
        return false;
    }
    
}