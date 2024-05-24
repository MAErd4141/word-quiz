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
    private ComboBox<Integer> comboBox;

    @FXML
    private Label infoLabel;

    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    public static Integer selectedValue = 10;
    public void initialize() {
        comboBox.getItems().addAll(5, 10, 15, 20);
        backButton.setOnAction(e -> {
            stage.close();
            goToMainStage();
        });

        saveButton.setOnAction(e -> {
            selectedValue = comboBox.getValue();
            infoLabel.setText("Başarıyla Kaydedildi!");
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
        SettingsController.stage = stage;
        stage.setScene(new Scene(root));
        stage.show();
    }
}