package org.direction.app.test.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.direction.app.main.java.studentProfile.StudentProfileController;

public class StudentTableTester extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/studentProfile/Students.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("PStudents Details");
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
