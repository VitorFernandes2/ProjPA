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
public class Room11 implements shipJavaInterface,Serializable{
        String name = "Holodeck";
        protected static boolean sealedroom = false;
    
    @Override
    public shipJavaInterface newlocation(int location) {

        if(location == 7 && new Room7().getsealledstatus() == false)
            return new Room7();
        if(location == 4 && new Room4().getsealledstatus() == false)
            return new Room4();

        return this;
        
    }

    @Override
    public String ReturnName() {

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
