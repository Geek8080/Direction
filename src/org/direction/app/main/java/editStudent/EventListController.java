package org.direction.app.main.java.editStudent;

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
import org.direction.app.main.java.eventProfile.EventColumn;
import org.direction.app.main.java.resources.AlertMaker;
import org.direction.app.main.java.resources.DatabaseHandler;

public class EventListController {

    ObservableList<EventColumn> list = FXCollections.observableArrayList();
    public String eventType = "upcoming";

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane eventListRoot;

    @FXML
    private TableView<EventColumn> tableView;

    @FXML
    private TableColumn<EventColumn, String> eidCol;

    @FXML
    private TableColumn<EventColumn, String> locationCol;

    @FXML
    private TableColumn<EventColumn, String> dateCol;

    public void init(){
        initTable();
        initData();
    }

    private void initData() {
        list.addAll(DatabaseHandler.getEventColumns(eventType));
    }

    private void initTable() {
        eidCol.setCellValueFactory(new PropertyValueFactory<>("EID"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableView.setItems(list);
    }

    public void all(MouseEvent evt){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/editStudent/StudentsList.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Students");
            stage.show();
            StudentsListController controller = loader.getController();
            controller.init();
            ((Stage)root.getScene().getWindow()).close();
        }catch(Exception ex){
            AlertMaker.showErrorMessage(ex);
        }
    }

    public void selected(MouseEvent evt){
        String eid = tableView.getSelectionModel().getSelectedItem().getEID();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/editStudent/StudentsList.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Students");
            stage.show();
            StudentsListController controller = loader.getController();
            controller.eid = eid;
            controller.init();
            ((Stage)root.getScene().getWindow()).close();
        }catch(Exception ex){
            AlertMaker.showErrorMessage(ex);
        }
    }

}
