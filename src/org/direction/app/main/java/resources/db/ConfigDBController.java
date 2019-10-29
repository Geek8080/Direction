package org.direction.app.main.java.resources.db;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.direction.app.main.java.Main;
import org.direction.app.main.java.home.HomeController;
import org.direction.app.main.java.resources.*;
import org.h2.tools.RunScript;
import org.h2.tools.Server;

import java.io.FileReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.DriverManager;

public class ConfigDBController {

    public static boolean changeUserData = false;

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane ConfigDBRoot;

    @FXML
    private JFXTextField serverip;

    @FXML
    private JFXButton connect;

    @FXML
    private JFXButton startServer;

    //Listener methods follows here
    /*
    on connect check validity of server IP and set the ServerIP variable in Main to new value(Previously set to 127.0.0.1)
    on server start connect to the ip represented by Main ServerIP variable and update it to the private ip of the system
    close this window->launch home window->show connection details and set the ip fields to server and client
     */

    public void server(MouseEvent evt){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/home/Home.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Home");
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("Closing all connections...");
                    Main.dbServer.server.stop();
                }
            });
            stage.show();
            ((Stage)root.getScene().getWindow()).close();
            System.out.println("After closing scene...");
            Main.dbServer = new DatabaseHandler();
            HomeController controller = loader.getController();
            DatabaseHandler.ServerIP = getSystemIPAddress();
            controller.serverIP.setText(DatabaseHandler.ServerIP);
            DatabaseHandler.ClientIP = "127.0.0.1";
            controller.clientIP.setText(DatabaseHandler.ClientIP);

            new Thread(new DB()).start();

        }catch(Exception ex){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showTrayMessage("DB Server error","Couldn't launch main window.");
                }
            });
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(ex.getMessage());
            //javax.swing.JOptionPane.showMessageDialog(null, "It seems you are not authorised to use the application.");
            System.exit(125);
        }
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



    public class DB implements Runnable{
        public void run() {
            try {
                //server = Server.createWebServer("-webAllowOthers", "-webPort", "8082");
                //server.start();
                //This one is working but above code is better (fail-safe)
                DatabaseHandler.server = Server.createTcpServer("-tcp", "-tcpAllowOthers").start();
                System.out.println("Server Started Successfully...\nServer IP: " + DatabaseHandler.ServerIP);
                DatabaseHandler.ServerIP = "127.0.0.1";
                Class.forName("org.h2.Driver");
                try {
                    DatabaseHandler.conn = DriverManager.getConnection("jdbc:h2:tcp://" + DatabaseHandler.ServerIP + "/~/Documents/Direction/Data/test", "", "");
                } catch (Exception ex) {
                    System.out.println("Couldn't make connection...\n" + ex.getMessage());
                }
                DatabaseHandler.stmt = DatabaseHandler.conn.createStatement();
                if (!Main.CONF.DBExists) {
                    System.out.println(Main.CONF.DBExists);
                    FileReader reader = new FileReader("db.sql");
                    
                    RunScript.execute(DatabaseHandler.conn, new FileReader("db.sql"));
                    Main.CONF.DBExists = true;
                    System.out.println("Creating configurations... "+Main.CONF.DBExists);
                    FileHandler.deflate("config.properties",Main.CONF);
                    System.out.println("DB successfully created...");
                    DatabaseHandler.stmt.executeUpdate("INSERT INTO USER VALUES('" +
                            Main.USER.getUID() + "', '" +
                            Main.USER.getPost() + "', '" +
                            Main.USER.getName() + "', '" +
                            Main.USER.getPassword() + "', '" +
                            Main.USER.getContact() + "', '" +
                            Main.USER.getHometown() + "' " +
                            ")");
                    System.out.println("User data inserted into the database...");
                }
                if(changeUserData){
                    System.out.println("Changing user Data in DB");
                    DatabaseHandler.updateUsers();
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        AlertMaker.showTrayMessage("DB Server Started", "ConfigDBController: DBServer Successfully started.(Line 139)");
                    }
                });

            } catch (Exception ex) {
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        AlertMaker.showErrorMessage(ex);
                    }
                });
                
                System.out.println("Couldn't start Server Successfully..." + ex.getMessage());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        AlertMaker.showTrayMessage("DB Server error", "ConfigDBController: Couldn't start DB Server.(Line 146)");
                    }
                });
                
                ex.printStackTrace();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //javax.swing.JOptionPane.showMessageDialog(null, "It seems you are not authorised to use the application.");
                System.exit(1);
            }
        }
    }

    public void client(MouseEvent evt){
        try{
            Class.forName("org.h2.Driver");
            DatabaseHandler.ServerIP = serverip.getText().trim();
            DatabaseHandler.conn = DriverManager.getConnection("jdbc:h2:tcp://" + DatabaseHandler.ServerIP + "/~/Documents/Direction/Data/test", "", "");
            DatabaseHandler.stmt = DatabaseHandler.conn.createStatement();
            System.out.println("Successfully connected to the server at " + serverip);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showSimpleAlert("DB Server Connection", "ConfigDBController: Successfully connected to the DB Server.(Line 171)");
                }
            });
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showTrayMessage("DB Server Connection", "ConfigDBController: Successfully connected to the DB Server.(Line 171)");
                }
            });

            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/home/Home.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(parent));
                stage.setTitle("Home");
                stage.show();
                ((Stage)root.getScene().getWindow()).close();
                System.out.println("After closing scene...");
                //Main.dbServer = new DatabaseHandler();
                HomeController controller = loader.getController();
                controller.serverIP.setText(DatabaseHandler.ServerIP);
                DatabaseHandler.ClientIP = getSystemIPAddress();;
                controller.clientIP.setText(DatabaseHandler.ClientIP);
            }catch(Exception ex){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        AlertMaker.showTrayMessage("DB Server error","ConfigDBController: Couldn't launch main window.(Line 193)");
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
            if(changeUserData){
                System.out.println("Changing user Data in DB");
                DatabaseHandler.updateUsers();
            }

        }catch(Exception ex){

            System.out.println("Couldn't connect" + ex.getMessage());
        }
    }

    public void initialize(){
    }
}
