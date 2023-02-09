package com.damian.apexedu.control;



import animatefx.animation.*;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.entity.CCS;
import com.damian.apexedu.entity.UNDERGRAD;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.Navigator;
import com.damian.apexedu.util.Routes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AdminDashBoardController implements Initializable {


    public AnchorPane ap;

    public JFXButton ccs;
    public JFXButton hnd;
    public JFXButton undergrad;
    public JFXButton lecturers;
    public JFXButton finance;
    public ImageView image;
    public Label label;
    public Label dateAndTime;
    public JFXButton homeButton;
    public Label welcomeLabel;
    public ImageView image1;
    public JFXButton CCSAttendance;
    public JFXButton HNDAttendance;
    public JFXButton UNDERGRADAttendance;
    public static String studentType;
    public static AnchorPane staticPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        staticPane = ap;
        //Animating fields when the dashboard loads...
        Node [] nodeArray = {label, image, image1, welcomeLabel, dateAndTime, ccs, hnd, undergrad, lecturers, finance, homeButton, CCSAttendance, HNDAttendance, UNDERGRADAttendance};
        for(Node n : nodeArray){
            Animator.setJackInTheBox(n);
        }

        AnimationTimer timer = new AnimationTimer() {   //Loading the live clock.
            @Override
            public void handle(long now) {   //Loading the live clock, and setting it to a label.
                dateAndTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss")));
            }
        };
        timer.start();
        Animator.setJackInTheBox(dateAndTime);


        Calendar c = Calendar.getInstance(); //Setting a customized greeting based on the time of the day.
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
           label.setText("GOOD MORNING!");
           label.setPrefWidth(376);
        }else if(timeOfDay >= 12 && timeOfDay < 16){
           label.setText("GOOD AFTERNOON!");
            label.setPrefWidth(436);
        }else if(timeOfDay >= 16 && timeOfDay < 24){
           label.setText("GOOD EVENING!");
            label.setPrefWidth(360);

        }
    }
    public void mouseEntered(MouseEvent mouseEvent) {
        String s =mouseEvent.getPickResult().getIntersectedNode().getId();
        if(s.equals("image")){
            Animator.setShake(image);
        }
       if(s.equals("ccs")){
           ccs.setTextFill(Color.WHITE);
           setFocusAndPaint(ccs);
       }
        if(s.equals("hnd")){
            hnd.setTextFill(Color.WHITE);
            setFocusAndPaint(hnd);
        }
        if(s.equals("undergrad")){
            undergrad.setTextFill(Color.WHITE);
            setFocusAndPaint(undergrad);
        }
        if(s.equals("lecturers")){
            undergrad.setTextFill(Color.WHITE);
            setFocusAndPaint(lecturers);
        }
        if(s.equals("finance")){
            finance.setTextFill(Color.WHITE);
            setFocusAndPaint(finance);

        }
        if(s.equals("homeButton")){
            homeButton.setTextFill(Color.WHITE);
            setFocusAndPaint(homeButton);

        }
        if(s.equals("CCSAttendance")){
            CCSAttendance.setTextFill(Color.WHITE);
            setFocusAndPaint(CCSAttendance);
        }
        if(s.equals("HNDAttendance")){
            HNDAttendance.setTextFill(Color.WHITE);
            setFocusAndPaint(HNDAttendance);
        }
        if(s.equals("UNDERGRADAttendance")){
            UNDERGRADAttendance.setTextFill(Color.WHITE);
            setFocusAndPaint(UNDERGRADAttendance);
        }
    }
    public void setFocusAndPaint(Node node){
        Animator.setShake(node);
        node.requestFocus();
        node.setStyle("-fx-background-color: #0c1010");
    }


    public void ccsOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.CCS);
    }

    public void hndOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.HND);
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)homeButton.getScene().getWindow(); //Getting the current stage.
        Navigator.navigate(stage, Routes.HOMESCREEN);
    }

    public void underGradOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.UNDERGRAD);
    }

    public void lecturerOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.LECTURERS);
    }
    public void CCSAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        studentType = "CCS.";
        Navigator.navigate(ap,Routes.ATTENDANCEMANAGER);
    }

    public void HNDattendanceOnAction(ActionEvent actionEvent) throws IOException {
        studentType = "HND.";
        Navigator.navigate(ap,Routes.ATTENDANCEMANAGER);
    }

    public void UNDERGRADAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        studentType = "UNDERGRAD.";
        Navigator.navigate(ap,Routes.ATTENDANCEMANAGER);
    }

    public void financeOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.PIECHART);
    }

    public void mouseExited(MouseEvent mouseEvent) {
        String s = ((Button) mouseEvent.getSource()).getId();//Getting the ID of the exited node.
        if(s.equals("ccs")){
            ccs.setTextFill(Color.BLACK);
            resetColor(ccs);
        }
        if(s.equals("hnd")){
            hnd.setTextFill(Color.BLACK);
            resetColor(hnd);
        }
        if(s.equals("undergrad")){
            undergrad.setTextFill(Color.BLACK);
            resetColor(undergrad);
        }
        if(s.equals("lecturers")){
            lecturers.setTextFill(Color.BLACK);
            resetColor(lecturers);
        }
        if(s.equals("finance")){
            finance.setTextFill(Color.BLACK);
            resetColor(finance);
        }
        if(s.equals("homeButton")){
            homeButton.setTextFill(Color.BLACK);
            resetColor(homeButton);
        }
        if(s.equals("CCSAttendance")){
            CCSAttendance.setTextFill(Color.BLACK);
            resetColor(CCSAttendance);
        }
        if(s.equals("HNDAttendance")){
            HNDAttendance.setTextFill(Color.BLACK);
            resetColor(HNDAttendance);
        }
        if(s.equals("UNDERGRADAttendance")){
            UNDERGRADAttendance.setTextFill(Color.BLACK);
            resetColor(UNDERGRADAttendance);
        }

    }
    public void resetColor(Node node){
        Animator.setShake(node);
        node.setStyle("-fx-background-color: #f3f3f5");
    }
}
