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
public class Room8 extends RoomState implements Serializable {

    String name = "Mess Hall";
    protected static boolean sealedroom = false;
    

    @Override
    public String getName() {

        return name;
    }

    @Override
    public ArrayList<Integer> Return_avaible_rooms() {
       
        ArrayList<Integer> returnvalues = new ArrayList<>();

        returnvalues.add(1);
        returnvalues.add(2);
        returnvalues.add(4);
        returnvalues.add(5);
        
        
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
        return 4;
    }

    @Override
    public int getUp() {
        return 1;
    }

    @Override
    public int getLeft() {
        return 5;
    }

    @Override
    public int getDown() {
        return 2;
    }

    
}
