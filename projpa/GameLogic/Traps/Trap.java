package projpa.GameLogic.Traps;

import java.io.Serializable;
import projpa.GameLogic.MapRooms.*;
import projpa.GameLogic.GameData;

/**
 * Main function of the traps
 * 
 * @author Vitor
 * @author João
 */
public abstract class Trap implements Serializable{

    protected RoomState trapRoom;
    protected GameData game;
    private static int numberOfTraps = 0;
    private int id;

    public Trap(GameData game) {

        this.trapRoom = null;
        this.game = game;
        numberOfTraps++;
        this.id = numberOfTraps;

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public RoomState chooseTrapRoom(int roomNumber){

        RoomState room = new Room1();

        switch(roomNumber){
            
            case 1:
                room = new Room1();
                break;

            case 2:
                room = new Room2(); 
                break;

            case 3:
                room = new Room3();
                break;

            case 4:
                room = new Room4();
                break;

            case 5:
                room = new Room5();
                break;

            case 6:
                room = new Room6();
                break;

            case 7:
                room = new Room7();
                break;

            case 8:
                room = new Room8();
                break;

            case 9:
                room = new Room9();
                break;

            case 10:
                room = new Room10();
                break;

            case 11:
                room = new Room11();
                break;

            case 12:
                room = new Room12();
                break;

            default:

        }

        return room;

    }

    /**
     * @return the room
     */
    public RoomState getRoom() {
        return this.trapRoom;
    }

    /**
     * @return the trapRoom
     */
    public RoomState getTrapRoom() {
        return trapRoom;
    }

    /**
     * @return 
     */
    public abstract String getName();

    /**
     * @return 0 if nothing has been destroyed, -1 if a crew member has been destroyed 
     * and number of aliens destroyd by the trap
     */
    public abstract int destroyAliens();

    public boolean TrapRoomNameIsEqual(String name){
        return this.getTrapRoom().getName().equals(name);
    }

    public String getRoomName(){
        return this.getRoom().getName();
    }
    
}