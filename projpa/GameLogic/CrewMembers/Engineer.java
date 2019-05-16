package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;
import projpa.GameLogic.MapRooms.shipJavaInterface;

/**
 * Engineer
 */
public class Engineer extends CrewMember implements Serializable{

    public Engineer(int roomNumber, GameLogic game){
        //old
        super(roomNumber, game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }

    public Engineer(GameLogic game){
        
        super(game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }
    
    @Override
    public String getName() {
        return "Engineer";
    }

    @Override
    public boolean Special() {

        shipJavaInterface room = this.getCrewMemberRoom(); 

        if (room.ReturnName().equals("Engineering")) {
            
            if (this.game.getHullHealth() < this.game.getHullMaxHealth()) {
                
                this.game.addHullHealth();

            }

        }

        return true;
    
    }

    @Override
    public boolean Special(int room) {
        return false;
    }

}