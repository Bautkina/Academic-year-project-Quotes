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

public class UpdateControl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button but_back;

    @FXML
    private TextField but_quote;

    @FXML
    private TextField but_subject;

    @FXML
    private TextField but_teacher;

    @FXML
    private Button but_update;

    @FXML
    private TextField id_quote;

    @FXML
    void initialize() {
        but_update.setOnAction(actionEvent -> {
            newUpdate();
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

    private void newUpdate() {
        DataBase db = new DataBase();

        int id = Integer.parseInt(id_quote.getText());
        String teacher = but_teacher.getText();
        String subject = but_subject.getText();
        String quote = but_quote.getText();

        Quote quote1 = new Quote(id,teacher, subject,quote);
        db.update(quote1);
    }


}
