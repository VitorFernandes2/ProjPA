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
public class Room10 implements shipJavaInterface,Serializable{
        String name = "Astrometrics";
        protected static boolean sealedroom = false;   
        
        
    @Override
    public shipJavaInterface newlocation(int location) {

        if(location == 5 && new Room5().getsealledstatus() == false)
            return new Room5();
        if(location == 12 && new Room12().getsealledstatus() == false)
            return new Room12();
        if(location == 6 && new Room6().getsealledstatus() == false)
            return new Room6();

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
        returnvalues.add(12);
        returnvalues.add(6);
     
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
        return 5;
    }

    @Override
    public int getLeft() {
        return 12;
    }

    @Override
    public int getDown() {
        return 6;
    }

}
