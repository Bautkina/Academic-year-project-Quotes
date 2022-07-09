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

public class CreateQuoteControl extends Control{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button but_back;



    @FXML
    private Button but_create;

    @FXML
    private TextField but_quote;

    @FXML
    private TextField but_subject;

    @FXML
    private TextField but_teacher;


    @FXML
    void initialize() {
        but_create.setOnAction(actionEvent -> {
            newQuote();
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
    private void newQuote() {
        DataBase db = new DataBase();

        String teacher = but_teacher.getText();
        String subject = but_subject.getText();
        String quote = but_quote.getText();

        //st.execute("INSERT INTO Users (name ,login,password) VALUES ('aaa','vscv33c','acvvcvcv')");
        Quote quote1 = new Quote(teacher, subject,quote);
        db.createQ(quote1);



    }


}
