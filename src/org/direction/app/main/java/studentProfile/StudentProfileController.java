package org.direction.app.main.java.studentProfile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.direction.app.main.java.resources.AlertMaker;
import org.direction.app.main.java.resources.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

//The flag will store whether the data is to bve updated in database

public class StudentProfileController {

    public boolean flag = false;


    //Handler for the button will check the flag and update the data in database if required
    //true flag mean it has to be updated
    //Update using the SID




    @FXML
    public StackPane root;

    @FXML
    private AnchorPane studentProfileRoot;

    @FXML
    protected Text name;

    @FXML
    private Text sid;

    @FXML
    protected Text sidValue;

    @FXML
    protected JFXTextField cls;

    @FXML
    protected JFXTextField age;

    @FXML
    protected JFXTextField contact;

    @FXML
    protected JFXTextField gender;

    @FXML
    protected JFXTextField parentContact;

    @FXML
    protected JFXTextField event;

    @FXML
    protected JFXTextField user;

    @FXML
    protected JFXTextField marks;

    @FXML
    protected JFXTextField parentName;

    @FXML
    protected JFXTextArea address;

    @FXML
    protected JFXButton top;

    @FXML
    protected JFXButton middle;

    @FXML
    protected JFXButton bottom;

    //On launch initialise using the separate thread started from the class launching the scene

    public void okay(MouseEvent evt){
        ((Stage)event.getScene().getWindow()).close();
    }
}
