package com.example.ogreniyorum.managers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginManager {
    private static final String URL = "jdbc:mysql://localhost:3306/kelimequiz";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    public boolean validateLogin(String email, String password) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Eğer sonuç varsa, kullanıcı doğrulanmıştır.
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isThere(String email) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Eğer sonuç varsa, kullanıcı doğrulanmıştır.
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Integer IdByEmail(String email) {
        Integer userId = null;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT user_id FROM users WHERE email = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                userId = resultSet.getInt("user_id"); // Eğer sonuç varsa, kullanıcı doğrulanmıştır.
            }
           return userId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
