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
public class Room6 implements shipJavaInterface,Serializable {
        String name = "Shuttle Bay";
        protected static boolean sealedroom = false;
    
    @Override
    public shipJavaInterface newLocation(int location) {

        if(location == 2 && !new Room2().getSealStatus())
            return new Room2();
        if(location == 10 && new Room10().getSealStatus())
            return new Room10();
        return this;
        
    }

    @Override
    public String ReturnName() {

        return name;
    }

    @Override
    public ArrayList<Integer> ReturnAvailableRooms() {
       
        ArrayList<Integer> returnvalues = new ArrayList<>();

        returnvalues.add(2);
        returnvalues.add(10);

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
        return 2;
    }

    @Override
    public int getUp() {
        return 0;
    }

    @Override
    public int getLeft() {
        return 10;
    }

    @Override
    public int getDown() {
        return 0;
    }
    
}
