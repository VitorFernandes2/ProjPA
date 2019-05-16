package projpa.GameLogic.StateMachine;

import java.io.Serializable;
import projpa.GameLogic.CrewMembers.*;
import projpa.GameLogic.GameLogic;
import projpa.GameLogic.Dice.Dice;


/**
 * AwaitCrewMembersSelection
 */
public class AwaitCrewMembersSelection extends StateAdapter implements Serializable{

    public AwaitCrewMembersSelection(GameLogic game){
        
        super(game);
        
    }

    @Override
    public IStates selectCrewMembers(String crewMember1, String crewMember2) {
        
        int pos1=0,pos2=0; // c style costruction verification
        
        
        //choose 1st member
        switch(crewMember1){
            case "Captain":
                game.getUser().addCrewMember(new Captain(this.game), 0);
                pos1++;
            break;
            case "Commander":
                game.getUser().addCrewMember(new Commander(this.game), 0);
                pos1++;
            break;
            case "CommsOfficer":
                game.getUser().addCrewMember(new CommsOfficer(this.game), 0);
                pos1++;
            break;
            case "Doctor":
                game.getUser().addCrewMember(new Doctor(this.game), 0);
                pos1++;
            break;
            case "Engineer":
                game.getUser().addCrewMember(new Engineer(this.game), 0);
                pos1++;
            break;
            case "MoralOfficer":
                game.getUser().addCrewMember(new MoralOfficer(this.game), 0);
                pos1++;
            break;
            case "NavigationOfficer":
                game.getUser().addCrewMember(new NavigationOfficer(this.game), 0);
                pos1++;
            break;
            case "RedShirt":
                game.getUser().addCrewMember(new RedShirt(this.game), 0);
                pos1++;
            break;
            case "ScienceOfficer":
                game.getUser().addCrewMember(new ScienceOfficer(this.game), 0);
                pos1++;
            break;
            case "SecurityOfficer":
                game.getUser().addCrewMember(new SecurityOfficer(this.game), 0);
                pos1++;
            break;
            case "ShuttlePilot":
                game.getUser().addCrewMember(new ShuttlePilot(this.game), 0);
                pos1++;
            break;
            case "TransporterChief":
                game.getUser().addCrewMember(new TransporterChief(this.game), 0);
                pos1++;
            break;
        }
        //choose 2nd member
        switch(crewMember2){
            case "Captain":
                game.getUser().addCrewMember(new Captain(this.game), 1);
                pos2++;
            break;
            case "Commander":
                game.getUser().addCrewMember(new Commander(this.game), 1);
                pos2++;
            break;
            case "CommsOfficer":
                game.getUser().addCrewMember(new CommsOfficer(this.game), 1);
                pos2++;
            break;
            case "Doctor":
                game.getUser().addCrewMember(new Doctor(this.game), 1);
                pos2++;
            break;
            case "Engineer":
                game.getUser().addCrewMember(new Engineer(this.game), 1);
                pos2++;
            break;
            case "MoralOfficer":
                game.getUser().addCrewMember(new MoralOfficer(this.game), 1);
                pos2++;
            break;
            case "NavigationOfficer":
                game.getUser().addCrewMember(new NavigationOfficer(this.game), 1);
                pos2++;
            break;
            case "RedShirt":
                game.getUser().addCrewMember(new RedShirt(this.game), 1);
                pos2++;
            break;
            case "ScienceOfficer":
                game.getUser().addCrewMember(new ScienceOfficer(this.game), 1);
                pos2++;
            break;
            case "SecurityOfficer":
                game.getUser().addCrewMember(new SecurityOfficer(this.game), 1);
                pos2++;
            break;
            case "ShuttlePilot":
                game.getUser().addCrewMember(new ShuttlePilot(this.game), 1);
                pos2++;
            break;
            case "TransporterChief":
                game.getUser().addCrewMember(new TransporterChief(this.game), 1);
                pos2++;
            break;
        }
        
        
        if(pos1==1 && pos2==1)
            return new AwaitThirdTokenFirstCrewMember(this.game);
        else
            return this;
        
        }

    @Override
    public IStates selectrandomCrewMembers() {
        
        int pos1=0,pos2=0; // c style costruction verification
        Dice dice = new Dice();
        
        
        int crewMember1 = dice.roll(1,12);
        int crewMember2 = dice.roll(1, 12);
        
        //choose 1st member
        switch(crewMember1){
            case 1:
                game.getUser().addCrewMember(new Captain(this.game), 0);
                pos1++;
            break;
            case 2:
                game.getUser().addCrewMember(new Commander(this.game), 0);
                pos1++;
            break;
            case 3:
                game.getUser().addCrewMember(new CommsOfficer(this.game), 0);
                pos1++;
            break;
            case 4:
                game.getUser().addCrewMember(new Doctor(this.game), 0);
                pos1++;
            break;
            case 5:
                game.getUser().addCrewMember(new Engineer(this.game), 0);
                pos1++;
            break;
            case 6:
                game.getUser().addCrewMember(new MoralOfficer(this.game), 0);
                pos1++;
            break;
            case 7:
                game.getUser().addCrewMember(new NavigationOfficer(this.game), 0);
                pos1++;
            break;
            case 8:
                game.getUser().addCrewMember(new RedShirt(this.game), 0);
                pos1++;
            break;
            case 9:
                game.getUser().addCrewMember(new ScienceOfficer(this.game), 0);
                pos1++;
            break;
            case 10:
                game.getUser().addCrewMember(new SecurityOfficer(this.game), 0);
                pos1++;
            break;
            case 11:
                game.getUser().addCrewMember(new ShuttlePilot(this.game), 0);
                pos1++;
            break;
            case 12:
                game.getUser().addCrewMember(new TransporterChief(this.game), 0);
                pos1++;
            break;
        }
        //choose 2nd member
        switch(crewMember2){
            case 1:
                game.getUser().addCrewMember(new Captain(this.game), 1);
                pos2++;
            break;
            case 2:
                game.getUser().addCrewMember(new Commander(this.game), 1);
                pos2++;
            break;
            case 3:
                game.getUser().addCrewMember(new CommsOfficer(this.game), 1);
                pos2++;
            break;
            case 4:
                game.getUser().addCrewMember(new Doctor(this.game), 1);
                pos2++;
            break;
            case 5:
                game.getUser().addCrewMember(new Engineer(this.game), 1);
                pos2++;
            break;
            case 6:
                game.getUser().addCrewMember(new MoralOfficer(this.game), 1);
                pos2++;
            break;
            case 7:
                game.getUser().addCrewMember(new NavigationOfficer(this.game), 1);
                pos2++;
            break;
            case 8:
                game.getUser().addCrewMember(new RedShirt(this.game), 1);
                pos2++;
            break;
            case 9:
                game.getUser().addCrewMember(new ScienceOfficer(this.game), 1);
                pos2++;
            break;
            case 10:
                game.getUser().addCrewMember(new SecurityOfficer(this.game), 1);
                pos2++;
            break;
            case 11:
                game.getUser().addCrewMember(new ShuttlePilot(this.game), 1);
                pos2++;
            break;
            case 12:
                game.getUser().addCrewMember(new TransporterChief(this.game), 1);
                pos2++;
            break;
        }
        
        
        if(pos1==1 && pos2==1)
            return new AwaitThirdTokenFirstCrewMember(this.game);
        else
            return this; 
        
    }

    @Override
    public IStates goBack() {
        return new AwaitBeginning();
    }


}