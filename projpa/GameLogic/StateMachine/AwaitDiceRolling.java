package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import projpa.GameLogic.GameData;
import projpa.GameLogic.Dice.Dice;
import projpa.GameLogic.MapRooms.shipJavaInterface;

/**
 * AwaitDiceRolling
 */
public class AwaitDiceRolling extends StateAdapter implements Serializable{
    
    public AwaitDiceRolling(GameData game){
        super(game);        
    }

    @Override
    public IStates atack(int crewMemberIndex) {
        
        if(this.game.gotSomeActionPoints()){

            shipJavaInterface room;

            if (crewMemberIndex == 0)
                room = this.game.getCrewMember1Room();
            else
                room = this.game.getCrewMember2Room();
            
            int alienPosition = this.game.verifyalien(room.ReturnName()); 
            
            if(alienPosition != -1){

                Dice crewMemberDice = new Dice();
                int dif = game.getNumberOfAtackDiceResult() + this.commanderBonus();
                
                switch (this.game.getCrewMemberAtack(crewMemberIndex)) {
                    
                    case 106:
                        dif += crewMemberDice.roll(1, 6);
                        break;

                    case 206:
                        dif += crewMemberDice.roll(2, 12);
                        break;

                    case 306:
                        dif += crewMemberDice.roll(3, 36);
                        break;

                    default:
                        break;

                }
                
                this.game.decrementActionPoints();

                //If crew member wins
                if (dif >= 5) {
                    
                    //Alien would be destroyd
                    this.game.removeAlien(game.getAlienByPosition(alienPosition));
                    this.game.addInspirationPoints();
                    this.game.addPoints();

                }
                else{
                    
                    //Health tracker - 1
                    this.game.healthTrackerHit();
                    
                    if(this.game.isDead())
                        return new GameOver(this.game);                    
                    
                }

            }

        }

        return new AwaitCrewPhaseActions(this.game);
   
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
    
}