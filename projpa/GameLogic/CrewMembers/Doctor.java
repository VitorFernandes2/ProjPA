package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * Doctor
 */
public class Doctor extends CrewMember implements Serializable{

    public Doctor(int roomNumber, GameLogic game){
        //old
        super(roomNumber, game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }

        public Doctor(GameLogic game){
        
        super(game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }
    
    @Override
    public String getName() {
        return "Doctor";
    }

    @Override
    public boolean Special() {
        
        if ("Sick Bay".equals(this.getCrewMemberRoom().ReturnName())){
            
            game.addHealthTrackerHealth();
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean Special(int room) {
        return false;
    }

}