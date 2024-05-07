package com.example.ogreniyorum.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterManager {

    private static final String URL = "jdbc:mysql://localhost:3306/kelimequiz";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    public boolean register(String fullName, String email, String password) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO users (full_name, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,fullName);
            statement.setString(2, email);
            statement.setString(3, password);
            int rowsAffected = statement.executeUpdate(); // INSERT, UPDATE, DELETE işlemleri için executeUpdate() kullanılır
            return rowsAffected > 0; // Eğer sonuç varsa, kullanıcı doğrulanmıştır.
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

}
