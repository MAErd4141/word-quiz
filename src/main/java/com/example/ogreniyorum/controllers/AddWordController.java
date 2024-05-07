package com.example.ogreniyorum.controllers;

import com.example.ogreniyorum.Models.Word;
import com.example.ogreniyorum.managers.AddWordManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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
    private TableColumn<Word, String> column1;

    @FXML
    private TableColumn<Word, String> column2;
    private ObservableList<Word> wordList = FXCollections.observableArrayList();
    public static Stage stage;

    @FXML
    public void initialize() {

        AddWordManager addWordManager = new AddWordManager();
        wordList = addWordManager.getAllWords();

        // TableColumn'ların PropertyValueFactory'leri ayarlanıyor
        column1.setCellValueFactory(new PropertyValueFactory<>("turkce"));
        column2.setCellValueFactory(new PropertyValueFactory<>("ingilizce"));

        // Veri listesini TableView'e ekle
        tableView.setItems(wordList);

        addButton.setOnAction( e -> {
            if (engTextField.getText().isEmpty() || trTextField.getText().isEmpty()) {
                infoLabel.setText("Alanları boş bırakmayınız!");
            } else {
                addWordManager.addWord(trTextField.getText(),engTextField.getText());
                infoLabel.setText("Kelime Eklendi!");

            }

        });


    }


}
