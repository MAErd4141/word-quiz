package com.example.ogreniyorum.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class QuizController {

    @FXML
    private VBox vbox;

    @FXML
    public void initialize(){

        for (int i = 1; i <= 10; i++) {
            Label label = new Label("Etiket " + i);
            label.setStyle("-fx-font-size: 16px;");

            TextField textField = new TextField();
            textField.setPromptText("Metin " + i);

            vbox.getChildren().addAll(label, textField);
        }
    }
}
