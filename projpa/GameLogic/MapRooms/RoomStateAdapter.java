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
    
    public RoomStateAdapter(int i){
        
        //temp, falata simplificar
        switch(i){
            case 1:
                this.estado = new Room1();
            break;
            case 2:
                this.estado = new Room2();
            break;
            case 3:
                this.estado = new Room3();
            break;
            case 4:
                this.estado = new Room4();
            break;
            case 5:
                this.estado = new Room5();
            break;
            case 6:
                this.estado = new Room6();
            break;
            case 7:
                this.estado = new Room7();
            break;
            case 8:
                this.estado = new Room8();
            break;
            case 9:
                this.estado = new Room9();
            break;
            case 10:
                this.estado = new Room10();
            break;
            case 11:
                this.estado = new Room11();
            break;
            case 12:
                this.estado = new Room12();
            break;
            
        }
            
    }
    
    public String getName() {
        return estado.ReturnName();
    }
            
    public void newlocation(int value) {
        estado = estado.newlocation(value);
    }
    
    public ArrayList<Integer> Return_avaible_rooms () {
        return estado.Return_avaible_rooms();
    }
    
    public boolean getsealledstatus (){
        return estado.getsealledstatus();
    }
    
    public boolean setsealed() {
        return estado.setsealed();
    }
    
}
