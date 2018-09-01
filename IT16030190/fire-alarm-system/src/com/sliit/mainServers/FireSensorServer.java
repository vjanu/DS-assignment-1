package com.sliit.mainServers;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


import com.sliit.sensorTypes.Sensor;
import com.sliit.sensorTypes.SensorListener;

/** This class handles as the server which handles the sensor and monitor activities
 * @author virajw
 */

public class FireSensorServer extends UnicastRemoteObject implements FireSensorServerRemote{

	private static final long serialVersionUID = 1L;
    //Fire Sensors 
    private List<Sensor> fireSensor= new ArrayList<>();
    //FireSensorMonitors Connected with the RemoteServer
    private List<SensorListener> fireMonitor = new ArrayList<>();
    
	public FireSensorServer() throws RemoteException{}  
	 
	  //count no of monitors connected
    @Override
    public int getConnectedMonitorCount() throws RemoteException{
        return fireMonitor.size();
    }
    
    //count no of sensors connected
    @Override
    public int getFireSensorCount() throws RemoteException{
        return fireSensor.size();
    }
    
    //get the sensor name
    @Override
    public List<Sensor> getSensorType() throws RemoteException {
        return fireSensor;
    }
  
    //getLatest readings
    @Override
    public void getLatestReadings(SensorListener sensorListener) throws RemoteException  {
         	sensorListener.ModifyReading(fireSensor, getConnectedMonitorCount(), getFireSensorCount());
    }
    
    //add listeners 
    @Override
    public void addFireSensorMonitor(SensorListener sensorListener) throws RemoteException {
        System.out.println(sensorListener + " is added");
        //other threads cannot interfere while adding sensors
        synchronized(fireMonitor){
        	fireMonitor.add(sensorListener);
        }
        // Start Thread for this monitor
        new MonitorHandler(this,fireSensor).start();
    }
     
   //getLatest readings according to the floor defined
    @Override
    public Sensor getLatestReadingsByFloor(SensorListener sensorListener, int floor) throws Exception {
        for(Sensor sensor : fireSensor){
            if (sensor.getFloor() == floor ){
                return sensor;
            }
        }
        
        throw new Exception();
    }
    
   //add new sensors
    public void addNewSensor(Sensor sensor) {
    	System.out.println(sensor + " is added");
    	//threadsafe
        synchronized(fireSensor){
            this.fireSensor.add(sensor);
        }
    }
    
   //remove existing sensors
    public void removeExistingSensor(Sensor sensor) {
    	System.out.println(sensor + " is removed");
    	//threadsafe
        synchronized(fireSensor){
            this.fireSensor.remove(sensor);
        }
    }
    
   //providing alerts to monitors 
    public void makeAlerts(Sensor sensor){
        if(!fireMonitor.isEmpty()){
            // Notify all the listeners 
        	fireMonitor.forEach((listener) -> {
                try {
                    System.out.println(sensor + " in the danger");
                    // alerting the monitor
                    listener.notifyAlertSituation(sensor);
                }  
                catch (ConnectException e){
                    // remove listeners if failure occurs
                	fireMonitor.remove(listener);
                }catch (RemoteException r) {
                        System.err.println("Error is "+r.getMessage());
                    }
            });
        }
    }
  //hourly basis notification
    public void notifyListeners(List<Sensor> sensors) {
        if(!fireMonitor.isEmpty()){
            // Notify all the listeners 
        	fireMonitor.forEach((listener) -> {
                try {
                	//readings get updated
                    listener.ModifyReading(sensors, getConnectedMonitorCount(), getFireSensorCount());
                } catch (ConnectException e){
                	fireMonitor.remove(listener);
                    System.err.println("Error is  " + e);
                } catch (RemoteException e) {
                    System.err.println("Error is  " + e);
                }
            });
        }
    }
    //providing alerts to monitors if sensor fails
    public void makeFailureAlerts(Sensor sensor){
        if(!fireMonitor.isEmpty()){
            // Notify all the listeners
        	fireMonitor.forEach((listener) -> {
                try {
                	System.out.println(sensor + " in a fail situation");
                    // notifying sensors
                    listener.makeAlertsOnFailures(sensor);
                }
                catch (ConnectException e){
                	// remove listeners if failure occurs
                	fireMonitor.remove(listener);
                }catch (RemoteException ex1) {
                        System.err.println(ex1.getMessage());
                }
                
            });
        }
    }
    
   

    
}
