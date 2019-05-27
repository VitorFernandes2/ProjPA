/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projpa.Iu.Graphic;

import static java.lang.Math.atan2;
import java.util.ArrayList;

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
        
        ArrayList<Double> cords = new ArrayList<>();
        
        // TEMP -> change with game implementation
        
        switch(ini){
            case 1:
                
                if ( end == 8){
                    cords.add(40.0);
                    cords.add(200.0);  
                }

                if ( end == 5){ 
                    cords.add(-40.0);
                    cords.add(200.0); 
                }
  
                break;
            case 2:
                
                if ( end == 8){
                    cords.add(0.0);
                    cords.add(-200.0);  
                }

                if ( end == 7){ 
                    cords.add(-40.0);
                    cords.add(200.0); 
                }
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:
                
                break;
            case 6:
                
                break;
            case 7:
                
                break;
                
            case 8:
                
                break;
            case 9:
                
                break;
                
                        
            case 10:
                
                break;
                
            case 11:
                
                break;
            
            case 12:
                
                break;    
                
        }
        
        return cords;
    }
    
    // return "real" pixel location in game panel
    public ArrayList<Double> getlocationcords(int roomnumber){
        
        ArrayList<Double> cords = new ArrayList<>();
        
        // movement database
        
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
                
                cords.add(shipbasex );
                cords.add(shipbasey + 120);
                
                break;
            
            case 12:
                
                cords.add(shipbasex - 120);
                cords.add(shipbasey + 150);
                
                break;    
                
        }
        
        return cords;
    }
    
    public double calculatedegree(double xa, double ya, double xb, double yb){
        
        
        xb = xb - xa;
        yb = yb - ya;
        yb = -yb;
        
        return atan2(xb,yb);
    }
    
}
