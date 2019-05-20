package projpa.GameLogic.UpgradeCards;

import java.io.Serializable;
import projpa.GameLogic.GameData;
import projpa.GameLogic.CrewMembers.CrewMember;
import projpa.GameLogic.MapRooms.RoomState;

/**
 * RepairOneHull
 */
public class RepairOneHull extends UpgradeCard implements Serializable{

    public RepairOneHull(GameData game) {
        super(game);
    }

    @Override
    public boolean upgradeFunction() {

        // Just the Engineer can do this upgrade

        for (int i = 0; i < 2; i++) {

            if (this.game.isTheSameCrewMemberRoomNameByIndex(i, "Engineer")) {

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
    public boolean closeRoomFunction(RoomState room) {
        return false;
    }

    @Override
    public boolean addOneHability(CrewMember crewMember) {
        return false;
    }
    
}