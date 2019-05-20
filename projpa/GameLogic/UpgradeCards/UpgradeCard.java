package projpa.GameLogic.UpgradeCards;

import java.io.Serializable;
import java.util.ArrayList;

import projpa.GameLogic.Alien;
import projpa.GameLogic.GameData;
import projpa.GameLogic.CrewMembers.CrewMember;
import projpa.GameLogic.MapRooms.RoomState;
import projpa.GameLogic.Trackers.*;
import projpa.GameLogic.User.User;

/**
 * @UpgradeCard
 * This class is the main class of the upgrade cards
 * In this function we have the main sources to make the upgrades work
 */
public abstract class UpgradeCard implements Serializable{

    GameData game;

    public UpgradeCard(GameData game){

        this.game = game;

    }

    /**
     * @return the game
     */
    public GameData getGame() {
        return game;
    }

    /**
     * Return the User of the Game
     * @return Game User
     */
    public User getUser(){
        return this.game.getUser();
    }

    /**
     * This function returns all aliens of the game
     * @return game aliens
     */
    public ArrayList<Alien> getAliens(){

        ArrayList<Alien> aliens = new ArrayList<>();
        aliens.addAll(this.game.getAliens());
        return aliens;

    }

    /**
     * This function returns the hull tracker of the game
     * @return Game Hull Tracker
     */
    public HullTracker getHullTracker(){
        return this.game.getHullTracker();
    }

    /**
     * This function returns all crew members
     * @return crew members
     */
    public CrewMember[] getCrewMembers(){
        return getUser().getCrewMembers();
    }

    /**
     * This function returns the game health tracker
     * @return the game health tracker
     */
    public HealthTracker gethHealthTracker(){
        return this.game.getHealthTracker();
    }

    /**
     * This function sets the value of the game action points to the value of actionPoints
     * @param actionPoints
     */
    public void setActionPoints(int actionPoints){
        this.game.setActionPoints(actionPoints);
    }

    /**
     * This function get the action points
     * @return
     */
    public int getActionPoints(){
        return this.game.getActionPoints();
    }

    /**
     * This function returns if the game has some Action Points 
     * @return the action points
     */
    public boolean gotSomeActionPoints(){

        if(getActionPoints() > 0)
            return true;

        return false;

    }

    /**
     * This function do the upgrade of the game
     * @return true if all work has been done successfully
     */
    public abstract boolean upgradeFunction();

    /**
     * This function is used to close the room
     * @param room
     * @return true if all work has been done successfully
     */
    public abstract boolean closeRoomFunction(RoomState room);

    /**
     * This function is used to put a crew member with one more atack power or one more movement
     * @param crewMember
     * @return true if all work has been done successfully
     */
    public abstract boolean addOneHability(CrewMember crewMember);

}