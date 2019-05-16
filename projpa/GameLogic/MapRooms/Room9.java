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
public class Room9 implements shipJavaInterface,Serializable{
        String name = "Engineering";
        protected static boolean sealedroom = false;
    
    @Override
    public shipJavaInterface newlocation(int location) {

        if(location == 3 && new Room3().getsealledstatus() == false)
            return new Room3();
        if(location == 12 && new Room12().getsealledstatus() == false)
            return new Room12();
        return this;
        
    }

    @Override
    public String ReturnName() {

        return name;
    }

    @Override
    public ArrayList<Integer> Return_avaible_rooms() {
       
        ArrayList<Integer> returnvalues = new ArrayList<>();

        returnvalues.add(3);
        returnvalues.add(12);

        
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
        return 3;
    }

    @Override
    public int getLeft() {
        return 0;
    }

    @Override
    public int getDown() {
        return 12;
    }

    
}
