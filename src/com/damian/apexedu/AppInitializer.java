package com.damian.apexedu;

import com.damian.apexedu.animations.Animator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/com/damian/apexedu/view/HomeScreen.fxml"))));
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image("/com/damian/apexedu/assets/apex.png"));
        primaryStage.setTitle("APEX Institute.");
        primaryStage.show();

        Animator.getInstance();
    }
}
