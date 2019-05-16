package projpa.GameLogic;

import java.io.Serializable;
import projpa.GameLogic.MapRooms.shipJavaInterface;

/**
 * Alien
 */
public class Alien implements Serializable{

    private shipJavaInterface room;
    private static int numberOfAliens = 0;
    private int id;

    public Alien(){
        numberOfAliens++;
        this.id = numberOfAliens;
    }

    public Alien(shipJavaInterface room){

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
    public shipJavaInterface getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(shipJavaInterface room) {
        this.room = room;
    }
    
}