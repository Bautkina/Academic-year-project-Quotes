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
           but_sign_in.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/newappli/sign_up.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent p = loader.getRoot();
            Stage s = new Stage();
            s.setScene(new Scene(p));
            s.showAndWait();
        });
    }
}
