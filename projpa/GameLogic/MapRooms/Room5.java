/* 
################################
#### Projeto de PA 2018/2019 ###
################################
 */
package projpa.GameLogic.MapRooms;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author a21270909
 */
public class Room5 extends RoomState implements Serializable {

    String name = "Conference Room";
    protected static boolean sealedroom = false;

    @Override
    public String getName() {

        return name;
    }

    @Override
    public ArrayList<Integer> Return_avaible_rooms() {
       
        ArrayList<Integer> returnvalues = new ArrayList<>();

        returnvalues.add(3);
        returnvalues.add(8);
        returnvalues.add(10);
        returnvalues.add(1);

        return returnvalues;
    }

    @Override
    public boolean setsealed() {
        sealedroom = true;
        return true;
    }

    @Override
    public boolean getsealledstatus() {
        return sealedroom;
    }

    @Override
    public int getRight() {
        return 8;
    }

    @Override
    public int getUp() {
        return 1;
    }

    @Override
    public int getLeft() {
        return 3;
    }

    @Override
    public int getDown() {
        return 10;
    }

    
}
