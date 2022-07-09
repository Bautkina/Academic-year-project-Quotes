package com.example.newappli;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DataBase {
    public Connection connection;
    public static int user_id;
    public static int date_cur;

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
        ResultSet result2 = null;

        String s = "select * from Users where login =? AND password =?";
        //String s2 = "select * from Users where login =? AND password =?";

        try{
            PreparedStatement pr = getConnection().prepareStatement(s);
            pr.setString(1, user.getLogin());
            pr.setString(2, user.getPassword());
            PreparedStatement pr2 = getConnection().prepareStatement(s);
            pr2.setString(1, user.getLogin());
            pr2.setString(2, user.getPassword());
            result = pr.executeQuery();
            result2 = pr2.executeQuery();

            while (result2.next()) {
                user_id = result2.getInt("id");
                //date_cur = result2.getInt("date");
                System.out.println(user_id);
                //System.out.println(date_cur);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public ObservableList<Quote> getDataBaseUsers(){
        String select = "SELECT * FROM Quote";
        ObservableList<Quote> list = FXCollections.observableArrayList();
        try {
            PreparedStatement pr = getConnection().prepareStatement(select);
            ResultSet result = pr.executeQuery();
            while (result.next()){
                list.add(new Quote(Integer.parseInt(result.getString("id")), result.getString("user_comment"),result.getString("comment"), result.getString("subject"), result.getString("teacher"),  result.getString("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }



    public void createQ(Quote quote1) {
        String insert = "INSERT INTO Quote(comment, user_comment, teacher, subject, date) values(?,'"+user_id+"',?,?, CURRENT_TIMESTAMP)";
        try {
            PreparedStatement pr = getConnection().prepareStatement(insert);
            pr.setString(1, quote1.getComment());
            //pr.setString(2, user_id);
            pr.setString(2, quote1.getTeacher());
            pr.setString(3, quote1.getSubject());

            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
