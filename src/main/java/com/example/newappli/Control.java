package com.example.newappli;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Control {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField button_field_log;

    @FXML
    private Button button_go;

    @FXML
    private Button button_login;

    @FXML
    private TextField button_pass;

    @FXML
    void initialize() {
        assert button_field_log != null : "fx:id=\"button_field_log\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert button_go != null : "fx:id=\"button_go\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert button_login != null : "fx:id=\"button_login\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert button_pass != null : "fx:id=\"button_pass\" was not injected: check your FXML file 'hello-view.fxml'.";

    }
}


