package com.example.ogreniyorum.controllers;

import com.example.ogreniyorum.Models.Word;
import com.example.ogreniyorum.managers.WordManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class AddWordController {

    @FXML
    private Button addButton;
    @FXML
    private TextField trTextField;
    @FXML
    private TextField engTextField;
    @FXML
    private Label infoLabel;
    @FXML
    private TableView<Word> tableView;
    @FXML
    private Button backButton;
    @FXML
    private TableColumn<Word, String> column1;

    @FXML
    private TableColumn<Word, String> column2;
    private ObservableList<Word> wordList = FXCollections.observableArrayList();
    public static Stage stage;

    @FXML
    public void initialize() {

        WordManager addWordManager = new WordManager();
        wordList = addWordManager.getAllWords();
        // TableColumn'ların PropertyValueFactory'leri ayarlanıyor
        column1.setCellValueFactory(new PropertyValueFactory<>("turkce"));
        column2.setCellValueFactory(new PropertyValueFactory<>("ingilizce"));
        // Veri listesini TableView'e ekle
        tableView.setItems(wordList);


        addButton.setOnAction( e -> {
            if (engTextField.getText().isEmpty() || trTextField.getText().isEmpty()) {
                infoLabel.setText("Alanları boş bırakmayınız lütfen!");
            } else {
                addWordManager.addWord(trTextField.getText(),engTextField.getText());
                infoLabel.setText("Kelime eklendi!");

            }

        });

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
