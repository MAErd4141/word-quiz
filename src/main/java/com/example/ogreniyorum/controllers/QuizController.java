package com.example.ogreniyorum.controllers;

import com.example.ogreniyorum.managers.QuizManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizController {

    @FXML
    private VBox vbox;

    @FXML
    private VBox resultVBox;

    @FXML
    private Button completeButton;

    @FXML
    private Button backButton;

    public static Stage stage;

    private Map<Integer, String> wordEngList;
    private List<String> wordEngs;
    private List<String> textList;

    @FXML
    public void initialize(){

        wordEngList = new HashMap<>();
        textList = new ArrayList<>();
        wordEngs = new ArrayList<>();


        getRandomWordTr();
        backButton.setOnAction(e -> {
            stage.close();
            goToMainStage();

        });

        completeButton.setOnAction(e -> {

            for (int i = 0; i < vbox.getChildren().size(); i++) {
                if (vbox.getChildren().get(i) instanceof TextField) {
                    TextField textField = (TextField) vbox.getChildren().get(i);
                    String text = textField.getText();
                    textList.add(text);
                }
            }

            checkResults();
        });

    }

    private void checkResults() {
        QuizManager quizManager = new QuizManager();
        for (int i = 0 ; i < wordEngList.values().size() ; i++) {
            Label label = new Label("sonuç");
            label.setStyle("-fx-font-size: 16px;");
            boolean sonuc = quizManager.isCorrect(textList.get(i),wordEngs.get(i));

            if (sonuc) {
                label.setText("DOĞRU");
                label.setTextFill(Color.GREEN);
            } else {
                label.setText("YANLIŞ");
                label.setTextFill(Color.RED);
            }

            resultVBox.setSpacing(25.5);
            resultVBox.getChildren().add(label);

        }

    }

    private void getRandomWordTr() {
        QuizManager quizManager = new QuizManager();
        wordEngList = quizManager.randomWordEng(10);
        for (String word : wordEngList.values()) {
            Label label = new Label(word);
            label.setStyle("-fx-font-size: 16px;");

            TextField textField = new TextField();
            textField.setPromptText("Cevabı Giriniz");
            wordEngs.add(word);
            vbox.getChildren().addAll(label, textField);
        }
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
