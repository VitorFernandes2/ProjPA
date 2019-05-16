package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * Commander
 */
public class Commander extends CrewMember implements Serializable{

    public Commander(GameLogic game){
        
        super(game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);
        
        //special
        game.setActionPoints(6);

    }
    

    @Override
    public String getName() {
        return "Commander";
    }

    @Override
    public boolean Special() {
        return true;
    }

    @Override
    public boolean Special(int room) {
        return false;
    }

}