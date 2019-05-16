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
    public shipJavaInterface newLocation(int location) {

        if(location == 5 && !new Room5().getSealStatus())
            return new Room5();
        if(location == 12 && !new Room12().getSealStatus())
            return new Room12();
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

        returnvalues.add(5);
        returnvalues.add(12);
        returnvalues.add(6);
     
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
