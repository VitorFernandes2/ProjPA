package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * SecurityOfficer
 */
public class SecurityOfficer extends CrewMember implements Serializable{

    public SecurityOfficer(GameLogic game){
        
        super(game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(206);

    }

    @Override
    public String getName() {
        return "Security Officer";
    }

    @Override
    public boolean Special() {
        // aplied in the constructer
        return true;
    }

    @Override
    public boolean Special(int room) {
        return false;
    }
    
}