/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projpa.Iu.Graphic;

import static java.lang.Math.atan2;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author a21270909
 */
public class ShipGps {
    
    private double shipbasex;
    private double shipbasey;

    public ShipGps(double shipbasex, double shipbasey) {
        this.shipbasex = shipbasex;
        this.shipbasey = shipbasey;
    }
    
    public ShipGps() {
        this.shipbasex = 0;
        this.shipbasey = 0;
    }
    
    public ArrayList<Double> getcords(int ini, int end){
        
        ArrayList<Double> cords = new ArrayList<Double>();
        
        // TEMP -> change with game implementation
        
        ArrayList<Double> inicords = new ArrayList<Double>();
        inicords = getlocationcords(ini);
        ArrayList<Double> endcords = new ArrayList<Double>();
        endcords = getlocationcords(end);
        
        cords.add(endcords.get(0) - inicords.get(0));
        cords.add(endcords.get(1) - inicords.get(1));

        
        return cords;
    }
    
    // return "real" pixel location in game panel
    public ArrayList<Double> getlocationcords(int roomnumber){
        
        ArrayList<Double> cords = new ArrayList<Double>();
        
        // movement database -> get(0) = x || get(1) = y
        
        switch(roomnumber){
            case 1:
                
                cords.add(shipbasex);
                cords.add(shipbasey - 300);
                
                break;
            case 2:
                
                cords.add(shipbasex + 40);
                cords.add(shipbasey + 100);
                
                break;
            case 3:
                
                cords.add(shipbasex - 120);
                cords.add(shipbasey - 150);
                
                break;
            case 4:
                
                cords.add(shipbasex + 120);
                cords.add(shipbasey - 150);
                
                break;
            case 5:
                
                cords.add(shipbasex - 40);
                cords.add(shipbasey - 100);
                
                break;
            case 6:
                
                cords.add(shipbasex);
                cords.add(shipbasey + 280);
                
                break;
            case 7:
                
                cords.add(shipbasex + 120);
                cords.add(shipbasey + 150);
                
                break;
                
            case 8:
                
                cords.add(shipbasex + 40);
                cords.add(shipbasey - 100);
                
                break;
            case 9:
                
                cords.add(shipbasex - 120);
                cords.add(shipbasey);
                
                break;                 
            case 10:
                
                cords.add(shipbasex - 40);
                cords.add(shipbasey + 100);
                
                break;
                
            case 11:
                
                cords.add(shipbasex + 120);
                cords.add(shipbasey);
                
                break;
            
            case 12:
                
                cords.add(shipbasex - 120);
                cords.add(shipbasey + 150);
                
                break;    
                
        }
        
        return cords;
    }
    
