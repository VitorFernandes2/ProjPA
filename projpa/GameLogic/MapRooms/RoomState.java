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
public abstract class RoomState implements Serializable{
    
    
    public abstract String getName() ;
    
    public abstract ArrayList<Integer> Return_avaible_rooms();

    public int[][] getMap(){

        int map[][] = {
                { 0, 1, 1, 0},
                { 3, 5, 8, 4},
                { 9, -1, -1, 11},
                { 12, 10, 2, 7},
                { 0, 6, 6, 0}
        };

        return map;

    }
          
    public RoomState newlocation(int value) {
        
        ArrayList<Integer> pode = new ArrayList<Integer>(); 
        pode = Return_avaible_rooms();
        
        int room=-1;
        
        for (int i=0;i < pode.size();i++){
            
            if(pode.get(i) == value)
                room = value;
            
        }
            
        if(room != -1)
            getRoomByIndex(room);
           
        return this;
    }

    public RoomState getRoomByIndex(int index){

        switch(index){
            case 1:
                return new Room1();
            case 2:
                return new Room2();
            case 3:
                return new Room3();
            case 4:
                return new Room4();
            case 5:
                return new Room5();
            case 6:
                return new Room6();
            case 7:
                return new Room7();
            case 8:
                return new Room8();
            case 9:
                return new Room9();
            case 10:
                return new Room10();
            case 11:
                return new Room11();
            case 12:
                return new Room12();
        }

        return this;

    }

    public int getNumberOfRooms(){
        return 12;
    }

    public String getAllClassesNumberAndName(){

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= getNumberOfRooms() ; i++) {

            sb.append(i + " - " + getRoomByIndex(i).getName() + "\n");

        }

        return sb.toString();

    }

    public abstract boolean getsealledstatus ();

    public abstract boolean setsealed() ;

    public abstract int getRight();
    public abstract int getLeft();
    public abstract int getUp();
    public abstract int getDown();
    
    
    
}
