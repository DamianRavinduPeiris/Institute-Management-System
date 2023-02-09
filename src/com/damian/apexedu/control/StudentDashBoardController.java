package com.damian.apexedu.control;

import animatefx.animation.JackInTheBox;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.util.Navigator;
import com.damian.apexedu.util.Routes;
import com.jfoenix.controls.JFXButton;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

public class StudentDashBoardController implements Initializable {
    public Label dateAndTime;
    public JFXButton ccsDetails;
    public JFXButton hndDetails;
    public JFXButton undergradDetails;
    public JFXButton ccsAttendance;
    public JFXButton hndAttendance;
    public JFXButton undergradAttendance;
    public JFXButton ccsNotices;
    public JFXButton hndNotices;
    public JFXButton undergradNotices;
    public JFXButton ccsPayments;
    public JFXButton hndPayments;
    public JFXButton undergradPayements;
    public Label welcomeLabel;
    public JFXButton homeButton;
    public AnchorPane ap;
    public static  String status="";
    public Label studentName;
    public Label studentID;
    public ImageView i1;
    public ImageView i3;
    public Label greetings;
    public JFXButton obButton;
    public static  AnchorPane staticAP;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        staticAP = ap;
        AnimationTimer timer = new AnimationTimer() {   //Loading the live clock.
            @Override
            public void handle(long now) {   //Loading the live clock, and setting it to a label.
                dateAndTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss")));
            }
        };
        timer.start();
        Calendar c = Calendar.getInstance(); //Setting a customized greeting based on the time of the day.
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            greetings.setText("GOOD MORNING!");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            greetings.setText("GOOD AFTERNOON!!");
        }else if(timeOfDay >= 16 && timeOfDay < 24) {
            greetings.setText("GOOD EVENING!");
        }


        Node [] nodeArray = {i1,i3,dateAndTime,welcomeLabel,studentName,homeButton,obButton};
       for(Node n : nodeArray){
           Animator.setJackInTheBox(n);
       }
        studentName.setText("NAME - "+StudentLoginController.studentName);
        studentID.setText("ID - "+StudentLoginController.studentID);

        ccsDetails.setVisible(false);
        hndDetails.setVisible(false);
        undergradDetails.setVisible(false);
        ccsAttendance.setVisible(false);
        hndAttendance.setVisible(false);
        undergradAttendance.setVisible(false);
        ccsNotices.setVisible(false);
        hndNotices.setVisible(false);
        undergradNotices.setVisible(false);
        ccsPayments.setVisible(false);
        hndPayments.setVisible(false);
        undergradPayements.setVisible(false);


        if(StudentLoginController.status.equals("CCS.")){
            ccsDetails.setVisible(true);
            Animator.setJackInTheBox(ccsDetails);
            ccsAttendance.setVisible(true);
            Animator.setJackInTheBox(ccsAttendance);
            ccsNotices.setVisible(true);
            Animator.setJackInTheBox(ccsNotices);
            ccsPayments.setVisible(true);
            Animator.setJackInTheBox(ccsPayments);
        }
        if(StudentLoginController.status.equals("HND.")){
            hndDetails.setVisible(true);
           Animator.setJackInTheBox(hndDetails);
            hndAttendance.setVisible(true);
            Animator.setJackInTheBox(hndAttendance);
            hndNotices.setVisible(true);
            Animator.setJackInTheBox(hndNotices);
            hndPayments.setVisible(true);
            Animator.setJackInTheBox(hndPayments);
        }
        if(StudentLoginController.status.equals("UNDERGRAD.")){
            undergradDetails.setVisible(true);
            Animator.setJackInTheBox(undergradDetails);
            undergradAttendance.setVisible(true);
            Animator.setJackInTheBox(undergradAttendance);
            undergradNotices.setVisible(true);
            Animator.setJackInTheBox(undergradNotices);
            undergradPayements.setVisible(true);
            Animator.setJackInTheBox(undergradPayements);
        }


    }

    public void ccsDetailsOnAction(ActionEvent actionEvent) throws IOException {
        status="CCS.";
        Navigator.navigate(ap,Routes.CCS);
    }

    public void hndDetailsOnAction(ActionEvent actionEvent) throws IOException {
        status="HND.";
        Navigator.navigate(ap,Routes.HND);
    }

    public void undergradDetailsOnAction(ActionEvent actionEvent) throws IOException {
        status="UNDERGRAD.";
        Navigator.navigate(ap,Routes.UNDERGRAD);
    }

    public void ccsAttendanceOnAction(ActionEvent actionEvent) throws IOException {
       Navigator.navigate(ap,Routes.ATTENDANCEVIEWER);
    }

    public void hndAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.ATTENDANCEVIEWER);
    }

    public void undergradAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.ATTENDANCEVIEWER);

    }

    public void ccsNoticesOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.VIEWANNOUNCEMENTS);
    }

    public void hndNoticesOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.VIEWANNOUNCEMENTS);
    }

    public void undergradNoticesOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.VIEWANNOUNCEMENTS);
    }

    public void ccsPaymentsOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.FEES);
    }

    public void hndPaymentsOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.FEES);
    }

    public void undergradPayementsOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.FEES);
    }


    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        StudentDashBoardController.status = "";
        Stage stage = (Stage)homeButton.getScene().getWindow();
        Navigator.navigate(stage, Routes.HOMESCREEN);
    }

    public void obButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)homeButton.getScene().getWindow();
        Navigator.navigate(stage,Routes.WEB);
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        String id = mouseEvent.getPickResult().getIntersectedNode().getId();
        if(id.equals("ccsDetails")){
            ccsDetails.setTextFill(Color.BLACK);
            animateAndPaint(ccsDetails);
        }
        if(id.equals("hndDetails")){
            hndDetails.setTextFill(Color.BLACK);
            animateAndPaint(hndDetails);
        }
        if(id.equals("undergradDetails")){
            undergradDetails.setTextFill(Color.BLACK);
            animateAndPaint(undergradDetails);
        }
        if(id.equals("ccsAttendance")){
            ccsAttendance.setTextFill(Color.BLACK);
            animateAndPaint(ccsAttendance);
        }
        if(id.equals("hndAttendance")){
            hndAttendance.setTextFill(Color.BLACK);
            animateAndPaint(hndAttendance);
        }
        if(id.equals("undergradAttendance")){
            undergradAttendance.setTextFill(Color.BLACK);
            animateAndPaint(undergradAttendance);
        }
        if(id.equals("ccsNotices")){
            ccsNotices.setTextFill(Color.BLACK);
            animateAndPaint(ccsNotices);
        }
        if(id.equals("hndNotices")){
            hndNotices.setTextFill(Color.BLACK);
            animateAndPaint(hndNotices);
        }
        if(id.equals("undergradNotices")){
            undergradNotices.setTextFill(Color.BLACK);
            animateAndPaint(undergradNotices);
        }
        if(id.equals("ccsPayments")){
            ccsPayments.setTextFill(Color.BLACK);
            animateAndPaint(ccsPayments);
        }
        if(id.equals("hndPayments")){
            hndPayments.setTextFill(Color.BLACK);
            animateAndPaint(hndPayments);
        }
        if(id.equals("undergradPayements")){
            undergradPayements.setTextFill(Color.BLACK);
            animateAndPaint(undergradPayements);
        }
        if(id.equals("obButton")){
            obButton.setTextFill(Color.BLACK);
            animateAndPaint(obButton);
        }
        if(id.equals("homeButton")){
            homeButton.setTextFill(Color.BLACK);
            animateAndPaint(homeButton);
        }
        if(id.equals("i3")){
            Animator.setShake(i3);
        }

    }
    public void animateAndPaint(Node node){
        Animator.setShake(node);
        node.setStyle("-fx-background-color: #f7f7f8");
    }

    public void mouseExited(MouseEvent mouseEvent) {
        String s = ((Button) mouseEvent.getSource()).getId();
        if(s.equals("ccsDetails")){
            ccsDetails.setTextFill(Color.WHITE);
            resetColor(ccsDetails);
        }
        if(s.equals("hndDetails")){
            hndDetails.setTextFill(Color.WHITE);
            resetColor(hndDetails);
        }
        if(s.equals("undergradDetails")){
            undergradDetails.setTextFill(Color.WHITE);
            resetColor(undergradDetails);
        }
        if(s.equals("ccsAttendance")){
            ccsAttendance.setTextFill(Color.WHITE);
            resetColor(ccsAttendance);
        }
        if(s.equals("hndAttendance")){
            hndAttendance.setTextFill(Color.WHITE);
            resetColor(hndAttendance);
        }
        if(s.equals("undergradAttendance")){
            undergradAttendance.setTextFill(Color.WHITE);
            resetColor(undergradAttendance);
        }
        if(s.equals("ccsNotices")){
            ccsNotices.setTextFill(Color.WHITE);
            resetColor(ccsNotices);
        }
        if(s.equals("hndNotices")){
            hndNotices.setTextFill(Color.WHITE);
            resetColor(hndNotices);
        }
        if(s.equals("undergradNotices")){
            undergradNotices.setTextFill(Color.WHITE);
            resetColor(undergradNotices);
        }
        if(s.equals("ccsPayments")){
            ccsPayments.setTextFill(Color.WHITE);
            resetColor(ccsPayments);
        }
        if(s.equals("hndPayments")){
            hndPayments.setTextFill(Color.WHITE);
            resetColor(hndPayments);
        }
        if(s.equals("undergradPayements")){
            undergradPayements.setTextFill(Color.WHITE);
            resetColor(undergradPayements);
        }
        if(s.equals("obButton")){
            obButton.setTextFill(Color.WHITE);
            resetColor(obButton);
        }
        if(s.equals("homeButton")){
            homeButton.setTextFill(Color.WHITE);
            resetColor(homeButton);
        }

    }
    public void resetColor(Node node){
        Animator.setShake(node);
        node.setStyle("-fx-background-color: #010113");
    }
}
