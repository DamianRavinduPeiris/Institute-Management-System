package com.damian.apexedu.control;

import animatefx.animation.JackInTheBox;
import animatefx.animation.LightSpeedIn;
import animatefx.animation.RollIn;
import animatefx.animation.Shake;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.dto.Lecturer_Credentials_DTO;
import com.damian.apexedu.dto.Lecturer_Details_DTO;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.Lecturer_CredentialsServiceImpl;
import com.damian.apexedu.service.impl.Lecturer_DetailsServiceImpl;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.Navigator;
import com.damian.apexedu.util.Routes;
import com.jfoenix.controls.*;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

public class LecturerLoginController implements Initializable {
    public AnchorPane ap;
    public JFXTextField t1;
    public JFXPasswordField t2;
    public JFXCheckBox sp;
    public JFXButton loginButton;
    public Label fp;
    public JFXTextField t3;
    public Label greetings;
    public Label l1;
    public Label dateAndTime;
    public JFXComboBox cmb;
    public String [] optionsArray = {"CCS.","HND.","UNDERGRAD."};
    public static String status;
    public static  String lecName;
    public static  String lecID;
    public ImageView image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Calendar c = Calendar.getInstance(); //Setting a customized greeting based on the time of the day.
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
           greetings.setText("GOOD MORNING!");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            greetings.setText("GOOD AFTERNOON!!");
        }else if(timeOfDay >= 16 && timeOfDay < 24){
            greetings.setText("GOOD EVENING!");
        }
        Node [] nodeArray1 = {ap,dateAndTime,greetings,image};
        for(Node n : nodeArray1){
            Animator.setJackInTheBox(n);
        }
        Node [] nodeArray2 = {t1,t2,l1};
        for(Node n : nodeArray2){
            Animator.setLightSpeedIn(n);
        }
        t3.setVisible(false);


        AnimationTimer timer = new AnimationTimer() {   //Loading the live clock.
            @Override
            public void handle(long now) {   //Loading the live clock, and setting it to a label.
                dateAndTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss")));
            }
        };
        timer.start();
        ObservableList<String> obList = FXCollections.observableArrayList();
        for(String s : optionsArray){
            obList.add(s);
        }
        cmb.setItems(obList);
    }

    public void spOnAction(ActionEvent actionEvent) {
        t3.setVisible(true);
        Animator.setLightSpeedIn(t3);
        t3.setText(t2.getText());
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
                if(cmb.getValue()==null && t1.getText().isEmpty() && t2.getText().isEmpty()){
                AlertSender.sendAlert("Fields cannot be null!","WARNING!", Alert.AlertType.WARNING);

                }
                else{
                    status = cmb.getValue().toString();
                    Lecturer_CredentialsServiceImpl lcs = ServiceFactory.getServiceObject(ServiceTypes.LECTURER_CREDENTIALS);
                    Lecturer_Credentials_DTO lcdto = lcs.searchByEmail(t1.getText());//Getting the lecturer credentials from the database.

                    if(lcdto!=null && t1.getText().equals(lcdto.getUsername()) && t2.getText().equals(lcdto.getPassword())){
                        Lecturer_DetailsServiceImpl lds = ServiceFactory.getServiceObject(ServiceTypes.LECTURER_DETAILS);
                        Lecturer_Details_DTO ld = lds.search(lcdto.getLecturer_id());//Getting the lecturer details from the database.
                        lecName = ld.getLecturer_name();
                        lecID = ld.getLecturer_id();
                        Stage stage = (Stage)loginButton.getScene().getWindow();
                        Navigator.navigate(stage,Routes.LECTURERDASHBOARD);

                    }else{
                        AlertSender.sendAlert("ENTER VALID CREDENTIALS!","WARNING!", Alert.AlertType.WARNING);
                        Animator.setShake(t1);
                        Animator.setShake(t2);
                        t2.requestFocus();
                    }

                }
    }

    public void fpOnAction(MouseEvent mouseEvent) {
        AlertSender.sendAlert("Contact your IT administrator!","INFORMATION.", Alert.AlertType.INFORMATION);
    }


    public void mouseEntered(MouseEvent mouseEvent) {
        loginButton.requestFocus();
        loginButton.setRipplerFill(Paint.valueOf("RED"));
        Animator.setShake(loginButton);
    }

    public void mouseExited(MouseEvent mouseEvent) {
        loginButton.requestFocus();
        loginButton.setRipplerFill(Paint.valueOf("WHITE"));
    }

    public void spMouseEntered(MouseEvent mouseEvent) {
        sp.requestFocus();
        sp.setCheckedColor(Paint.valueOf("RED"));
    }


    public void goHomeOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage)loginButton.getScene().getWindow(); //Getting the current stage.
        Navigator.navigate(stage, Routes.HOMESCREEN);
    }

    public void t2OnAction(ActionEvent actionEvent) throws IOException {
        loginOnAction(actionEvent);
    }

    public void t1OnAction(ActionEvent actionEvent) {
        t2.requestFocus();
    }
}