package projpa.GameLogic.General;

import projpa.GameLogic.MapRooms.*;

public class General {

    /**
     * This function converts integer to room
     * @param roomNumber
     * @return the correspondent room
     */
    public shipJavaInterface convertIntToRoom(int roomNumber){

        shipJavaInterface room = null;

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

}
