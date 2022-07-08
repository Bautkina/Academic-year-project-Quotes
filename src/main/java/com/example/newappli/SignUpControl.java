package com.example.newappli;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpControl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button but_back;

    @FXML
    private URL location;

    @FXML
    private TextField but_login;

    @FXML
    private TextField but_name;

    @FXML
    private TextField but_pass;

    @FXML
    private Button but_sign_in;

    @FXML
    void initialize() {

        but_sign_in.setOnAction((actionEvent -> {
            signUpNewUser();
        }));

        but_back.setOnAction((actionEvent -> {
            but_back.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/newappli/hello-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка");
            }
            Parent p = loader.getRoot();
            Stage s = new Stage();
            s.setScene(new Scene(p));
            s.showAndWait();
        }));
    }

    private void signUpNewUser() {
        DataBase db = new DataBase();

        String name = but_name.getText();
        String login = but_login.getText();
        String password = but_pass.getText();

            //st.execute("INSERT INTO Users (name ,login,password) VALUES ('aaa','vscv33c','acvvcvcv')");
        User user = new User(name, login,password);
        db.signUp(user);



    }

}
