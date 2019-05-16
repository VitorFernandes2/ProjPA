package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * NavigationOfficer
 */
public class NavigationOfficer extends CrewMember implements Serializable{

    public NavigationOfficer(int roomNumber, GameLogic game){
        //old
        super(roomNumber, game);
        this.setMovement(2);
        this.refillMovementToDo();
        this.setAttack(106);

    }
    
        public NavigationOfficer(GameLogic game){
        
        super(game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }

    @Override
    public String getName() {
        return "Navigation Officer";
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