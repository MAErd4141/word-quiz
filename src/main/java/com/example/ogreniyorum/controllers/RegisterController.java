package com.example.ogreniyorum.controllers;

import com.example.ogreniyorum.HelloApplication;
import com.example.ogreniyorum.managers.RegisterManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button backButton;
    @FXML
    private Button registerButton;
    @FXML
    private PasswordField confirmPassField;
    @FXML
    private Label infoLabel;
    public static Stage stage;


    @FXML
    public void initialize() {

        backButton.setOnAction(e -> {
            stage.close();

        });


        registerButton.setOnAction(e -> {
            RegisterManager registerManager = new RegisterManager();
            if (emailField.getText().isEmpty() || passField.getText().isEmpty() || confirmPassField.getText().isEmpty() || fullNameTextField.getText().isEmpty()) {
                infoLabel.setText("Alanları boş bırakmayınız!");
            } else if (passField.getText().length() < 8) {
                infoLabel.setText("Şifreniz 8 haneden uzun olmalı!");
            } else if (!passField.getText().equals(confirmPassField.getText())) {
                infoLabel.setText("Şifreler eşleşmiyor!");
            } else if (registerManager.isThere(emailField.getText())) {
                infoLabel.setText("Bu email zaten kayıtlı!");
            } else {
                registerManager.register(fullNameTextField.getText(), emailField.getText(), passField.getText());
                infoLabel.setText("Kayıt Başarılı!! Lütfen Bekleyin..");
                stage.hide();
            }
        });
    }
}
