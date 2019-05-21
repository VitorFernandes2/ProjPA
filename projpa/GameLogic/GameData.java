/* 
################################
#### Projeto de PA 2018/2019 ###
################################
 */
package projpa.GameLogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import projpa.GameLogic.CrewMembers.*;
import projpa.GameLogic.Dice.Dice;
import projpa.GameLogic.MapRooms.*;
import projpa.GameLogic.StateMachine.*;
import projpa.GameLogic.Trackers.HealthTracker;
import projpa.GameLogic.Trackers.HullTracker;
import projpa.GameLogic.Trackers.JourneyTracker;
import projpa.GameLogic.Trackers.InspirationPoints;
import projpa.GameLogic.Traps.Trap;
import projpa.GameLogic.UpgradeCards.AddOneAtackDiceResult;
import projpa.GameLogic.UpgradeCards.GainOneSealedRoom;
import projpa.GameLogic.UpgradeCards.UpgradeCard;
import projpa.GameLogic.User.User;
import projpa.GameLogic.General.General;

/**
 *
 * @author Vitor
 */
public class GameData implements Serializable{

    private User user;
    private IStates stateOfTheGame;
    private int actionPoints;
    private List<Alien> aliens;
    private HullTracker hullTracker;
    private HealthTracker healthTracker;
    private JourneyTracker journeyTracker;
    private InspirationPoints inspirationspoints;
    
    public GameData(){

        this.aliens = new ArrayList<>();
        this.stateOfTheGame = new AwaitBeginning();
        this.actionPoints = 5;
        this.hullTracker = new HullTracker(8);
        this.healthTracker = new HealthTracker(8);
        this.journeyTracker = new JourneyTracker();
        this.inspirationspoints = new InspirationPoints();
        
    }

    public GameData(GameData game){
        
        this.user = game.getUser();
        this.aliens = game.getAliens();
        this.stateOfTheGame = game.getStateOfTheGame();
        this.actionPoints = game.getActionPoints();
        this.hullTracker = game.getHullTracker();
        this.healthTracker = game.getHealthTracker();
        this.journeyTracker = game.getJourneyTracker();
        this.inspirationspoints = game.getInspirationPoints();

    }

    /**
     * @return the stateOfTheGame
     */
    public IStates getStateOfTheGame() {
        return stateOfTheGame;
    }

    /**
     * @param stateOfTheGame the stateOfTheGame to set
     */
    public void setStateOfTheGame(IStates stateOfTheGame) {
        this.stateOfTheGame = stateOfTheGame;
    }

    public GameData(String name, CrewMember member1, CrewMember member2, int points){

        this();
        user = new User(name, member1, member2, points);
        
    }

    public void addTurn(){
        this.journeyTracker.addJourneyState();
    }

    /**
     * This function returns the traps of the user
     * @return the traps
     */
    public List<Trap> getTraps(){
        return this.getUser().getTraps();
    }

    /**
     * This function returns the trap correspondent to the index
     * @param id
     * @return the id correspondent trap
     */
    public Trap getTrapById(int id){

        for (Trap trap : getTraps())             
            if(trap.getId() == id)
                return trap;

        return null;

    }

    /**
     * This function see if is any trap inside the room
     * @param room
     * @return true if some trap inside
     */
    public boolean isTrapInside(RoomState room){

        for (Trap trap : this.getTraps())
            if (trap.getRoom().getName().equals(room.getName()))
                return true;

        return false;

    }

    /**
     * @return the aliens
     */
    public List<Alien> getAliens() {
        return aliens;
    }
    

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @return the actionPoints
     */
    public int getActionPoints() {
        return actionPoints;
    }

    /**
     * @param actionPoints the actionPoints to set
     */
    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    /**
     * @return the healthTracker
     */
    public HealthTracker getHealthTracker() {
        return healthTracker;
    }
    
    
    public InspirationPoints getInspirationPoints() {
        return inspirationspoints;
    }

    public void addInspirationPoints(){

        if (this.inspirationspoints.getMaxSize() > this.inspirationspoints.getInspstate()) {
            
            this.inspirationspoints.setInspstate(this.inspirationspoints.getInspstate() + 1);

        }

    }

    /**
     * @return the hullTracker
     */
    public HullTracker getHullTracker() {
        return hullTracker;
    }

    /**
     * @return the journeyTracker
     */
    public JourneyTracker getJourneyTracker() {
        return journeyTracker;
    }

    /**
     * @param healthTracker the healthTracker to set
     */
    public void setHealthTrackervalue(int healthTracker) {
        this.getHealthTracker().setHullstate(healthTracker);
    }

    /**
     * @param healthTracker the healthTracker to set
     */
    public void setHullTrackervalue(int healthTracker) {
        this.getHullTracker().setHullstate(healthTracker);
    }

    /**
     * This function adds a new alien
     */
    public void addRandomAlien(){

        Alien alien = null;
        RoomState alienRoom = null;

        {

            int roomNumber;
            Boolean created = false;
            Dice dice = new Dice();

            while(!created){

                roomNumber = dice.roll(1, 12);
                alienRoom = isRoomLocked(roomNumber);

                if(alienRoom != null)
                    created = true;

            }

        }
        
        alien = new Alien(alienRoom);
        this.aliens.add(alien);

    }

    /**
     * This function gets if the room is locked if it isn't returns the room
     * @param roomNumber
     * @return a room or null if the door is locked
     */
    public RoomState isRoomLocked(int roomNumber){

        RoomState room;

        room = new General().convertIntToRoom(roomNumber);

        if(room.getsealledstatus() == true)
            room = null;

        return room;

    }

