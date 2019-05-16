package projpa.GameLogic.CrewMembers;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;

/**
 * ShuttlePilot
 */
public class ShuttlePilot extends CrewMember implements Serializable{

    public ShuttlePilot(int roomNumber, GameLogic game){
        //old
        super(roomNumber, game);
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }
    
        public ShuttlePilot(GameLogic game){
        
        super(game);
        // special
        game.getHealthTracker().setHullstate(game.getHealthHealthTracker() + 4);
        
        this.setMovement(1);
        this.refillMovementToDo();
        this.setAttack(106);

    }

    @Override
    public String getName() {
        return "Shuttle Pilot";
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