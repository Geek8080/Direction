package org.direction.app.main.java.signup;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.direction.app.main.java.Main;
import org.direction.app.main.java.resources.AlertMaker;
import org.direction.app.main.java.resources.DatabaseHandler;
import org.direction.app.main.java.resources.FileHandler;
import org.direction.app.main.java.resources.User;
import org.direction.app.main.java.resources.db.ConfigDBController;

import java.sql.SQLException;

public class SignupController {

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane signupRoot;

    @FXML
    public Text uid;

    @FXML
    public Text post;

    @FXML
    public JFXTextField uname;

    @FXML
    public JFXPasswordField password;

    @FXML
    public JFXTextField mobile;

    @FXML
    public JFXTextField hometown;

    @FXML
    public JFXButton signup;

    @FXML
    private JFXButton cancel;

    //Listener method follows here
    /*
    on signup save the new user object and update the database for the user details
    on cancel terminate the application
    launch the database connection window
     */

    public void close(MouseEvent evt){
        ((Stage)root.getScene().getWindow()).close();
    }

    public void signup(MouseEvent evt){
        Main.USER.setName(uname.getText().trim());
        Main.USER.setPassword(password.getText().trim());
        Main.USER.setHometown(hometown.getText().trim());
        Main.USER.setContact(mobile.getText().trim());
        ConfigDBController.changeUserData = true;
        if(FileHandler.deflate("user.ser",Main.USER)){
            System.out.println("Successfully saved new Object");
        }else{
            System.out.println("Couldn't save new Object...");
        }
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/org/direction/app/main/java/resources/db/ConfigDB.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("DB Configuration");
            stage.show();
            stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    if(DatabaseHandler.conn!=null){
                        try {
                            DatabaseHandler.conn.close();
                        } catch (SQLException e) {
                            AlertMaker.showErrorMessage(e);
                        }
                    }
                }
            });
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        ((Stage)root.getScene().getWindow()).close();
    }

}