    /**
     * This function removes an alien
     * @param id
     */
    public void removeAlien(int id){

        this.aliens.remove(searchAlien(id));

    }

    /**
     * This function searchs for an alien
     * @param id
     * @return the alien with the id
     */
    public Alien searchAlien(int id){

        for (Alien alien : this.aliens)             
            if(alien.getId() == id)
                return alien;        

        return null;

    }

    /**
     * This function returns a random room from a room list
     * @param roomList
     * @return a room
     */
    public RoomState randomRoom(ArrayList<Integer> roomList){
        
        RoomState room = null;

        {
        
            Random rand = new Random();
            int randomElement;
            boolean validRoom = false;
            
            while(!validRoom){

                randomElement = roomList.get(rand.nextInt(roomList.size()));
                room = isRoomLocked(randomElement);

                if(room == null){
                    
                    validRoom = false;
                    roomList.remove(roomList.indexOf(randomElement));

                }
                else{

                    validRoom = true;

                }

                if(roomList.isEmpty())
                    validRoom = true;
                                    
            }
        
        }

        return room;
        
    }

    /**
     * This function returns the unlocked rooms in the neighborhood
     * @param rooms
     * @return the available rooms
     */
    public ArrayList<RoomState> getAvailableNextRooms(ArrayList<Integer> rooms){
        
        ArrayList<RoomState> nextRooms = new ArrayList<>();

        for (int i = 0; i < rooms.size(); i++) {
            
            RoomState room = isRoomLocked(rooms.get(i));

            if(room != null)
                nextRooms.add(room);

        }

        return nextRooms;
        
    }

    /**
     * This function returns the name of the crew member in that index
     * @param index
     * @return the name of the index crew member
     */
    public String getCrewMemberName(int index){
        return this.getCrewMember(index).getName();
    }

    /**
     * This function makes the alien follow the closest crew member if the room is unlocked
     * @param alien
     */
    public void followCrewMember(Alien alien){

        //Objective rooms
        ArrayList<Integer> crewMembersLocation = new ArrayList<>();
        crewMembersLocation.add(convertRoomToInt(getCrewMember1Room()));
        crewMembersLocation.add(convertRoomToInt(getCrewMember2Room()));

        alien.setRoom(getBestRoomForAlienMove(alien, crewMembersLocation));

    }

    /**
     * This function calculates the nearest room to the nearest crew member
     * @param alien
     * @param crewMembersLocation
     * @return the nearest room to follow the nearest crew member
     */
    public RoomState getBestRoomForAlienMove(Alien alien, ArrayList<Integer>crewMembersLocation){

        int map[][] = getMap();
        int roomNumber = convertRoomToInt(alien.getRoom());
        RoomState room;

        //best Otion of deslocation
        room = new General().convertIntToRoom(findBestRoom(map, roomNumber, crewMembersLocation));

        //First room to find the crew member
        return room;

    }

    public int[] getCoords(int map[][], int roomNumber){

        int coords[] = new  int[2];

        for (int i = 0; i < 5; i++) {
            
            for (int j = 0; j < 4; j++) {
                
                if (map[i][j] == roomNumber) {
                    
                    coords[1] = i; //y
                    coords[0] = j; //x

                }

            }

        }

        return coords;

    }

    public int findCostInMapByNumber(int map[][], int crewMemberRoomNumber, int roomNumber){

        int cost;

        //Location of the Crew Member
        int distanceCrew[] = getCoords(map, crewMemberRoomNumber);

        //Location of the Alien
        int distanceAlien[] = getCoords(map, roomNumber);

        cost = distanceAlien[1] - distanceCrew[1];

        if (cost < 0) cost *= -1;

        int cost2 =  distanceAlien[0] - distanceCrew[0];

        if (cost2 < 0) cost2 *= - 1;

        cost += cost2;

        return cost;

    }

    public int bestCrewMember(int map[][], ArrayList<Integer>crewMembersLocation, int roomNumber){

        int cost1 = findCostInMapByNumber(map, crewMembersLocation.get(0), roomNumber);
        int cost2 = findCostInMapByNumber(map, crewMembersLocation.get(1), roomNumber);

        if (cost1 <= cost2)
            return 1;
        else
            return 2;

    }

    public int adjustFindBestRoom(int position, int roomNumber){

        if(position != 0){

            RoomState auxRoom = new General().convertIntToRoom(position);
            RoomState auxRoom2 = new General().convertIntToRoom(roomNumber);

            if (!auxRoom.getsealledstatus()) {
            
                return position;

            }
            else{

                RoomState tmpRoom = randomRoom(auxRoom2.Return_avaible_rooms());
                
                if(tmpRoom != null)
                    return convertRoomToInt(tmpRoom);
                else
                    return roomNumber;

            }

        }
        else{

            RoomState auxRoom2 = new General().convertIntToRoom(roomNumber);
            RoomState tmpRoom = randomRoom(auxRoom2.Return_avaible_rooms());
            
            if(tmpRoom != null)
                return convertRoomToInt(tmpRoom);
            else
                return roomNumber;

        }

    }

