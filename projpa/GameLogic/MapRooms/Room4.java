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
public class Room4 implements shipJavaInterface,Serializable{
        String name = "Crew Quarters";
        protected static boolean sealedroom = false;
    
    @Override
    public shipJavaInterface newLocation(int location) {

        if(location == 8 && !new Room8().getSealStatus())
            return new Room8();
        if(location == 11 && !new Room11().getSealStatus())
            return new Room11();
        return this;
        
    }

    @Override
    public String ReturnName() {

        return name;
    }

    @Override
    public ArrayList<Integer> ReturnAvailableRooms() {
       
        ArrayList<Integer> returnvalues = new ArrayList<>();

        returnvalues.add(8);
        returnvalues.add(11);
        
        return returnvalues;
    }
    
    @Override
    public boolean setSealed() {
        return true;
    }

    @Override
    public boolean getSealStatus() {
        return sealedroom;
    }

    @Override
    public int getRight() {
        return 0;
    }

    @Override
    public int getUp() {
        return 8;
    }

    @Override
    public int getLeft() {
        return 8;
    }

    @Override
    public int getDown() {
        return 11;
    }

    
}
