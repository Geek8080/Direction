package org.direction.app.main.java.preloaders;


import javafx.application.Platform;
import org.direction.app.main.java.Main;
import org.direction.app.main.java.resources.AlertMaker;
import org.direction.app.main.java.resources.Configuration;
import org.direction.app.main.java.resources.FileHandler;
import org.direction.app.main.java.resources.User;

public class Preloader implements Runnable {
    public void run(){
        //Check if the user file and configurations file exists
        if(!FileHandler.fileExists("user.ser")||!FileHandler.fileExists("config.properties")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showTrayMessage("file error","It seems you are not authorised to use the application.");
                }
            });
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //javax.swing.JOptionPane.showMessageDialog(null, "It seems you are not authorised to use the application.");
            System.exit(1);
        }

        //Initialise Static variables to be used by other parts of the application
        Main.USER = (User) FileHandler.inflate("user.ser");
        Main.CONF = (Configuration) FileHandler.inflate("config.properties");
        System.out.println(Main.USER.getUID());

        System.out.println("Preloader:Instantiated User and Config Objects.(Line 35)");
        System.out.println("DB Exists: " + Main.CONF.DBExists);

    }
}
