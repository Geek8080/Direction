package org.direction.app.main.java.upcomingEvent;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.direction.app.main.java.eventProfile.EventsController;
import org.direction.app.main.java.resources.AlertMaker;
import org.direction.app.main.java.resources.DatabaseHandler;
import org.direction.app.main.java.resources.Event;

import java.sql.SQLException;
import java.util.ArrayList;

//the table view of upcoming event will be launched and on selection
//editable form will appear with pre-assigned values
public class EditUpcomingEventController {
    public ObservableList<String> listAll = FXCollections.observableArrayList();
    public ObservableList<String> listSelected = FXCollections.observableArrayList();

    @FXML
    private JFXListView<String> options;

    @FXML
    private JFXListView<String> selection;

    @FXML
    public JFXTextField loc;

    @FXML
    public JFXDatePicker date;

    @FXML
    public JFXToggleButton completed;


    public static int attendance = 0;
    public static String eid = "";

    public void initialize(){
        options.setItems(listAll);
        selection.setItems(listSelected);
    }

    public void include(MouseEvent evt){
        if(options.getSelectionModel().getSelectedItem()!=null) {
            listSelected.add(options.getSelectionModel().getSelectedItem());
            listAll.remove(options.getSelectionModel().getSelectedItem());
        }
        options.getSelectionModel().clearSelection();
    }

    public void exclude(MouseEvent evt){
        if(selection.getSelectionModel().getSelectedItem()!=null) {
            listAll.add(selection.getSelectionModel().getSelectedItem());
            listSelected.remove(selection.getSelectionModel().getSelectedItem());
        }
        selection.getSelectionModel().clearSelection();
    }

    public void updateDB(MouseEvent evt){
        try{
            DatabaseHandler.stmt.executeUpdate("DELETE FROM Event WHERE EID='" + eid + "'");
            Event event = new Event();
            event.setDate(date.getValue().toString());
            event.setLocation(loc.getText());
            event.setStatus(completed.isSelected()?"completed":"upcoming");
            event.setEID();
            ArrayList<String> members = new ArrayList<>();
            members.addAll(listSelected);
            event.setMembers(members);
            System.out.println("EID: "+event.getEID()+" Date: "+event.getDate()+" No. of members: "+members.size());
            String mem = members.toString();
            System.out.println(mem);
            try {
                DatabaseHandler.stmt.executeUpdate("INSERT INTO EVENT VALUES('" +
                        event.getEID() + "', '" +
                        event.getLocation() + "', '" +
                        mem + "', '" +
                        event.getDate() + "', 0, " +
                        attendance + ", '" +
                        event.getStatus() + "'" +
                        ")");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        AlertMaker.showTrayMessage("Event Edit","Event Details Updated Successfully... ");
                    }
                });
                DatabaseHandler.stmt.executeUpdate("UPDATE Student SET EID='" + event.getEID() +"' WHERE EID='" + eid + "'");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        AlertMaker.showTrayMessage("Event Edit","Student Details Updated Successfully... ");
                    }
                });
                ((Stage)loc.getScene().getWindow()).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }catch(Exception ex){
            AlertMaker.showErrorMessage(ex);
        }
    }
}
