package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameData;

/**
 * Doctor
 */
public class Doctor extends CrewMember implements Serializable{

    public Doctor(GameData game){
        
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
        
        if ("Sick Bay".equals(this.getCrewMemberRoom().getName())){
            
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