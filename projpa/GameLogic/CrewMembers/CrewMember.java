package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;
import projpa.GameLogic.MapRooms.*;

/*
 * This class is the crew member main class
 * @author Vitor
 * @author João
 */

public abstract class CrewMember implements Serializable{

    private int movement;
    private int movementToDo;
    private int attack;
    private shipJavaInterface crewMemberRoom;
    protected GameLogic game;  //This variable is used to allow Crew Members to have Special Move

    public CrewMember(int roomNumber, GameLogic game){
        // old
        this.crewMemberRoom = chooseRoom(roomNumber);
        this.game = game;

    }
    
    public CrewMember(GameLogic game){

        
        this.game = game;

    }

    /**
     * This function refill the movements of the CrewMembers
     */
    public void refillMovementToDo(){
        this.movementToDo = this.movement;
    }

    /**
     * @return the movementToDo
     */
    public int getMovementToDo() {
        return movementToDo;
    }

    /**
     * @param movementToDo the movementToDo to set
     */
    public void setMovementToDo(int movementToDo) {
        this.movementToDo = movementToDo;
    }

    /**
     * This function decrements the movement to do
     */
    public void decrementMovementToDo(){

        if (this.movementToDo > 0) 
            this.movementToDo--;

    }

    public shipJavaInterface chooseRoom(int roomNumber){
        
        // REVER, pode colidir com as mecanicas de rooms
        
        
        crewMemberRoom = new Room1();

        switch(roomNumber){
            
            case 1:
                crewMemberRoom = new Room1();
                break;

            case 2:
                crewMemberRoom = new Room2(); 
                break;

            case 3:
                crewMemberRoom = new Room3();
                break;

            case 4:
                crewMemberRoom = new Room4();
                break;

            case 5:
                crewMemberRoom = new Room5();
                break;

            case 6:
                crewMemberRoom = new Room6();
                break;

            case 7:
                crewMemberRoom = new Room7();
                break;

            case 8:
                crewMemberRoom = new Room8();
                break;

            case 9:
                crewMemberRoom = new Room9();
                break;

            case 10:
                crewMemberRoom = new Room10();
                break;

            case 11:
                crewMemberRoom = new Room11();
                break;

            case 12:
                crewMemberRoom = new Room12();
                break;

            default:

        }

        return crewMemberRoom;

    }

    /**
     * @return the crewMemberRoom
     */
    public shipJavaInterface getCrewMemberRoom() {
        return crewMemberRoom;
    }

    /**
     * @param crewMemberRoom the crewMemberRoom to set
     */
    public void setCrewMemberRoom(shipJavaInterface crewMemberRoom) {
        this.crewMemberRoom = crewMemberRoom;
    }

    /**
     * @param crewMemberRoomNumber
     */
    public void setCrewMemberRoom(int crewMemberRoomNumber) {
        this.crewMemberRoom = chooseRoom(crewMemberRoomNumber);
    }

    /**
     * @return the attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * @return the movement
     */
    public int getMovement() {
        return movement;
    }

    /**
     * @param attack the attack to set
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * @param movement the movement to set
     */
    public void setMovement(int movement) {
        this.movement = movement;
    }

    /**
     * 
     * @return name of the Crew Member
     */
    public abstract String getName();

    /**
     * This function makes the special move
     * @return the boolean special move value
     */
    public abstract boolean Special();   
    
    /**
     * This function makes the special move
     * @param room
     * @return the boolean special move value
     */
    public abstract boolean Special(int room);

}