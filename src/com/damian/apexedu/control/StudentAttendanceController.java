package com.damian.apexedu.control;

import animatefx.animation.JackInTheBox;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.dto.*;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.*;;
import com.damian.apexedu.util.AlertSender;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;

import java.net.URL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentAttendanceController implements Initializable {
    public ImageView image;
    public JFXComboBox idCmb;
    public JFXTextField date;
    public JFXComboBox statusCmb;
    public JFXTextField name;
    public JFXButton ccs;
    public JFXButton hnd;
    public JFXButton undergrad;
    public String [] status  = {"PRESENT.","ABSENT."};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodeArray = {image,date,idCmb,name,statusCmb,ccs,hnd,undergrad};
        for(Node node : nodeArray){
            Animator.setJackInTheBox(node);
        }
        date.setText(String.valueOf(LocalDate.now()));
        ccs.setVisible(false);
        hnd.setVisible(false);
        undergrad.setVisible(false);

        if(LecturerLoginController.status.equals("CCS.")){
            ccs.setVisible(true);

            CCS_DetailsServiceImpl ccsDetailsService= ServiceFactory.getServiceObject(ServiceTypes.CCS_DETAILS);
            ArrayList<CCS_DTO> all = ccsDetailsService.getAll();
            ObservableList<String> idList = FXCollections.observableArrayList();

            for(CCS_DTO cad : all){
                    idList.add(cad.getStudent_id());
            }
            idCmb.setItems(idList);


        }
        if(LecturerLoginController.status.equals("HND.")) {
            hnd.setVisible(true);
           HND_DetailsServiceImpl hndDetailsService  = ServiceFactory.getServiceObject(ServiceTypes.HND_DETAILS);
            ArrayList<HND_DTO> hndList = hndDetailsService.getAll();
            ObservableList<String> idList = FXCollections.observableArrayList();
            for(HND_DTO had : hndList){
                idList.add(had.getStudent_id());
            }
            idCmb.setItems(idList);
        }

        if(LecturerLoginController.status.equals("UNDERGRAD.")) {
            undergrad.setVisible(true);
     UnderGrad_DetailsServiceImpl underGradDetailsService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD);
            ArrayList<UNDERGRAD_DTO> undergrads = underGradDetailsService.getAll();
            ObservableList<String> idList = FXCollections.observableArrayList();
            for(UNDERGRAD_DTO uad : undergrads){
                idList.add(uad.getStudent_id());
            }
            idCmb.setItems(idList);
        }
        ObservableList<String> obList = FXCollections.observableArrayList();
        for(String s : status){
            obList.add(s);
        }
        statusCmb.setItems(obList);

        }

    public void idCmbOnAction(ActionEvent actionEvent) {
        if(LecturerLoginController.status.equals("CCS.")){
            CCS_DetailsServiceImpl ccsDetailsService = ServiceFactory.getServiceObject(ServiceTypes.CCS_DETAILS);
            CCS_DTO ccsDTO = ccsDetailsService.search(idCmb.getValue().toString());
            name.setText(ccsDTO.getStudent_name());
        }
        if(LecturerLoginController.status.equals("HND.")){
            HND_DetailsServiceImpl hndDetailsService = ServiceFactory.getServiceObject(ServiceTypes.HND_DETAILS);
            HND_DTO hndDTO = hndDetailsService.search(idCmb.getValue().toString());
            name.setText(hndDTO.getStudent_name());
        }
        if(LecturerLoginController.status.equals("UNDERGRAD.")){
            UnderGrad_DetailsServiceImpl underGradDetailsService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD);
            UNDERGRAD_DTO underGradDTO = underGradDetailsService.search(idCmb.getValue().toString());
            name.setText(underGradDTO.getStudent_name());
        }
    }

    public void statusCmbOnAction(ActionEvent actionEvent) {
    }

    public void ccsAttendanceOnAction(ActionEvent actionEvent) {
        CCS_Attendance_DTO ca = new CCS_Attendance_DTO(String.valueOf(LocalDate.now()),String.valueOf(idCmb.getValue()),String.valueOf(statusCmb.getValue()));
       CCS_AttendanceServiceImpl ccsAttendanceService =  ServiceFactory.getServiceObject(ServiceTypes.CCS_ATTENDANCE);
        boolean b = ccsAttendanceService.add(ca);
        if(b){
            AlertSender.sendAlert("Successfully marked!","INFORMATION", Alert.AlertType.INFORMATION);
        }
    }

    public void hndAttendanceOnAction(ActionEvent actionEvent) {
        HND_Attendance_DTO ha = new HND_Attendance_DTO(String.valueOf(LocalDate.now()),String.valueOf(idCmb.getValue()),String.valueOf(statusCmb.getValue()));
       HND_AttendanceServiceImpl hndAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.HND_ATTENDANCE);
        boolean b = hndAttendanceService.add(ha);
        if(b){
            AlertSender.sendAlert("Successfully marked!","INFORMATION", Alert.AlertType.INFORMATION);
        }

    }

    public void undergradAttendanceOnAction(ActionEvent actionEvent) {
        UNDERGRAD_Attendance_DTO ua = new UNDERGRAD_Attendance_DTO(String.valueOf(LocalDate.now()),String.valueOf(idCmb.getValue()),String.valueOf(statusCmb.getValue()));
        UnderGrad_AttendanceServiceImpl underGradAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_ATTENDANCE);
        boolean b = underGradAttendanceService.add(ua);
        if(b){
            AlertSender.sendAlert("Successfully marked!","INFORMATION", Alert.AlertType.INFORMATION);
        }
    }
}
