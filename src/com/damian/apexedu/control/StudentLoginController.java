package com.damian.apexedu.control;

import animatefx.animation.*;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.dto.*;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.*;
import com.damian.apexedu.util.AlertSender;
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
import javafx.scene.control.TextField;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentLoginController implements Initializable {
    public ImageView image;
    public AnchorPane ap;
    public JFXTextField t1;
    public JFXPasswordField t2;
    public JFXCheckBox sp;
    public Label l1;
    public JFXTextField t3;
    public Label greetings;
    public Label dateAndTime;
    public Label fpw;
    public JFXButton ccs;
    public JFXComboBox cmb;
    public JFXButton hnd;
    public JFXButton undergrad;
    public String [] optionsArray  = {"CCS.","HND.","UNDERGRAD."};
    public static String  status;
    public JFXButton clear;
    public ImageView homeImage;
    public static String studentName;
    public static String studentID;

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
        Node [] nodeArray1 = {image,ap,dateAndTime,greetings};
        for(Node n : nodeArray1){
            Animator.setJackInTheBox(n);
        }
        Node [] nodeArray2 ={t1,t2,l1};
        for(Node n : nodeArray2){
            Animator.setLightSpeedIn(n);
        }
        t3.setVisible(false);
        ccs.setVisible(false);
        hnd.setVisible(false);
        undergrad.setVisible(false);


        AnimationTimer timer = new AnimationTimer() {   //Loading the live clock.
            @Override
            public void handle(long now) {   //Loading the live clock, and setting it to a label.
                dateAndTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss")));
            }
        };
        timer.start();

        ObservableList<String>obList = FXCollections.observableArrayList();
        for(String  s : optionsArray){
            obList.add(s);
        }
        cmb.setItems(obList);

    }

    public void goHomeOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage)greetings.getScene().getWindow(); //Getting the current stage.
        Navigator.navigate(stage,Routes.HOMESCREEN);
    }

    public void spOnAction(ActionEvent actionEvent) {
        if(sp.isSelected()) {
            t3.setVisible(true);
            Animator.setLightSpeedIn(t3);
            t3.setText(t2.getText());
        }
        if(!sp.isSelected()){
            t3.setVisible(false);
            Animator.setLightSpeedOut(t3);
        }

    }






    public void fpwOnAction(MouseEvent mouseEvent) {
        AlertSender.sendAlert("Contact your IT administrator!","INFORMATION.", Alert.AlertType.INFORMATION);

    }

    public void undergradOnAction(ActionEvent actionEvent) {
        UnderGrad_CredentialsServiceImpl underGradCredentialsService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_CREDENTIALS);
        Optional<UNDERGRAD_Student_Credentials_DTO> undergradStudentCredentialsDto = underGradCredentialsService.searchByEmail(t1.getText());

        if(undergradStudentCredentialsDto!=null && t1.getText().equals(undergradStudentCredentialsDto.get().getUsername()) && t2.getText().equals(undergradStudentCredentialsDto.get().getPassword())){

            UnderGrad_DetailsServiceImpl underGradDetailsService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD);
            UNDERGRAD_DTO usd = underGradDetailsService.search(undergradStudentCredentialsDto.get().getStudent_id());

            status = "UNDERGRAD.";
            studentName = usd.getStudent_name();
            studentID = usd.getStudent_id();

            Stage stage = (Stage)undergrad.getScene().getWindow();
            try {
                Navigator.navigate(stage,Routes.STUDENTDASHBOARD);
            } catch (IOException e) {
                AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR.", Alert.AlertType.ERROR);
            }

        }

        else{
            AlertSender.sendAlert("Enter valid credentials!","ERROR.", Alert.AlertType.ERROR);
        }

    }

    public void hndOnAction(ActionEvent actionEvent) {
        HND_CredentialsServiceImpl hndCredentialsService = ServiceFactory.getServiceObject(ServiceTypes.HND_CREDENTIALS);
        Optional<HND_Student_Credentials_DTO> hsc = hndCredentialsService.searchByEmail(t1.getText());

        if(hsc!=null && t1.getText().equals(hsc.get().getUsername()) && t2.getText().equals(hsc.get().getPassword()) ){
            HND_DetailsServiceImpl hndDetailsService = ServiceFactory.getServiceObject(ServiceTypes.HND_DETAILS);
            HND_DTO hd = hndDetailsService.search(hsc.get().getStudent_id());
            status = "HND.";
            studentName = hd.getStudent_name();
            studentID = hd.getStudent_id();
            Stage stage = (Stage)hnd.getScene().getWindow();
            try {
                Navigator.navigate(stage,Routes.STUDENTDASHBOARD);
            } catch (IOException e) {
                AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR.", Alert.AlertType.ERROR);
            }

        }
        else{
            AlertSender.sendAlert("Enter valid credentials!","ERROR.", Alert.AlertType.ERROR);
        }

    }

    public void cmbOnAction(ActionEvent actionEvent) {
        if(cmb.getValue().toString().equals("CCS.")){
            status = "CCS.";
            t1.requestFocus();
            ccs.setVisible(true);
            Animator.setLightSpeedIn(ccs);
        }
        else{

            ccs.setVisible(false);
        }
        if(cmb.getValue().toString().equals("HND.")){
            status = "HND.";
            hnd.setVisible(true);
            t1.requestFocus();
            Animator.setLightSpeedIn(hnd);
        }
        else{
            hnd.setVisible(false);
        }
        if(cmb.getValue().toString().equals("UNDERGRAD.")){
            status = "UNDERGRAD.";
            undergrad.setVisible(true);
            t1.requestFocus();
            Animator.setLightSpeedIn(undergrad);
        }
        else{
            undergrad.setVisible(false);

        }
    }

    public void ccsOnAction(ActionEvent actionEvent) {
        CCS_CredentialImplService ccsCredentialsService = ServiceFactory.getServiceObject(ServiceTypes.CCS_CREDENTIALS);
        Optional<CCS_Student_Credentials_DTO> csc = ccsCredentialsService.searchByEmail(t1.getText());


        if(csc!=null && t1.getText().equals(csc.get().getUsername()) && t2.getText().equals(csc.get().getPassword())){
            CCS_DetailsServiceImpl ccsDetailsService = ServiceFactory.getServiceObject(ServiceTypes.CCS_DETAILS);
            CCS_DTO cd = ccsDetailsService.search(csc.get().getStudent_id());
            status = "CCS.";
            studentName = cd.getStudent_name();
            studentID = cd.getStudent_id();
            Stage stage = (Stage)ccs.getScene().getWindow();
            try {
                Navigator.navigate(stage,Routes.STUDENTDASHBOARD);
            } catch (IOException e) {
                AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR.", Alert.AlertType.ERROR);
            }
        }
        else{
            AlertSender.sendAlert("Enter valid credentials!","ERROR.", Alert.AlertType.ERROR);
        }

    }

    public void clearOnAction(ActionEvent actionEvent) {
        TextField [] textFields = {t1,t2};
        for (TextField textField : textFields) {
            textField.clear();
        }
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        String s =mouseEvent.getPickResult().getIntersectedNode().getId();
        if(s.equals("ccs")){
            animateAndRefill(ccs);
        }
        if(s.equals("hnd")){
            animateAndRefill(hnd);
        }
        if(s.equals("undergrad")){
            animateAndRefill(undergrad);
        }
        if(s.equals("clear")){
            animateAndRefill(clear);
        }
        if(s.equals("homeImage")){
            animateAndRefill(homeImage);
        }
    }
    public void animateAndRefill(JFXButton button){
        new Shake(button).play();
        button.requestFocus();
        button.setRipplerFill(Paint.valueOf("RED"));
    }
    public void animateAndRefill(ImageView image) {
        new Shake(image).play();
        image.requestFocus();

    }


    public void t1OnAction(ActionEvent actionEvent) {
        t2.requestFocus();
    }

    public void t2OnAction(ActionEvent actionEvent) {
        if(status.equals("CCS.")){
            ccsOnAction(actionEvent);
        }
        if(status.equals("HND.")){
            hndOnAction(actionEvent);
        }

        if(status.equals("UNDERGRAD.")){
            undergradOnAction(actionEvent);
        }

    }
}
