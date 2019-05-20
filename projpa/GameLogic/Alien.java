package projpa.GameLogic;

import java.io.Serializable;
import projpa.GameLogic.MapRooms.RoomState;

/**
 * Alien
 */
public class Alien implements Serializable{

    private RoomState room;
    private static int numberOfAliens = 0;
    private int id;

    public Alien(){
        numberOfAliens++;
        this.id = numberOfAliens;
    }

    public Alien(RoomState room){

        this();
        this.room = room;

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the room
     */
    public RoomState getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(RoomState room) {
        this.room = room;
    }
    
}