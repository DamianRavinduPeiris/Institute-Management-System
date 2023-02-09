package com.damian.apexedu.control;

import animatefx.animation.JackInTheBox;
import animatefx.animation.Shake;
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
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.MotionBlur;
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

public class LecturerDashBoardController implements Initializable {
    public Label dateAndTime;
    public AnchorPane ap;
    public JFXButton myDetailsButton;
    public JFXButton ccsAttendance;
    public JFXButton hndAttendance;
    public JFXButton undergradAttendance;
    public JFXButton ccsAnnouncements;
    public JFXButton hndAnnouncements;
    public JFXButton undergradAnnouncements;
    public JFXButton manageCCS;
    public JFXButton manageHND;
    public JFXButton manageUndergrad;
    public JFXButton homeButton;
    public Label lecturerName;
    public ImageView image;
    public static String status="";
    public Label greetings;
    public Label lecturerID;
    public ImageView image1;
    public Label welcomeLabel;
    public static AnchorPane staticPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        staticPane = ap;
        ccsAttendance.setVisible(false);
        hndAttendance.setVisible(false);
        undergradAttendance.setVisible(false);
        ccsAnnouncements.setVisible(false);
        hndAnnouncements.setVisible(false);
        undergradAnnouncements.setVisible(false);
        manageCCS.setVisible(false);
        manageHND.setVisible(false);
        manageUndergrad.setVisible(false);
        lecturerName.setText(LecturerLoginController.lecName+"!");

        Node[] nodeArray1 = {myDetailsButton,ccsAttendance,hndAttendance,undergradAttendance,ccsAnnouncements,hndAnnouncements,undergradAnnouncements,manageCCS,manageHND,manageUndergrad,homeButton};
        for (Node node : nodeArray1) {
            Animator.setShake(node);
        }
        Node [] nodeArray2 = {image,image1,welcomeLabel,greetings,lecturerName,lecturerID,dateAndTime};
        for (Node node : nodeArray2) {
            Animator.setJackInTheBox(node);
        }

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
        lecturerName.setText("NAME - "+LecturerLoginController.lecName);
        lecturerID.setText("ID - "+LecturerLoginController.lecID);

