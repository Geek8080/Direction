package org.direction.app.main.java.upcomingEvent;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.direction.app.main.java.resources.AlertMaker;
import org.direction.app.main.java.resources.DatabaseHandler;
import org.direction.app.main.java.resources.Event;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddEventController {
    ObservableList<String> listAll = FXCollections.observableArrayList();
    ObservableList<String> listSelected = FXCollections.observableArrayList();

    @FXML
    private JFXListView<String> options;

    @FXML
    private JFXListView<String> selection;

    @FXML
    private JFXTextField loc;

    @FXML
    private JFXDatePicker date;


    public void initialize(){
        options.setItems(listAll);
        selection.setItems(listSelected);
        listAll.addAll(DatabaseHandler.getUserColumns());
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


    public void scheduleEvent(MouseEvent evt){
        Event event = new Event();
        event.setDate(date.getValue().toString());
        event.setLocation(loc.getText());
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
                    event.getDate() + "', 0, 0, '" +
                    "upcoming'" +
                    ")");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showTrayMessage("New Event","Event Scheduled Successfully... ");
                }
            });
            ((Stage)loc.getScene().getWindow()).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
