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
public class Room5 implements shipJavaInterface,Serializable {

    String name = "Conference Room";
    protected static boolean sealedroom = false;
    
    @Override
    public shipJavaInterface newLocation(int location) {

        if(location == 8 && !new Room8().getSealStatus())
            return new Room8();
        if(location == 3 && !new Room3().getSealStatus())
            return new Room3();
        if(location == 10 && !new Room10().getSealStatus())
            return new Room10();
        if(location == 1 && !new Room1().getSealStatus())
            return new Room1();
        return this;
        
    }

    @Override
    public String ReturnName() {

        return name;
    }

    @Override
    public ArrayList<Integer> ReturnAvailableRooms() {
       
        ArrayList<Integer> returnvalues = new ArrayList<>();

        returnvalues.add(3);
        returnvalues.add(8);
        returnvalues.add(10);
        returnvalues.add(1);

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
        return 8;
    }

    @Override
    public int getUp() {
        return 1;
    }

    @Override
    public int getLeft() {
        return 3;
    }

    @Override
    public int getDown() {
        return 10;
    }

    
}
