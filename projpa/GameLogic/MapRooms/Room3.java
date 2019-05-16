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
public class Room3 implements shipJavaInterface,Serializable {
        String name = "Brig";
        protected static boolean sealedroom = false;
    
    @Override
    public shipJavaInterface newlocation(int location) {

        if(location == 5 && new Room5().getsealledstatus() == false)
            return new Room5();
        if(location == 9 && new Room9().getsealledstatus() == false)
            return new Room9();
        return this;
        
    }

    @Override
    public String ReturnName() {

        return name;
    }

    @Override
    public ArrayList<Integer> Return_avaible_rooms() {
       
        ArrayList<Integer> returnvalues = new ArrayList<>();

        returnvalues.add(5);
        returnvalues.add(9);
        
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
        return 5;
    }

    @Override
    public int getUp() {
        return 5;
    }

    @Override
    public int getLeft() {
        return 0;
    }

    @Override
    public int getDown() {
        return 9;
    }

}
