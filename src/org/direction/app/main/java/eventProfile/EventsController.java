package org.direction.app.main.java.eventProfile;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;
import org.direction.app.main.java.Main;
import org.direction.app.main.java.addStudent.AddStudentController;
import org.direction.app.main.java.resources.AlertMaker;
import org.direction.app.main.java.resources.DatabaseHandler;
import org.direction.app.main.java.resources.Student;
import org.direction.app.main.java.studentProfile.StudentProfileController;
import org.direction.app.main.java.upcomingEvent.EditUpcomingEventController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventsController {

    public int EDIT = 0;
    public int SHOW_DETAILS = 1;
    public int ADD_STUDENT = 2;
    public int action;
    public String status = "upcoming";

    ObservableList<EventColumn> list = FXCollections.observableArrayList();

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane eventsRoot;

    @FXML
    private TableView<EventColumn> tableView;

    @FXML
    private TableColumn<EventColumn, String> eidCol;

    @FXML
    private TableColumn<EventColumn, String> locationCol;

    @FXML
    private TableColumn<EventColumn, String> dateCol;

    @FXML
    private TableColumn<EventColumn, Integer> budgetCol;

    @FXML
    private TableColumn<EventColumn, Integer> attendanceCol;

    public void initialize(String status){
        this.status = status;
        initTable();
        initData();
    }

    private void initData() {
        list.addAll(DatabaseHandler.getEventColumns(status));
    }

    private void initTable() {
        eidCol.setCellValueFactory(new PropertyValueFactory<>("EID"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        budgetCol.setCellValueFactory(new PropertyValueFactory<>("budget"));
        attendanceCol.setCellValueFactory(new PropertyValueFactory<>("attendance"));
        tableView.setItems(list);
    }

    public void clicked(ActionEvent evt){
        EventColumn eve = tableView.getSelectionModel().getSelectedItem();
        if(eve == null){
            return;
        }

        if(action == EDIT){
            if(status.equalsIgnoreCase("upcoming")) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/upcomingEvent/EditUpcomingEvent.fxml"));
                    Parent parent = loader.load();
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setScene(new Scene(parent));
                    stage.setTitle("Edit Upcoming Event");
                    stage.show();
                    /*stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
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
                    });
                    */
                    stage.setOnHiding(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
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
                    });
                    EditUpcomingEventController controller = loader.getController();
                    controller.listAll.addAll(DatabaseHandler.getUserColumns());
                    ResultSet rs = DatabaseHandler.stmt.executeQuery("SELECT * FROM EVENT WHERE EID='" + eve.EID + "'");
                    try {
                        if (rs.first()) {
                            controller.eid = eve.getEID();
                            controller.loc.setText(rs.getString("Location"));
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            System.out.println(rs.getString("Date"));
                            controller.date.setValue(LocalDate.parse(rs.getString("Date"), formatter));
                            String includedUsers = rs.getString("Members");
                            includedUsers = includedUsers.substring(1, includedUsers.length() - 1);
                            System.out.println(includedUsers);
                            String users[] = includedUsers.split(",");
                            for (String user : users) {
                                controller.listSelected.add(user.trim());
                                controller.listAll.remove(user.trim());
                            }
                            controller.attendance = rs.getInt("Attendance");
                        }
                    } catch (Exception ex) {
                        AlertMaker.showErrorMessage(ex);
                    }
                } catch (Exception ex) {
                    AlertMaker.showErrorMessage(ex);
                }
            }else{

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/eventProfile/EventProfile.fxml"));
                    Parent parent = loader.load();
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setScene(new Scene(parent));
                    stage.setTitle("Edit Event");
                    stage.show();
                    /*stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            System.out.println("Window Closed.. ");
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
                    });

                     */
                    stage.setOnHiding(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
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
                    });
                    EventProfileController controller = loader.getController();
                    ResultSet rs = DatabaseHandler.stmt.executeQuery("SELECT * FROM EVENT WHERE EID='" + eve.EID + "'");
                    try {
                        if (rs.first()) {
                            controller.eid.setText(eve.getEID());
                            controller.loc.setText(rs.getString("Location"));
                            controller.date.setText(rs.getString("Date"));
                            controller.budget.setText(rs.getString("Budget"));
                            controller.budget.setEditable(true);
                            controller.budget.setMouseTransparent(false);
                            controller.budget.setFocusTraversable(true);
                            controller.attendance.setText(rs.getString("Attendance"));
                            String includedUsers = rs.getString("Members");
                            includedUsers = includedUsers.substring(1, includedUsers.length() - 1);
                            System.out.println(includedUsers);
                            String users[] = includedUsers.split(",");
                            for (String user : users) {
                                controller.list.add(user.trim());
                            }
                        }
                    } catch (Exception ex) {
                        AlertMaker.showErrorMessage(ex);
                    }
                } catch (Exception ex) {
                    AlertMaker.showErrorMessage(ex);
                }
            }
        }
        else if(action == SHOW_DETAILS){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/eventProfile/EventProfile.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(parent));
                stage.setTitle("Event");
                stage.show();
                stage.setOnHiding(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
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
                });
                EventProfileController controller = loader.getController();
                ResultSet rs = DatabaseHandler.stmt.executeQuery("SELECT * FROM EVENT WHERE EID='" + eve.EID + "'");
                try {
                    if (rs.first()) {
                        controller.eid.setText(eve.getEID());
                        controller.loc.setText(rs.getString("Location"));
                        controller.date.setText(rs.getString("Date"));
                        controller.budget.setText(rs.getString("Budget"));
                        controller.attendance.setText(rs.getString("Attendance"));
                        String includedUsers = rs.getString("Members");
                        includedUsers = includedUsers.substring(1, includedUsers.length() - 1);
                        System.out.println(includedUsers);
                        String users[] = includedUsers.split(",");
                        for (String user : users) {
                            controller.list.add(user.trim());
                        }
                    }
                } catch (Exception ex) {
                    AlertMaker.showErrorMessage(ex);
                }
            } catch (Exception ex) {
                AlertMaker.showErrorMessage(ex);
            }
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/addStudent/AddStudent.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(parent));
                stage.setTitle("Add Student");
                stage.show();
                AddStudentController controller = loader.getController();
                controller.EID = eve.getEID();
                controller.init_fields();
                stage.setOnHiding(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/eventProfile/Events.fxml"));
                            Parent parent = loader.load();
                            Stage stage = new Stage(StageStyle.DECORATED);
                            stage.setScene(new Scene(parent));
                            stage.setTitle("Events");
                            stage.show();
                            EventsController controller = loader.getController();
                            controller.initialize("upcoming");
                            controller.action = controller.ADD_STUDENT;
                        } catch (Exception ex) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    AlertMaker.showErrorMessage(ex);
                                }
                            });
                        }
                    }
                });
            }
            catch(Exception ex){
                AlertMaker.showErrorMessage(ex);
            }
        }
        ((Stage)tableView.getScene().getWindow()).close();
    }

    public void close(MouseEvent event){
        ((Stage)tableView.getScene().getWindow()).close();
    }

}
