package com.sliit.mainServers;

import java.util.List;

import com.sliit.sensorTypes.Sensor;

/** This class handles all the clients who are connected with the server
 * @author virajw
 */
public class MonitorHandler extends Thread implements Runnable {
        
        private FireSensorServer fireSensorServer;
        private List<Sensor> sensors;
        
        //Set constructors values 
        public MonitorHandler(FireSensorServer fireSensorServer, List<Sensor> sensors) {
            this.fireSensorServer = fireSensorServer;
            this.sensors = sensors;
        }
        //override run method in thread class
        @Override
        public void run(){
            
            while(true){
            	// Every hour listeners get notified
                try {
                    Thread.sleep(3600000);
                    //to avoid threads accessing same data simultaneously
                    synchronized( sensors ){
                      	fireSensorServer.notifyListeners(sensors);
                    }
                    System.out.println("Notification is done Successfully!");
                } catch (InterruptedException ex) {
                    System.err.println("Unable notify the listeners s"+ex.getMessage());
                }
            }
        }
    }
