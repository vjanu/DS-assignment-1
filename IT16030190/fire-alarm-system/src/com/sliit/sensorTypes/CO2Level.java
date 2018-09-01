package com.sliit.sensorTypes;

import java.util.Random;

/**
 *This class contains reading of co2level
 * @author virajw
 */
public class CO2Level {

    private double co2Level;
    
    public CO2Level() {
    	//define initial value 
    	co2Level = 1;
    }
    
    
    public double generateReading(){
    	Random random = new Random();
    	// generate a random integer from 0 to 1000
    	int value = random.nextInt(1000);
        if (value < 0) {
        	co2Level += 10;
        	return co2Level;
        } 
        return co2Level+value;
    }
    
}
