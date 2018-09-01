package com.sliit.sensorTypes;

import java.io.Serializable;

import com.sliit.monitorControllers.SensorBean;

/** This act as bean which contain the reading data
 * @author virajw
 */
public class Sensor implements Serializable{
    private int floor;
    private SensorBean latestReading;

    public Sensor() {}
    //getter to get the floor no
    public int getFloor() {
        return floor;
    }
  //setter to set the floor no
    public void setFloor(int floor) {
        this.floor = floor;
    }
    //getter to get the latest reading
    public SensorBean getLatestReading() {
        return latestReading;
    }
    //setter to set the latest reading
    public void setLatestReading(SensorBean latestReading) {
        this.latestReading = latestReading;
    }

    //display latest reading with floor number
    @Override
    public String toString() {
        return "Reading of the Sensor\n" 
       + "Floor ID is: " + floor + "\nLatest Reading is: " + latestReading;
    }

    
    
}
