package com.example.ogreniyorum.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button addWordButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button startButton;

    public static Stage stage;

    @FXML
    public void initialize() {
        startButton.setOnAction(e -> {
            goQuizStage();
            stage.hide();
        });
        addWordButton.setOnAction( e -> {
            goAddWordStage();
            stage.hide();
        });
        settingsButton.setOnAction(e -> {
            goSettingsStage();
            stage.hide();
        });
    }
    private void goQuizStage() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/example/ogreniyorum/quiz-view.fxml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Stage stage = new Stage();
        QuizController.stage = stage;
        stage.setScene(new Scene(root));
        stage.show();
    }
    private void goAddWordStage() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/example/ogreniyorum/addword-view.fxml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Stage stage = new Stage();
        AddWordController.stage = stage;
        stage.setScene(new Scene(root));
        stage.show();
    }
    private void goSettingsStage() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/example/ogreniyorum/settings-view.fxml"));//değişecek
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Stage stage = new Stage();
        SettingsController.stage = stage;//değişecek
        stage.setScene(new Scene(root));
        stage.show();
    }
}
