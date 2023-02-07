package com.damian.apexedu.control;

import animatefx.animation.*;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.util.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {


    public ImageView image;
    public Label label;
    public JFXTextField t1;
    public Label dateAndTime;
    public AnchorPane ap;
    public Label adminLabel;
    public JFXPasswordField t2;
    public JFXTextField t3;
    public JFXCheckBox checkBox;
    public JFXButton loginButton;
    public Label fpLabel;
    public JFXButton lecButton;
    public JFXButton studentButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new JackInTheBox(label).play();//Animating the components on the home screen.
        new JackInTheBox(adminLabel).play();
        new JackInTheBox(dateAndTime).play();
        new JackInTheBox(fpLabel).play();
        new JackInTheBox(loginButton).play();
        new JackInTheBox(t1).play();
        new JackInTheBox(t2).play();
        new Shake(image).play();
        t3.setVisible(false);

        lecButton.disableVisualFocusProperty().set(true);

        AnimationTimer timer = new AnimationTimer() {   //Loading the live clock.
            @Override
            public void handle(long now) {   //Loading the live clock, and setting it to a label.
               dateAndTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss")));
            }
        };
        timer.start();

        new JackInTheBox(lecButton).play();
        new JackInTheBox(studentButton).play();
    }


    public void fpOnAction(MouseEvent mouseEvent) {
        AlertSender.sendAlert("Contact your IT administrator!","INFORMATION.", Alert.AlertType.INFORMATION);
    }

    public void spOnAction(ActionEvent actionEvent) {
        if(checkBox.isSelected()){
            t3.setText(t2.getText());
            t3.setVisible(true);
            Animator.setLightSpeedIn(t3);
        }
        else{
            Animator.setLightSpeedOut(t3);
        }
    }

    public void loginOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        ResultSet RS = PerformCRUD.execute("SELECT username,password FROM admin_details");
        while (RS.next()){
            String username = RS.getString("username");
            String password = RS.getString("password");

            if(username.equals(t1.getText()) && password.equals(t2.getText())){
                Stage stage = (Stage)loginButton.getScene().getWindow(); //Getting the current stage.
                Navigator.navigate(stage, Routes.AdminDashBoard);
            }
            else{
                t2.requestFocus();
                t2.setFocusColor(Paint.valueOf("RED"));
                AlertSender.sendAlert("Enter valid credentials!","ERROR.", Alert.AlertType.ERROR);

                Animator.setShake(loginButton);
                Animator.setShake(t1);
                Animator.setShake(t2);
            }

        }
    }

    public void t2OnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        loginOnAction(actionEvent);
    }


    public void lecOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)loginButton.getScene().getWindow();
        Navigator.navigate(stage,Routes.LECTURERLOGIN);
    }

    public void studentOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)loginButton.getScene().getWindow();
        Navigator.navigate(stage,Routes.STUDENTLOGIN);
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        String s =mouseEvent.getPickResult().getIntersectedNode().getId();
        if(s.equals("lecButton")){
            System.out.println(lecButton.toString());
            Animator.setShake(lecButton);
          lecButton.setRipplerFill(Paint.valueOf("RED"));
        }
        if(s.equals("studentButton")){
            System.out.println(studentButton.toString());
            Animator.setShake(studentButton);
            studentButton.setRipplerFill(Paint.valueOf("RED"));
        }
        if(s.equals("loginButton")){
            System.out.println(loginButton.toString());
            Animator.setShake(loginButton);
            loginButton.setRipplerFill(Paint.valueOf("RED"));
        }
    }


    public void t1OnAction(ActionEvent actionEvent) {
        t2.requestFocus();
    }

}
