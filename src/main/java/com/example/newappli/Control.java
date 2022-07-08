package com.example.newappli;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Control {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField but_login;

    @FXML
    private TextField but_pass;

    @FXML
    private Button but_sign_in;

    @FXML
    private Button but_sign_up;

    @FXML
    void initialize() {
        but_sign_in.setOnAction(actionEvent -> {
            String log_text = but_login.getText().trim();
            String password_text = but_pass.getText().trim();

            if(!log_text.equals("") && !password_text.equals("")){
                login(log_text,password_text);
                System.out.println("ВСё плохо");
            }
            else {
                System.out.println("Заполните поле");
            }
        });
        but_sign_up.setOnAction(actionEvent -> {
           but_sign_up.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/newappli/sign_up.fxml"));
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
        });
    }

    private void login(String log_text, String password_text) {
        DataBase bd = new DataBase();
        User user = new User();
        user.setLogin(log_text);
        user.setPassword(password_text);
        ResultSet result = bd.getUser(user);

        int counter = 0;
        try {
            while (result.next()) {
                counter=+1;
                System.out.println("t");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ошибка 2");
        }

        if(counter >= 1){
            System.out.println("Успешно");
        }
        else{
            System.out.println("bad");
        }
    }
}
