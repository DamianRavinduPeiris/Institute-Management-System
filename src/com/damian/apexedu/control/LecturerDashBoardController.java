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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
}
