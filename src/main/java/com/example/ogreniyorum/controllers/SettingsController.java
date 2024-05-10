package com.example.ogreniyorum.controllers;

import com.example.ogreniyorum.controllers.AddWordController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {

    public static Stage stage;
    @FXML
    private Spinner Spinner;

    @FXML
    private Button Button;

    @FXML
    private TextArea Textarea;

    @FXML
    private Button backButton;

    public void initialize() {

        backButton.setOnAction(e -> {
            stage.close();
            goToMainStage();
        });
    }
    private void goToMainStage() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/example/ogreniyorum/main-view.fxml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Stage stage = new Stage();
        AddWordController.stage = stage;
        stage.setScene(new Scene(root));
        stage.show();
    }
}