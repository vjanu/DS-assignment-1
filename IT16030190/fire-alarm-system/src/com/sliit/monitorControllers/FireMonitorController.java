package com.sliit.monitorControllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sliit.mainServers.FireSensorServerRemote;
import com.sliit.monitorGUI.FireMonitorGUI;
import com.sliit.sensorTypes.Sensor;
import com.sliit.sensorTypes.SensorListener;

/**Act as the middleTier which which transfer readings with server and client.Implements all callback methods
 * @author virajw
 */
public class FireMonitorController extends UnicastRemoteObject implements SensorListener {
	
	private MonitorApplication monitorController;
    private FireSensorServerRemote fireSensorRemoteServer; 
    private FireMonitorController fireMonitor;
    private Sensor sensor;
    private FireMonitorGUI fireMonitorGUI;
    
    
    public FireMonitorController(FireSensorServerRemote sensor) throws RemoteException{
        this.fireSensorRemoteServer = sensor;
        this.monitorController = new MonitorApplication();
    }
    
    //display the monitor controller gui to user
    public void displayMonitor(FireMonitorController fireMon) throws RemoteException{
    	fireMonitor = fireMon;
        // create a new instance of monitor
    	fireMonitorGUI = new FireMonitorGUI(fireMon);
    	//assign it's default property on closing
    	fireMonitorGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	//display gui
    	fireMonitorGUI.setVisible(true);
    	
    	//display relevant data
    	fireMonitorGUI.setMonitorCount(fireSensorRemoteServer.getConnectedMonitorCount());
    	fireMonitorGUI.setSensorCount(fireSensorRemoteServer.getFireSensorCount());
    	fireMon.monitorController.addMonitor(fireMonitorGUI, fireMon.fireSensorRemoteServer.getSensorType());
        
    }
    //retrieve all the latest readings
    public void updateExistingReadings(){
        try {
        	fireSensorRemoteServer.getLatestReadings(fireMonitor);
        } catch (RemoteException ex) {
            System.out.println("Updation Failed: "+ ex);
        }
    }
    @Override
    public void makeAlertsOnFailures(Sensor sensor) throws RemoteException {
    	//update each sensor when sensors are malfunctioning
        JOptionPane.showMessageDialog(fireMonitorGUI,"Floor "+sensor.getFloor()+" Sensors are malfunctioning","Alert Situation",JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void ModifyReading(List<Sensor> sensors, int monitorCount, int sensorCount) throws RemoteException {
        //Setting up values in interface
    	fireMonitorGUI.setMonitorCount(monitorCount);
    	fireMonitorGUI.setSensorCount(sensorCount);
        
    	//Add monitors if they exists to controller
        if( sensors != null || !sensors.isEmpty() ){
            this.monitorController.addMonitor(fireMonitorGUI, sensors);
            
        }
        else{
            JOptionPane.showMessageDialog(null,"alert" ,"Please restart your Server failed to get Updates",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    //retrieve all the latest readings according to the floor
    public void getLatestReadingsByFloorNo(int floor){
        try {
            Sensor sensor = fireSensorRemoteServer.getLatestReadingsByFloor(fireMonitor,floor);
            monitorController.updateMonitor(fireMonitorGUI, sensor);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public void notifyAlertSituation(Sensor sensor) throws RemoteException {
        //update each sensor when new readings comes
    	updateExistingReadings();
        JOptionPane.showMessageDialog(fireMonitorGUI,"Floor "+sensor.getFloor()+" is in danger.","Alert Situation",JOptionPane.WARNING_MESSAGE);
    }
    
}
