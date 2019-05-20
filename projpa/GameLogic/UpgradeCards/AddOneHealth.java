package projpa.GameLogic.UpgradeCards;

import java.io.Serializable;
import projpa.GameLogic.GameData;
import projpa.GameLogic.CrewMembers.CrewMember;
import projpa.GameLogic.MapRooms.RoomState;

/**
 * AddOneHealth
 */
public class AddOneHealth extends UpgradeCard implements Serializable{

    public AddOneHealth(GameData game){
        super(game);
    }

    @Override
    public boolean upgradeFunction() {

        // Just the Doctor can do this upgrade
        if(gotSomeActionPoints())
            for (int i = 0; i < 2; i++) {

                if (getCrewMembers()[i].getName().equals("Doctor")) {

                    int hullstate = gethHealthTracker().getHullstate();
                    int limitHealth = 12;

                    if (hullstate < limitHealth) {

                        setActionPoints(getActionPoints() - 1); 
                        gethHealthTracker().setHullstate(hullstate + 1);
                        return true;

                    }

                }

            }

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