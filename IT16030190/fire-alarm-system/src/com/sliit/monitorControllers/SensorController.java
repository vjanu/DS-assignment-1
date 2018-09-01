package com.sliit.monitorControllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sliit.mainServers.FireSensorServer;
import com.sliit.sensorTypes.Sensor;

/** This class handles all the activities related with the sensors
 * @author virajw
 */
public class SensorController extends Thread implements Runnable {
    
	private SensorBean LatestReading;
	private String givenSensor;
    private final Socket socket;
    private final Sensor sensor;
  
    
    //Set is used to assign unique names for sensors
    private static HashSet<String> sensorTypes = new HashSet<String>();

    // Map sensors with unique identification given
    private static Map<String,Sensor> mappedSensors = new HashMap<>();
    
    // FireSensorServer class injected
    private FireSensorServer fireSensorServer;
	private String password;
	private int floor;
	private String input;

    public SensorController(Socket socket, FireSensorServer fireSensorServer) {
       this.socket = socket;
       this.sensor = new Sensor();
       this.fireSensorServer = fireSensorServer;
       this.LatestReading = new SensorBean();
   }

   @Override
   public void run() {
	 
	   BufferedReader bufferedReader;
	   PrintWriter printWriter;
       try {

           // get user input and output them
    	   bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	   printWriter = new PrintWriter(socket.getOutputStream(), true);

           
    	   while (true) {
        	   //get the sensor name
			   printWriter.println("GETNAME");
        	   givenSensor = bufferedReader.readLine();
               if (givenSensor == null) {
            	   System.out.println("Please enter a valid name!");
                   return;
               }
               //Check whether entered name already exists or not.i.e : threadsafe
               synchronized (sensorTypes) {
                   if (!sensorTypes.contains(givenSensor)) {
                	   sensorTypes.add(givenSensor);
                       break;
                   }
               }
           }
    	   
    	 
           
           // Assign passwords to sensors
    	   while (true){
        	   printWriter.println("GETPASSWORD");
               password = bufferedReader.readLine();
               if (password == null) {
            	   System.out.println("Please enter a valid password!");
                   return;
               }
               // credentials are validated
               if(authenticateSensorUser(givenSensor, password)){
                   break;
               } 
               else {
            	   System.out.println("Please enter a valid password!");
                   return;
               }
            }
           
    	   // Assign floor id
    	   while (true) {
        	   printWriter.println("GETFLOOR");
               floor = Integer.parseInt(bufferedReader.readLine());
               if (floor == 0) {
            	   System.out.println("Please enter a valid Floor no!");
                   return;
               }
               sensor.setFloor(floor);
               break;
            }
    	   
           //Notify successfull registration to sensors
           printWriter.println("REGISTEREDSENSOR");
           
          //Adding new sensors to server
           fireSensorServer.addNewSensor(sensor);
           
           //Adding new sensors to map
           mappedSensors.put(givenSensor, sensor);
           
                     
           //Get Readings from the sensors
           while (true) {
                input = bufferedReader.readLine();
                if (input == null) {
                    return;
                }
                // Deserializing the records to byte stream
                try(ObjectInputStream objc = new ObjectInputStream
                		(new ByteArrayInputStream(Base64.getDecoder().decode(input)))){  
                	LatestReading = (SensorBean) objc.readObject();  
                    sensor.setLatestReading(LatestReading);
                    mappedSensors.entrySet().forEach(x->System.out.println(x.getValue())); 
                    
                    // Validate critical conditions
                    validateAlertConditions(sensor);
                } catch (IOException | ClassNotFoundException ex) {  
                    System.err.println(ex.getMessage());
                }  
           } 
       } catch (IOException ex) {
    	   System.err.println(ex.getMessage());
       } finally {
           // Removing existing sensor if failure occurs
        	fireSensorServer.removeExistingSensor(sensor);
           // Disconnected sensors are removed
           sensorTypes.remove(givenSensor);
           try {
        	   //close the connection
			socket.close();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
            
       }
   }
   
   public static boolean authenticateSensorUser(String username, String password){
	    String credentials;
	     String[] data;
	     String uname;
	     String pword;
       // Get data from text file
       try (BufferedReader br = new BufferedReader(new InputStreamReader
       		(SensorController.class.getResourceAsStream("SensorLogins.txt")))) {
           while ( null != (credentials = br.readLine()) ) { 
               data = credentials.split("-");
               uname = data[0];
               pword = data[1];
               if(uname.equals(username) && pword.equals(password)){
                       return true;
               }
           }
           
           return false;
       } catch (IOException e) {
           System.err.println(e.getMessage());

       }
       return false;
   }
  //critical conditions are checked
   public void validateAlertConditions(Sensor sensor){
        double temp = sensor.getLatestReading().getTemperature();
        int smoke = sensor.getLatestReading().getSmokeLevel();
        	//alert situations
        if( temp > 50 ||  smoke > 7){
            System.out.println("In a critical Situation");
            //Allow sensor to make a alert
            fireSensorServer.makeAlerts(sensor);
        }

   }
 
  

}