    /**
     * This function returns the best option of deslocation of the neighboors
     * @param map
     * @param roomNumber
     * @param crewMembersLocation
     * @return best neighboor
     */
    public int findBestRoom(int map[][], int roomNumber, ArrayList<Integer>crewMembersLocation){

        //Nearest Crew Member
        int target = bestCrewMember(map, crewMembersLocation, roomNumber);
        //Nearest Crew Member location (x, y)
        int targetLocation[] = getCoords(map, convertRoomToInt(getCrewMember(target - 1).getCrewMemberRoom()));
            
        //lines
        for (int i = 0; i < 5; i++) {
            
            //columns
            for (int j = 0; j < 4; j++) {
                
                if (map[i][j] == roomNumber) {

                    RoomState auxRoom = new General().convertIntToRoom(roomNumber);

                    if (targetLocation[1] == i) { //If in the same line
                        
                        if (targetLocation[0] == j) { //If in the same column
                            
                            return roomNumber;

                        }
                        else
                            if(targetLocation[0] < j){ //If in the left column
                                    
                                int position = auxRoom.getLeft();
                                return adjustFindBestRoom(position, roomNumber);

                            }
                            else{ //If in the right column

                                int position = auxRoom.getRight();
                                return adjustFindBestRoom(position, roomNumber);                              

                            }

                    }
                    else
                        if(targetLocation[1] > i){ //If in one of the lanes below
                            
                            int position = auxRoom.getDown();
                            return adjustFindBestRoom(position, roomNumber);

                        }
                        else{ //If in one of the lanes above

                            int position = auxRoom.getUp();
                            return adjustFindBestRoom(position, roomNumber);

                        }

                }

            }

        }       

        return roomNumber;

    }

    /**
     * Return the map
     * @return the map
     */
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

    /**
     * This function converts a room to the correspondent integer
     * @param room
     * @return the room integer
     */
    public int convertRoomToInt(RoomState room){

        int number = 0;

        if (room instanceof Room1)
            number = 1;
        if (room instanceof Room2)
            number = 2;
        if (room instanceof Room3)
            number = 3;
        if (room instanceof Room4)
            number = 4;
        if (room instanceof Room5)
            number = 5;
        if (room instanceof Room6)
            number = 6;
        if (room instanceof Room7)
            number = 7;
        if (room instanceof Room8)
            number = 8;
        if (room instanceof Room9)
            number = 9;
        if (room instanceof Room10)
            number = 10;
        if (room instanceof Room11)
            number = 11;
        if (room instanceof Room12) 
            number = 12;

        return number;

    }

    /**
     * This function returns the room of the first crew member
     * @return the room of the first Crew Member
     */
    public RoomState getCrewMember1Room(){
        return user.getCrewMembers()[0].getCrewMemberRoom();
    }

    /**
     * This function returns the room of the second crew member
     * @return the room of the second Crew Member
     */
    public RoomState getCrewMember2Room(){
        return user.getCrewMembers()[1].getCrewMemberRoom();
    }

    /**
     * This function returns all available rooms
     * @param nearRooms
     * @return the available rooms
     */
    public ArrayList<RoomState> getAvailableRooms(ArrayList<RoomState> nearRooms){

        ArrayList<RoomState> availableNearRooms = new ArrayList<>();

        for (RoomState room : nearRooms)
            if(!room.getsealledstatus())
                availableNearRooms.add(room);


        return availableNearRooms;

    }

    /**
     * This function returns the correspondent rooms
     * @param nearRooms
     * @return the correspondent rooms of the integers
     */
    public ArrayList<RoomState> convertIntegerRoomStates(ArrayList<Integer> nearRooms){

        ArrayList<RoomState> roomList = new ArrayList<>();

        for (int i = 0; i < nearRooms.size(); i++)             
            roomList.add(new General().convertIntToRoom(nearRooms.get(i)));

        return roomList;

    }

    /**
     * This function returns the crew member room name
     * @param index
     * @return the crew member room name
     */
    public String getCrewMemberRoomName(int index){
        return getCrewMember(index).getCrewMemberRoom().getName();
    }

    /**
     * This function moves the aliens
     */
    public void moveAliens(){

        for (Alien alien : this.aliens) {
            
            //Available Rooms
            ArrayList<RoomState> alienRooms = new ArrayList<>();
            alienRooms.addAll(getAvailableNextRooms(Return_Alien_avaible_rooms(alien)));

            if(!alienRooms.isEmpty() && alien.getRoom().getsealledstatus() == false)
                followCrewMember(alien);
            
            if (this.explodeTrapToAlien(alien)){
                this.aliens.remove(alien);
                this.addPoints();
            }                

        }

    }

    /**
     * This function returns alien Available rooms
     * @param alien
     * @return Alien Available rooms
     */
    public ArrayList<Integer> Return_Alien_avaible_rooms (Alien alien){
        return alien.getRoom().Return_avaible_rooms();
    }

    /**
     * This function returns all crew members
     * @return All Crew Members
     */
    public CrewMember[] getCrewMembers(){
        return this.user.getCrewMembers();
    }

    /**
     * This function returns a crew member
     * @param index
     * @return A crew member
     */
    public CrewMember getCrewMember(int index){
        return getCrewMembers()[index];
    }

    public boolean alienHasTheSameLocation(String location, int i){

        return aliens.get(i).getRoom().getName().equals(location);

    }

    /**
     * This function returns if a alien is in a determinated location
     * @param location
     * @return true if there is crew member
     */
    public int verifyalien(String location){
        
        int pos = -1; // se if there is some... if not stays -1
        
        for (int i = 0; i < aliens.size();i++){
            
            if(alienHasTheSameLocation(location, i))
                pos = i;
            
        }  

        return pos;
        
    }
    
