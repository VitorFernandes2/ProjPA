package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import projpa.GameLogic.Alien;
import projpa.GameLogic.GameLogic;
import projpa.GameLogic.Dice.Dice;
import projpa.GameLogic.General.General;
import projpa.GameLogic.MapRooms.shipJavaInterface;

/**
 * ScienceOfficer
 */
public class ScienceOfficer extends CrewMember implements Serializable{
    
    public ScienceOfficer(GameLogic game){
        
        super(game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }

    @Override
    public String getName() {
        return "Science Officer";
    }

    @Override
    public boolean Special() {

        ArrayList<Integer> arrInteger = this.getCrewMemberRoom().ReturnAvailableRooms();
        Collections.shuffle(arrInteger);

        for (int var : arrInteger) {
            
            shipJavaInterface room = new General().convertIntToRoom(var);
            
            if (this.game.verifyalien(room.ReturnName()) != -1) {
            
                Dice dice = new Dice();
                int roll = dice.roll(1, 6);
                roll += this.game.getNumberOfAtackDiceResult();

                if (roll >= 5) {
                    
                    for (Alien alien : this.game.getAliens()) {
                        
                        if (alien.getRoom().ReturnName().equals(room.ReturnName())) {
                            
                            this.game.removeAlien(alien.getId());
                            return true;

                        }

                    }                    

                }
                else{
                    return false;
                }

            }            

        }

        return false;
    
    }

    @Override
    public boolean Special(int room) {
        return false;
    }

}