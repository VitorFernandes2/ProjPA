package projpa.GameLogic.UpgradeCards;

import java.io.Serializable;
import projpa.GameLogic.GameData;
import projpa.GameLogic.CrewMembers.CrewMember;
import projpa.GameLogic.MapRooms.RoomState;

/**
 * AddOneMovement
 */
public class AddOneMovement extends UpgradeCard implements Serializable{

    public AddOneMovement(GameData game){
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

        if(!gotSomeActionPoints() || crewMember.getMovement() >= 3)
            return false;

        setActionPoints(getActionPoints() - 1);
        crewMember.setMovement(crewMember.getMovement() + 1);
        return true;

    }
    
}