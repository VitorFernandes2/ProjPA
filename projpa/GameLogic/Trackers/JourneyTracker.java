package projpa.GameLogic.Trackers;

import java.io.Serializable;
import java.util.ArrayList;
import projpa.GameLogic.GameConstants;

/**
 * JourneyTracker
 */
public class JourneyTracker extends GameConstants implements Serializable{

    String [] JourneyTrackers = {"S", "2A", "3A*", "4A","5A","R","4A","5A","6A","R","6A","7A","R","8A","E"};
    private int JourneyState;

    public JourneyTracker() {
    
        this.JourneyState = 0;
    
    }

    public String[] getJourneyTrackers() {
        return JourneyTrackers;
    }

    public int getJourneyState() {
        return JourneyState;
    }

    public boolean verifyAlienJourney(String Option){

        int count = 0;

        for (int i = 0; i < Option.length(); i++) {

            if (i == Option.length() - 1){
                if (Option.charAt(i) == 'A' && count > 0)
                    return true;
                else
                if (Option.charAt(i) == '*' && count > 0 && Option.charAt(i - 1) == 'A')
                    return true;
            }
            else{

                if(Character.isDigit(Option.charAt(i)))
                    count++;
                else
                if (i == Option.length() - 2){
                    if (Option.charAt(i) != 'A')
                        return false;
                }
                else
                    return false;

            }

        }

        return false;

    }

    public Boolean changeJourney(int pos, String value){

        if(!value.toUpperCase().equals("R") && !verifyAlienJourney(value.toUpperCase())) {
            return false;
        }

        String[] change = new String[15];
        ArrayList<String> temp = new ArrayList<String>();
        
        // copy value to array List
        for(int i = 0; i < 15;i++) {   
            temp.add(getJourneyTrackers()[i]);
        }
        
        // modify value and sees if its possible
            if (pos >=0 && pos < 15)
                temp.set(pos, value);
            else
                return false;
        
        // make new String   
        for (int i= 0;i < 15; i++)
             change[i] = temp.get(i);
         
        JourneyTrackers = change;
        return true;
    }
    
    /**
     * This function gets the journey state
     * @return the journey state
     */
    public String getJourneyTurn(){
        return this.JourneyTrackers[this.JourneyState];
    }

    /**
     * This function goes to the next turn
     */
    public void addJourneyState(){
        this.JourneyState++;
    }
    
}