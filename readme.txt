This fire alarm system has 3 main components to be executed.
1) MainRemoteServer
2) FireSensor
3) UserLoginMonitorGUI

First of all Server should be up and running
*Goto com.sliit.mainServers package
*Inside the package, run MainRemoteServer.java

Next Sensors should be executed
*Goto com.sliit.sensorTypes package
*Inside the package, run FireSensor.java

When it executes, it will prompt for some credentials.Those credentials are available inside the com.sliit.monitorControllers package, SensorLogins.txt file.

for eg:
Please Enter your sensor username :sensor
Please Enter your sensor password :in90
Please Enter Floor ID(1-50):3

Finally GUI component need to be executed.
*Goto com.sliit.monitorGUI package
*Inside the package, run UserLoginMonitorGUI.java

To log into the system, login credentials need to be provided.Logging credentials are available in the com.sliit.monitorControllers package, UserLoginCredentials.txt file. 

for eg:
username :admin
password :fire123

Then click login button to log into the system.........



IT16030190
V.A.Wickramasinghe