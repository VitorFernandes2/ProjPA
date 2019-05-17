package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameData;

/**
 * NavigationOfficer
 */
public class NavigationOfficer extends CrewMember implements Serializable{

    public NavigationOfficer(GameData game){
        
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