package com.sliit.monitorControllers;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sliit.mainServers.FireSensorServerRemote;
import com.sliit.monitorGUI.FireMonitorGUI;
import com.sliit.monitorGUI.SensorMonitorGUI;
import com.sliit.sensorTypes.Sensor;

/** This class initialize the components to run the fire monitors
 * @author virajw
 */
public class MonitorApplication {
    
    private Map<Integer,SensorMonitorGUI> sensorMonitors = new HashMap<>();
    private List<Integer> sensorLocations = new ArrayList<>();
  
    //start the monitor after initializing components
    public void intializeMonitor(){
        try {

            // lookup for remote object
            FireSensorServerRemote fireSensorServerRemote = (FireSensorServerRemote) 
            		Naming.lookup("rmi://localhost/fireSensor");

            // Instantiating fire monitor controller
            FireMonitorController monitor = new FireMonitorController(fireSensorServerRemote);
            
            // monitors are added to remote object
            fireSensorServerRemote.addFireSensorMonitor(monitor);

            // display each monitor on gui
            monitor.displayMonitor(monitor);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            System.err.println(ex.getMessage());
        }
    
    }
    
    public void addMonitor(FireMonitorGUI monitorGUI,List<Sensor> sensors){
        
        sensors.forEach(sensor ->{
            SensorMonitorGUI monitor;
            //get floor number
            int floor = sensor.getFloor();
            
            // check whether floor no already exists
            if(!sensorMonitors.containsKey(floor)){
                monitor = new SensorMonitorGUI(floor);
                
                //floors are put to a list with their no
                sensorMonitors.put(floor, monitor);
                
                //New monitors are added to each floor
                monitorGUI.addNewMonitor(monitor);
                
                //set floor numbers
                monitorGUI.addToFloor(floor);
                
                // Add sensors to each floor
                sensorLocations.add(floor);
            }
            //when floor no already exists
            else{
                monitor = sensorMonitors.get(sensor.getFloor());
            }
            
            //set the reading of each floor
            monitor.setTemperature(sensor.getLatestReading().getTemperature());
            monitor.setBatteryLevel(sensor.getLatestReading().getBatteryLevel());
            monitor.setSmokeLevel(sensor.getLatestReading().getSmokeLevel());
            monitor.setCO2Level(sensor.getLatestReading().getCO2Level());
        });
        
    }
    
    //update the monitor with data
    public void updateMonitor(FireMonitorGUI monitorGUI, Sensor sensor){
    	//check the floor no
        if(sensorMonitors.containsKey(sensor.getFloor())){
            SensorMonitorGUI monitor = sensorMonitors.get(sensor.getFloor());
            //set readings to each sensor
            monitor.setTemperature(sensor.getLatestReading().getTemperature());
            monitor.setBatteryLevel(sensor.getLatestReading().getBatteryLevel());
            monitor.setSmokeLevel(sensor.getLatestReading().getSmokeLevel());
            monitor.setCO2Level(sensor.getLatestReading().getCO2Level());
        }
    }
    
  

   
    
}
