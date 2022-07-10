package com.example.newappli;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableControl extends Control{
    static String count_user;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button but_create;

    @FXML
    private Button but_delete;

    @FXML
    private Button but_update;

    @FXML
    private TableColumn<Quote, Integer> col_id;

    @FXML
    private TableColumn<Quote, String> col_quote;

    @FXML
    private TableColumn<Quote,String> col_subject;

    @FXML
    private TableColumn<Quote, String> col_teacher;

    @FXML
    private String col_user;

    @FXML
    private TableView<Quote> table;

    @FXML
    private Button but_back;

    @FXML
    private TableColumn<Quote, String> col_date;

    @FXML
    private TableColumn<Quote, String> col_author;

    @FXML
    private Label count;


    ObservableList<Quote> list;
    int index = -1;
    Connection conn = null;
    ResultSet result = null;
    PreparedStatement pr = null;


    @FXML
    void initialize() throws SQLException {
        col_id.setCellValueFactory(new PropertyValueFactory<Quote, Integer>("id"));
        col_author.setCellValueFactory(new PropertyValueFactory<Quote, String>("user"));
        col_teacher.setCellValueFactory(new PropertyValueFactory<Quote, String>("teacher"));
        col_subject.setCellValueFactory(new PropertyValueFactory<Quote, String>("subject"));
        col_quote.setCellValueFactory(new PropertyValueFactory<Quote, String>("comment"));
        DataBase bd = new DataBase();
        //col_user = bd.countUser_1();
        col_user = bd.countUser_2();
        col_date.setCellValueFactory(new PropertyValueFactory<Quote, String>("date"));
        if (reg == 1){
            count.setText(col_user);
        }
        else{
            count.setText("0");
        }


        //c//ount.labelForProperty(new PropertyValueFactory<>(bd.countUser()));

        //DataBase bd = new DataBase();
        list = bd.getDataBaseUsers();
        table.setItems(list);



        but_create.setOnAction(actionEvent -> {
            if (reg == 1) {
                but_create.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/newappli/create_quote.fxml"));
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
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                DataBase db = new DataBase();

                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Незарегистрированный пользователь не может создавать цитаты!");

                alert.showAndWait();
            }
        });

        but_update.setOnAction(actionEvent -> {
            if (reg == 1) {
                but_create.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/newappli/update.fxml"));
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
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Незарегистрированный пользователь не может изменять цитаты!");

                alert.showAndWait();
            }
        });

        but_delete.setOnAction(actionEvent -> {
            if (reg == 1) {
                but_delete.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/newappli/delete.fxml"));
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
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Незарегистрированный пользователь не может удалять цитаты!");

                alert.showAndWait();
            }
        });

        but_back.setOnAction((actionEvent -> {
            but_back.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            //col_user = "0";
            loader.setLocation(getClass().getResource("/com/example/newappli/hello-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка");
            }
            reg = 0;
            Parent p = loader.getRoot();
            Stage s = new Stage();
            s.setScene(new Scene(p));
            s.show();
        }));
    }


}
