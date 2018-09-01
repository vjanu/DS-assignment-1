package com.sliit.monitorGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.sliit.monitorControllers.FireMonitorController;

/**
*This class contain the GUI components of Monitor
* @author virajw
*/
public class FireMonitorGUI extends JFrame {
    private JButton btnUpdate;
    private JButton btnGetReading;
    private JLabel lblMon;
    private JLabel lblSen;
    private JLabel cmbFloor;
    private JLabel lblImg;
    private JPanel readingPanel;
    private JScrollPane scrollPanel;
    private JComboBox<Integer> floorno;
    private JLabel monitorCount;
    private JLabel sensorCount;
	private int floor;
	private JLabel lblSensor;

    private static FireMonitorController fireMonitor;
   
    public FireMonitorGUI(FireMonitorController fireMonitor) {
        initComponents();
        this.fireMonitor = fireMonitor; 
        this.setLocationRelativeTo(null);
       
    }
    //setting up monitor count
    public void setMonitorCount(int monitorCount){
        this.monitorCount.setText(Integer.toString(monitorCount));
    }
  //setting up sensor count
	public void setSensorCount(int sensorCount){
        this.sensorCount.setText(Integer.toString(sensorCount));
    }
	//update new monitor count in the gui
    public void addNewMonitor(SensorMonitorGUI sensorMonitor){
        readingPanel.add(sensorMonitor);
        sensorMonitor.setVisible(true);
    }
     //add floor no to combo box  
    public void addToFloor(int floor){
        this.floorno.addItem(floor);
    }
    
    //initialize gui components
    private void initComponents() {
    	lblMon = new JLabel();
    	lblSensor = new JLabel();
        scrollPanel = new JScrollPane();
        readingPanel = new JPanel();
        cmbFloor = new JLabel();
        lblImg = new JLabel();
        btnUpdate = new JButton();
        lblSen = new JLabel();
        monitorCount = new JLabel();
        sensorCount = new JLabel();
        floorno = new JComboBox<>();
        btnGetReading = new JButton();
        
        //setting up gui components of firemonitor
        setMaximumSize(new Dimension(1100, 600));
        setMinimumSize(new Dimension(1100, 600));
        setPreferredSize(new Dimension(1100, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(1100, 600));
        getContentPane().setLayout(null);
        setTitle("Fire Monitor System Control Panel");

        readingPanel.setLayout(new GridLayout(0, 14, 100, 150));
        readingPanel.setBackground(new Color(19, 200, 255));
        scrollPanel.setViewportView(readingPanel);

        getContentPane().add(scrollPanel);
        scrollPanel.setBounds(20, 110, 1020, 380);
        scrollPanel.setBackground(new Color(59, 89, 182));

        lblMon.setFont(new java.awt.Font("Arial", 0, 18)); 
        lblMon.setForeground(new java.awt.Color(255, 255, 255));
        lblMon.setText("Monitor Count");
        getContentPane().add(lblMon);
        lblMon.setBounds(20, 30, 170, 21);

        lblSen.setFont(new java.awt.Font("Arial", 0, 18)); 
        lblSen.setForeground(new java.awt.Color(255, 255, 255));
        lblSen.setText("Sensor Count");
        getContentPane().add(lblSen);
        lblSen.setBounds(250, 30, 190, 21);
        
        lblSensor.setFont(new java.awt.Font("Arial", 0, 18)); 
        lblSensor.setForeground(new java.awt.Color(255, 255, 255));
        lblSensor.setText("Sensor Controllers");
        getContentPane().add(lblSensor);
        lblSensor.setBounds(806, 30, 190, 21);

        monitorCount.setFont(new java.awt.Font("Arial", 0, 30)); 
        monitorCount.setForeground(new java.awt.Color(255, 255, 255));
        monitorCount.setText("0");
        getContentPane().add(monitorCount);
        monitorCount.setBounds(70, 30, 50, 75);

        btnUpdate.setText("Latest Reading");
        btnUpdate.setBackground(new Color(189, 28, 230));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        
        sensorCount.setFont(new java.awt.Font("Arial", 0, 30)); 
        sensorCount.setForeground(new java.awt.Color(255, 255, 255));
        sensorCount.setText("4");
        getContentPane().add(sensorCount);
        sensorCount.setBounds(300, 55, 20, 21);
        getContentPane().add(btnUpdate);
        btnUpdate.setBounds(700, 55, 164, 29);
        
        btnGetReading.setBackground(new Color(189, 28, 230));
        btnGetReading.setText("Get New Monitors");
        btnGetReading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetReadingActionPerformed(evt);
            }
        });
        getContentPane().add(btnGetReading);
        btnGetReading.setBounds(880, 55, 164, 29);

        getContentPane().add(floorno);
        floorno.setBounds(450, 56, 181, 27);

        cmbFloor.setFont(new java.awt.Font("Arial", 0, 18));
        cmbFloor.setForeground(new java.awt.Color(255, 255, 255));
        cmbFloor.setText("Select Floor ID");
        getContentPane().add(cmbFloor);
        cmbFloor.setBounds(480, 30, 130, 21);

        lblImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sliit/images/fire.jpg"))); 
        getContentPane().add(lblImg);
        lblImg.setBounds(0, 0, 1100, 590);

        pack();
    }
    //update button action
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        fireMonitor.updateExistingReadings();
    }
   //reading button action
    private void btnGetReadingActionPerformed(java.awt.event.ActionEvent evt) {
        floor = Integer.parseInt(floorno.getSelectedItem().toString());
        fireMonitor.getLatestReadingsByFloorNo(floor);
    }

    public static void main(String args[]) throws ClassNotFoundException, IllegalAccessException, UnsupportedLookAndFeelException {
        
        try {
            for (UIManager.LookAndFeelInfo data : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(data.getName())) {
                    UIManager.setLookAndFeel(data.getClassName());
                    break;
                }
            }
        }  catch (Exception ex) {
            System.err.println(ex.getMessage());
        } 
      
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FireMonitorGUI(fireMonitor).setVisible(true);
            }
        });
    }

  

   
}
