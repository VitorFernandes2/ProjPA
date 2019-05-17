package projpa.GameLogic.StateMachine;

import projpa.GameLogic.GameData;

/**
 *This class represents the interface for the states of the game
 * @author Vitor
 * @author João
 */
public interface IStates {
    
    /**
     * This function setups all the data necessary to initialize the game
     * @param name
     * @return Actual State
     */
    public IStates setup(String name);
    
    
    /**
     * this function receives 2 crew members and put them has the crew members of the ship
     * @param crewMember1
     * @param crewMember2
     * @return Actual State
     */
    public IStates selectCrewMembers(String crewMember1, String crewMember2);
    
    /**
     * this function doesnt recive nothing, the objetivo is to randomely create/select the 2 crewmemebers
     * @return Actual State
     */
    public IStates selectRandomCrewMembers();

    /**
     * This function places the first Crew Member
     * @param room
     * @return Actual state
     */
    public IStates placeFirstCrewMember(int room);

    /**
     * This function places the second Crew Member
     * @param room
     * @return Actual State
     */
    public IStates placeSecondCrewMember(int room);

    /**
     * This function will start an atack crewMember vs Alien
     * @param crewMemberIndex
     * @return Actual State
     */
    public IStates atack(int crewMemberIndex);

    /**
     * This function will move one crewMember
     * @param crewMemberIndex
     * @param crewMemberNewRoom
     * @return Actual State
     */
    public IStates move(int crewMemberIndex, int crewMemberNewRoom);
    
    /**
     * This function heals 1 of health of the health tracker
     * @return Actual State
     */
    public IStates heal();
    
    /**
     * This function fixs one point of the hull tracker
     * @return Actual State
     */
    public IStates fixOneHull();
    
    /**
     * This function creates a trap in a room
     * @param room
     * @param trapIndex
     * @return Actual State
     */
    public IStates setTrap(int room, int trapIndex);

    /**
     * This function detonates a Particle Disperser
     * @param particleDisperser
     * @return Actual State
     */
    public IStates detonateParticleDisperser(String particleDisperser);

    /**
     * This function locks a room
     * @param roomIndex
     * @return Actual State
     */
    public IStates sealRoom(int roomIndex);

    /**
     * This function will save the game in a file
     * @return Actual State
     */
    public IStates saveGame();

    /**
     * This function creates the game
     * @param game
     * @return Actual State
     */
    public IStates InputBeginning(GameData game);

    /**
     * This function restarts the game
     * @return the Actual State of the game
     */
    public IStates StartNewBegin();

    /**
     * This function returns the anterior state
     * @return The Anterior state
     */
    public IStates goBack();

    /**
     * This function does all Alien Phase and proceeds in the turn
     * @return
     */
    public IStates nextTurn(); 
    
    //rest phase

    public void addonehealth();
    public void repairhull();
    public void buildorganicdetonator();
    public void builoneparticleddispenser();

    public void addonemovement(int crewmemeber);
    public void gainonesealedromtoken();
    
    public void gainoneattack(int crewmember);
    public void addonethedice();
    
    public Boolean wasSaved();
    
}
