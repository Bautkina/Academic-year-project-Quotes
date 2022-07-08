package com.example.newappli;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableControl {

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
    private TableColumn<Quote, String> col_user;

    @FXML
    private TableView<Quote> table;

    ObservableList<Quote> list;
    int index = -1;
    Connection conn = null;
    ResultSet result = null;
    PreparedStatement pr = null;


    @FXML
    void initialize() {
        col_id.setCellValueFactory(new PropertyValueFactory<Quote, Integer>("id"));
        col_user.setCellValueFactory(new PropertyValueFactory<Quote, String>("user_comment"));
        col_teacher.setCellValueFactory(new PropertyValueFactory<Quote, String>("teacher"));
        col_subject.setCellValueFactory(new PropertyValueFactory<Quote, String>("subject"));
        col_quote.setCellValueFactory(new PropertyValueFactory<Quote, String>("comment"));

        DataBase bd = new DataBase();
        list = bd.getDataBaseUsers();
        table.setItems(list);

    }

}
