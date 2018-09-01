package com.sliit.sensorTypes;

import java.util.Random;

/**
 *This class contains reading of temperature
 * @author virajw
 */
public class Temperature{
    
    private double temperature;
    
    public Temperature() {
    	//define initial value 
    	temperature = 4;
    }
    

    public double generateReading(){
    	
        	Random random = new Random();
        	// generate a random integer from 0 to 90
        	int value = random.nextInt(90);
            if (value < 0) {
            	temperature += 1.5;
            	return temperature;
            } 
        
        return temperature+value;
    }
   
}
