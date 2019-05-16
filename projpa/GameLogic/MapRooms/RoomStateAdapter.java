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
public class RoomStateAdapter implements Serializable{
    
    private shipJavaInterface estado;
    
    public String getName() {
        return estado.ReturnName();
    }
            
    public void newlocation(int value) {
        estado = estado.newLocation(value);
    }
    
    public ArrayList<Integer> Return_avaible_rooms () {
        return estado.ReturnAvailableRooms();
    }
    
    public boolean getsealledstatus (){
        return estado.getSealStatus();
    }
    
    public boolean setsealed() {
        return estado.setSealed();
    }
    
}
