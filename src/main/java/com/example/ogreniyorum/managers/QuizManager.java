package com.example.ogreniyorum.managers;

import com.example.ogreniyorum.Models.Answer;
import com.example.ogreniyorum.Models.Word;
import com.example.ogreniyorum.controllers.HelloController;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QuizManager {
    private static final String URL = "jdbc:mysql://localhost:3306/kelimequiz";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    public List<Word> randomWordEng(Integer limit) {
        List<Word> randomWordList = new ArrayList<Word>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM words ORDER BY RAND();";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            int sayac = 0; //sonsuz döngüden çıkar
            //words tablosunda correctcount 0 olan kelime kalmazsa sonsuz döngüye girme riski var
            while (resultSet.next() && randomWordList.size() < limit) {
                sayac++;
                QuizManager quizManager = new QuizManager();
                int id = resultSet.getInt("word_id");
                String wordEng = resultSet.getString("word_eng");
                String wordTr = resultSet.getString("word_tr");

                if (!quizManager.isThere(HelloController.userId,id)) { // "correct count" 0 değilse ekleme
                    Word word = new Word(id, wordTr, wordEng);
                    randomWordList.add(word);
                } else{
                    if(quizManager.getCorrectCount(HelloController.userId,id) == 0) {
                        Word word = new Word(id, wordTr, wordEng);
                        randomWordList.add(word);
                    }
                }
                if(sayac > 250) {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return randomWordList;
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

    public boolean addAnswer(Integer userId,Integer wordId,boolean isCompleted, LocalDate date, Integer correctCount) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO answers (user_id, word_id, is_completed, correct_time, correct_count) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,userId);
            statement.setInt(2,wordId );
            statement.setBoolean(3,isCompleted);
            statement.setDate(4, Date.valueOf(date));
            statement.setInt(5, correctCount);
            int rowsAffected = statement.executeUpdate(); // INSERT, UPDATE, DELETE işlemleri için executeUpdate() kullanılır
            return rowsAffected > 0; // Eğer sonuç varsa, kullanıcı doğrulanmıştır.
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean increaseCorrectCount(Integer userId,Integer wordId) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "UPDATE answers SET correct_count = correct_count + 1, correct_time = CURRENT_DATE WHERE user_id = ? AND word_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, wordId);
            int rowsAffected = statement.executeUpdate(); // INSERT, UPDATE, DELETE işlemleri için executeUpdate() kullanılır
            return rowsAffected > 0; // Eğer sonuç varsa, kullanıcı doğrulanmıştır.
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFromAnswers(Integer userId,Integer wordId) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "DELETE FROM answers WHERE user_id = ? AND word_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, wordId);
            ResultSet resultSet = statement.executeQuery();
            int rowsAffected = statement.executeUpdate(); // INSERT, UPDATE, DELETE işlemleri için executeUpdate() kullanılır
            return rowsAffected > 0; // Eğer sonuç varsa, kullanıcı doğrulanmıştır.
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isThere(Integer userId, Integer wordId) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT COUNT(*) AS is_there FROM answers WHERE user_id = ? AND word_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, wordId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("is there");
                return count > 0; // Eğer sonuç varsa, kullanıcı doğrulanmıştır.
            } else {
                return false; // Sonuç yoksa, kullanıcı kaydı yoktur.
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Integer getCorrectCount(Integer userId,Integer wordId) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Integer correctCount = null;
            String query = "SELECT correct_count FROM answers WHERE user_id = ? AND word_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, wordId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                correctCount = resultSet.getInt("correct_count");
            }
            return correctCount;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean resetCorrectCount(Integer userId,Integer wordId) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "UPDATE answers SET correct_count = 0, correct_time = CURRENT_DATE WHERE user_id = ? AND word_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, wordId);
            int rowsAffected = statement.executeUpdate(); // INSERT, UPDATE, DELETE işlemleri için executeUpdate() kullanılır
            return rowsAffected > 0; // Eğer sonuç varsa, kullanıcı doğrulanmıştır.
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public LocalDate getCorrectDate(Integer userId, Integer wordId) {
        LocalDate date = null;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT correct_time FROM answers WHERE user_id = ? AND word_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, wordId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                date = resultSet.getDate("correct_time").toLocalDate();
            } else {
                System.out.println("No data found for userId: " + userId + " and wordId: " + wordId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
    public List<Answer> getAnswers(Integer count, Integer diff) {
        List<Answer> answers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM answers WHERE correct_count = ? AND DATEDIFF(CURDATE(), correct_time) > ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, count);
            statement.setInt(2, diff);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                Integer userId = resultSet.getInt("user_id");
                Integer wordId = resultSet.getInt("word_id");
                boolean isCompleted =resultSet.getBoolean("is_completed");
                LocalDate date = resultSet.getDate("correct_time").toLocalDate();
                Integer correctCount = resultSet.getInt("correct_count");

                Answer answer = new Answer(userId,wordId,isCompleted,date,correctCount);
                answers.add(answer);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return answers;
    }
}
