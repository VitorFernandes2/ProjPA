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
    
    shipJavaInterface newLocation(int location);
    
    String ReturnName();

    int getRight();

    int getUp();

    int getLeft();

    int getDown();

    ArrayList<Integer> ReturnAvailableRooms();
    
    boolean setSealed();
    
    boolean getSealStatus();

}
