package com.damian.apexedu.control;

import animatefx.animation.LightSpeedIn;
import animatefx.animation.Shake;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.Navigator;
import com.damian.apexedu.util.Routes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MySqlAuthenticatorController implements Initializable {
    public static String username;
    public static String password;
    public JFXTextField t1;
    public JFXTextField t2;
    public JFXButton b2;
    public JFXButton b1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    new LightSpeedIn(t1).play();
    new LightSpeedIn(t2).play();
    new Shake(b1).play();
    new Shake(b2).play();
    }

    public void t1OnAction(ActionEvent actionEvent) {
        t2.requestFocus();
    }

    public void t2OnAction(ActionEvent actionEvent) {
        b1OnAction(actionEvent);
    }

    public void b2OnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void b1OnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) b1.getScene().getWindow();
        username = t1.getText();
        password = t2.getText();
        if (username.isEmpty() || password.isEmpty()) {
            AlertSender.sendAlert("Please enter your SQL username and password!", "ERROR", Alert.AlertType.ERROR);

        }else {
            try {
                Navigator.navigate(stage, Routes.HOMESCREEN);
            } catch (IOException e) {
                AlertSender.sendAlert(e.getLocalizedMessage(), "ERROR", Alert.AlertType.ERROR);

            }
        }

    }

}