    public int KillAllAliensIn(String location){
        
        int pos = -1; // se if there is some... if not stays -1
        
        for (int i = 0; i <= aliens.size();i++){
            
            if(alienHasTheSameLocation(location, i)){
                aliens.remove(i);
                this.addPoints();
                i--;
            }
            
        }  
        return pos;
        
    }
    
    /**
     * This function return the number of cards to add atack dice result
     * @return number of cards add atack dice result
     */
    public int getNumberOfAtackDiceResult(){

        int count = 0;

        for (UpgradeCard card : getUpgradeCards())
            if (card instanceof AddOneAtackDiceResult)
                count++;

        return count;

    }    

    /**
     * This function returns the upgrade cards
     * @return upgrade cards
     */
    public ArrayList<UpgradeCard> getUpgradeCards(){
        
        ArrayList<UpgradeCard> upgradeCards = new ArrayList<>();
        upgradeCards.addAll(this.user.getUpgradeCards());
        return upgradeCards;

    }

    /**
     * This function set the name to the user
     * @param name
     */
    public void setUserName(String name){
        this.user.setName(name);
    }

    /**
     * This function returns user name
     * @return the user name
     */
    public String getUserName(){
        return user.getName();
    }

    /**
     * this function returns the game life
     * 
     * @return game life
     */
    public int getHealthTrackerLife() {
        return getHealthTracker().getHullstate();
    }

    /**
     * This function set a hit to the health tracker
     */
    public void healthTrackerHit() {
        getHealthTracker().Hullhit();
    }

    /**
     * This function returns if the game has some Action Points
     * 
     * @return true if have some action points
     */
    public boolean gotSomeActionPoints() {

        if (getActionPoints() > 0)
            return true;

        return false;

    }

    /**
     * This function creates the user of the game based in the variable user
     * @param user
     */
	public void setUser(User user) {
        this.user = user;        
    }
    
    /**
     * This function sets the Crew member value 
     * @param i
     * @param crewMember
     */
    public void setCrewMember(int i, CrewMember crewMember){
        this.user.setCrewMember(i ,crewMember);
    }

    /**
     * This function returns the hull tracker start health
     * @return the hull tracker start health
     */
    public int getHullStartHealth(){
        return this.hullTracker.getStartHealth();
    }

    /**
     * This function gets hull tracker maximum health
     * @return hull tracker maximum health
     */
    public int getHullMaxHealth(){
        return this.hullTracker.getMaxSize();
    }

    /**
     * This function gets the hull tracker current health
     * @return hull tracker current health
     */
    public int getHullHealth(){
        return this.hullTracker.getHullstate();
    }

    /**
     * This fucntion increment hull tracker health
     */
    public void addHullHealth(){
        this.hullTracker.addHullHealth();
    }

    /**
     * This function decrements the action points
     */
    public void decrementActionPoints(){
        this.actionPoints--;
    }

    /**
     * This function gets health tracker current health
     * @return health tracker current health
     */
    public int getHealthHealthTracker(){
        return this.healthTracker.getHullstate();
    }

    /**
     * This function increments health of health tracker by one
     */
    public void addHealthTrackerHealth(){
        this.healthTracker.setHullstate(getHealthHealthTracker() + 1);
    }

    /**
     * This function returns the boolean value of the game
     * @return if true game ends
     */
    public boolean viewIfGameOver(){
        
        if (this.getHullHealth() <= 0 || this.getHealthHealthTracker() <= 0)
            return true;

        if(this.getCrewMembersForMenu().size() == 1)
            return true;

        return false;

    }

    private boolean correctSpawnPhase(){

        if (getJourneyTurn().equals("R") || getJourneyTurn().equals("S")
            || getJourneyTurn().equals("E"))
            return false;
        return true;

    }

    /**
     * This function gets the name of the current turn
     * @return the current turn
     */
    public String getJourneyTurn(){
        return this.journeyTracker.getJourneyTurn();
    }

    /**
     * This function returns the number of aliens to spawn
     * @return number of aliens to spawn
     */
    private int getNumberOfAliens(){

        if (correctSpawnPhase()) {
        
            String str = new String(getJourneyTurn());
            char aux[] = new char[str.length() - 1];

            str.getChars(0, str.length() - 1, aux, 0);

            String str2 = new String(aux);
            int value = Integer.parseInt(str2);
            
            return value;

        }

        return 0;

    }

    /**
     * This fuction spawns the aliens
     */
    public void spawnAliens(){

        if (correctSpawnPhase())            
            for (int i = 0; i < getNumberOfAliens(); i++)
                this.addRandomAlien();

    }

    /**
     * This function does the Alien Phase
     */
    public void AlienPhase(){

        moveAliens();
        alienAtack();

    }

    private void alienAtack(){

        for (Alien alien : this.aliens) {
            
            if (isAlienAlone(alien))
                alienAtackAlone(alien);
            else
                alienAtackCrewMember(alien);

        }

    }

    /**
     * This function returns the boolean result for alien room empty or not
     * @param alien
     * @return true if no crew member inside alien room
     */
    private boolean isAlienAlone(Alien alien){

        RoomState room = alien.getRoom();

        if (getCrewMember1Room().getName().equals(room.getName()))
            return false;

        if (getCrewMember2Room().getName().equals(room.getName()))
            return false;

        return true;

    }

    public void decrementHullHealth(){
        this.hullTracker.setHullstate(this.hullTracker.getHullstate() - 1);
    }
    


    private void alienAtackAlone(Alien alien){

        this.decrementHullHealth();

    }

    public void decrementHealthTrackerHealth(){
        
        int hullTrackerHealth = this.healthTracker.getHullstate();
        this.healthTracker.setHullstate(hullTrackerHealth - 1);
    
    }
    
