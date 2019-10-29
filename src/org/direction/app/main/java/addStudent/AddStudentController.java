package org.direction.app.main.java.addStudent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.java.accessibility.util.EventID;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.direction.app.main.java.Main;
import org.direction.app.main.java.resources.AlertMaker;
import org.direction.app.main.java.resources.DatabaseHandler;
import org.direction.app.main.java.resources.Student;
import org.direction.app.main.java.upcomingEvent.AddEventController;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddStudentController {

    public String EID = "";

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane addStudentRoot;


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
    private JFXButton add;

    @FXML
    private JFXButton addNext;

    @FXML
    private JFXButton cancel;

    //Populate User, Event and SID at launch
    //User and event should contain name and EID Respectively
    //on ADD operation add the data to database
    //on ADD Next operation the data will be added and the form will be cleared
    //with new SID and user and EID unchanged

    public void init_fields(){

        System.out.println(EID);
        ResultSet resultSet = null;
        try{
            resultSet = DatabaseHandler.stmt.executeQuery("SELECT * FROM Event WHERE Status='upcoming' AND EID='" + EID + "'");//Add code to retrieve data of only upcoming event
            System.out.println("Retrieved Data... " + resultSet.wasNull());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(resultSet.last()){
                System.out.println("Retrieved Non-Null Data from Event table... ");
                if(resultSet.getString(3).contains(Main.USER.getUID())){
                    user.setText(Main.USER.getUID() + ": " + Main.USER.getName());
                }
                else{
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            AlertMaker.showErrorMessage("User not Registered","You are not authorized to make entries... ");
                        }
                    });
                    ((Stage)root.getScene().getWindow()).close();
                    return;
                }
                event.setText(resultSet.getString(1));
                Student student = new Student();
                student.setEID(event.getText().trim());
                student.setUID(Main.USER.getUID());
                student.setSID();
                sid.setText(student.getSID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(MouseEvent evt){
        try{
            String Sid = sid.getText().trim();
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
            updateAttendance(Eid);
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

    private void updateAttendance(String eid) {
        try{
            ResultSet rs = DatabaseHandler.stmt.executeQuery("SELECT COUNT(SID) FROM Student WHERE EID='"+eid+"'");
            int attendance = 1;
            if(rs.first()) {
                attendance = rs.getInt(1);
            }
            DatabaseHandler.stmt.executeUpdate("UPDATE Event SET Attendance="+attendance+"WHERE EID='"+eid+"'");
            System.out.println("Attendance updated to " + attendance + "where EID was " + eid);
        }catch(Exception ex){
            System.out.println("Attendance:186");
            AlertMaker.showErrorMessage(ex);
        }
    }

    public void addNext(MouseEvent evt){
        try{
            String Sid = sid.getText().trim();
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
            name.setText("");
            cls.setText("");
            gender.selectToggle(male);
            age.setText("");
            marks.setText("");
            parentName.setText("");
            parentContact.setText("");
            address.setText("");
            contact.setText("");
            updateAttendance(Eid);
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    AlertMaker.showTrayMessage("New Student","Student added Successfully... ");
                }
            });
        }catch(Exception ex){
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    AlertMaker.showErrorMessage(ex);
                }
            });
        }
        init_fields();
    }

    public void cancel(MouseEvent evt){
        ((Stage)root.getScene().getWindow()).close();
    }
}
