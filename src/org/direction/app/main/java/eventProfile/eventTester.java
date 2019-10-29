package org.direction.app.main.java.eventProfile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.direction.app.main.java.studentProfile.StudentProfileController;

public class eventTester extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/org/direction/app/main/java/eventProfile/Events.fxml"));
        primaryStage.setTitle("Event");
        primaryStage.setScene(new Scene(root));//, 500, 350));
        primaryStage.initStyle(StageStyle.DECORATED);
        //resources.classes.Utilities.setStageIcon(primaryStage);
        primaryStage.show();
        //loader.load();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
