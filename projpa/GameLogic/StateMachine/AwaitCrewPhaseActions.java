package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * AwaitCrewPhaseActions
 */
public class AwaitCrewPhaseActions extends StateAdapter implements Serializable{

    //int turns = 5; // look better -> FINISH
    
    public AwaitCrewPhaseActions(GameLogic game){
        super(game);
    }

    @Override
    public IStates sealRoom(int roomIndex) {
        
        if (game.getActionPoints()>0)
            if (this.game.hasUpgradeSealedToken()) {
                return new AwaitPeekRoom(this.game);   
            }            
        
        return this;

    }

    @Override
    public IStates heal() {
        
        if (this.game.gotSomeActionPoints()) {
            
            if (this.game.getCrewMember2Name().equals("Doctor") || this.game.getCrewMember1Name().equals("Doctor")) {
                
                if (this.game.getHealthHealthTracker() < this.game.getHullMaxHealth()) {

                    this.game.addHealthTrackerHealth();
                    this.game.decrementActionPoints();
    
                }

            }

        }   

        return this;
    
    }

    @Override
    public IStates fixOneHull() {

        if (this.game.gotSomeActionPoints()){

            if(this.game.getCrewMember2Name().equals("Engineer") || this.game.getCrewMember1Name().equals("Engineer")){

                if (this.game.getHullHealth() < this.game.getHullMaxHealth()) {

                    this.game.addHullHealth();
                    this.game.decrementActionPoints();
    
                }

            }
        
        }
        
        return this;

    }

    @Override
    public IStates detonateParticleDisperser(String particleDisperser) {
        
        if (this.game.gotSomeActionPoints())
            return new AwaitChooseParticleDisperser(this.game);    
        return this;
    
    }

    @Override
    public IStates setTrap(int room, int trapIndex) {
        
        if (this.game.gotSomeActionPoints())
            return new AwaitPeekRoom(this.game);

        return this;

    }

    @Override
    public IStates saveGame() {
        return new AwaitSaveGame(this.game);
    }

    @Override
    public IStates move(int crewMember, int crewMemberNewRoom) { // VERRRRR
        
        if (this.game.gotSomeActionPoints())
            return new AwaitPeekRoom(this.game);

        return this;

    }

    @Override
    public IStates atack(int crewMemberIndex) {
        
        if (this.game.gotSomeActionPoints())
            return new AwaitDiceRolling(this.game);
        
        return this;

    }    
    
    @Override
    public IStates nextTurn() {

        this.game.AlienPhase();

        if (this.game.viewIfGameOver())
            return new GameOver(this.game);

        this.game.addTurn();

        //commander special
        
        if ("Commander".equals(game.getCrewMember(0).getName()) || "Commander".equals(game.getCrewMember(1).getName()))
            this.game.setActionPoints(6);
        else
            this.game.setActionPoints(5);
        
        this.game.renewCrewMembersMove();        
        
        switch (this.game.getJourneyTurn()) {

            case "R":
            
                // CONFERIR -> apagar todos os aliens...
                
                this.game.spawnAliens();
                
                return new AwaitRestPhaseActions(this.game);
        
            case "E":

                return new GameOver(this.game);

            default:

                this.game.spawnAliens();
                return new AwaitCrewPhaseActions(this.game);

        }

    }    

    @Override
    public IStates goBack() {
        return new GameOver(this.game);
    }
    
}