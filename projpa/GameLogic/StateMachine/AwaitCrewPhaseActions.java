package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * AwaitCrewPhaseActions
 */
public class AwaitCrewPhaseActions extends StateAdapter implements Serializable{

    public AwaitCrewPhaseActions(GameLogic game){
        super(game);
    }

    @Override
    public IStates sealRoom(int roomIndex) {
        
        if (this.game.gotSomeActionPoints())
            if (this.game.hasUpgradeSealedToken()) {
                return new AwaitPeekRoom(this.game);   
            }            
        
        return this;

    }

    @Override
    public IStates heal() {
        
        if (this.game.gotSomeActionPoints()) {
            
            if (this.game.isTheSameCrewMembersName("Doctor")) {
                
                if (this.game.notMaxHealthHealthTracker()) {

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

            if(this.game.isTheSameCrewMembersName("Engineer")){

                if (this.game.notMaxHealthHealthTracker()) {

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
    public IStates move(int crewMember, int crewMemberNewRoom) {
        
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
        
        if (this.game.isTheSameCrewMembersName("Commander"))
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