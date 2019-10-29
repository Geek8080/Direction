package org.direction.app.main.java.eventProfile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.direction.app.main.java.studentProfile.StudentProfileController;

//this class will allow to edit certain fields and save the data

public class EventProfileEditor extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/eventProfile/EventProfile.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Event Update");
        primaryStage.setScene(new Scene(root));//, 500, 350));
        primaryStage.initStyle(StageStyle.DECORATED);
        //resources.classes.Utilities.setStageIcon(primaryStage);
        primaryStage.show();
        //loader.load();

        EventProfileController controller = loader.getController();
        controller.attendance.setMouseTransparent(false);
        controller.attendance.setEditable(true);
        controller.budget.setMouseTransparent(false);
        controller.budget.setEditable(true);
    }

    public static void main(String []args){
        launch(args);
    }
}
