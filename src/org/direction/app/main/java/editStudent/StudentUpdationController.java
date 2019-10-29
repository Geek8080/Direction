package org.direction.app.main.java.editStudent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.direction.app.main.java.resources.AlertMaker;
import org.direction.app.main.java.resources.DatabaseHandler;
import org.direction.app.main.java.resources.Event;

import java.sql.ResultSet;

public class StudentUpdationController {

    public static String SID;

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane updateStudentRoot;

    @FXML
    private VBox rootForm;

    @FXML
    private JFXTextField user;

    @FXML
    private JFXTextField event;

    @FXML
    private JFXTextField sid;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField cls;

    @FXML
    private JFXTextField age;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXRadioButton others;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXTextField marks;

    @FXML
    private JFXTextField contact;

    @FXML
    private JFXTextField parentName;

    @FXML
    private JFXTextField parentContact;

    @FXML
    private JFXTextArea address;

    @FXML
    private JFXButton update;

    public void init() {
        try{
            ResultSet resultSet = DatabaseHandler.stmt.executeQuery("SELECT * FROM Student WHERE SID='" + SID + "'");
            if(resultSet.first()) {
                user.setText(resultSet.getString("UID"));
                event.setText(resultSet.getString("EID"));
                sid.setText(SID);
                name.setText(resultSet.getString("name"));
                age.setText(resultSet.getString("age"));
                cls.setText(resultSet.getString("class"));
                marks.setText(resultSet.getString("marks"));
                parentName.setText(resultSet.getString("ParentsName"));
                parentContact.setText(resultSet.getString("ParentsContact"));
                contact.setText(resultSet.getString("contact"));
                switch (resultSet.getString("gender")) {
                    case "m":
                        male.setSelected(true);
                        break;
                    case "f":
                        female.setSelected(true);
                        break;
                    default:
                        others.setSelected(true);
                }
                address.setText(resultSet.getString("address"));
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        AlertMaker.showTrayMessage("Student Data Update","Student data has been successfully updated.");
                    }
                });
            }
        }catch(Exception ex){
            AlertMaker.showErrorMessage(ex);
        }
    }

    public void cancel(MouseEvent evt){
        ((Stage)root.getScene().getWindow()).close();
    }

    public void update(MouseEvent evt){
        try{
            String Sid = sid.getText().trim();

            DatabaseHandler.stmt.executeUpdate("DELETE FROM Student WHERE SID='" + Sid + "'");
            String Name = name.getText().trim();
            String Eid = event.getText().trim();
            String Uid = user.getText().trim();
            int Marks = Integer.parseInt(marks.getText().trim());
            char Gender = ((JFXRadioButton)gender.getSelectedToggle()).getText().trim().toLowerCase().charAt(0);
            int Cls = Integer.parseInt(cls.getText().trim());
            int Age = Integer.parseInt(age.getText().trim());
            String Address = address.getText().trim();
            String Contact = contact.getText().trim();
            String ParName = parentName.getText().trim();
            String ParContact = parentContact.getText().trim();
            DatabaseHandler.stmt.executeUpdate("INSERT INTO Student(SID, Name, EID, UID, Marks, Gender, Class, Age, Address, Contact, ParentsName, ParentsContact) VALUES('" +
                    Sid + "', '" +
                    Name + "', '" +
                    Eid + "', '" +
                    Uid + "', " +
                    Marks + ", '" +
                    Gender + "', " +
                    Cls + ", " +
                    Age + ", '" +
                    Address + "', '" +
                    Contact + "', '" +
                    ParName + "', '" +
                    ParContact + "'" +
                    ")");
            System.out.println("Data Inserted Successfully..." + Name);
            ((Stage)root.getScene().getWindow()).close();
        }catch(Exception ex){
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    AlertMaker.showErrorMessage(ex);
                }
            });
        }
    }

}
