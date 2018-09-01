package com.sliit.monitorControllers;

import java.io.Serializable;

/**
 *This class holds all setters and getters of the sensors
 * @author virajw
 */
public class SensorBean implements Serializable {
   
	private static final long serialVersionUID = 1L;
	private double temperature;
    private double batteryLevel;
    private double co2Level;
    private int smokeLevel;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public double getCO2Level() {
        return co2Level;
    }

    public void setCO2Level(double co2Level) {
        this.co2Level = co2Level;
    }

    public int getSmokeLevel() {
        return smokeLevel;
    }

    public void setSmokeLevel(int smokeLevel) {
        this.smokeLevel = smokeLevel;
    }

    @Override
    public String toString() {
        return "Current Sensor Readings \n" 
            + "Temperature is: " + temperature + "\n"
    		+ "Battery Level is: " + batteryLevel + "\n"
    		+ "Smoke Level is: " + smokeLevel + "\n"
    		+ "CO2 Level is: " + co2Level;
    }   
    
}
