package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import projpa.GameLogic.GameData;
import projpa.GameLogic.UpgradeCards.*;
import projpa.GameLogic.Traps.*;
/**
 * AwaitRestPhaseActions
 */
public class AwaitRestPhaseActions extends StateAdapter implements Serializable{

    public AwaitRestPhaseActions(GameData game){
        super(game);
    } 
    
    @Override
    public IStates nextTurn() {

        if (this.game.viewIfGameOver())
            return new GameOver(this.game);

        this.game.addTurn();

        if (this.game.isTheSameCrewMembersName("Commander"))
            this.game.setActionPoints(6);
        else
            this.game.setActionPoints(5);

        this.game.renewCrewMembersMove();
        
        switch (this.game.getJourneyTurn()) {

            case "R":
            
                this.game.spawnAliens();
                return new AwaitRestPhaseActions(this.game);
        
            case "E":

                return new GameOver(this.game);

            default:

                this.game.spawnAliens();
                return new AwaitCrewPhaseActions(this.game);

        }

    }
    

    private boolean isAnyCrewMember(String name){

        if (this.game.getCrewMember1Name().equals(name) || this.game.getCrewMember2Name().equals(name))
            return true;
        return false;

    }    
    
    @Override
    public boolean addonehealth(){

        if (isAnyCrewMember("Doctor")) {
            
            if (game.getInspirationPoints().getInspstate() > 0){
            
                boolean enter = false;

                for (int i = 0; i < 2; i++)
                    if (this.game.getHealthHealthTracker() < this.game.getHullMaxHealth()){

                        enter = true;
                        game.addHealthTrackerHealth();

                    }             
                        
                if(enter)
                    game.decrementInspirationPoints();  
                
                return true;
            }
            
        }
        else{

            if (game.getInspirationPoints().getInspstate() > 0){

                if (this.game.getHealthHealthTracker() < this.game.getHullMaxHealth()){

                    game.addHealthTrackerHealth();
                    game.decrementInspirationPoints();
                    
                    return true;
                }        
                
            }

        } 
        return false;
    }
    
    @Override
    public boolean repairhull(){
        
        if (isAnyCrewMember("Engineer")) {
            
            if (game.getInspirationPoints().getInspstate() > 0){
                
                boolean enter = false;

                for (int i = 0; i < 2; i++)
                    if (this.game.getHullHealth() < this.game.getHullMaxHealth()){

                        enter = true;
                        game.addHullHealth();

                    }         
                    
                    if (enter)
                        game.decrementInspirationPoints();        
                    
                    return true;
            }

        }
        else{

            if (game.getInspirationPoints().getInspstate() > 0){

                if (this.game.getHullHealth() < this.game.getHullMaxHealth()){

                    game.addHullHealth();
                    game.decrementInspirationPoints();
                    return true;
                }      
            
            }

        }        
        return false;
    }
    
    @Override
    public boolean buildorganicdetonator(){
        
        if (game.getInspirationPoints().getInspstate() >= 2){
            
            game.getUser().addTraps(new OrganicDetonator(game));
            game.decrementInspirationPoints();
            game.decrementInspirationPoints();
            
            return true;
        }
        return false;
    }
    
    @Override
    public boolean builoneparticleddispenser(){
        
        if (game.getInspirationPoints().getInspstate() >= 5){
            
            game.getUser().addTraps(new ParticleDisperser(game));

            for (int i = 0; i < 5; i++)
                game.decrementInspirationPoints();
            
            return true;
        }
        return false;
    }
    

    private boolean getMaxMovementCrewMember(int i){

        if (game.getUser().getCrewMembers()[i].getMovement() == 3)
            return true;

        return false;

    }
    

    private void addMovement(int crewMember){

        game.getUser().getCrewMembers()[crewMember].setMovement(game.getUser().getCrewMembers()[crewMember].getMovement() + 1);

    }
    
    
    @Override
    public boolean addonemovement(int crewmemeber){
        
        if (game.getInspirationPoints().getInspstate() >= 4){

            if (crewmemeber == 1 || crewmemeber == 2){
            
                if (this.game.getCrewMemberName(crewmemeber - 1).equals("Transporter Chief") || getMaxMovementCrewMember(crewmemeber - 1))
                    return false;
                else{
                    
                    addMovement(crewmemeber - 1);

                    for (int i = 0; i < 4; i++)
                        this.game.decrementInspirationPoints();
                    
                    return true;
                }

             }
            
        }
        return false;
    }
    
    @Override
    public boolean gainonesealedromtoken(){
        
        if (game.getInspirationPoints().getInspstate() >= 5){

            game.getUser().addUpgradeCards(new GainOneSealedRoom(this.game));
            
            for (int i = 0; i < 5; i++)
                this.game.decrementInspirationPoints();
            
            return true;
        }
        return false;
    }
    
    private void addAtack(int crewmember){

        game.getUser().getCrewMembers()[crewmember].setAttack(game.getUser().getCrewMembers()[crewmember].getAttack() + 100);
        
        
    }
    
    @Override
    public boolean gainoneattack(int crewmember){
        
        if (game.getInspirationPoints().getInspstate() >= 6){
            if (crewmember == 1 || crewmember == 2){
            
                if (game.getUser().getCrewMembers()[crewmember - 1].getAttack() == 306)
                    return false;
                else{
                    
                    addAtack(crewmember - 1);

                    for (int i = 0; i < 6; i++)
                        this.game.decrementInspirationPoints();
                    
                    return true;
                }

             }
            
        }
        return false;
    }
    
    @Override
    public boolean addonethedice(){
        
        // to do;
        if (game.getInspirationPoints().getInspstate() >= 6){
            
            game.getUpgradeCards().add(new AddOneAtackDiceResult(this.game));

            for (int i = 0; i < 6; i++)
                this.game.decrementInspirationPoints();
             
            return true;
        }        
        return false;
    }
    
}