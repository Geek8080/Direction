package org.direction.app.main.java.editStudent;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import org.direction.app.main.java.studentProfile.StudentColumn;
import org.direction.app.main.java.studentProfile.StudentProfileController;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.util.LinkedList;

public class StudentsListController {

    ObservableList<StudentColumn> list = FXCollections.observableArrayList();
    public static String eid = null;

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane studentsListRoot;

    @FXML
    private JFXButton action;

    @FXML
    private JFXButton close;

    @FXML
    private TableView<StudentColumn> tableView;

    @FXML
    private TableColumn<StudentColumn, String> sidCol;

    @FXML
    private TableColumn<StudentColumn, String> nameCol;

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

    public void init(){
        initTable();
        initData();
    }

    private void initTable() {
        sidCol.setCellValueFactory(new PropertyValueFactory<>("SID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("user"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clsCol.setCellValueFactory(new PropertyValueFactory<>("cls"));
        marksCol.setCellValueFactory(new PropertyValueFactory<>("marks"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tableView.setItems(list);
    }

    private void initData() {
        list.clear();
        LinkedList<StudentColumn> students = new LinkedList<>();
        if(eid==null){
            students = DatabaseHandler.getStudentColumns();
        }else {
            students = DatabaseHandler.getStudentColumns(eid);
        }
        list.addAll(students);
    }

    public void edit(MouseEvent evt){
        String sid = tableView.getSelectionModel().getSelectedItem().getSID();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/editStudent/StudentUpdation.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Update Student");
            stage.show();
            StudentUpdationController controller = loader.getController();
            controller.SID = sid;
            controller.init();
            ((Stage)root.getScene().getWindow()).close();
        }catch(Exception ex){
            AlertMaker.showErrorMessage(ex);
        }
    }

    public void close(MouseEvent evt){
        ((Stage)root.getScene().getWindow()).close();
    }

}
