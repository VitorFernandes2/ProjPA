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


public class GameLogic {

    private GameData game;
    private IStates stateOfTheGame;
    
    
    public GameLogic() {
        this.game = new GameData();
        this.stateOfTheGame = new AwaitBeginning();
    }
    
    public GameLogic(GameData game) {
        this.game = new GameData(game);
        //this.stateOfTheGame = game.getStateOfTheGame();
        this.stateOfTheGame = new AwaitCrewPhaseActions(this.game);
    }
    
    public GameLogic(GameLogic game) {
        this.game = new GameData(game.getGame());
        //this.stateOfTheGame = game.getStateOfTheGame();
        this.stateOfTheGame = game.getStateOfTheGame();
    }
    
        /**
     * @return the a stateOfTheGame copy
     */
    public IStates getStateOfTheGame() {
        
        //IStates copygamestate = stateOfTheGame;
        //return copygamestate;
        return stateOfTheGame;
    }
    
            /**
     * @return the a TheGame
     */
    public GameData getGame() {
        return this.game;
    }
    
    public void setGame(GameData game2) {
        
        List<Alien> aliens = new ArrayList<>();
        aliens = game2.getAliens();
        
        if (aliens.size() == 0){
            this.game = game2;
        }
        else{
            this.game = game2;
            this.stateOfTheGame = new AwaitCrewPhaseActions(game2);
        }
    }
    
    
     /**
     * @param stateOfTheGame the stateOfTheGame to set
     */
    public void setStateOfTheGame(IStates stateOfTheGame) {
        this.stateOfTheGame = stateOfTheGame;
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
        this.stateOfTheGame = this.stateOfTheGame.InputBeginning(this.game);
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
    
    
}