    public void HealthTrackertozero(){
        
        this.healthTracker.setHullstate(0);
    
    }
    
    public boolean verifyifcoomofficerspecial(Alien alien){
        
        if ("Comm's Officer".equals(this.getCrewMember(0).getName()) && alien.getRoom().getName().equals(this.getCrewMemberRoomName(0))){
            
            return true;
        }
        if ("Comm's Officer".equals(this.getCrewMember(1).getName()) && alien.getRoom().getName().equals(this.getCrewMemberRoomName(1))){
            return true;
        }
        
        return false;
    }
    

    private void alienAtackCrewMember(Alien alien){

        // comm's officer special
        boolean spec = verifyifcoomofficerspecial(alien);
        
       if (spec){ 

            Dice dice = new Dice();
            int roll = dice.roll(1, 6);

            if(roll <=2) {
                //return;
            } else{

                int roll2 = dice.roll(1, 6);

                if (roll2 >= 5)
                    this.decrementHealthTrackerHealth();

            }
           
       }       
       else{ 
            
            //regular work    
            Dice dice = new Dice();
            int roll = dice.roll(1, 6);

            if (roll >= 5)
                this.decrementHealthTrackerHealth();
        
       }
    }

    /**
     * This function removes the trap and return if has explode or not
     * @param alien
     * @return true if the bomb explode
     */
    private boolean explodeTrapToAlien(Alien alien){

        RoomState room = alien.getRoom();

        for (Trap trap : this.getTraps()) {
            
            String trapRoomName = trap.getRoom().getName();

            if (trapRoomName.equals(room.getName())){
                
                if (trap.getName().equals("OrganicDetonator")) {
                    
                    this.user.removeTrap(trap.getId());
                    return true;

                }

            }

        }

        return false;

    }

    public void decrementInspirationPoints(){
        this.inspirationspoints.setInspstate(this.inspirationspoints.getInspstate() - 1);
    }

    public boolean hasUpgradeSealedToken(){

        for (UpgradeCard var : this.getUpgradeCards())
            if (var instanceof GainOneSealedRoom)
                return true;

        return false;

    }

    public void removeSealedToken(){

        for (UpgradeCard var : this.getUpgradeCards())
            if (var instanceof GainOneSealedRoom){

                this.user.removeUpgradeCard(var);
                return;

            }                

    }

    private ArrayList<Integer> getRoomsParticleDisperser(){

        ArrayList<Integer> rooms = new ArrayList<>();

        for (Trap trap2 : this.getTraps())
            if (trap2.getRoom() != null)
                rooms.add(this.convertRoomToInt(trap2.getRoom()));
        
        return rooms;

    }

    private ArrayList<Integer> getRoomsOrganicDetonator(){

        ArrayList<Integer> rooms = new ArrayList<>();

        rooms.add(this.convertRoomToInt(this.getCrewMember1Room()));
        rooms.add(this.convertRoomToInt(this.getCrewMember2Room()));

        return rooms;

    }

    public ArrayList<Integer> getAvailableTrapRooms(int trap) {

        ArrayList<Integer> rooms = new ArrayList<>();
        Trap trap2 = this.getTrapById(trap);
        String trapName = trap2.getName();

        if(trapName.equals("ParticleDisperser"))
            getRoomsParticleDisperser();
        else
            getRoomsOrganicDetonator();

        rooms.add(0);

        return rooms;

    }

    public String toStringAvailableTraps() {
    
        StringBuilder returnStringBuffer = new StringBuilder("@Destination Earth\n\n"
        + "Selecione a trap: \n");
        ArrayList<Integer> traps = getAvailableTraps();

        for (int trapIndex : traps) {
            
            if (trapIndex != 0) {
                
                Trap trap = this.getTrapById(trapIndex);
                returnStringBuffer.append(trapIndex).append(" - ").append(trap.getName()).append("\n");

            }
            
        }

        returnStringBuffer.append("0 - Voltar\n");

        return returnStringBuffer.toString();
    
    }

    public String toStringAvailableTrapRooms(int trap) {

        StringBuilder returnStringBuffer = new StringBuilder("@Destination Earth\n\n"
        + "Selecione a room: \n");
        ArrayList<Integer> rooms = getAvailableTrapRooms(trap);

        for (int roomIndex : rooms) {
            
            if (roomIndex != 0) {
                
                RoomState room = new General().convertIntToRoom(roomIndex);
                returnStringBuffer.append(roomIndex).append(" - ").append(room.getName()).append("\n");

            }
            
        }

        returnStringBuffer.append("0 - Voltar\n");

        return returnStringBuffer.toString();

    }
    
    public ArrayList<Integer> getAvailableTraps() {
        
        ArrayList<Integer> availableTraps = new ArrayList<>();
        
        for (Trap trap : this.getTraps())
            if (trap.getRoom() == null)
                availableTraps.add(trap.getId());

        availableTraps.add(0);

        return availableTraps;

    }

    public String toStringRooms() {
        
        StringBuilder returnString = new StringBuilder("@Destination Earth\n\n"
            + "Selecione a room: \n");

        for (int i = 1; i <= 12; i++) {
            
            RoomState room = new General().convertIntToRoom(i);
            returnString.append(i + " - " + room.getName() + "\n");

        }

        returnString.append("0 - Voltar\n");

        return returnString.toString();

    }  

    /**
     * This function returns crew member 1 room name
     * 
     * @return the crew member 1 room name
     */
    public String getCrewMember1RoomName() {
        return getCrewMember1Room().getName();
    }

