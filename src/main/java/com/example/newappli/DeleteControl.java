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

public class DeleteControl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button but_back;

    @FXML
    private Button but_delete;

    @FXML
    private TextField but_deletefield;

    @FXML
    void initialize() {
        but_delete.setOnAction(actionEvent -> {
            newDelete();
            but_delete.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/newappli/table.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent p = loader.getRoot();
            Stage s = new Stage();
            s.setScene(new Scene(p));
            s.show();
        });

        but_back.setOnAction((actionEvent -> {
            but_back.getScene().getWindow().hide();
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
        }));
    }

    private void newDelete() {
        DataBase db = new DataBase();

        int id = Integer.parseInt(but_deletefield.getText());

        Quote quote1 = new Quote(id);
        db.delete(quote1);
    }

}
