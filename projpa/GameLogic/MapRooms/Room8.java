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
public class Room8 implements shipJavaInterface,Serializable {

    String name = "Mess Hall";
    protected static boolean sealedroom = false;
    
    @Override
    public shipJavaInterface newlocation(int location) {

        if(location == 1 && new Room1().getsealledstatus() == false)
            return new Room1();
        if(location == 4 && new Room4().getsealledstatus() == false)
            return new Room4();
        if(location == 2 && new Room2().getsealledstatus() == false)
            return new Room2();
        if(location == 5 && new Room5().getsealledstatus() == false)
            return new Room5();
        return this;
        
    }

    @Override
    public String ReturnName() {

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
