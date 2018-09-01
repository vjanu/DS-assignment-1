package com.sliit.sensorTypes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/** Interface which notifies all the monitors with callback methods
 * @author virajw
 */
public interface SensorListener extends Remote{
    //get the updated readings
    public void ModifyReading(List<Sensor> sensors, int monitorCount, int sensorCount) throws RemoteException;
    //notify on alert situations
    public void notifyAlertSituation(Sensor sensor) throws RemoteException;
  //notify on failure situations
    public void makeAlertsOnFailures(Sensor sensor) throws RemoteException;
}
