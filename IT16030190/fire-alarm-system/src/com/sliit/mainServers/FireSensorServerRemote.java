package com.sliit.mainServers;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



import com.sliit.sensorTypes.Sensor;
import com.sliit.sensorTypes.SensorListener;

/**
 * This class contains all the RMI call back methods
 * @author virajw
 */
public interface FireSensorServerRemote extends Remote{
	
   //count no of connected sensors
    public int getFireSensorCount() throws RemoteException;
    
   //count no of connected monitors
    public int getConnectedMonitorCount() throws RemoteException;
    
   //add new sensor Listener
    public void addFireSensorMonitor(SensorListener sensorListener) throws RemoteException;
    
   //get sensor readings
    public void getLatestReadings(SensorListener sensorListener) throws RemoteException ;
    
  //get readings according to given floor
    public Sensor getLatestReadingsByFloor(SensorListener sensorListener, int floor) throws Exception ;
    
  //get sensors list
    public List<Sensor> getSensorType() throws RemoteException;

}
