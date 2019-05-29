package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import java.util.ArrayList;
import projpa.GameLogic.GameData;
import projpa.GameLogic.Dice.Dice;
import projpa.GameLogic.MapRooms.RoomState;

/**
 * AwaitDiceRolling
 */
public class AwaitDiceRolling extends StateAdapter implements Serializable{
    
    int dicersoult; // tells the number of the dice
    int dicerolls;  // tells the amount of rolled dices
    int diceendstat = -1; // tells if the it won or if it died  (-1 if there is no alien in location)
    
    
    public AwaitDiceRolling(GameData game){
        super(game);        
    }

    @Override
    public IStates atack(int crewMemberIndex) {
        
        if(this.game.gotSomeActionPoints()){

            RoomState room;

            if (crewMemberIndex == 0)
                room = this.game.getCrewMember1Room();
            else
                room = this.game.getCrewMember2Room();
            
            int alienPosition = this.game.verifyalien(room.getName()); 
            
            if(alienPosition != -1){

                Dice crewMemberDice = new Dice();
                int dif = game.getNumberOfAtackDiceResult() + this.commanderBonus();
                
                switch (this.game.getCrewMemberAtack(crewMemberIndex)) {
                    
                    case 106:
                        dicerolls = 1;
                        dicersoult = crewMemberDice.roll(1, 6);
                        dif += dicersoult;
                        break;

                    case 206:
                        dicerolls = 2;
                        dicersoult = crewMemberDice.roll(2, 12);
                        dif += dicersoult;
                        break;

                    case 306:
                        dicerolls = 3;
                        dicersoult = crewMemberDice.roll(3, 36);
                        dif += dicersoult;
                        break;

                    default:
                        break;

                }
                
                this.game.decrementActionPoints();

                //If crew member wins
                if (dif >= 5) {
                    
                    diceendstat = 1;
                    //Alien would be destroyd
                    this.game.removeAlien(game.getAlienByPosition(alienPosition));
                    this.game.addInspirationPoints();
                    this.game.addPoints();

                }
                else{
                    
                    diceendstat = -1;
                    //Health tracker - 1
                    this.game.healthTrackerHit();
                    
                    if(this.game.isDead()){
                        diceendstat = 0;
                        //return new GameOver(this.game);         
                        return this;
                    }          
                    
                }

            }

        }

        //return new AwaitCrewPhaseActions(this.game);
        return this;
    }
    
    @Override
    public IStates nextTurn() {
        
        if (diceendstat == 1 || diceendstat == -1)
            return new AwaitCrewPhaseActions(this.game);
        if (diceendstat == 0)
            return new GameOver(this.game);
        return this;
    }
    

    @Override
    public IStates goBack() {
        return new AwaitCrewPhaseActions(this.game);
    }
    
    private int commanderBonus(){
        
        if (this.game.isTheSameCrewMembersName("Commander"))
            return 3;
        else            
            return 0;
    }
    
    @Override
    public ArrayList<Integer> getgeninfo(){
        
        ArrayList<Integer> returns = new ArrayList<Integer>();
        
        returns.add(dicersoult);
        returns.add(dicerolls);
        returns.add(diceendstat);
        
        return returns;

    }
    
    
}