        // return "real" pixel location in game panel
    public ArrayList<Double> getlocationlocked(int roomnumber){
        
        ArrayList<Double> cords = new ArrayList<Double>();
        
        // movement database -> get(0) = x || get(1) = y
        
        switch(roomnumber){
            case 1:
                
                cords.add(shipbasex - 10);
                cords.add(shipbasey - 300);
                
                break;
            case 2:
                
                cords.add(shipbasex + 30);
                cords.add(shipbasey + 115);
                
                break;
            case 3:
                
                cords.add(shipbasex - 130);
                cords.add(shipbasey - 160);
                
                break;
            case 4:
                
                cords.add(shipbasex + 110);
                cords.add(shipbasey - 160);
                
                break;
            case 5:
                
                cords.add(shipbasex - 50);
                cords.add(shipbasey - 120);
                
                break;
            case 6:
                
                cords.add(shipbasex - 10);
                cords.add(shipbasey + 255);
                
                break;
            case 7:
                
                cords.add(shipbasex + 110);
                cords.add(shipbasey + 150);
                
                break;
                
            case 8:
                
                cords.add(shipbasex + 30);
                cords.add(shipbasey - 120);
                
                break;
            case 9:
                
                cords.add(shipbasex - 130);
                cords.add(shipbasey - 10);
                
                break;                 
            case 10:
                
                cords.add(shipbasex - 50);
                cords.add(shipbasey + 115);
                
                break;
                
            case 11:
                
                cords.add(shipbasex + 110);
                cords.add(shipbasey - 10);
                
                break;
            
            case 12:
                
                cords.add(shipbasex - 130);
                cords.add(shipbasey + 150);
                
                break;    
                
        }
        
        return cords;
    }
    
    
    // auxiliar to define location for counter for aliens in room
    public ArrayList<Double> auxlocationinfo(int roomnumber){
        
        ArrayList<Double> cords = new ArrayList<>();
        
        // movement database -> get(0) = x || get(1) = y
        
        switch(roomnumber){
            case 0: // room 1
                
                cords.add(shipbasex + 55);
                cords.add(shipbasey - 325);
                
                break;
            case 1: // room 2
                
                cords.add(shipbasex + 50);
                cords.add(shipbasey + 5);
                
                break;
            case 2: // room 3
                
                cords.add(shipbasex - 110);
                cords.add(shipbasey - 230);
                
                break;
            case 3: // room 4
                
                cords.add(shipbasex + 85);
                cords.add(shipbasey - 230);
                
                break;
            case 4: // room 5
                
                cords.add(shipbasex - 78);
                cords.add(shipbasey - 230);
                
                break;
            case 5: // room 6
                
                cords.add(shipbasex - 60);
                cords.add(shipbasey + 270);
                
                break;
            case 6: // room 7
                
                cords.add(shipbasex + 85);
                cords.add(shipbasey + 85);
                
                break;
                
            case 7: // room 8
                
                cords.add(shipbasex + 50);
                cords.add(shipbasey - 230);
                
                break;
            case 8: // room 9
                
                cords.add(shipbasex - 110);
                cords.add(shipbasey - 72);
                
                break;                 
            case 9: // room 10
                
                cords.add(shipbasex - 78);
                cords.add(shipbasey + 5);
                
                break;
                
            case 10: // room 11
                
                cords.add(shipbasex + 85);
                cords.add(shipbasey - 72);
                
                break;
            
            case 11: // room 12
                
                cords.add(shipbasex - 110);
                cords.add(shipbasey + 85);
                
                break;    
                
        }
        
        return cords;
    }
    
    
    
    public ArrayList<VBox> setbackgroundstats(double xcenter,double ycenter){
          
        
        ArrayList<VBox> array = new ArrayList<VBox>();
        
        // Player Image
        
        for (int i = 0; i < 12 ; i++){
        
            Image playerimagem = new Image(getClass().getResourceAsStream("\\Images\\playerstand.png"));
            ImageView playerimage = new ImageView(playerimagem);
            playerimage.setFitHeight(12);
            playerimage.setPreserveRatio(true);

            Label playerlabel = new Label("0");
            
            HBox temparray = new HBox(playerimage,playerlabel);
            
            Image alienimagem = new Image(getClass().getResourceAsStream("\\Images\\alienicon.png"));
            ImageView alienimage = new ImageView(alienimagem);
            alienimage.setFitHeight(12);
            alienimage.setPreserveRatio(true);

            Label alienlabel = new Label("0");
            
            HBox temparray2 = new HBox(alienimage,alienlabel);
            

            
            array.add(new VBox(temparray,temparray2));
            
            array.get(i).setLayoutX(auxlocationinfo(i).get(0));
            array.get(i).setLayoutY(auxlocationinfo(i).get(1));
            
            
        }
        
        
        return array;
        
        
        
    }
    
    
    
    
    public double calculatedegree(double xa, double ya, double xb, double yb){
        
        xb = xb - xa;
        yb = yb - ya;
        yb = -yb;
        
        return atan2(xb,yb); // needs -180 ao x100 from interface
    }
    
}
