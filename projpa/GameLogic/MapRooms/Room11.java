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
public class Room11 extends RoomState implements Serializable{
        String name = "Holodeck";
        protected static boolean sealedroom = false;
    

    @Override
    public String getName() {

        return name;
    }

    @Override
    public ArrayList<Integer> Return_avaible_rooms() {
       
        ArrayList<Integer> returnvalues = new ArrayList<>();

        returnvalues.add(4);
        returnvalues.add(7);

        
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
        return 0;
    }

    @Override
    public int getUp() {
        return 4;
    }

    @Override
    public int getLeft() {
        return 0;
    }

    @Override
    public int getDown() {
        return 7;
    }

}
