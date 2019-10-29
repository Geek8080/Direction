package org.direction.app.main.java.upcomingEvent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Tester extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/org/direction/app/main/java/upcomingEvent/AddEvent.fxml"));
        primaryStage.setTitle("Add Event");
        primaryStage.setScene(new Scene(root));//, 500, 350));
        primaryStage.initStyle(StageStyle.DECORATED);
        //resources.classes.Utilities.setStageIcon(primaryStage);
        primaryStage.show();
    }


    public static void main(String []args){
        launch(args);
    }
}
