package com.example.ogreniyorum.managers;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class QuizManager {
    private static final String URL = "jdbc:mysql://localhost:3306/kelimequiz";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    public Map<Integer, String> randomWordEng(Integer limit) {
        Map<Integer, String> randomWordTrMap = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT word_id, word_eng FROM words ORDER BY RAND() LIMIT ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("word_id");
                String wordEng = resultSet.getString("word_eng");
                randomWordTrMap.put(id, wordEng);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return randomWordTrMap;
    }
    public boolean isCorrect(String tr, String eng) {
        boolean exists = false;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT EXISTS (SELECT 1 FROM words WHERE word_eng = ? AND word_tr = ?) AS is_correct";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, eng);
            statement.setString(2, tr);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = resultSet.getBoolean("is_correct");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
}
