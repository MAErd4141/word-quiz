package com.example.ogreniyorum;

import com.example.ogreniyorum.controllers.HelloController;
import com.example.ogreniyorum.controllers.RegisterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Kelime Quiz");
        stage.setScene(scene);
        HelloController.stage = stage;
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}