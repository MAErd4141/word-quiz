package com.example.ogreniyorum.controllers;

import com.example.ogreniyorum.managers.LoginManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button loginButton;
    @FXML
    private Label infoLabel;
    @FXML
    private Button sifreunuttumbtnButton;
    @FXML
    private Button registerButton;
    public static Integer userId;

    public static Stage stage;

    @FXML
    public void initialize() {
        loginButton.setOnAction(e -> {
            infoLabel.setText("");
            LoginManager loginManager = new LoginManager();
            if (emailField.getText().isEmpty() || passField.getText().isEmpty()) {
                infoLabel.setText("Alanları boş bırakmayınız !");
            } else if(!loginManager.isThere(emailField.getText())){
                infoLabel.setText("Bu email kayıtlı değil!");
            } else if(loginManager.validateLogin(emailField.getText(),passField.getText())){
                infoLabel.setText("Giriş Başarılı!");
                userId = loginManager.IdByEmail(emailField.getText());
                goMainStage();
                stage.hide();
            } else {
                infoLabel.setText("Yanlış email ya da şifre tekrar giriniz !");
            }
        });
        registerButton.setOnAction((e ->{
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/com/example/ogreniyorum/register-view.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Stage stage = new Stage();
            RegisterController.stage = stage;
            stage.setScene(new Scene(root));
            stage.show();


        }));
    }

    private void goMainStage() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/example/ogreniyorum/main-view.fxml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Stage stage = new Stage();
        MainController.stage = stage;
        stage.setScene(new Scene(root));
        stage.show();
    }
}