package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import projpa.GameLogic.CrewMembers.*;
import projpa.GameLogic.GameData;
import projpa.GameLogic.Dice.Dice;
import projpa.GameLogic.General.General;


/**
 * AwaitCrewMembersSelection
 */
public class AwaitCrewMembersSelection extends StateAdapter implements Serializable{

    public AwaitCrewMembersSelection(GameData game){
        
        super(game);
        
    }

    @Override
    public IStates selectCrewMembers(String crewMember1, String crewMember2) {

        CrewMember crewMemberAux1 = new General().convertStringToCrewMember(crewMember1, this.game);
        CrewMember crewMemberAux2 = new General().convertStringToCrewMember(crewMember2, this.game);

        if (crewMemberAux1 != null)
            game.setCrewMember(0, crewMemberAux1);
        if (crewMemberAux2 != null)
            game.setCrewMember(1, crewMemberAux2);

        if(crewMemberAux1 != null && crewMemberAux2 != null)
            return new AwaitThirdTokenFirstCrewMember(this.game);
        else
            return this;

    }

    @Override
    public IStates selectRandomCrewMembers() {

        Dice dice = new Dice();
        
        int crewMember1 = dice.roll(1,12);
        int crewMember2 = dice.roll(1, 12);

        CrewMember crewMemberAux1 = new General().convertIntToCrewMember(crewMember1, this.game);
        CrewMember crewMemberAux2 = new General().convertIntToCrewMember(crewMember2, this.game);

        if (crewMemberAux1 != null)
            game.setCrewMember(0, crewMemberAux1);
        if (crewMemberAux2 != null)
            game.setCrewMember(1, crewMemberAux2);

        if(crewMemberAux1 != null && crewMemberAux2 != null)
            return new AwaitThirdTokenFirstCrewMember(this.game);
        else
            return this;

    }

    @Override
    public IStates goBack() {
        return new AwaitBeginning();
    }

}