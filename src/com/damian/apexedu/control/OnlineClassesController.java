package com.damian.apexedu.control;

import animatefx.animation.JackInTheBox;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.Navigator;
import com.damian.apexedu.util.Routes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.lang.annotation.Native;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class OnlineClassesController implements Initializable {
    public WebView webView;
    public JFXTextField urlField;
    public JFXButton goButton;
    public WebEngine engine;
    public Label dateAndTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node [] nodeArray = {webView, urlField, goButton};
        for (Node node : nodeArray) {
            Animator.setJackInTheBox(node);
        }
        engine = webView.getEngine();

        AnimationTimer timer = new AnimationTimer() {   //Loading the live clock.
            @Override
            public void handle(long now) {   //Loading the live clock, and setting it to a label.
                dateAndTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss")));
            }
        };
        timer.start();


    }

    public void urlFieldOnAction(ActionEvent actionEvent) {
        goOnAction(actionEvent);
    }

    public void goOnAction(ActionEvent actionEvent) {
        engine.load(urlField.getText());
    }


    public void goToHomeScreen(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage)goButton.getScene().getWindow();
        Navigator.navigate(stage, Routes.HOMESCREEN);
    }

    public void goToDashBoard(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage)goButton.getScene().getWindow();
        Navigator.navigate(stage, Routes.STUDENTDASHBOARD);
    }
}
