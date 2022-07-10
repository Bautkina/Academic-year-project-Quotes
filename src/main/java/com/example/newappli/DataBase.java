package com.example.newappli;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.sql.*;

public class DataBase {
    public Connection connection;
    public static int user_id;

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

        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec key = new SecretKeySpec("Aaaaaaaaaaaaaaaa".getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = cipher.doFinal(user.getPassword().getBytes());
            String hash_password = new String();
            for (byte b : bytes) {
                hash_password += b;
                System.out.print(b);
            }
            System.out.print(hash_password);


            String insert = "INSERT INTO " + Bd.table + "(" + Bd.name + "," + Bd.login + "," + Bd.password + ")" + "VALUES(?,?,?)";
            try {
                if (!user.getName().equals("") && !user.getLogin().equals("") && !user.getPassword().equals("")) {
                    PreparedStatement pr = getConnection().prepareStatement(insert);
                    pr.setString(1, user.getName());
                    pr.setString(2, user.getLogin());
                    pr.setString(3, hash_password);
                    pr.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Предупреждение");
                    alert.setHeaderText(null);
                    alert.setContentText("Вы зарегистрировались! Войдите в систему снова, чтобы продолжить.");

                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Предупреждение");
                    alert.setHeaderText(null);
                    alert.setContentText("Заполните все поля!");

                    alert.showAndWait();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
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

    public String countUser_1() throws SQLException {
        String insert = "Select COUNT(user_comment) as count FROM Quote WHERE  user_comment = " +user_id+ "";
        Connection connection = getConnection();
        Statement st = connection.createStatement();
        int count = 0;
        ResultSet result  = st.executeQuery(insert);
        while (result.next()){
            count = result.getInt("count");
            System.out.println(count);
        }
        return String.valueOf(count);
    }

    public String countUser_2() throws SQLException {
        String query = "SELECT * FROM Quote WHERE user_comment ='" + user_id + "'";
        PreparedStatement st = getConnection().prepareStatement(query);
        int count = 0;
        ResultSet res = st.executeQuery(query);

        while (res.next()) {
            count += 1;
        }
        return String.valueOf(count);
    }

    public void update(Quote quote){
        String insert = "UPDATE Quote SET subject = ?, teacher = ?, comment = ? WHERE id = ? AND user_comment = '" +user_id+ "'";
        try {
            if(!quote.getComment().equals("") && !quote.getTeacher().equals("") && !quote.getSubject().equals("")) {
                PreparedStatement pr = getConnection().prepareStatement(insert);
                pr.setString(1, quote.getSubject());
                pr.setString(2, quote.getTeacher());
                pr.setString(3, quote.getComment());
                pr.setInt(4, quote.getId());
                pr.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Вы изменили запись!");

                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Недостаточно прав!");

                alert.showAndWait();
            }
//            q

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            if(!quote1.getComment().equals("") && !quote1.getTeacher().equals("") && !quote1.getSubject().equals("")) {
                PreparedStatement pr = getConnection().prepareStatement(insert);
                pr.setString(1, quote1.getComment());
                //pr.setString(2, user_id);
                pr.setString(2, quote1.getTeacher());
                pr.setString(3, quote1.getSubject());

                pr.executeUpdate();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Заполните все поля!");

                alert.showAndWait();
            }
        } catch (SQLException e) {
        }

    }

    public void delete(Quote quote){
        String delete = "DELETE FROM `Quote` WHERE id = ? AND user_comment = '" +user_id+ "'";
        try {
                PreparedStatement pr = getConnection().prepareStatement(delete);
                pr.setInt(1, quote.getId());
                pr.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Вы изменили запись!");

                alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateD(User user_new, String login_old, String password_old) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec key = new SecretKeySpec("Aaaaaaaaaaaaaaaa".getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes_new = cipher.doFinal(user_new.getPassword().getBytes());
            byte[] bytes_old = cipher.doFinal(password_old.getBytes());
            String hash_password_new = new String();
            for (byte b : bytes_new) {
                hash_password_new += b;
            }
            String hash_password_old = new String();
            for (byte b : bytes_old) {
                hash_password_old += b;
            }
        String insert = "UPDATE Users SET name = ?, login = ?, password = ? WHERE login = ? AND password = ? ";
        try {
            //if ((quote.getUser().equals(user_id))){

            if (!user_new.getLogin().equals("") && !user_new.getPassword().equals("") && !login_old.equals("") && !password_old.equals("") && !user_new.getName().equals("")) {
                PreparedStatement pr = getConnection().prepareStatement(insert);
                pr.setString(1, user_new.getName());
                pr.setString(2, user_new.getLogin());
                pr.setString(3, hash_password_new);
                pr.setString(4, login_old);
                pr.setString(5, hash_password_old);
                pr.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Вы изменили регистрационные данные!");

                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Недостаточно прав!");

                alert.showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