    /**
     * This function returns crew member 2 room name
     * 
     * @return the crew member 2 room name
     */
    public String getCrewMember2RoomName() {
        return getCrewMember2Room().getName();
    }

    public String getCrewMember1Name() {
        return getCrewMemberName(0);
    }

    public String getCrewMember2Name() {
        return getCrewMemberName(1);
    }

    public boolean isAnTransporterChief(int crew){
        
        if(crew == 0)
            if(this.getCrewMember1Name().equals("Transporter Chief"))
                return true;
        if(crew == 1)
            if(this.getCrewMember2Name().equals("Transporter Chief"))
                return true;
        return false;
    }

    /**
     * This function returns trap room name
     * 
     * @param trapIndex
     * @return trap room name
     */
    public String getTrapRoomName(int trapIndex) {
        return this.getUser().getTraps().get(trapIndex).getRoom().getName();
    } 

    /**
     * This function returns trap name
     * 
     * @param trapIndex
     * @return Trap Name
     */
    public String getTrapName(int trapIndex) {
        return this.getUser().getTraps().get(trapIndex).getName();
    }   

    public ArrayList<Trap> getParticleDisperserTraps() {
    
        ArrayList<Trap> organicDetonaters = new ArrayList<>();

        for (Trap trap : this.getTraps()) {
            
            if(trap.getName().equals("ParticleDisperser"))
                organicDetonaters.add(trap);

        }

        return organicDetonaters;
    
    }

    public String getRoomsNear(int crewMemberIndex) {
        
        StringBuilder returnString = new StringBuilder("@Destination Earth\n\n"
            + "Selecione o crew Member: \n");
        ArrayList<Integer> roomsArray = getNearAvailableRooms(crewMemberIndex);

        for (int roomIndex : roomsArray) {
            
            if (roomIndex != 0) {
                
                RoomState room = new General().convertIntToRoom(roomIndex);
                returnString.append(roomIndex + " - " + room.getName() + "\n");    
            
            }            

        }

        returnString.append("0 - Voltar\n");

        return returnString.toString();

    }

    public String returnplayerslocation(int i){
        return this.getCrewMembers()[i].getCrewMemberRoom().getName();
    } 

    public ArrayList<Integer> getNearAvailableRooms(int crewMemberIndex) {

        ArrayList<Integer> returnArray = new ArrayList<>();

        returnArray.addAll(this.getCrewMember(crewMemberIndex).getCrewMemberRoom().Return_avaible_rooms());
        returnArray.add(0);

        return returnArray;
    
    }

    /**
     * This function returns if the life is equal to 0
     * 
     * @return true if life equals to 0
     */
    public boolean isDead() {

        if (getHealthTrackerLife() == 0)
            return true;
        return false;

    }

    public boolean seelockedroom(int i){
        // return true if its locked
        if (new Room1().getsealledstatus() == true && i == 1 )
            return true;
        if (new Room2().getsealledstatus() == true && i == 2 )
            return true;
        if (new Room3().getsealledstatus() == true && i == 3 )
            return true;
        if (new Room4().getsealledstatus() == true && i == 4 )
            return true;
        if (new Room5().getsealledstatus() == true && i == 5 )
            return true;
        if (new Room6().getsealledstatus() == true && i == 6 )
            return true;
        if (new Room7().getsealledstatus() == true && i == 7 )
            return true;
        if (new Room8().getsealledstatus() == true && i == 8 )
            return true;
        if (new Room9().getsealledstatus() == true && i == 9 )
            return true;
        if (new Room10().getsealledstatus() == true && i == 10 )
            return true;
        if (new Room11().getsealledstatus() == true && i == 11 )
            return true;
        if (new Room12().getsealledstatus() == true && i == 12 )
            return true;
        
        return false;
    }

	public ArrayList<Integer> atack(int i) {
            
            boolean wascrew = false;
            if(this.stateOfTheGame instanceof AwaitCrewPhaseActions)
                wascrew = true;

            this.stateOfTheGame = this.stateOfTheGame.atack(i);

            ArrayList<Integer> returns = new ArrayList<Integer>();
            if(wascrew == false && this.stateOfTheGame instanceof AwaitDiceRolling){  
                returns = this.stateOfTheGame.getgeninfo();
                this.stateOfTheGame = this.stateOfTheGame.nextTurn();

            }

            return returns;
	}

	public void goBack() {
        this.stateOfTheGame = this.stateOfTheGame.goBack();
	}

	public void setTrap(int roomIndex, int trapIndex) {
        this.stateOfTheGame = this.stateOfTheGame.setTrap(roomIndex, trapIndex);
	}

	public void sealRoom(int option) {
        this.stateOfTheGame = this.stateOfTheGame.sealRoom(option);
	}

	public void selectrandomCrewMembers() {
        this.stateOfTheGame = this.stateOfTheGame.selectRandomCrewMembers();
    }

	public void selectCrewMembers(String crewMember1, String crewMember2) {
        this.stateOfTheGame = this.stateOfTheGame.selectCrewMembers(crewMember1, crewMember2);
	}

	public void Inputbegining() {
        this.stateOfTheGame = this.stateOfTheGame.InputBeginning(this);
	}

	public void placeFirstCrewMember(int room) {
        this.stateOfTheGame = this.stateOfTheGame.placeFirstCrewMember(room);
	}

	public void placeSecondCrewMember(int room) {
        this.stateOfTheGame = this.stateOfTheGame.placeSecondCrewMember(room);
	}

