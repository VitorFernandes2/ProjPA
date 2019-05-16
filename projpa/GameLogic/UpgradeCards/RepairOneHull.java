package projpa.GameLogic.UpgradeCards;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;
import projpa.GameLogic.CrewMembers.CrewMember;
import projpa.GameLogic.MapRooms.shipJavaInterface;

/**
 * RepairOneHull
 */
public class RepairOneHull extends UpgradeCard implements Serializable{

    public RepairOneHull(GameLogic game) {
        super(game);
    }

    @Override
    public boolean upgradeFunction() {

        // Just the Engineer can do this upgrade

        for (int i = 0; i < 2; i++) {

            if (getCrewMembers()[i].getName().equals("Engineer")) {

                int hullstate = getHullTracker().getHullstate();
                int limitHealth = 12;

                if (hullstate < limitHealth) {

                    game.setActionPoints(game.getActionPoints() - 1);
                    getHullTracker().setHullstate(hullstate + 1);
                    return true;

                }

            }

        }

        return false;

    }

    // This function returns false because this class can't do this function
    @Override
    public boolean closeRoomFunction(shipJavaInterface room) {
        return false;
    }

    @Override
    public boolean addOneHability(CrewMember crewMember) {
        return false;
    }
    
}