package com.example.ogreniyorum.controllers;

import com.example.ogreniyorum.Models.Word;
import com.example.ogreniyorum.managers.AddWordManager;
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
import java.time.LocalDate;
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

    private List<Word> randomWords;
    private List<String> textList;

    @FXML
    public void initialize(){


        textList = new ArrayList<>();


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
        for (int i = 0 ; i < randomWords.size() ; i++) {
            Label label = new Label("sonuç");
            label.setStyle("-fx-font-size: 16px;");
            boolean sonuc = quizManager.isCorrect(textList.get(i),randomWords.get(i).getIngilizce());

            if (sonuc) {
                label.setText("DOĞRU");
                label.setTextFill(Color.GREEN);
                if(quizManager.isThere(HelloController.userId,randomWords.get(i).getWordId())){
                    quizManager.increaseCorrectCount(HelloController.userId,randomWords.get(i).getWordId());
                }else {
                    quizManager.addAnswer(HelloController.userId,randomWords.get(i).getWordId(),false,LocalDate.now(),1);
                }
            } else {
                label.setText("YANLIŞ");
                label.setTextFill(Color.RED);
                if(quizManager.isThere(HelloController.userId,randomWords.get(i).getWordId())){
                    quizManager.resetCorrectCount(HelloController.userId,randomWords.get(i).getWordId());
                }

            }

            resultVBox.setSpacing(25.5);
            resultVBox.getChildren().add(label);

        }

    }
    private void getRandomWordTr() {
        QuizManager quizManager = new QuizManager();
        randomWords = quizManager.randomWordEng(10);
        for (Word word : randomWords) {
            Label label = new Label(word.getIngilizce());
            label.setStyle("-fx-font-size: 16px;");

            TextField textField = new TextField();
            textField.setPromptText("Cevabı Giriniz");
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
