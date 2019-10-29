package org.direction.app.main.java.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.direction.app.main.java.Main;
import org.direction.app.main.java.signup.SignupController;

import java.io.IOException;


public class LoginController {

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane loginRoot;

    @FXML
    private JFXTextField uname;

    @FXML
    private JFXPasswordField password;

    @FXML
    private Hyperlink signupLink;

    @FXML
    private JFXButton login;

    //listener methods follow here

    /*
    on login get username and password and check
    on failed login change the color of fields to red
    on hyperlink click redirect to signup
    launch the database connection window
     */

    public void loginAction(MouseEvent evt) throws IOException {
        if(checkCredential(uname.getText().trim(), password.getText().trim())){
            Parent parent = FXMLLoader.load(getClass().getResource("/org/direction/app/main/java/resources/db/ConfigDB.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Sign Up");
            stage.show();
            ((Stage)uname.getScene().getWindow()).close();
        }else{
            System.out.println("Wrong name or password buddy...");
            uname.setStyle("-fx-text-fill: #FF0000;");
            password.setStyle("-fx-text-fill: #FF0000;");
        }
    }

    private boolean checkCredential(String name, String pass) {
        System.out.println(Main.USER.getName()+Main.USER.getPassword());
        if(name.equalsIgnoreCase(Main.USER.getName())&&pass.equals(Main.USER.getPassword()))
            return true;
        return false;
    }

    public void launchSignup(MouseEvent evt){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/direction/app/main/java/signup/Signup.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle("Sign Up");
            stage.show();
            ((Stage)uname.getScene().getWindow()).close();
            SignupController controller = loader.getController();
            controller.post.setText(Main.USER.getPost());
            controller.uid.setText(Main.USER.getUID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
