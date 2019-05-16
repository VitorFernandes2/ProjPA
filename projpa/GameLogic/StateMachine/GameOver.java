package projpa.GameLogic.StateMachine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.HashMap;
import projpa.GameLogic.GameLogic;

/**
 * GameOver
 */
public class GameOver extends StateAdapter implements Serializable{

    public GameOver(GameLogic game){
        super(game);
    }
    
    public void savetoscorefile(){
        
        HashMap<String, Integer> scoreloader = new HashMap<String, Integer>();
        
        try
        {
            
            // reads file
            BufferedReader reader = new BufferedReader(new FileReader("pontuacao.txt"));
            String line;
            while ((line = reader.readLine()) != null)
            {
              String line2[] = line.split("-");
              scoreloader.put(line2[0], Integer.valueOf(line2[1])); // convert string to int
            }
            reader.close();
          
            // se if you can enter in scoreboard
            int counter=0,pos = 0;
            for (String i : scoreloader.keySet()) {          
                if(scoreloader.get(i) > game.getUser().getPoints())
                    pos = counter; // need to add 1 to get real location
                counter++;
            }
            
            //update hash map
            if(pos < 9 || counter < 10){
                pos = pos; // updated to real position 
                counter= 0;
                
                HashMap<String, Integer> scoreloaderclone = new HashMap<String, Integer>();// clone hashmap
                scoreloaderclone = scoreloader;
                
                scoreloader.clear();
                
                for (String i : scoreloaderclone.keySet()) {
                    
                    if(counter < 10){
                        if(counter == pos){

                            scoreloader.put(game.getUserName(), game.getUser().getPoints());
                            pos = 9999;
                            //scoreloaderclone.keySet();

                        }

                            scoreloader.put(i, scoreloaderclone.get(i));
                    }  
                    counter++;
                    
                }
                
            }

            //writes in file   
            BufferedWriter writer = new BufferedWriter(new FileWriter("pontuacao.txt"));
            StringBuilder writetofile = new StringBuilder();
            for (String i : scoreloader.keySet()) {
                
                writetofile.append(i + "-" + scoreloader.get(i));
                writer.write(writetofile.toString());
            }
            writer.close();
          
          
          
        }
        catch (Exception e)
        {
          System.err.format("Exception occurred trying to read '%s'.", "pontuacao.txt");
          e.printStackTrace();
        }
        
    }
    
    
    @Override
    public IStates Startnewbegin(){

        return new AwaitBeginning();
    
    }
    
}