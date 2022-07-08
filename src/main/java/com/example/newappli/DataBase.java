package com.example.newappli;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.sql.*;

public class DataBase {
    public Connection connection;

    public Connection getConnection() {
        try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    connection = DriverManager.getConnection(
                            "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1967_us",
                            "std_1967_us", "Wowayf66");
                    return connection;

                } catch (ClassNotFoundException e) {
            System.out.println("e");
                    throw new RuntimeException(e);
                } catch (SQLException e) {
            System.out.println("ee");
                    throw new RuntimeException(e);
                }

            }
    public void signUp(User user) {

        String insert = "INSERT INTO "+ Bd.table+"("+Bd.name + ","+Bd.login+  "," + Bd.password +")" + "VALUES(?,?,?)";
        try {
            PreparedStatement pr = getConnection().prepareStatement(insert);
            pr.setString(1, user.getName());
            pr.setString(2, user.getLogin());
            pr.setString(3, user.getPassword());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user){
        ResultSet result = null;

        String s = "select * from Users where login =? AND password =?";

        try{
            PreparedStatement pr = getConnection().prepareStatement(s);
            pr.setString(1, user.getLogin());
            pr.setString(2, user.getPassword());

            result = pr.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public ObservableList<Quote> getDataBaseUsers(){
        String select = "SELECT * FROM Comment_z";
        ObservableList<Quote> list = FXCollections.observableArrayList();
        try {
            PreparedStatement pr = getConnection().prepareStatement(select);
            ResultSet result = pr.executeQuery();
            while (result.next()){
                list.add(new Quote(Integer.parseInt(result.getString("id")), result.getString("user_comment"),result.getString("comment"), result.getString("subject"), result.getString("teacher")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


}
