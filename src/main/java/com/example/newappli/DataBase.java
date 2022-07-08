package com.example.newappli;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void signUp(String name, String login,String password) throws SQLException, ClassNotFoundException {
        Statement st = connection.createStatement();

        String insert = "INSERT INTO "+ Bd.table+"("+Bd.name + ","+Bd.login+  "," + Bd.password +")" + "VALUES(?,?,?)";
        //st.execute(insert);
        try {
            PreparedStatement pr = getConnection().prepareStatement(insert);
            pr.setString(1, name);
            pr.setString(2, login);
            pr.setString(3, password);
            pr.executeUpdate();
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
