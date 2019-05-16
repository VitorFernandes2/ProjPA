package projpa.GameLogic.UpgradeCards;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;
import projpa.GameLogic.CrewMembers.CrewMember;
import projpa.GameLogic.MapRooms.shipJavaInterface;

/**
 * GainOneAtack
 */
public class GainOneAtack extends UpgradeCard implements Serializable{

    public GainOneAtack(GameLogic game){
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

        if(!gotSomeActionPoints() || crewMember.getAttack() >= 306)
            return false;

        setActionPoints(getActionPoints() - 1);
        crewMember.setAttack(crewMember.getAttack() + 100);
        return true;

    }
    
}