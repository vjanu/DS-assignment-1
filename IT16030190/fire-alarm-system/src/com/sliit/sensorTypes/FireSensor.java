package com.sliit.sensorTypes;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

import com.sliit.monitorControllers.SensorBean;


/**
 *This class use to add new sensors to the monitor after authentication
 * 
 * @author virajw
 */
public class FireSensor  {
    
    private static FireSensor fireSensor;
	private Temperature temperature;
    private SmokeLevel smokeLevel;
    private CO2Level co2Level;
    private BatteryLevel batteryLevel;
    
    private SensorBean reading;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
	private String server;
	private Socket socket;
	private String input;
	private String username;
	private String password;
	private Scanner userInput;
	private int floor;
	private long breakReading;

    public FireSensor() {
    	userInput = new Scanner(System.in);
        temperature = new Temperature();
        smokeLevel = new SmokeLevel();
        batteryLevel = new BatteryLevel();
        co2Level = new CO2Level();
        reading = new SensorBean();
       
    }
    
    
    public void run() throws IOException, InterruptedException {

        // Socket Connection is created
        server = "127.0.0.1";
        socket = new Socket(server, 9001);
        
        //get user input and output
        bufferedReader = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        printWriter = new PrintWriter(socket.getOutputStream(), true);

        // Sensor Authentication data is processed
        while (true) {
        	 input = bufferedReader.readLine();
        
        		
                 if (input.startsWith("GETNAME") && input != null ) {
                 	printWriter.println(getUsername());
                 } else if (input.startsWith("GETPASSWORD") && input != null){
                 	printWriter.println(getPassword());	
                 } else if (input.startsWith("GETFLOOR") && input != null){
                 	printWriter.println(getFloor());
                 } else if (input.startsWith("REGISTEREDSENSOR")&& input != null){
                 	generateSensorReading();
                 }
			
           
        }
    }
   //get the sensor username
    private String getUsername() {
    	try {
    		System.out.print("Please Enter your sensor username : ");
            username = userInput.nextLine();
            return username;
		} catch (Exception e) {
			 
			 return "Incorrect Username ";
		}
        
    }
    //get the sensor password
    private String getPassword(){  
    	try {
        System.out.print("Please Enter your sensor password: ");
        password = userInput.nextLine();
        return password;
    	} catch (Exception e) {
			 
			 return "Incorrect Username ";
		}
    	
    }
   //get the floor id
    private int getFloor() {
        System.out.print("Please Enter Floor ID(1-50): ");
        floor = Integer.parseInt(userInput.nextLine());
        //validation to check valid input
        if(floor<1 || floor >50) {
        	System.out.println("Please enter a valid floor number between 1 and 50");
        	 System.out.print("Please Enter Floor ID: ");
        	 floor = Integer.parseInt(userInput.nextLine());
        }
        return floor;
    }

    private void generateSensorReading() throws IOException, InterruptedException{
        for (; ;) {
            //setting up readings
			reading.setTemperature(temperature.generateReading());
			reading.setSmokeLevel((int) smokeLevel.generateReading());
			reading.setBatteryLevel(batteryLevel.generateReading());
			reading.setCO2Level(co2Level.generateReading());
			
			System.out.println(reading);
			
			// Serializing readings to display in the server
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try (ObjectOutputStream obj = new ObjectOutputStream( baos )) {
				obj.writeObject( reading );
				
			    // Transferring object to server
			    printWriter.println(Base64.getEncoder().encodeToString(baos.toByteArray()));
			} catch (IOException ex) {
			    System.err.println("Serialization failed "+ex.getMessage());
			  }
			//to avoid continuous display
			 breakReading = 300000;
             Thread.sleep(breakReading);
		}
    }
    //Initiate the fire sensor
    public static void main(String[] args) throws Exception {
        fireSensor = new FireSensor();
        fireSensor.run();
    }
}
