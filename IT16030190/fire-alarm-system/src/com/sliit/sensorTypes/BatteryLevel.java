package com.sliit.sensorTypes;

import java.util.Random;

/**
 * This class contains reading of batterylevel
 * @author virajw
 */
public class BatteryLevel {

    private double batteryLevel;
    
    public BatteryLevel() {
    	//define initial value 
    	batteryLevel = 1;
    }
    
    
    public double generateReading(){
    	Random random = new Random();
    	// generate a random integer from 0 to 100
    	int value = random.nextInt(100);
    	if (value < 0) {
    		batteryLevel += 10;
    		return batteryLevel;
        }
        return (batteryLevel+value);
    }
    
}
