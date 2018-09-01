package com.sliit.monitorGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

/**
*This class contains GUI components of the monitor screen
* @author virajw
*/
public class SensorMonitorGUI extends JInternalFrame {
	    private JTextField batterylevel;
	    private JTextField smoke;
	    private JLabel lblcentri;
	    private JLabel lbltem;
	    private JLabel lblsm;
	    private JLabel lblco2;
	    private JLabel lblbat;
	    private JLabel lblsmoke;
	    private JLabel lblperc;
	    private JLabel lblppm;
	    private JLabel floor;
	    private JTextField Co2level;
	    private JTextField temperature;
		private JLabel lblfloorNo;
  
    public SensorMonitorGUI(int floor) {
        initComponents();
        this.lblfloorNo.setText(String.valueOf(floor));
    }
    //Setting up values to respective labels
    public void setTemperature(Double temp){
        this.temperature.setText(Double.toString(temp));
    }
    
    public void setSmokeLevel(int smokeLevel){
        this.smoke.setText(Integer.toString(smokeLevel));
    }
    
    public void setCO2Level(double co2Level){
        this.batterylevel.setText(Double.toString(co2Level));
    }
    
    public void setBatteryLevel(double batteryLevel){
        this.Co2level.setText(Double.toString(batteryLevel));
    }

    //setting up gui components
    @SuppressWarnings("unchecked")
    private void initComponents() {

        smoke = new JTextField();
        lbltem = new JLabel();
        lblsm = new JLabel();
        temperature = new JTextField();
        floor = new JLabel();
        batterylevel = new JTextField();
        lblco2 = new JLabel();
        lblbat = new JLabel();
        Co2level = new JTextField();
        lblcentri = new JLabel();
        lblsmoke = new JLabel();
        lblperc = new JLabel();
        lblppm = new JLabel();
        lblfloorNo = new JLabel();

        setMaximumSize(new java.awt.Dimension(280, 250));
        setSize(new java.awt.Dimension(280, 250));
        setBackground(new Color(204, 0, 204));
        smoke.setEditable(false);
        smoke.setName("smoke"); 
        smoke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smokeActionPerformed(evt);
            }
        });

        lbltem.setText("Temperature");
        lblsm.setText("Smoke Level");

        temperature.setEditable(false);
        temperature.setName("temperature"); 
        temperature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temperatureActionPerformed(evt);
            }
        });
        //default value of floor set to 1
        floor.setText("Floor");
        floor.setFont(new Font("Arial", Font.BOLD, 17)); 
        floor.setForeground(Color.BLACK);
        floor.setAlignmentX(0);
        lblfloorNo.setText("Floor");
        
        
        batterylevel.setEditable(false);
        batterylevel.setName("batterylevel");
        batterylevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batterylevelActionPerformed(evt);
            }
        });

        lblco2.setText("CO2 Level");
        lblbat.setText("Battery Level");
        Co2level.setEditable(false);
        Co2level.setName("Co2level");
        Co2level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Co2levelActionPerformed(evt);
            }
        });
        //setting up units in the labels
        lblcentri.setText("°C");
        lblsmoke.setText("");
        lblperc.setText("%");
        lblppm.setText("ppm");
        
        lblcentri.setFont(new Font("Courier New", Font.ITALIC, 14)); 
        lblcentri.setForeground(Color.BLUE); 
        lblsmoke.setFont(new Font("Courier New", Font.ITALIC, 14)); 
        lblsmoke.setForeground(Color.BLUE); 
        lblperc.setFont(new Font("Courier New", Font.ITALIC, 14)); 
        lblperc.setForeground(Color.BLUE); 
        lblppm.setFont(new Font("Courier New", Font.ITALIC, 14)); 
        lblppm.setForeground(Color.BLUE); 

        lbltem.setFont(new Font("Arial", Font.BOLD, 15)); 
        lbltem.setForeground(Color.MAGENTA); 
        lblsm.setFont(new Font("Arial", Font.BOLD, 15)); 
        lblsm.setForeground(Color.MAGENTA); 
        lblbat.setFont(new Font("Arial", Font.BOLD, 15)); 
        lblbat.setForeground(Color.MAGENTA); 
        lblco2.setFont(new Font("Arial", Font.BOLD, 15)); 
        lblco2.setForeground(Color.MAGENTA); 
        
        lblfloorNo.setFont(new Font("Arial", Font.BOLD, 19)); 
        lblfloorNo.setForeground(Color.PINK); 
        
        //set layout for child reading gui
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        
        
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
               
                .addComponent(floor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            
            .addGroup(layout.createSequentialGroup()
                    .addGap(130, 130, 130)
                   
                    .addComponent(lblfloorNo)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltem)
                    .addComponent(lblsm)
                    .addComponent(lblbat)
                    .addComponent(lblco2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(smoke, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblsmoke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(batterylevel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblppm))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(temperature, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblcentri, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Co2level, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblperc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
        		  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(floor)
                .addGroup(layout.createSequentialGroup()
                 .addGap(1, 1, 1)
                .addComponent(lblfloorNo)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)		
              
                	                		
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbltem)
                        .addGap(39, 39, 39)
                        .addComponent(lblsm, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(lblbat, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(lblco2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(smoke, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblsmoke)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(temperature, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblcentri)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Co2level, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblperc))
                        .addGap(30, 30,30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(batterylevel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblppm))))
                .addContainerGap(14, Short.MAX_VALUE))
    ));

        pack();
    }

    private void smokeActionPerformed(java.awt.event.ActionEvent evt) {       
    }

    private void temperatureActionPerformed(java.awt.event.ActionEvent evt) {       
    }

    private void batterylevelActionPerformed(java.awt.event.ActionEvent evt) {       
    }

    private void Co2levelActionPerformed(java.awt.event.ActionEvent evt) {    }


   
}
