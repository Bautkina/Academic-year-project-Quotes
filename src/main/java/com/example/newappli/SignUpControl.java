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
        DataBase db = new DataBase();
        but_sign_in.setOnAction((actionEvent -> {
            try {
                Statement st = db.getConnection().createStatement();
                //st.execute("INSERT INTO Users (name ,login,password) VALUES ('aaa','vscv33c','acvvcvcv')");
                db.signUp(but_name.getText(),but_login.getText(),but_pass.getText());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ош1");
            }
        }));
    }

}
