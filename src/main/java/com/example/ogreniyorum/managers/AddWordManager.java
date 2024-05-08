package com.example.ogreniyorum.managers;

import com.example.ogreniyorum.Models.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddWordManager {
    private static final String URL = "jdbc:mysql://localhost:3306/kelimequiz";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    public boolean addWord(String wordTr, String wordEng) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO words (word_tr, word_eng) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, wordTr);
            statement.setString(2, wordEng);
            int rowsAffected = statement.executeUpdate(); // INSERT, UPDATE, DELETE işlemleri için executeUpdate() kullanılır
            return rowsAffected > 0;  // Eğer sonuç varsa, kullanıcı doğrulanmıştır.
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public ObservableList<Word> getAllWords() {
        TableView<Word> tableView = new TableView<>();
        ObservableList<Word> wordList = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM words";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            // Veritabanından gelen verileri ObservableList'e ekle
            while (resultSet.next()) {
                wordList.add(new Word(resultSet.getInt("word_id"),resultSet.getString("word_tr"), resultSet.getString("word_eng")));
            }

            return wordList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
