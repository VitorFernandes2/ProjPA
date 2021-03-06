package projpa.GameLogic.UpgradeCards;

import java.io.Serializable;
import projpa.GameLogic.Alien;
import projpa.GameLogic.GameData;
import projpa.GameLogic.CrewMembers.CrewMember;
import projpa.GameLogic.MapRooms.*;

/**
 * GainOneSealedRoom
 */
public class GainOneSealedRoom extends UpgradeCard implements Serializable{

    public GainOneSealedRoom(GameData game) {
        super(game);
    }

    // This function returns false because this class can't do this function
    @Override
    public boolean upgradeFunction() {
        return false;
    }

    /**
     * This function returns all crew members in the room
     * 
     * @param room
     * @return number of crew members in the room
     */
    private int countCrewMembers(RoomState room) {

        int count = 0;

        for (int i = 0; i < 2; i++)
            if (getCrewMembers()[i].getCrewMemberRoom() == room)
                count++;

        return count;

    }

    /**
     * This function returns all aliens of the room
     * 
     * @param room
     * @return number of aliens in the room
     */
    private int countAliens(RoomState room) {

        int count = 0;

        for (Alien alien : getAliens())
            if (alien.getRoom() == room)
                count++;

        return count;

    }

    /**
     * This function returns if is someone in the room
     * 
     * @param room
     * @return false if someone inside
     */
    private boolean getSomeoneInside(RoomState room) {

        int count = 0;

        count += countAliens(room);
        count += countCrewMembers(room);

        return count == 0;

    }

    /***
     * This function returns if the room can be blocked or not
     * 
     * @param room
     * @return true if can
     */
    public boolean blockRooms(RoomState room) {

        if (room instanceof Room3) {
            return true;
        }
        if (room instanceof Room9) {
            return true;
        }
        if (room instanceof Room12) {
            return true;
        }
        if (room instanceof Room4) {
            return true;
        }
        if (room instanceof Room11) {
            return true;
        }
        if (room instanceof Room7) {
            return true;
        }

        return false;

    }

    @Override
    public boolean closeRoomFunction(RoomState room) {

        if(gotSomeActionPoints())
            if (!room.getsealledstatus())
                if (blockRooms(room))
                    if (getSomeoneInside(room)){
                        setActionPoints(getActionPoints() - 1);
                        return room.setsealed();
                    }
                        

        return false;

    }

    @Override
    public boolean addOneHability(CrewMember crewMember) {
        return false;
    }
    
}