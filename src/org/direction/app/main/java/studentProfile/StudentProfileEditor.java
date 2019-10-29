package org.direction.app.main.java.studentProfile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.direction.app.main.java.preloaders.Preloader;


//this class allows to update the student data
//At launch the fields will be pre-Assigned with given fields editable

public class StudentProfileEditor extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/studentProfile/StudentProfile.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Profile Update");
        primaryStage.setScene(new Scene(root));//, 500, 350));
        primaryStage.initStyle(StageStyle.DECORATED);
        //resources.classes.Utilities.setStageIcon(primaryStage);
        primaryStage.show();
        //loader.load();

        StudentProfileController controller = loader.getController();
        controller.marks.setEditable(true);
        controller.marks.setMouseTransparent(false);
        controller.address.setEditable(true);
        controller.age.setEditable(true);
        controller.age.setMouseTransparent(false);
        controller.cls.setEditable(true);
        controller.cls.setMouseTransparent(false);
        controller.contact.setEditable(true);
        controller.age.setMouseTransparent(false);
        controller.gender.setEditable(true);
        controller.gender.setMouseTransparent(false);
        controller.parentContact.setEditable(true);
        controller.parentContact.setMouseTransparent(false);
        controller.parentName.setEditable(true);
        controller.parentName.setMouseTransparent(false);
        controller.address.setEditable(true);
        controller.address.setMouseTransparent(false);
        controller.contact.setEditable(true);
        controller.contact.setMouseTransparent(false);
        controller.flag = true;

        //the flag will be handled by the mouse event onb the OKAY button
    }


    public static void main(String[] args) {
        launch(args);
    }

}
