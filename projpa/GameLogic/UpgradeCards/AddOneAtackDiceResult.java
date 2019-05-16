package projpa.GameLogic.UpgradeCards;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;
import projpa.GameLogic.CrewMembers.CrewMember;
import projpa.GameLogic.MapRooms.shipJavaInterface;

/**
 * AddOneAtackDiceResult
 */
public class AddOneAtackDiceResult extends UpgradeCard implements Serializable{

    public AddOneAtackDiceResult(GameLogic game){
        super(game);
    }

    @Override
    public boolean upgradeFunction() {
        return false;
    }

    @Override
    public boolean closeRoomFunction(shipJavaInterface room) {
        return false;
    }

    @Override
    public boolean addOneHability(CrewMember crewMember) {
        return false;
    }
    
}