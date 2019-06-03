package projpa.GameLogic;

import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projpa.GameLogic.General.General;
import projpa.GameLogic.MapRooms.Room1;
import projpa.GameLogic.MapRooms.RoomState;
import projpa.GameLogic.StateMachine.*;
import projpa.GameLogic.Traps.Trap;
import projpa.GameLogic.User.User;


public class GameLogic extends PropertyChangeSupport implements Serializable{

    private GameData game;
    private IStates stateOfTheGame;
    private boolean wascrew;
    
    public GameLogic(IStates stateOfTheGame) {
        super(stateOfTheGame);
        this.game = new GameData();
        this.stateOfTheGame = stateOfTheGame;
    }

    public boolean LoadGame(){

        try {

            FileInputStream fi = new FileInputStream(new File("savegame.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            GameData pr1 = (GameData) oi.readObject();

            oi.close();

            this.game = new GameData(pr1);
            this.stateOfTheGame = new AwaitCrewPhaseActions(this.game);
            firePropertyChange(null, false, true); // inform interface what state it is

        } catch (IOException e) {

            return false;

        } catch (ClassNotFoundException ex) {

            //Logger.getLogger(TextInterface.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }

        return true;

    }
    
    public boolean LoadGame(String direct){

        try {

            FileInputStream fi = new FileInputStream(new File(direct));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            GameData pr1 = (GameData) oi.readObject();

            oi.close();

            this.game = new GameData(pr1);
            this.stateOfTheGame = new AwaitCrewPhaseActions(this.game);
            firePropertyChange(null, false, true); // inform interface what state it is

        } catch (IOException e) {

            return false;

        } catch (ClassNotFoundException ex) {

            //Logger.getLogger(TextInterface.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }

        return true;

    }

    public boolean inGameOverState(){
        return stateOfTheGame instanceof GameOver;
    }

    public boolean inAwaitThirdTokenSecondCrewMember(){
        return stateOfTheGame instanceof AwaitThirdTokenSecondCrewMember;
    }

    public boolean inAwaitDiceRolling(){
        return stateOfTheGame instanceof AwaitDiceRolling;
    }

    public boolean inAwaitSaveGame(){
        return stateOfTheGame instanceof AwaitSaveGame;
    }

    public boolean inAwaitPeekRoom(){
        return stateOfTheGame instanceof AwaitPeekRoom;
    }

    public boolean inAwaitRestPhaseActions(){
        return stateOfTheGame instanceof AwaitRestPhaseActions;
    }

    public boolean inAwaitCrewPhaseActions(){
        return stateOfTheGame instanceof AwaitCrewPhaseActions;
    }

    public boolean inAwaitChooseParticleDisperser(){
        return stateOfTheGame instanceof AwaitChooseParticleDisperser;
    }

    public boolean validateJourneyTrackerOption(String info){
        return this.game.validateJourneyTrackerOption(info);
    }

    public boolean changeJourney(int pos, String info){
        return this.game.getJourneyTracker().changeJourney(pos, info.toUpperCase());
    }

    public int getJourneyState(){
        return this.game.getJourneyTracker().getJourneyState();
    }

    public String getJourneyValue(int index){
        return this.game.getJourneyTracker().getJourneyTrackers()[index];
    }

    public String showStatus(){

        StringBuilder sb = new StringBuilder();

        sb.append("-----Status-----");
        if (this.game.returnplayerslocation(0)!= "SecretRoom"){
            sb.append("\nCrew Member 1 - Name: "
                    + this.game.getCrewMember1Name()
                    + " - Location: "
                    + this.game.returnplayerslocation(0)
            );}
        else
            sb.append("\nCrew Member 1 - Name: " + this.game.getCrewMember1Name() + " is dead");

        if (this.game.returnplayerslocation(1)!= "SecretRoom"){
            sb.append("\nCrew Member 2 - Name: "
                    + this.game.getCrewMember2Name()
                    + " - Location: "
                    + this.game.returnplayerslocation(1)
            );}
        else
            sb.append("\nCrew Member 2 - Name: " + this.game.getCrewMember2Name() + " is dead");


        sb.append("\n-----Aliens-----");

        for (int i = 0; i < game.getAliens().size();i++){

            sb.append("\nAlien number:" + (i + 1)
                    + " is in: " + game.getAliens().get(i).getRoom().getName());

        }

        sb.append("\nHealth: " + this.game.getHealthHealthTracker());
        sb.append("\nHull: " + this.game.getHullHealth());
        sb.append("\nAction Points: " + this.game.getActionPoints());

        sb.append("\n-----Journey Location-----\n");

        for (int i = 0; i < 15;i++){

            sb.append(this.getJourneyValue(i));

            if (this.getJourneyState() == i)
                sb.append("  <- you are here");

            sb.append("\n");
        }
        sb.append("\n--------------------\n");

        return sb.toString();

    }
    
    public List<Alien> getalienarraycopy (){
        
        List<Alien> copy = new ArrayList<Alien>(game.getAliens());
        return copy;
    }
    

    public boolean isAnTransporterChief(int crewMemberIndex){

        if (crewMemberIndex <= 0 || crewMemberIndex >=1)
            return false;

        return this.game.isAnTransporterChief(crewMemberIndex);

    }

    public String toStringRoomsToSeal(){

        return this.game.toStringRoomsToSeal();

    }

    public ArrayList<Integer> roomsToSeal(){

        ArrayList<Integer> toSealRooms = new ArrayList<>();
        toSealRooms.addAll(this.game.roomsToSeal());
        return toSealRooms;

    }

    public ArrayList<Integer> getAvailableTraps(){

        ArrayList<Integer> availableTraps = new ArrayList<>();
        availableTraps.addAll(this.game.getAvailableTraps());
        return availableTraps;

    }

    public String toStringAvailableTraps(){
        return this.game.toStringAvailableTraps();
    }

    public ArrayList<Integer> getAvailableTrapRooms(int trapIndex){

        ArrayList<Integer> availableTrapRooms = new ArrayList<>();
        availableTrapRooms.addAll(this.game.getAvailableTrapRooms(trapIndex));
        return availableTrapRooms;

    }

    public String toStringAvailableTrapRooms(int trapIndex){
        return this.game.toStringAvailableTrapRooms(trapIndex);
    }

    public void setHealthTrackerValue(int value){
            this.game.setHealthTrackervalue(value);
    }

    public void setHullTrackerValue(int value){
            this.game.setHullTrackervalue(value);
    }

    public boolean inAwaitBeginning(){
        return stateOfTheGame instanceof AwaitBeginning;
    }

    public boolean inAwaitCrewMembersSelection(){
        return stateOfTheGame instanceof AwaitCrewMembersSelection;
    }

    public boolean inAwaitThirdTokenFirstCrewMember(){
        return stateOfTheGame instanceof AwaitThirdTokenFirstCrewMember;
    }

    public String getUserPontuation(){

        StringBuilder sb = new StringBuilder();

        HashMap<String, Integer> scoreloader = new HashMap<String, Integer>();
        scoreloader = this.game.scoreopener();

        int j = 0;

        for (String i : scoreloader.keySet()) {
            sb.append((j+1) +" - " + i + " - " + scoreloader.get(i));
            j++;
            sb.append("\n");
        }

        return sb.toString();

    }

    public boolean SaveScore(){
        return this.game.savetoscorefile();
    }

    public int getUserPoints(){
        if (this.game.getUser() != null)
            return this.game.getUser().getPoints();
        else
            return 0;
    }

    public boolean wasSaved(){
        return this.stateOfTheGame.wasSaved();
    }

    public void endGame(){
        this.stateOfTheGame = new GameOver(this.game);
        firePropertyChange(null, false, true);
    }

    public void endGame2(){
        this.game = new GameData();
        this.stateOfTheGame = new GameOver(this.game);
        firePropertyChange(null, false, true);
    }

    public ArrayList<Integer> atack(int i) {


        if(inAwaitCrewPhaseActions()){
            wascrew = true;
        }
        else{
            wascrew = false;
        }

        this.stateOfTheGame = this.stateOfTheGame.atack(i);

        ArrayList<Integer> returns = new ArrayList<Integer>();

        if(!wascrew && inAwaitDiceRolling()) {

            returns = this.stateOfTheGame.getgeninfo();
            this.stateOfTheGame = this.stateOfTheGame.nextTurn();

        }

        return returns;

	}

	public ArrayList<Integer> getNearAvailableRooms(int index){

        if (index < 0 || index > 1)
            return null;

        return this.game.getNearAvailableRooms(index);

    }

    public String getRoomsNear(int index){

        if (index < 0 || index > 1)
            return null;

        return this.game.getRoomsNear(index);

    }

    public String showAllRooms(){

        StringBuilder sb = new StringBuilder();

        sb.append(new Room1().getAllClassesNumberAndName());

        return sb.toString();

    }

    public boolean seelockedroom(int option){
        return this.game.seelockedroom(option);
    }

	public void goBack() {
        this.stateOfTheGame = this.stateOfTheGame.goBack();
        firePropertyChange(null, false, true);
	}

	public void setTrap(int roomIndex, int trapIndex) {
        this.stateOfTheGame = this.stateOfTheGame.setTrap(roomIndex, trapIndex);
        firePropertyChange(null, false, true);
	}

	public void sealRoom(int option) {
        this.stateOfTheGame = this.stateOfTheGame.sealRoom(option);
        firePropertyChange(null, false, true);
	}

	public boolean canSealRoom(){
        return this.game.getActionPoints() > 4 && this.game.hasUpgradeSealedToken();
    }

	public void selectrandomCrewMembers() {
        this.stateOfTheGame = this.stateOfTheGame.selectRandomCrewMembers();
        firePropertyChange(null, false, true);
    }

	public void selectCrewMembers(String crewMember1, String crewMember2) {
        if (!crewMember1.equals(crewMember2))
            this.stateOfTheGame = this.stateOfTheGame.selectCrewMembers(crewMember1, crewMember2);

        firePropertyChange(null, false, true);

    }

	public String toStringParticleDisperser(){

        StringBuilder sb = new StringBuilder();
        ArrayList<Trap> particleDisperser = this.game.getParticleDisperserTraps();
        int i = 0;

        for (Trap trap : particleDisperser) {

            i++;
            sb.append(i + " - Particle Disperser na room " + trap.getRoom().getName());

        }

        return sb.toString();

    }

    public int getHullState(){
        return this.game.getHullHealth();
    }

    public int getHealthTrackerHealth(){
        return this.game.getHealthHealthTracker();
    }

    public int getNumberOfParticleDisperser(){

        ArrayList<Trap> particleDisperser = this.game.getParticleDisperserTraps();
        return particleDisperser.size();

    }

    public String getRoomNameOfParticleDisperser(int index){
        ArrayList<Trap> particleDisperser = this.game.getParticleDisperserTraps();
        return particleDisperser.get(index).getRoomName();
    }

    public int getInspirationPoints(){
        return this.game.getInspirationPoints().getInspstate();
    }

    public ArrayList<Integer> getCrewMembersForMenu(){

        ArrayList<Integer> CrewMembersForMenu = new ArrayList<>();
        CrewMembersForMenu.addAll(this.game.getCrewMembersForMenu());
        return CrewMembersForMenu;

    }

    public String toStringAvailableCrewMembers(ArrayList<Integer> options){
        return this.game.toStringAvailableCrewMembers(options);
    }

	public void Inputbegining() {
        this.stateOfTheGame = this.stateOfTheGame.InputBeginning(this.game);
        firePropertyChange(null, false, true);
	}

	public void placeFirstCrewMember(int room) {
        this.stateOfTheGame = this.stateOfTheGame.placeFirstCrewMember(room);
        firePropertyChange(null, false, true);
	}

	public void placeSecondCrewMember(int room) {
        this.stateOfTheGame = this.stateOfTheGame.placeSecondCrewMember(room);
        firePropertyChange(null, false, true);
	}

	public String getCrewmember1Name(){
        String name = this.game.getCrewMember1Name();
        return name;
    }

    public String getCrewmember2Name(){
        String name = this.game.getCrewMember2Name();
        return name;
    }

    public String getCrewmember1RoomName(){
        
        //if(this.game.getCrewMember1RoomName() != null){
        String name = this.game.getCrewMember1RoomName();
        return name;
        //}
        //else
           // return String.valueOf(-1);
    }

    public String getCrewMember1Atack(){
        switch (this.game.getCrewMembers()[0].getAttack()){
            case 106:
                return "1D6";
            case 206:
                return "2D6";
            case 306:
                return "3D6";
            default:
                return "0D6";
        }
    }

    public String getCrewMember2Atack(){
        switch (this.game.getCrewMembers()[1].getAttack()){
            case 106:
                return "1D6";
            case 206:
                return "2D6";
            case 306:
                return "3D6";
            default:
                return "0D6";
        }
    }

    public int getCrewMember1Move(){
        return this.game.getCrewMember(0).getMovement();
    }

    public int getCrewMember2Move(){
        return this.game.getCrewMember(1).getMovement();
    }

    public String getCrewmember2RoomName(){
        String name = this.game.getCrewMember2RoomName();
        return name;
    }

	public void detonateParticleDisperser(String option) {
        this.stateOfTheGame = this.stateOfTheGame.detonateParticleDisperser(option);
        firePropertyChange(null, false, true);
    }

    public void detonateParticleDisperser(int option) {
        String str = new General().convertIntToRoom(option).getName();
        this.detonateParticleDisperser(str);
        this.detonateParticleDisperser(str);
    }

	public void move(int i, int option) {
        this.stateOfTheGame = this.stateOfTheGame.move(i, option);
        firePropertyChange(null, false, true);
	}

	public boolean hasParticleDispersersToPlace(){
        return this.getUnplacedParticleDisperser().size() > 0;
    }

    public boolean hasOrganicDetonatorToPlace(){
        return this.getUnplacedOrganicDetonaters().size() > 0;
    }

    public boolean hasOrganicDetonatorToExplode(){
        return this.getPlacedorganicdetonator().size() > 0;
    }

	public void nextTurn() {
        this.stateOfTheGame = this.stateOfTheGame.nextTurn();
        firePropertyChange(null, false, true);
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

    public ArrayList<Integer> getUnplacedOrganicDetonaters(){

        ArrayList<Integer> unplacedOrganicDetonaters = new ArrayList<>();

        for (int i = 0; i < this.game.getTraps().size(); i++) {

            if (this.game.getTraps().get(i).getName().equals("OrganicDetonator") && this.game.getTraps().get(i).getRoom() == null)
                unplacedOrganicDetonaters.add(this.game.getTraps().get(i).getId());

        }

        return unplacedOrganicDetonaters;

    }

    public ArrayList<Integer> getUnplacedParticleDisperser(){

        ArrayList<Integer> unplacedOrganicDetonaters = new ArrayList<>();

        for (int i = 0; i < this.game.getTraps().size(); i++) {

            if (this.game.getTraps().get(i).getName().equals("ParticleDisperser") && this.game.getTraps().get(i).getRoom() == null)
                unplacedOrganicDetonaters.add(this.game.getTraps().get(i).getId());

        }

        return unplacedOrganicDetonaters;

    }

    public ArrayList<Integer> getPlacedParticleDisperser(){

        ArrayList<Integer> placedOrganicDetonaters = new ArrayList<>();

        for (int i = 0; i < this.game.getTraps().size(); i++) {

            if (this.game.getTraps().get(i).getName().equals("ParticleDisperser") && this.game.getTraps().get(i).getRoom() != null)
                placedOrganicDetonaters.add(i);

        }

        return placedOrganicDetonaters;

    }
    
    public ArrayList<Integer> getPlacedorganicdetonator(){

        ArrayList<Integer> placedOrganicDetonaters = new ArrayList<>();

        for (int i = 0; i < this.game.getTraps().size(); i++) {

            if (this.game.getTraps().get(i).getName().equals("OrganicDetonator") && this.game.getTraps().get(i).getRoom() != null)
                placedOrganicDetonaters.add(i);

        }

        return placedOrganicDetonaters;

    }

    public ArrayList<Integer> getPlacedParticleDisperser2(){

        ArrayList<Integer> placedOrganicDetonaters = new ArrayList<>();

        for (int i = 0; i < this.game.getTraps().size(); i++) {

            if (this.game.getTraps().get(i).getName().equals("ParticleDisperser") && this.game.getTraps().get(i).getRoom() != null)
                placedOrganicDetonaters.add(new General().convertRoomStrinToInt(this.game.getTraps().get(i).getRoomName()));

        }

        return placedOrganicDetonaters;

    }

    public ArrayList<Integer> getAvailableTrapRooms(){

        ArrayList<Integer> availableTrapRooms = new ArrayList<>();

        for (int i = 1; i <= new Room1().getNumberOfRooms(); i++) {

            boolean canPlace = true;
            RoomState room2 = new General().convertIntToRoom(i);

            if (room2.getsealledstatus()){
                canPlace = false;
            }
            else
                if (this.game.isCrewMember1SameRoomName(room2.getName()) || this.game.isCrewMember2SameRoomName(room2.getName())){
                    for (int j = 0; j < this.game.getTraps().size(); j++) {
                        RoomState room = this.game.getTraps().get(j).getTrapRoom();
                        if (room != null)
                            if (room.getName().equals(room2.getName()))
                                canPlace = false;
                    }
                }
                else{
                    canPlace = false;
                }

            if (canPlace)
                availableTrapRooms.add(i);

        }

        return availableTrapRooms;

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

    public int redShirtSpecial(){

        int count = 0;

        if (this.game.getCrewMember1Name().equals("Red Shirt") && !this.game.getCrewMember1RoomName().equals("SecretRoom")) {
            
            this.game.getCrewMember(0).Special();
            count++;

        }
        else
            if(this.game.getCrewMember2Name().equals("Red Shirt") && !this.game.getCrewMember2RoomName().equals("SecretRoom")){

                this.game.getCrewMember(1).Special();
                count++;

            }

        if (count == 2) {
            this.stateOfTheGame = new GameOver(this.game);
            return 2; // end game
        }
        if (count == 0) {
            return 1; // no red shirts
        }
        
        return 0; // sucess

    }

    public void newGame() {
        this.game = new GameData();
        this.stateOfTheGame = new AwaitBeginning();
        firePropertyChange(null, false, true);
    }

    public void startGame(){
        //if (inAwaitPeekRoom()){
            this.stateOfTheGame = new AwaitBeginning();
            firePropertyChange(null, false, true);
        //}
    }

    public void startGame2(){
        //if (inAwaitPeekRoom()){
        this.game = new GameData();
        //}
    }

    public void newUser(String name) {

        User user = new User(name);
        this.game.setUser(user);

    }

    public void SaveGame() {
        if (inAwaitSaveGame())
            this.stateOfTheGame = this.stateOfTheGame.saveGame();
    }
    
    public void SaveGame(String input) {
        if (inAwaitSaveGame())
            this.stateOfTheGame = this.stateOfTheGame.saveGame(input);
    }


    public ArrayList<String> getJourneyTracker(){

        ArrayList<String> journeyCopy = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            journeyCopy.add(this.game.getJourneyTurn(i));
        }

        return journeyCopy;

    }
    
    public int getActionPoints() {
        return this.game.getActionPoints();
    }
    
    public int getPoints(){
           
            if (this.game.wasusercreated() == false)
                return 0;
            else
                return this.game.getPoints();
    }
    
    
    public List<Trap> gettrapsclone (){
        
        List<Trap> clone = new ArrayList<Trap>(this.game.getTraps());
        return clone;
        
    }

}
