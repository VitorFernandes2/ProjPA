package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;
import projpa.GameLogic.MapRooms.SecretRoom;
import projpa.GameLogic.MapRooms.shipJavaInterface;

/**
 * RedShirt
 */
public class RedShirt extends CrewMember implements Serializable{

    public RedShirt(GameLogic game){
        
        super(game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }

    @Override
    public String getName() {
        return "Red Shirt";
    }

    @Override
    public boolean Special() {

        shipJavaInterface room = new SecretRoom();
        this.setCrewMemberRoom(room);
        
        for (int i = 0; i < 5; i++) 
            if (this.game.getHealthHealthTracker() < this.game.getHullMaxHealth()) {
                this.game.addHealthTrackerHealth();    
            }            

        return true;
    
    }

    @Override
    public boolean Special(int room) {
        return false;
    }

}