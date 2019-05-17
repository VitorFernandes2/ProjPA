package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameData;
import projpa.GameLogic.MapRooms.*;
import projpa.GameLogic.General.*;

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
    protected GameData game;  //This variable is used to allow Crew Members to have Special Move

    public CrewMember(int roomNumber, GameData game){
        // old
        this.crewMemberRoom = chooseRoom(roomNumber);
        this.game = game;

    }
    
    public CrewMember(GameData game){
        
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

        this.crewMemberRoom = new General().convertIntToRoom(roomNumber);
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

    public boolean gotMovementsToDo(){
        return (movementToDo > 0);
    }

    public boolean gotMovement(){
        return (this.movement > 0);
    }

}