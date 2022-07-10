package com.example.newappli;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateDataControl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button but_back;

    @FXML
    private TextField but_login_old;

    @FXML
    private TextField but_name_new;

    @FXML
    private TextField but_new_login;

    @FXML
    private TextField but_pass_new;

    @FXML
    private TextField but_pass_old;

    @FXML
    private Button but_update;

    @FXML
    void initialize() {
        but_update.setOnAction(actionEvent -> {
            UpdateData();
            but_update.getScene().getWindow().hide();
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
            s.show();
        });

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
            s.show();
        }));
    }

    private void UpdateData() {
        DataBase db = new DataBase();

        String login_old = but_login_old.getText();
        String password_old = but_pass_old.getText();
        String login_new = but_new_login.getText();
        String password_new = but_pass_new.getText();
        String name_new = but_name_new.getText();

        User user_new = new User(name_new,login_new,password_new);
        db.updateD(user_new, login_old, password_old);
    }

}
