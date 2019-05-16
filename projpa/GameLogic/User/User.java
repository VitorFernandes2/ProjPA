package projpa.GameLogic.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import projpa.GameLogic.Traps.Trap;
import projpa.GameLogic.UpgradeCards.UpgradeCard;
import projpa.GameLogic.CrewMembers.*;

/**
 * Class to specify the user settings
 * 
 * @author Vitor
 */

public class User implements Serializable{

    private String name;
    private CrewMember [] crewMember;
    private List<Trap> traps;
    private List<UpgradeCard> upgradeCards;
    private int points;
    
    public User(String name){

        this.upgradeCards = new ArrayList<>();
        this.crewMember = new CrewMember[2];
        this.name = new String(name);
        this.traps = new ArrayList<>();
        this.points = 0;

    }

    public User(String name, CrewMember member1, CrewMember member2, int points){

        this(name);
        this.crewMember[0] = member1;
        this.crewMember[1] = member2;
        this.points = points;

    }
    
    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
    public void addCrewMember(CrewMember CrewMember, int pos){
        // add crewmember if not defined
        this.crewMember[pos] = CrewMember;
        
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This function sets the crew member value
     * @param i
     * @param crewMember
     */
    public void setCrewMember(int i, CrewMember crewMember){
        this.crewMember[i] = crewMember;
    }

    /**
     * @return the upgradeCards
     */
    public List<UpgradeCard> getUpgradeCards() {
        return upgradeCards;
    }

    /**
     * @param upgradeCards the upgradeCards to set
     */
    public void setUpgradeCards(List<UpgradeCard> upgradeCards) {
        this.upgradeCards = upgradeCards;
    }
    
    public void addUpgradeCards(UpgradeCard upgradeCards) {
        this.upgradeCards.add(upgradeCards);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the crewMembers
     */
    public CrewMember[] getCrewMembers() {
        return crewMember;
    }

    /**
     * @return the traps
     */
    public List<Trap> getTraps() {
        return traps;
    }

    /**
     * @param traps the traps to set
     */
    public void setTraps(List<Trap> traps) {
        this.traps = traps;
    }

    /**
     * this function is used to add traps
     * @param trap
     */
    public void addTraps(Trap trap){
        this.traps.add(trap);
    }

    /**
     * this funtion removes one trap
     * @param id
     */
    public void removeTrap(int id){
        
        this.traps.remove(searcTrap(id));

    }

    /**
     * This function searchs for traps
     * @param id
     * @return 
     */
    public Trap searcTrap(int id){

        for (Trap tmpTrap : this.traps) {
            
            if (tmpTrap.getId() == id) {
                
                return tmpTrap;

            }

        }

        return null;

    }

    public void removeUpgradeCard(UpgradeCard upgradeCard){

        this.upgradeCards.remove(upgradeCard);

    }

}