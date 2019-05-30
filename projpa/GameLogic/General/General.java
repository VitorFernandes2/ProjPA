package projpa.GameLogic.General;

import projpa.GameLogic.CrewMembers.*;
import projpa.GameLogic.GameData;
import projpa.GameLogic.MapRooms.*;

public class General {

    /**
     * This function converts integer to room
     * @param roomNumber
     * @return the correspondent room
     */
    public RoomState convertIntToRoom(int roomNumber){

        RoomState room = null;

        switch(roomNumber){

            case 1:
                room = new Room1();
                break;
            case 2:
                room = new Room2();
                break;
            case 3:
                room = new Room3();
                break;
            case 4:
                room = new Room4();
                break;
            case 5:
                room = new Room5();
                break;
            case 6:
                room = new Room6();
                break;
            case 7:
                room = new Room7();
                break;
            case 8:
                room = new Room8();
                break;
            case 9:
                room = new Room9();
                break;
            case 10:
                room = new Room10();
                break;
            case 11:
                room = new Room11();
                break;
            case 12:
                room = new Room12();
                break;
            default:

        }

        return room;

    }

    public int convertRoomStrinToInt(String roomNumber){

        int room = 0;

        switch(roomNumber){

            case "Bridge":
                room = 1;
                break;
            case "Sick Bay":
                room = 2;
                break;
            case "Brig":
                room = 3;
                break;
            case "Crew Quarters":
                room = 4;
                break;
            case "Conference Room":
                room = 5;
                break;
            case "Shuttle Bay":
                room = 6;
                break;
            case "Weapons Bay":
                room = 7;
                break;
            case "Mess Hall":
                room = 8;
                break;
            case "Engineering":
                room = 9;
                break;
            case "Astrometrics":
                room = 10;
                break;
            case "Holodeck":
                room = 11;
                break;
            case "Hydroponics":
                room = 12;
                break;
            default:

        }

        return room;

    }
    
    
    public CrewMember convertStringToCrewMember(String CrewMemberName, GameData game){

        switch(CrewMemberName){
            case "Captain":
                return new Captain(game);
            case "Commander":
                return new Commander(game);
            case "CommsOfficer":
                return new CommsOfficer(game);
            case "Doctor":
                return new Doctor(game);
            case "Engineer":
                return new Engineer(game);
            case "MoralOfficer":
                return new MoralOfficer(game);
            case "NavigationOfficer":
                return new NavigationOfficer(game);
            case "RedShirt":
                return new RedShirt(game);
            case "ScienceOfficer":
                return new ScienceOfficer(game);
            case "SecurityOfficer":
                return new SecurityOfficer(game);
            case "ShuttlePilot":
                return new ShuttlePilot(game);
            case "TransporterChief":
                return new TransporterChief(game);
        }

        return null;

    }

    public CrewMember convertIntToCrewMember(int CrewMemberName, GameData game){

        switch(CrewMemberName){
            case 1:
                return new Captain(game);
            case 2:
                return new Commander(game);
            case 3:
                return new CommsOfficer(game);
            case 4:
                return new Doctor(game);
            case 5:
                return new Engineer(game);
            case 6:
                return new MoralOfficer(game);
            case 7:
                return new NavigationOfficer(game);
            case 8:
                return new RedShirt(game);
            case 9:
                return new ScienceOfficer(game);
            case 10:
                return new SecurityOfficer(game);
            case 11:
                return new ShuttlePilot(game);
            case 12:
                return new TransporterChief(game);
        }

        return null;

    }

}
