/*
 */
package com.sliit.monitorGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.sliit.monitorControllers.LoginController;
import com.sliit.monitorControllers.MonitorApplication;

/**
*This class contains the login GUI components 
* @author virajw
*/
public class UserLoginMonitorGUI extends JFrame {
	
	    private JButton btnLogin;
	    private JLabel lblImage;
	    private JPasswordField password;
	    private JTextField username;
        private MonitorApplication monitorApp;
       
       public UserLoginMonitorGUI() {
        initComponents();
        // Relative to center
        this.setLocationRelativeTo(null);
        //initialize the application
        monitorApp = new MonitorApplication();
    }

  
    @SuppressWarnings("unchecked")
    private void initComponents() {

        username = new JTextField();
        btnLogin = new JButton();
        password = new JPasswordField();
        lblImage = new JLabel();

        //setting up dimensions of logging screen
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setForeground(new Color(204, 0, 204));
        setLocationByPlatform(true);
        setMaximumSize(new Dimension(700, 400));
        setMinimumSize(new Dimension(700, 400));
        setPreferredSize(new Dimension(700, 400));
        setResizable(false);
        setName("Login");
        setTitle("Login To Fire Monitor System");
        getContentPane().setLayout(null);
        getContentPane().add(username);
        username.setBounds(460, 130, 190, 26);

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
					try {
						btnLoginActionPerformed(evt);
					} catch (HeadlessException | IOException e) {
						System.out.println(e.getMessage());
					}
				 
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBackground(new Color(59, 89, 182));
        btnLogin.setBounds(500, 200, 110, 40);
        getContentPane().add(password);
        password.setBounds(460, 160, 190, 26);
        //background image
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sliit/images/fire1.jpg"))); 
        getContentPane().add(lblImage);
        lblImage.setBounds(0, 0, 700, 400);

        pack();
    }
    //action of logging button
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) throws HeadlessException, IOException {
        if(LoginController.authenticateUser(username.getText(),password.getText())){
            monitorApp.intializeMonitor();
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(
                                    null,
                                    "Authentication Failed! Please check your username and password",
                                    "Login Failed",
                                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserLoginMonitorGUI().setVisible(true);
            }
        });
    }


  

}
