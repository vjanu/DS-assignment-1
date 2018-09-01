package com.sliit.sensorTypes;

import java.util.Random;

/**
 *This class contains reading of smokelevel
 * @author virajw
 */
public class SmokeLevel {
    private int smokeLevel;
    
    public SmokeLevel() {
    	//define initial value 
    	smokeLevel = 1;
    }
    
    public double generateReading(){
    	Random random = new Random();
    	// generate a random integer from 0 to 10
    	int value = random.nextInt(10);
        if (value < 0) {
        	smokeLevel += 1;
        	return smokeLevel;
        } 
        
        return smokeLevel+value;
    }
}
