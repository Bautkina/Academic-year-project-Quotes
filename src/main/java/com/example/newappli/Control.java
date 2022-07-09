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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Control {
    public static int reg;

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
    private Button but_read;

    @FXML
    void initialize() {
        but_sign_in.setOnAction(actionEvent -> {
            String log_text = but_login.getText().trim();
            String password_text = but_pass.getText().trim();

            if(!log_text.equals("") && !password_text.equals("")){
                login(log_text,password_text);
                if (reg == 1){
                        but_sign_in.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/example/newappli/table.fxml"));
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
                    }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Такого пользователя нет!");

                    alert.showAndWait();
                }
                }
            else {
                System.out.println("Заполните поля");
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

        but_read.setOnAction(actionEvent -> {
            but_read.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/newappli/table.fxml"));
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
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ошибка 2");
        }

        if(counter >= 1){
            System.out.println("Успешно");
            reg = 1;
        }
        else{
            System.out.println("Такого пользователя нет");
            reg = 0;
        }
    }
}
