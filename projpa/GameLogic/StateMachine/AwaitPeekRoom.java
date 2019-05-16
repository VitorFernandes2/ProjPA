package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import projpa.GameLogic.GameLogic;
import projpa.GameLogic.CrewMembers.CrewMember;
import projpa.GameLogic.General.General;
import projpa.GameLogic.MapRooms.shipJavaInterface;
import projpa.GameLogic.Traps.Trap;

/**
 * AwaitPeekRoom
 */
public class AwaitPeekRoom extends StateAdapter implements Serializable{

    public AwaitPeekRoom(GameLogic game){
        
        super(game);

    }

    private int NumberOfCrewMembersInRoom(String roomName){

        int numberOfCrewInRoom = 0;

        if (this.game.getCrewMember1RoomName().equals(roomName))
            numberOfCrewInRoom++;
        
        if (this.game.getCrewMember2RoomName().equals(roomName))
            numberOfCrewInRoom++;

        return numberOfCrewInRoom;

    }

    @Override
    public IStates setTrap(int room, int trapIndex) {
        
        if (this.game.gotSomeActionPoints()) {
            
            shipJavaInterface roomInterface = new General().convertIntToRoom(room);
            Trap trap = this.game.getTrapById(trapIndex);

            if(trap != null)
                if (!this.game.isTrapInside(roomInterface)){
                 
                    trap.chooseTrapRoom(trapIndex);
                    this.game.decrementActionPoints();

                }

        }

        return new AwaitCrewPhaseActions(this.game);
    
    }

    @Override
    public IStates sealRoom(int roomIndex) {
        
        if(this.game.gotSomeActionPoints()){

            if (this.game.hasUpgradeSealedToken()) {
                
                shipJavaInterface roomInterface = new General().convertIntToRoom(roomIndex);

                int alienInRoom = this.game.verifyalien(roomInterface.ReturnName());
                int crewMemberInRoom = NumberOfCrewMembersInRoom(roomInterface.ReturnName());


                if (alienInRoom == -1 && crewMemberInRoom == 0){

                    roomInterface.setsealed();
                    this.game.removeSealedToken();
                    this.game.decrementActionPoints();

                }

            }            

        }

        return new AwaitCrewPhaseActions(this.game);

    }

    @Override
    public IStates move(int crewMemberIndex, int crewMemberNewRoom) {

        if (this.game.gotSomeActionPoints()) {
            
            CrewMember crewMember = this.game.getCrewMember(crewMemberIndex);

            if (crewMember.getMovement() > 0)
                if (crewMember.getMovementToDo() > 0) {
                
                    crewMember.chooseRoom(crewMemberNewRoom);
                    crewMember.decrementMovementToDo();
                    this.game.decrementActionPoints();
                    
                }
            
        }

        return new AwaitCrewPhaseActions(this.game);

    }

    @Override
    public IStates goBack() {
        return new AwaitCrewPhaseActions(this.game);
    }

}