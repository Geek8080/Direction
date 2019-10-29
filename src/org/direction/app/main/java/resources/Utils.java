package org.direction.app.main.java.resources;

import org.direction.app.main.java.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static String Date(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

    public static String getYear(){
        DateFormat df = new SimpleDateFormat("yyyy");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

    public static class IPAddress implements Runnable {

        public void run() {
            getAddress();
        }

        public void getAddress() {
            String publicIPAddress, systemIPAddress;
            //publicIPAddress = getPublicIPAddress();
            systemIPAddress = getSystemIPAddress();
            DatabaseHandler.ClientIP = systemIPAddress;
            javax.swing.JOptionPane.showMessageDialog(null, "System IP: " + systemIPAddress + "\nPublic IP : " + "\n");//publicIPAddress + "\n");
        }

        public String getSystemIPAddress() {
            String systemIPAddress = "Unable to recognise... ";
            try {
                InetAddress localhost = InetAddress.getLocalHost();
                systemIPAddress = localhost.getHostAddress().trim();
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            }
            return systemIPAddress;
        }

        public String getPublicIPAddress() {
            String systemIPAddress = "Cannot read from the server... ";
            try {
                URL urlName = new URL("http://bot.whatismyipaddress.com");
                BufferedReader sc = new BufferedReader(new InputStreamReader(urlName.openStream()));
                systemIPAddress = sc.readLine().trim();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return systemIPAddress;
        }
    }
}
