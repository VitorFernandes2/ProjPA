package projpa.GameLogic.MapRooms;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * SecretRoom
 */
public class SecretRoom implements shipJavaInterface,Serializable {

    @Override
    public shipJavaInterface newLocation(int location) {
        return this;
    }

    @Override
    public String ReturnName() {
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
    public ArrayList<Integer> ReturnAvailableRooms() {
        return null;
    }

    @Override
    public boolean setSealed() {
        return false;
    }

    @Override
    public boolean getSealStatus() {
        return false;
    }
    
}