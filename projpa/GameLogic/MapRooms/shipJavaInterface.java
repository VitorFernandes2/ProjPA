/* 
################################
#### Projeto de PA 2018/2019 ###
################################
 */
package projpa.GameLogic.MapRooms;

import java.util.ArrayList;

/**
 *
 * @author a21270909
 */
public interface shipJavaInterface {    
    
    public shipJavaInterface newlocation (int location);
    
    public String ReturnName();

    public int getRight();

    public int getUp();

    public int getLeft();

    public int getDown();

    public ArrayList<Integer> Return_avaible_rooms ();
    
    public boolean setsealed();
    
    public boolean getsealledstatus();    

}
