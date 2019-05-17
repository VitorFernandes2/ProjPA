package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameData;

/**
 * TransporterChief
 */
public class TransporterChief extends CrewMember implements Serializable{

    public TransporterChief(GameData game){
        
        super(game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }

    @Override
    public String getName() {
        return "Transporter Chief";
    }

    @Override
    public boolean Special() {
        return false;
    }

    @Override
    public boolean Special(int room) {
    
        this.setCrewMemberRoom(room);

        return true;
    
    }

}