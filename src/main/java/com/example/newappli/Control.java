package com.example.newappli;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Control {
    public static int reg;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField but_login;

    @FXML
    private TextField but_pass;

    @FXML
    private Button but_sign_in;

    @FXML
    private Button but_sign_up;

    @FXML
    private Button but_read;

    @FXML
    private Button but_update_data;

    @FXML
    void initialize() throws SQLException {
        but_sign_in.setOnAction(actionEvent -> {
            try {
            String log_text = but_login.getText().trim();
            String password_text = but_pass.getText().trim();
            Cipher cipher = null;
            cipher = Cipher.getInstance("AES");

            SecretKeySpec key = new SecretKeySpec("Aaaaaaaaaaaaaaaa".getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = cipher.doFinal(password_text.getBytes());
            String hash_password = new String();
            for (byte b : bytes) {
                hash_password += b;
                System.out.print(b);
            }
            System.out.print(hash_password);

            if(!log_text.equals("") && !password_text.equals("")){
                login(log_text,hash_password);
                if (reg == 1){
                        but_sign_in.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/example/newappli/table.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("????????????");
                        }
                        Parent p = loader.getRoot();
                        Stage s = new Stage();
                        s.setScene(new Scene(p));
                        s.show();
                    }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("????????????");
                    alert.setHeaderText(null);
                    alert.setContentText("???????????? ???????????????????????? ??????!");

                    alert.showAndWait();
                }
                }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("????????????");
                alert.setHeaderText(null);
                alert.setContentText("?????????????????? ????????!");

                alert.showAndWait();
            }
        }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        });;
        but_update_data.setOnAction(actionEvent -> {
            but_update_data.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/newappli/update_data.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("????????????");
            }
            Parent p = loader.getRoot();
            Stage s = new Stage();
            s.setScene(new Scene(p));
            s.showAndWait();
        });

        but_sign_up.setOnAction(actionEvent -> {
           but_sign_up.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/newappli/sign_up.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("????????????");
            }
            Parent p = loader.getRoot();
            Stage s = new Stage();
            s.setScene(new Scene(p));
            s.showAndWait();
        });

        but_read.setOnAction(actionEvent -> {
            but_read.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/newappli/table.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("????????????");
            }
            Parent p = loader.getRoot();
            Stage s = new Stage();
            s.setScene(new Scene(p));
            s.show();
        });
    }


    public void login(String log_text, String password_text) {
        DataBase bd = new DataBase();
        User user = new User();

        user.setLogin(log_text);
        user.setPassword(password_text);
        ResultSet result = bd.getUser(user);

        int counter = 0;
        try {
            while (result.next()) {
                counter=+1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("???????????? 2");
        }

        if(counter >= 1){
            System.out.println("??????????????");
            reg = 1;
        }
        else{
            System.out.println("???????????? ???????????????????????? ??????");
            reg = 0;
        }
    }
}
