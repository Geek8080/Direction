package org.direction.app.main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.direction.app.main.java.preloaders.Preloader;
import org.direction.app.main.java.resources.Configuration;
import org.direction.app.main.java.resources.DatabaseHandler;
import org.direction.app.main.java.resources.User;

public class Main extends Application {

    public static DatabaseHandler dbServer;
    public static Configuration CONF;
    public static User USER;
    public static final String ImageLoc = "/org/direction/app/main/resources/load.gif";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/org/direction/app/main/java/login/Login.fxml"));
        primaryStage.setTitle("LogIn");
        primaryStage.setScene(new Scene(root));//, 500, 350));
        primaryStage.initStyle(StageStyle.DECORATED);
        //resources.classes.Utilities.setStageIcon(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        Runnable pl = new Preloader();
        Thread thread = new Thread(pl);
        thread.start();
        launch(args);
    }
}