	public void detonateParticleDisperser(String option) {
        this.stateOfTheGame = this.stateOfTheGame.detonateParticleDisperser(option);
	}

	public void move(int i, int option) {
        this.stateOfTheGame = this.stateOfTheGame.move(i, option);
	}

	public void nextTurn() {
        this.stateOfTheGame = this.stateOfTheGame.nextTurn();
	}

	public boolean heal() {
        return this.stateOfTheGame.heal();
	}

	public boolean fixOneHull() {
        return this.stateOfTheGame.fixOneHull();
    }

	public void saveGame() {
        this.stateOfTheGame = this.stateOfTheGame.saveGame();
    }
        
    public boolean addonehealth(){
        return this.stateOfTheGame.addonehealth();
            
    }
     
    public boolean repairhull(){
        return this.stateOfTheGame.repairhull();
    }
    
    public boolean buildorganicdetonator(){
        return this.stateOfTheGame.buildorganicdetonator();
    }
    
    public boolean addonemovement(int crewmemeber){
        
        return this.stateOfTheGame.addonemovement(crewmemeber);
    }
        
    public boolean builoneparticleddispenser(){
        
        return this.stateOfTheGame.builoneparticleddispenser();
    }    
        
    public boolean gainonesealedromtoken(){
        return this.stateOfTheGame.gainonesealedromtoken();
    }    
     
    public boolean gainoneattack(int crewmember){
        return this.stateOfTheGame.gainoneattack(crewmember);
    }
    
    public boolean addonethedice(){
        return this.stateOfTheGame.addonethedice();
    }
        

    public void removeAlien(Alien alien){
        this.getAliens().remove(alien);
    }

    public void renewCrewMembersMove(){

        this.getCrewMember(0).setMovementToDo(this.getCrewMember(0).getMovement());
        this.getCrewMember(1).setMovementToDo(this.getCrewMember(1).getMovement());

    }

    public boolean verifyAlienJourney(String Option){

        int count = 0;

        for (int i = 0; i < Option.length(); i++) {
            
            if (i == Option.length() - 1){
                if (Option.charAt(i) == 'A' && count > 0)
                    return true;
            }
            else{

                if(Character.isDigit(Option.charAt(i)))
                    count++;                                   

            }
            
        }

        return false;

    }

    public boolean validateJourneyTrackerOption(String option){

        switch (option.toUpperCase()) {
            case "R":
                return false;
        
            default:
                
                if (verifyAlienJourney(option.toUpperCase()))
                    return false;

        }

        return true;
    
    }

    public String toStringRoomsToSeal(){

        StringBuilder returnString = new StringBuilder("@Destination Earth\n\n"
            + "Selecione a room: \n");

        for (int i : roomsToSeal()) {

            if (i != 0) {

                RoomState room = new General().convertIntToRoom(i);
                returnString.append(i + " - " + room.getName() + "\n");
        
            }
            
        }

        returnString.append("0 - Voltar\n");

        return returnString.toString();

    }

    public boolean canSealRoom(int roomNumber){

        RoomState room = new General().convertIntToRoom(roomNumber);

        for (Alien alien : this.getAliens()) {
            
            if (alien.getRoom().getName().equals(room.getName())) {
                return false;
            }

        }

        for (int i = 0; i < 2; i++) {
            
            if (this.getCrewMember(i).getCrewMemberRoom().getName().equals(room.getName())) {
                return false;
            }

        }

        if (room.getsealledstatus())
            return false;

        return true;

    }

    public ArrayList<Integer> roomsToSeal(){

        ArrayList<Integer> roomsSeal = new ArrayList<>();

        if (canSealRoom(3)) {
            roomsSeal.add(3);
        }
        if (canSealRoom(9)) {
            roomsSeal.add(9);
        }
        if (canSealRoom(4)) {
            roomsSeal.add(4);
        }
        if (canSealRoom(12)) {
            roomsSeal.add(12);
        }
        if (canSealRoom(11)) {
            roomsSeal.add(11);
        }
        if (canSealRoom(7)) {
            roomsSeal.add(7);
        }
        roomsSeal.add(0);

        return roomsSeal;

    }

    public void removeTrap(int id){
        this.user.removeTrap(id);
    }

    public int findParticleDisperserByRoom(String trapRoom){

        for (Trap var : this.getTraps()) {
            
            if (var.getName().equals("ParticleDisperser")) {
                
                if (var.getRoom().getName().equals(trapRoom)) {
                    
                    return var.getId();

                }

            }

        }

        return 0;
    
    }

