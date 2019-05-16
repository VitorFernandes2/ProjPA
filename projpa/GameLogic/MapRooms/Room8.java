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
    public shipJavaInterface newLocation(int location) {

        if(location == 1 && !new Room1().getSealStatus())
            return new Room1();
        if(location == 4 && !new Room4().getSealStatus())
            return new Room4();
        if(location == 2 && !new Room2().getSealStatus())
            return new Room2();
        if(location == 5 && !new Room5().getSealStatus())
            return new Room5();
        return this;
        
    }

    @Override
    public String ReturnName() {

        return name;
    }

    @Override
    public ArrayList<Integer> ReturnAvailableRooms() {
       
        ArrayList<Integer> returnvalues = new ArrayList<>();

        returnvalues.add(1);
        returnvalues.add(2);
        returnvalues.add(4);
        returnvalues.add(5);
        
        
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
