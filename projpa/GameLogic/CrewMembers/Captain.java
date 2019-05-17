package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameData;

/**
 * Captain
 */
public class Captain extends CrewMember implements Serializable{

    public Captain(GameData game){
        
        super(game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }

    @Override
    public String getName() {
        return "Captain";
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