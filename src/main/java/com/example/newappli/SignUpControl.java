package com.example.newappli;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignUpControl {

    @FXML
    private ResourceBundle resources;

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
