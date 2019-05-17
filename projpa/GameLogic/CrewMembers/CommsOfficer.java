package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameData;

/**
 * CommsOfficer
 */
public class CommsOfficer extends CrewMember implements Serializable{

    public CommsOfficer(GameData game){
        
        super(game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }

    @Override
    public String getName() {
        return "Comm's Officer";
    }

    @Override
    public boolean Special() {
        // defined already in gamelogic... cant be called...
        return true;
    }

    @Override
    public boolean Special(int room) {
        return false;
    }

}