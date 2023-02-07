package com.damian.apexedu.util;

import animatefx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    public static AnchorPane anchorPane;
    public static void navigate(Stage stage,Routes routes) throws IOException {
        switch (routes){
            case AdminDashBoard:
                stage.setTitle("ADMIN DASHBOARD.");
                changeUI(stage,"AdminDashBoard.fxml");
                break;
            case LECTURERLOGIN:
                stage.setTitle("LECTURER LOGIN.");
                changeUI(stage,"LecturerLoginScreen.fxml");
                break;
            case HOMESCREEN:
                stage.setMaximized(true);
                stage.setTitle("APEX INSTITUTE.");
                changeUI(stage,"HomeScreen.fxml");
                break;
            case STUDENTLOGIN:
                stage.setTitle("STUDENT LOGIN.");
                changeUI(stage,"StudentLogin.fxml");
                break;
            case LECTURERDASHBOARD:
                stage.setTitle("LECTURER DASHBOARD.");
                changeUI(stage,"LecturerDashBoard.fxml");
                break;
            case STUDENTDASHBOARD:
                stage.setTitle("STUDENT DASHBOARD.");
                changeUI(stage,"StudentDashBoard.fxml");
                break;
            case WEB:
                stage.setTitle("WEB.");
                changeUI(stage,"OnlineClasses.fxml");
                break;
            default:
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("UI NOT FOUND!");
                a.show();
                break;
        }

    }
    public static void navigate(AnchorPane ap,Routes routes) throws IOException {
        anchorPane = ap;
        anchorPane.getChildren().clear(); //Clearing the current anchor pane.
        switch (routes){
            case CCS:
                changeUI("CSSManager.fxml");
                break;
            case AdminDashBoard:
                changeUI("AdminDashBoard.fxml");
                break;
            case HND:
                changeUI("HNDManager.fxml");
                break;
            case UNDERGRAD:
                changeUI("UNDERGRADManager.fxml");
                break;
            case STUDENTSIGNUP:
                changeUI("StudentSignup.fxml");
                break;
            case LECTURERS:
                changeUI("LecturerManager.fxml");
                break;
            case CCSANNOUNCEMENTS:
                changeUI("CCSAnnouncements.fxml");
                break;
            case HNDANNOUNCEMENTS:
                changeUI("HNDAnnouncements.fxml");
                break;
            case UNDERGRADANNOUNCEMENTS:
                changeUI("UNDERGRADAnnouncements.fxml");
                break;
            case ATTENDANCE:
                changeUI("StudentAttendance.fxml");
                break;
            case ATTENDANCEVIEWER:
                changeUI("AttendanceViewer.fxml");
                break;
            case VIEWANNOUNCEMENTS:
                changeUI("NoticeViewer.fxml");
                break;
            case FEES:
                changeUI("PaymentsViewer.fxml");
                break;
            case ATTENDANCEMANAGER:
                changeUI("ManageAttendance.fxml");
                break;
            case PIECHART:
                changeUI("PieChart.fxml");
                break;
            case BARCHART:
                changeUI("BarChart.fxml");
                break;
            default:
              AlertSender.sendAlert("UI NOT FOUND!","WARNING", Alert.AlertType.WARNING);
                break;
        }

    }
    public static void changeUI(String location) throws IOException {
      anchorPane.getChildren().add(FXMLLoader.load(Navigator.class.getResource("/com/damian/apexedu/view/"+location)));
      new JackInTheBox(anchorPane).play();

    }
    public static void changeUI(Stage stage,String location ) throws IOException {
        stage.setMaximized(true);
        stage.close();
        stage.setScene(new Scene(FXMLLoader.load(Navigator.class.getResource("/com/damian/apexedu/view/"+location))));
        stage.show();
    }
}
