package projpa.GameLogic.MapRooms;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * SecretRoom
 */
public class SecretRoom extends RoomState implements Serializable {


    @Override
    public String getName() {
        return "SecretRoom";
    }

    @Override
    public int getRight() {
        return 0;
    }

    @Override
    public int getUp() {
        return 0;
    }

    @Override
    public int getLeft() {
        return 0;
    }

    @Override
    public int getDown() {
        return 0;
    }

    @Override
    public ArrayList<Integer> Return_avaible_rooms() {
        return null;
    }

    @Override
    public boolean setsealed() {
        return false;
    }

    @Override
    public boolean getsealledstatus() {
        return false;
    }
    
}