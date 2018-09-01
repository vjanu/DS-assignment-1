package com.sliit.mainServers;

import java.net.ServerSocket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.sliit.monitorControllers.SensorController;
import com.sliit.sensorTypes.FireSensor;

/**Main server which creates the rmi registry and looksup for the service
 * @author virajw
 */
public class MainRemoteServer {

    // Server Listening port 
    private static final int SERVERPORT = 9001;
    // Injecting Remote service 
    private static FireSensorServer fireSensorServer;
    
  
    public static void main(String[] args) throws Exception {
        System.out.println("Finding a server......");
        
        try{
            // Bind to rmi registry and lookup for name
        	fireSensorServer = new FireSensorServer();
            FireSensor fireSensor = new FireSensor();
            Registry reg = LocateRegistry.createRegistry(1099);
      
            Naming.rebind("//127.0.0.1/fireSensor", fireSensorServer);
            //sleep for 0.5s
            Thread.sleep(500);
            System.out.println("Server is Connected.");
        }
        
        catch(RemoteException e){
            System.err.println("Server is not working properly "+ e);
        }
        
        // Initiate Socket Connection
        try (ServerSocket serverSocket  = new ServerSocket(SERVERPORT)) {
            while (true) {
                SensorController sensorController  = new SensorController(serverSocket.accept(),fireSensorServer);
                sensorController .start();
            }
        }
        catch(RemoteException e){
            System.err.println("Socket is not working properly "+ e);
        }
    }
}
