package org.direction.app.main.java.home;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.direction.app.main.java.Main;
import org.direction.app.main.java.addStudent.AddStudentController;
import org.direction.app.main.java.editStudent.StudentsListController;
import org.direction.app.main.java.eventProfile.EventsController;
import org.direction.app.main.java.resources.AlertMaker;
import org.direction.app.main.java.resources.DatabaseHandler;
import org.direction.app.main.java.signup.SignupController;


public class HomeController {

    @FXML
    private StackPane root;

    @FXML
    private ScrollPane rootScroll;

    @FXML
    private AnchorPane homeRoot;


    //Pane Declaration for side-pane
    @FXML
    private Pane welcomePane;

    @FXML
    private Text welcome;

    @FXML
    private Pane buttonPane;

    @FXML
    private JFXButton students;

    @FXML
    private Label studentsLabel;
    private static boolean studentsLabelFlag = true;

    @FXML
    private JFXButton events;

    @FXML
    private Label eventsLabel;
    private static boolean eventsLabelFlag = false;

    @FXML
    private JFXButton updateRecords;

    @FXML
    private Label updateRecordsLabel;
    private static boolean updateRecordsFlag = false;

    @FXML
    private Pane footer;

    @FXML
    private Text serverIPLabel;

    @FXML
    private Text clientIPLabel;

    @FXML
    public Text serverIP;

    @FXML
    public Text clientIP;
    //side pane complete

    //Main panes

//------------------------------------------------------------------------------------------//

    @FXML
    private Pane studentPane;


    //Controls in students pane
    @FXML
    private Text studentTitle;

    @FXML
    private JFXButton addStudent;

    @FXML
    private JFXButton studentDetails;
    //Student pane completed

//------------------------------------------------------------------------------------------//

    @FXML
    private Pane eventPane;


    //Controls in events pane
    @FXML
    private Text eventTitle;

    @FXML
    private JFXButton addEvent;

    @FXML
    private JFXButton pastEvent;
    //events pane completed

//------------------------------------------------------------------------------------------//

    @FXML
    private Pane updateRecordPane;


    //Controls in update records pane
    @FXML
    private Text updateRecordTitle;

    @FXML
    private JFXButton updateEventRecord;

    @FXML
    private JFXButton updateUpcomingEvent;

    @FXML
    private JFXButton updateStudentDetail;

    @FXML
    private JFXButton updatePersonalDetail;
    //update records pane completed



    //Listener Method Follows

    public void showStudentPane(MouseEvent evt){
        studentPane.setVisible(true);
        eventPane.setVisible(false);
        updateRecordPane.setVisible(false);
    }

    public void showEventsPane(MouseEvent evt){
        studentPane.setVisible(false);
        eventPane.setVisible(true);
        updateRecordPane.setVisible(false);
    }

    public void showUpdatePane(MouseEvent evt){
        studentPane.setVisible(false);
        eventPane.setVisible(false);
        updateRecordPane.setVisible(true);
    }

    //Student Pane controls
    public void launchAddStudent(MouseEvent evt){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/eventProfile/Events.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Upcoming Events");
            stage.show();
            EventsController controller = loader.getController();
            controller.initialize("upcoming");
            controller.action = controller.ADD_STUDENT;
        }catch(Exception ex){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showErrorMessage(ex);
                }
            });
        }
        /*try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/addStudent/AddStudent.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Add Student");
            stage.show();
        }catch(Exception ex){
            System.out.println("Error while launching the window");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showErrorMessage(ex);
                }
            });
        }
         */
    }

    public void launchStudentDetails(MouseEvent evt){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/studentProfile/Students.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Students");
            stage.show();
        }catch(Exception ex){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showErrorMessage(ex);
                }
            });
        }
    }



    //Events Pane controls
    public void launchAddUpcomingEvent(MouseEvent evt){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/upcomingEvent/AddEvent.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Add Event");
            stage.show();
            //AddStudentController controller = loader.getController();
        }catch(Exception ex){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showErrorMessage(ex);
                }
            });
        }
    }

    public void launchUpcomingEvent(MouseEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/eventProfile/Events.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Upcoming Events");
            stage.show();
            EventsController controller = loader.getController();
            controller.initialize("upcoming");
            controller.action = controller.EDIT;
        }catch(Exception ex){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showErrorMessage(ex);
                }
            });
        }

    }

    public void launchEvent(MouseEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/eventProfile/Events.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Events");
            stage.show();
            EventsController controller = loader.getController();
            controller.initialize("completed");
            controller.action = controller.EDIT;
        }catch(Exception ex){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showErrorMessage(ex);
                }
            });
        }
    }

    public void launchPastEvents(MouseEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/eventProfile/Events.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Events");
            stage.show();
            EventsController controller = loader.getController();
            controller.initialize("completed");
            controller.action = controller.SHOW_DETAILS;
        }catch(Exception ex){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMaker.showErrorMessage(ex);
                }
            });
        }
    }

    public void launchUpdateStudent(MouseEvent evt){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/editStudent/EventType.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Select Event Type");
            stage.show();
        }catch(Exception ex){
            AlertMaker.showErrorMessage(ex);
        }
    }

    public void launchUpdatePersonalDetails(MouseEvent evt){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/signup/Signup.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Events");
            stage.show();
            SignupController controller = loader.getController();
            controller.signup.setText("Save");
            controller.uid.setText(Main.USER.getUID());
            controller.post.setText(Main.USER.getPost());
            controller.uname.setText(Main.USER.getName());
            controller.password.setText(Main.USER.getPassword());
            controller.mobile.setText(Main.USER.getContact());
            controller.hometown.setText(Main.USER.getHometown());
            ((Stage)root.getScene().getWindow()).close();
        }catch(Exception ex){
            AlertMaker.showErrorMessage(ex);
        }
    }
}
