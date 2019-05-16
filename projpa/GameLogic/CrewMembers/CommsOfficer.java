package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * CommsOfficer
 */
public class CommsOfficer extends CrewMember implements Serializable{

    public CommsOfficer(int roomNumber, GameLogic game){
        // old
        super(roomNumber, game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }
    
    public CommsOfficer(GameLogic game){
        
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