    public void addPoints(){
        this.user.setPoints(this.user.getPoints() + 1);
    }
    
    
    public HashMap<String,Integer> scoreopener(){
        
        HashMap<String, Integer> scoreloader = new HashMap<String, Integer>();
        
        try
        {
          BufferedReader reader = new BufferedReader(new FileReader("pontuacao.txt"));
          String line;
          while ((line = reader.readLine()) != null)
          {
            String line2[] = line.split("-");
            scoreloader.put(line2[0], Integer.valueOf(line2[1])); // convert string to int
          }
          reader.close();
        }
        catch (Exception e)
        {
          System.err.format("Exception occurred trying to read '%s'.", "pontuacao.txt");
          e.printStackTrace();
          return null;
        }

        return scoreloader;
    }
    
    
    public void savetoscorefile(){
        
        HashMap<String, Integer> scoreloader = new HashMap<String, Integer>();
        
        try
        {
            
            // reads file
            BufferedReader reader = new BufferedReader(new FileReader("pontuacao.txt"));
            String line;
            while ((line = reader.readLine()) != null)
            {
              String line2[] = line.split("-");
              scoreloader.put(line2[0], Integer.valueOf(line2[1])); // convert string to int
            }
            reader.close();
          
            // se if you can enter in scoreboard
            int counter=0,pos = 0;
            for (String i : scoreloader.keySet()) {          
                if(scoreloader.get(i) > getUser().getPoints())
                    pos = counter; // need to add 1 to get real location
                counter++;
            }
            
            //update hash map
            if(pos < 9 || counter < 10){
                pos = pos +1; // updated to real position 
                counter= 0;
                
                HashMap<String, Integer> scoreloaderclone = new HashMap<String, Integer>(scoreloader);// clone hashmap
                //scoreloaderclone = scoreloader;
                
                scoreloader.clear();
                
                for (String i : scoreloaderclone.keySet()) {
                    
                    if(counter < 10){
                        if(counter == pos){

                            scoreloader.put(getUserName(), getUser().getPoints());
                            pos = 9999;
                            //scoreloaderclone.keySet();

                        }

                            scoreloader.put(i, scoreloaderclone.get(i));
                    }  
                    counter++;
                    
                }
                
                if(pos != 9999 && scoreloaderclone.size() < 10){
                    scoreloader.put(getUserName(), getUser().getPoints());
                }
                
                
            }
            
            
                
            

            //writes in file   
            BufferedWriter writer = new BufferedWriter(new FileWriter("pontuacao.txt"));
            StringBuilder writetofile = new StringBuilder();
            for (String i : scoreloader.keySet()) {
                
                writetofile.append(i + "-" + scoreloader.get(i));
                writer.write(writetofile.toString() + "\n");
                writetofile.setLength(0); // clear StringBuilder
            }
            writer.close();
          
          
          
        }
        catch (Exception e)
        {
          System.err.format("Exception occurred trying to read '%s'.", "pontuacao.txt");
          e.printStackTrace();
        }
        
    }

    public int redShirtSpecial(){

        int count = 0;

        if (this.getCrewMember1Name().equals("Red Shirt") && !this.getCrewMember1RoomName().equals("SecretRoom")) {
            
            this.getCrewMember(0).Special();
            count++;

        }
        else
            if(this.getCrewMember2Name().equals("Red Shirt") && !this.getCrewMember2RoomName().equals("SecretRoom")){

                this.getCrewMember(1).Special();
                count++;

            }

        if (count == 2) {
            this.stateOfTheGame = new GameOver(this);
            return 2; // end game
        }
        if (count == 0) {
            return 1; // no red shirts
        }
        
        return 0; // sucess
    }    

    public ArrayList<Integer> getCrewMembersForMenu(){

        ArrayList<Integer> lista = new ArrayList<>();

        if (this.getCrewMember1Name().equals("Red Shirt") && !this.getCrewMember1RoomName().equals("SecretRoom")) {
            
            lista.add(1);

        }

        if(this.getCrewMember2Name().equals("Red Shirt")  && !this.getCrewMember2RoomName().equals("SecretRoom")){

            lista.add(2);

        }

        if (!this.getCrewMember1Name().equals("Red Shirt")) {
            lista.add(1);
        }
        if (!this.getCrewMember2Name().equals("Red Shirt")) {
            lista.add(2);
        }

        lista.add(0);

        return lista;
        
    }

    public String toStringAvailableCrewMembers(ArrayList<Integer>crewMembers){

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < crewMembers.size(); i++) {
            
            if(crewMembers.get(i) != 0){

                sb.append(crewMembers.get(i) + " - " + this.getCrewMember(crewMembers.get(i) - 1).getName() + "\n");

            }

        }
        
        sb.append("0 - Voltar");

        return sb.toString();

    }

    public Alien getAlienByPosition(int position){

        return this.getAliens().get(position);

    }

    public boolean isTheSameCrewMembersName(String crewMemberName){
        return (this.getCrewMember2Name().equals(crewMemberName) || this.getCrewMember1Name().equals(crewMemberName));
    }

    public boolean notMaxHealthHealthTracker(){
        return (this.getHealthHealthTracker() < this.getHullMaxHealth());
    }

    public int getCrewMemberAtack(int crewMemberIndex){
        return this.getCrewMember(crewMemberIndex).getAttack();
    }

    public boolean isTheSameCrewMember1Name(String crewMemberName){
        return (this.getCrewMember1Name().equals(crewMemberName));
    }

    public boolean isTheSameCrewMember2Name(String crewMemberName){
        return (this.getCrewMember2Name().equals(crewMemberName));
    }

    public void chooseCrewMemberRoom(int room, int crewMember){
        this.getUser().getCrewMembers()[crewMember].chooseRoom(room);
    }

    public int getSizeOfAliens(){
        return (this.getAliens().size());
    }

    public boolean isCrewMember1SameRoomName(String RoomName){
        return (getCrewMember1Room().getName().equals(RoomName));
    }

    public boolean isCrewMember2SameRoomName(String RoomName){
        return (getCrewMember2Room().getName().equals(RoomName));
    }

    public String getAlienRoomNameByPosition(int i){
        return getAlienByPosition(i).getRoom().getName();
    }

    public void addOneInspPoint(){
        this.getInspirationPoints().addoneInspPoint();
    }

    public void removeAlienByPosition(int i){
        this.getAliens().remove(i);
    }

    public boolean isTheSameCrewMemberRoomNameByIndex(int i, String RoomName){
        return getCrewMembers()[i].getName().equals(RoomName);
    }

}
