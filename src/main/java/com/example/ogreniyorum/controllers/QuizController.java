package com.example.ogreniyorum.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class QuizController {

    @FXML
    private VBox vbox;

    @FXML
    private Button backButton;

    public static Stage stage;
    @FXML
    public void initialize(){

        for (int i = 1; i <= 10; i++) {
            Label label = new Label("Etiket " + i);
            label.setStyle("-fx-font-size: 16px;");

            TextField textField = new TextField();
            textField.setPromptText("Metin " + i);

            vbox.getChildren().addAll(label, textField);
        }

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
