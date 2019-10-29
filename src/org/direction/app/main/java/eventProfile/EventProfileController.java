package org.direction.app.main.java.eventProfile;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.direction.app.main.java.resources.AlertMaker;
import org.direction.app.main.java.resources.DatabaseHandler;

import java.util.HashSet;

public class EventProfileController {

    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane eventRoot;

    @FXML
    public JFXTextField loc;

    @FXML
    public JFXTextField date;

    @FXML
    public JFXTextField attendance;

    @FXML
    public JFXTextField budget;

    @FXML
    public ListView<String> listMem;

    @FXML
    public Text eid;

    public void initialize(){
        listMem.setItems(list);
    }

    public void okay(MouseEvent event){
        try{
            DatabaseHandler.stmt.executeUpdate("UPDATE Event SET Budget="+budget.getText()+"WHERE EID='" + eid.getText() + "'");
        }catch(Exception ex){
            AlertMaker.showErrorMessage(ex);
        }
        ((Stage)loc.getScene().getWindow()).close();
    }
}
