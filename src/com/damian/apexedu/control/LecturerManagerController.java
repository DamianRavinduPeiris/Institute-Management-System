package com.damian.apexedu.control;

import animatefx.animation.LightSpeedIn;
import animatefx.animation.Shake;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.dto.Lecturer_Credentials_DTO;
import com.damian.apexedu.dto.Lecturer_Details_DTO;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.Lecturer_CredentialsServiceImpl;
import com.damian.apexedu.service.impl.Lecturer_DetailsServiceImpl;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.Patterns;
import com.damian.apexedu.util.Validator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;



import java.net.URL;
import java.util.ResourceBundle;

public class LecturerManagerController implements Initializable {
    public JFXTextField t2;
    public JFXTextField t3;
    public JFXTextField t4;
    public JFXTextField t5;
    public JFXComboBox cmb;
    public JFXButton add;
    public JFXButton view;
    public JFXButton update;
    public JFXButton delete;
    public JFXComboBox cmb1;
    public JFXTextField t1;
    public JFXButton clear;
    public JFXTextField t6;
    public JFXTextField t7;
    public ImageView image;
    public JFXTextField t8;
    public JFXTextField t9;
    public String [] optionsArray = {"ADD A LECTURER.","VIEW DETAILS OF A LECTURER.","UPDATE AN EXISTING LECTURER.","DELETE A LECTURER."};
    public String [] pidArray = {"P001.","P002.","P003."};
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Animator.setShake(cmb);
        ObservableList<String> obList = FXCollections.observableArrayList();
        for(String s : optionsArray){
            obList.add(s);
        }
        cmb.setItems(obList);
        ObservableList<String> obList1 = FXCollections.observableArrayList();
        for(String s : pidArray){
            obList1.add(s);
        }
        cmb1.setItems(obList1);
        add.setVisible(false);
        view.setVisible(false);
        update.setVisible(false);
        delete.setVisible(false);
        if(LecturerDashBoardController.status.equals("lecturerDashBoard")){
            cmb.setVisible(false);
            update.setVisible(true);
            cmb1.setVisible(true);
            t1.setText(LecturerLoginController.lecID);
            t1OnAction(new ActionEvent());
          }
        else{
            update.setVisible(false);
            cmb.setVisible(true);
        }

    }

    public void cmbOnAction(ActionEvent actionEvent) {
        if(cmb.getValue().equals(optionsArray[0])){
            add.setVisible(true);
            Animator.setLightSpeedIn(add);
        }
        else{
            add.setVisible(false);
        }
        if(cmb.getValue().equals(optionsArray[1])){
            view.setVisible(true);
            Animator.setLightSpeedIn(view);
        }
        else{
            view.setVisible(false);
        }
        if(cmb.getValue().equals(optionsArray[2])){
            update.setVisible(true);
            Animator.setLightSpeedIn(update);
        }
        else{
            update.setVisible(false);
        }
        if(cmb.getValue().equals(optionsArray[3])){
            delete.setVisible(true);
            Animator.setLightSpeedIn(delete);
        }
        else{
            delete.setVisible(false);
        }
    }

    public void addOnAction(ActionEvent actionEvent) {
        boolean c1 = Validator.check(t4.getText(), Patterns.EMAIL);
        if(c1){
            boolean c2 = Validator.check(t5.getText(), Patterns.TELEPHONE);
            if(c2){
                boolean c3 = Validator.check(t7.getText(), Patterns.USERNAME);
                if(c3){
                    Lecturer_Details_DTO ld = new Lecturer_Details_DTO(t1.getText(),cmb1.getValue().toString(),t2.getText(),t3.getText(),t4.getText(),Integer.parseInt(t5.getText()),Double.parseDouble(t6.getText()));
                    Lecturer_Credentials_DTO lc = new Lecturer_Credentials_DTO(t1.getText(),t7.getText(),t8.getText());

                    Lecturer_DetailsServiceImpl lecturerDetailsService = ServiceFactory.getServiceObject(ServiceTypes.LECTURER_DETAILS);
                    lecturerDetailsService.setValues(lc);
                    boolean b1 = lecturerDetailsService.add(ld);
                    if(b1){
                        AlertSender.sendAlert("Added!","INFORMATION.", Alert.AlertType.INFORMATION);
                    }
                }
                else{
                   focusAndAnimate(t7);
                }
            }
            else{
               focusAndAnimate(t5);
            }
        }
        else{
            focusAndAnimate(t4);
        }


    }
    public void focusAndAnimate(JFXTextField t){
        t.requestFocus();
        Animator.setShake(t);
        t.setFocusColor(Paint.valueOf("RED"));
    }

    public void viewOnAction(ActionEvent actionEvent) {
            Lecturer_DetailsServiceImpl lecturerDetailsService = ServiceFactory.getServiceObject(ServiceTypes.LECTURER_DETAILS);
        Lecturer_Details_DTO ld = lecturerDetailsService.search(t1.getText());
       Lecturer_CredentialsServiceImpl lecturerCredentialsService =  ServiceFactory.getServiceObject(ServiceTypes.LECTURER_CREDENTIALS);
        Lecturer_Credentials_DTO lc = lecturerCredentialsService.search(t1.getText());

        if(ld!=null && lc!=null){
            ObservableList<String> oblist = FXCollections.observableArrayList();
            oblist.add(ld.getProgram_id());
            cmb1.setItems(oblist);
            t2.setText(ld.getLecturer_name());
            t3.setText(ld.getLecturer_address());
            t4.setText(ld.getLecturer_email());
            t5.setText(String.valueOf(ld.getLecturer_telephone()));
            t6.setText(String.valueOf(ld.getBasic_salary()));
            t7.setText(lc.getUsername());
            t8.setText(lc.getPassword());

        }
        else{
            AlertSender.sendAlert("Lecturer Not Found!","Error!", Alert.AlertType.ERROR);

        }

    }

    public void updateOnAction(ActionEvent actionEvent) {

        Lecturer_Details_DTO ld = new Lecturer_Details_DTO(t1.getText(),cmb1.getValue().toString(),t2.getText(),t3.getText(),t4.getText(),Integer.parseInt(t5.getText()),Double.parseDouble(t6.getText()));
        Lecturer_Credentials_DTO lc = new Lecturer_Credentials_DTO(t1.getText(),t7.getText(),t8.getText());

        Lecturer_DetailsServiceImpl lecturerDetailsService = ServiceFactory.getServiceObject(ServiceTypes.LECTURER_DETAILS);
        lecturerDetailsService.setValues(lc);
        boolean b = lecturerDetailsService.update(ld);
        if(b){
            AlertSender.sendAlert("Updated!","INFORMATION.", Alert.AlertType.INFORMATION);
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
            Lecturer_DetailsServiceImpl lecturerDetailsService = ServiceFactory.getServiceObject(ServiceTypes.LECTURER_DETAILS);
        boolean b = lecturerDetailsService.delete(t1.getText());
        if(b){
            AlertSender.sendAlert("Deleted!!","INFORMATION.", Alert.AlertType.INFORMATION);
        }
    }

    public void t1OnAction(ActionEvent actionEvent) {

        viewOnAction(actionEvent);
    }

    public void clearOnAction(ActionEvent actionEvent) {
        TextField [] textFields = {t1,t2,t3,t4,t5,t6,t7,t8};
        for (TextField textField : textFields) {
            textField.clear();
        }
        t4.setPromptText("LECTURER EMAIL.");
        t5.setPromptText("LECTURER CONTACT NUMBER.");
        t8.setPromptText("ENTER USERNAME. (MUST BE YOUR EMAIL!)");
    }


}
