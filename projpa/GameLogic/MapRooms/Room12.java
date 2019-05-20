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
public class Room12 extends RoomState implements Serializable{
        String name = "Hydroponics";
        protected static boolean sealedroom = false;
    

    @Override
    public String getName() {

        return name;
    }

    @Override
    public ArrayList<Integer> Return_avaible_rooms() {
       
        ArrayList<Integer> returnvalues = new ArrayList<>();

        returnvalues.add(9);
        returnvalues.add(10);

        
        return returnvalues;
    }
    
    @Override
    public boolean setsealed() {
        sealedroom = true;
        if (sealedroom == true)
            return true;
        return false;
        
    }

    @Override
    public boolean getsealledstatus() {
        return sealedroom;
    }

    @Override
    public int getRight() {
        return 10;
    }

    @Override
    public int getUp() {
        return 9;
    }

    @Override
    public int getLeft() {
        return 0;
    }

    @Override
    public int getDown() {
        return 10;
    }

}
