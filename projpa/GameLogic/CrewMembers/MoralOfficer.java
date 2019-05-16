package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * MoralOfficer
 */
public class MoralOfficer extends CrewMember implements Serializable{

    public MoralOfficer(GameLogic game){
        
        super(game);
        // special
        game.getInspirationPoints().setInspstate(4);
        
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }

    @Override
    public String getName() {
        return "Moral Officer";
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