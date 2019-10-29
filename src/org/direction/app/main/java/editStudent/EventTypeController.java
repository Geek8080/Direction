package org.direction.app.main.java.editStudent;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.direction.app.main.java.eventProfile.EventsController;
import org.direction.app.main.java.resources.AlertMaker;

public class EventTypeController {

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane rootEventType;

    @FXML
    private JFXButton upcoming;

    @FXML
    private JFXButton completed;

    public void upcoming(MouseEvent evt){
        launchEvents("upcoming");
    }

    public void complete(MouseEvent evt){
        launchEvents("completed");
    }

    public void launchEvents(String str){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/editStudent/EventList.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Events");
            stage.show();
            EventListController controller = loader.getController();
            controller.eventType = str;
            controller.init();
            ((Stage)root.getScene().getWindow()).close();
        }catch(Exception ex){
            AlertMaker.showErrorMessage(ex);
        }
    }
}
