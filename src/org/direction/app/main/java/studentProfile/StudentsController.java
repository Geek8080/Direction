package org.direction.app.main.java.studentProfile;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.direction.app.main.java.resources.AlertMaker;
import org.direction.app.main.java.resources.DatabaseHandler;

import java.sql.ResultSet;
import java.util.LinkedList;


public class StudentsController {

    ObservableList<StudentColumn> list = FXCollections.observableArrayList();

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane studentsRoot;

    @FXML
    public JFXButton action;

    @FXML
    public JFXButton close;

    @FXML
    protected TableView<StudentColumn> tableView;

    @FXML
    private TableColumn<StudentColumn, String> sidCol;

    @FXML
    private TableColumn<StudentColumn, String> nameCol;

    @FXML
    private TableColumn<StudentColumn, String> eventCol;

    @FXML
    private TableColumn<StudentColumn, String> userCol;

    @FXML
    private TableColumn<StudentColumn, String> genderCol;

    @FXML
    private TableColumn<StudentColumn, Integer> clsCol;

    @FXML
    private TableColumn<StudentColumn, Integer> marksCol;

    @FXML
    private TableColumn<StudentColumn, Integer> contactCol;

    public void initialize(){
        initTable();
        loadData();
    }

    private void initTable() {
        sidCol.setCellValueFactory(new PropertyValueFactory<>("SID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        eventCol.setCellValueFactory(new PropertyValueFactory<>("event"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("user"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clsCol.setCellValueFactory(new PropertyValueFactory<>("cls"));
        marksCol.setCellValueFactory(new PropertyValueFactory<>("marks"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tableView.setItems(list);
    }

    private void loadData() {
        list.clear();
        LinkedList<StudentColumn> students = DatabaseHandler.getStudentColumns();
        list.addAll(students);

    }

    public void launchProfile(MouseEvent evt){
        //get SID and launch studentProfile with the data
        StudentColumn student = tableView.getSelectionModel().getSelectedItem();
        try{
            ResultSet rs = DatabaseHandler.stmt.executeQuery("SELECT * FROM STUDENT WHERE SID='" + student.SID + "'");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/studentProfile/StudentProfile.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(parent));
                stage.setTitle("Student Profile");
                stage.show();
                StudentProfileController controller = loader.getController();
                try {
                    if(rs.first()) {
                        controller.name.setText(rs.getString("Name"));
                        controller.sidValue.setText(rs.getString("SID"));
                        controller.event.setText(rs.getString("EID"));// + ": " + DatabaseHandler.stmt.executeQuery("SELECT Date FROM EVENT WHERE EID='" + rs.getString("EID") + "'").getString(1));
                        controller.cls.setText("" + rs.getInt("Class"));
                        controller.age.setText("" + rs.getInt("Age"));
                        controller.user.setText(rs.getString("UID"));
                        controller.marks.setText(rs.getString("Marks"));
                        controller.parentContact.setText(rs.getString("ParentsContact"));
                        controller.parentName.setText(rs.getString("ParentsName"));
                        controller.address.setText(rs.getString("Address"));
                        if(rs.getString("Gender").equalsIgnoreCase("m")){
                            controller.gender.setText("Male");
                        }
                        else if(rs.getString("Gender").equalsIgnoreCase("f")){
                            controller.gender.setText("Female");
                        }
                        else{
                            controller.gender.setText("Others");
                        }
                        controller.contact.setText(rs.getString("Contact"));
                    }
                } catch (Exception e) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            AlertMaker.showErrorMessage(e);
                        }
                    });
                }
            }catch(Exception ex){
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        }catch(Exception ex){
            AlertMaker.showErrorMessage(ex);
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    AlertMaker.showTrayMessage("Getting Student Data","Couldn't Retrieve the required data from the database...");
                }
            });
        }
        System.out.println(student.name);//handle the NullPointerException thrown in case no items are selected
    }

    public void hide(MouseEvent evt){
        (((Node)evt.getSource()).getScene().getWindow()).hide();
    }

}
