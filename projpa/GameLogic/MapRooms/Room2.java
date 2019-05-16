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
public class Room2 implements shipJavaInterface,Serializable{

    String name = "Sick Bay";
    protected static boolean sealedroom = false;    
    
    @Override
    public shipJavaInterface newLocation(int location) {

        if(location == 8 && !new Room8().getSealStatus())
            return new Room8();
        if(location == 7 && !new Room7().getSealStatus())
            return new Room7();
        if(location == 6 && !new Room6().getSealStatus())
            return new Room6();
        return this;
        
    }

    @Override
    public String ReturnName() {

        return name;
    }

    @Override
    public ArrayList<Integer> ReturnAvailableRooms() {
       
        ArrayList<Integer> returnvalues = new ArrayList<>();

        returnvalues.add(6);
        returnvalues.add(7);
        returnvalues.add(8);
        
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
        return 7;
    }

    @Override
    public int getUp() {
        return 8;
    }

    @Override
    public int getLeft() {
        return 0;
    }

    @Override
    public int getDown() {
        return 6;
    }

    
}
