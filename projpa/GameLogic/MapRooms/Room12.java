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
public class Room12 implements shipJavaInterface,Serializable{
        String name = "Hydroponics";
        protected static boolean sealedroom = false;
    
    @Override
    public shipJavaInterface newLocation(int location) {

        if(location == 9 && !new Room9().getSealStatus())
            return new Room9();
        if(location == 10 && !new Room10().getSealStatus())
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

        returnvalues.add(9);
        returnvalues.add(10);

        
        return returnvalues;
    }
    
    @Override
    public boolean setSealed() {
        sealedroom = true;
        if (sealedroom == true)
            return true;
        return false;
        
    }

    @Override
    public boolean getSealStatus() {
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