        if(LecturerLoginController.status.equals("CCS.")){
            ccsAttendance.setVisible(true);
            Animator.setJackInTheBox(ccsAttendance);
            ccsAnnouncements.setVisible(true);
            Animator.setJackInTheBox(ccsAnnouncements);
            manageCCS.setVisible(true);
            Animator.setJackInTheBox(manageCCS);

        }
        if(LecturerLoginController.status.equals("HND.")){
            hndAttendance.setVisible(true);
            Animator.setJackInTheBox(hndAttendance);
           hndAnnouncements.setVisible(true);
            Animator.setJackInTheBox(hndAnnouncements);
            manageHND.setVisible(true);
            Animator.setJackInTheBox(manageHND);
        }
        if(LecturerLoginController.status.equals("UNDERGRAD.")){
            undergradAttendance.setVisible(true);
            Animator.setJackInTheBox(undergradAttendance);
            undergradAnnouncements.setVisible(true);
            Animator.setJackInTheBox(undergradAnnouncements);
            manageUndergrad.setVisible(true);
            Animator.setJackInTheBox(manageUndergrad);
        }
    }

    public void ccsOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap, Routes.CCS);
    }

    public void ccsAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.ATTENDANCE);
    }

    public void hndAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.ATTENDANCE);
    }

    public void undergradAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.ATTENDANCE);
    }

    public void ccsAnnouncementsOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.CCSANNOUNCEMENTS);
    }

    public void hndAnnouncementsOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.HNDANNOUNCEMENTS);
    }

    public void undergradAnnouncementsOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.UNDERGRADANNOUNCEMENTS);
    }

    public void manageCCSOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.CCS);
    }

    public void manageHNDOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.HND);

    }

    public void manageUndergradOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(ap,Routes.UNDERGRAD);
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        status = "home";
        Stage stage = (Stage)homeButton.getScene().getWindow();
        Navigator.navigate(stage,Routes.HOMESCREEN);
    }

    public void myDetailsOnAction(ActionEvent actionEvent) throws IOException {
        status = "lecturerDashBoard";
        Navigator.navigate(ap,Routes.LECTURERS);
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        String s = mouseEvent.getPickResult().getIntersectedNode().getId();
        if(s.equals("image")){
            Animator.setShake(image);
        }
        if(s.equals("myDetailsButton")){
            myDetailsButton.setTextFill(Color.BLACK);
            animateAndPaint(myDetailsButton);
        }if(s.equals("ccsAttendance")){
            ccsAttendance.setTextFill(Color.BLACK);
            animateAndPaint(ccsAttendance);
        }
        if(s.equals("hndAttendance")){
            hndAttendance.setTextFill(Color.BLACK);
            animateAndPaint(hndAttendance);
        }
        if(s.equals("undergradAttendance")){
            undergradAttendance.setTextFill(Color.BLACK);
            animateAndPaint(undergradAttendance);
        }
        if(s.equals("ccsAnnouncements")){
            ccsAnnouncements.setTextFill(Color.BLACK);
            animateAndPaint(ccsAnnouncements);
        }
        if(s.equals("hndAnnouncements")){
            hndAnnouncements.setTextFill(Color.BLACK);
            animateAndPaint(hndAnnouncements);
        }
        if(s.equals("undergradAnnouncements")){
            undergradAnnouncements.setTextFill(Color.BLACK);
            animateAndPaint(undergradAnnouncements);
        }
        if(s.equals("manageCCS")){
            manageCCS.setTextFill(Color.BLACK);
            animateAndPaint(manageCCS);
        }
        if (s.equals("manageHND")){
            manageHND.setTextFill(Color.BLACK);
            animateAndPaint(manageHND);
        }
        if(s.equals("manageUndergrad")){
            manageUndergrad.setTextFill(Color.BLACK);
            animateAndPaint(manageUndergrad);
        }
        if(s.equals("homeButton")){
            homeButton.setTextFill(Color.BLACK);
            animateAndPaint(homeButton);
        }
    }
    public void animateAndPaint(Node node){
        Animator.setShake(node);
        node.requestFocus();
        node.setStyle("-fx-background-color: #ffffff");
    }


    public void mouseExited(MouseEvent mouseEvent) {
        String s = ((Button) mouseEvent.getSource()).getId();//Getting the ID of the exited node.
        if(s.equals("myDetailsButton")){
            myDetailsButton.setTextFill(Color.WHITE);
            resetColor(myDetailsButton);
        }if(s.equals("ccsAttendance")){
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
        if(s.equals("ccsAnnouncements")){
            ccsAnnouncements.setTextFill(Color.WHITE);
            resetColor(ccsAnnouncements);
        }
        if(s.equals("hndAnnouncements")){
            hndAnnouncements.setTextFill(Color.WHITE);
            resetColor(hndAnnouncements);
        }
        if(s.equals("undergradAnnouncements")){
            undergradAnnouncements.setTextFill(Color.WHITE);
            resetColor(undergradAnnouncements);
        }
        if(s.equals("manageCCS")){
            manageCCS.setTextFill(Color.WHITE);
            resetColor(manageCCS);
        }
        if (s.equals("manageHND")){
            manageHND.setTextFill(Color.WHITE);
            resetColor(manageHND);
        }
        if(s.equals("manageUndergrad")){
            manageUndergrad.setTextFill(Color.WHITE);
            resetColor(manageUndergrad);
        }
        if(s.equals("homeButton")){
            homeButton.setTextFill(Color.WHITE);
            resetColor(homeButton);
        }

    }
    public void resetColor(Node node){
        Animator.setShake(node);
        node.setStyle("-fx-background-color: #0e0e0e");
    }
}
