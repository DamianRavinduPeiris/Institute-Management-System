package com.damian.apexedu.control;

import animatefx.animation.JackInTheBox;
import animatefx.animation.LightSpeedIn;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.dto.CCS_Attendance_DTO;
import com.damian.apexedu.dto.HND_Attendance_DTO;
import com.damian.apexedu.dto.UNDERGRAD_Attendance_DTO;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.CCS_AttendanceServiceImpl;
import com.damian.apexedu.service.impl.HND_AttendanceServiceImpl;
import com.damian.apexedu.service.impl.UnderGrad_AttendanceServiceImpl;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceManagerController implements Initializable {
    public Label label;
    public JFXTextField t1;
    public JFXTextField t2;
    public JFXTextField t3;
    public JFXButton clear;
    public JFXButton updateButton;
    public JFXButton delete;
    public ImageView image;
    public TableView tableView;
    public TableColumn c1;
    public TableColumn c2;
    public TableColumn c3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node [] nodeArray1 = {label,image,updateButton,delete,clear};
        for(Node n : nodeArray1){
            Animator.setJackInTheBox(n);
        }
        Node [] nodeArray2 = {t1,t2,t3};

        for(Node n : nodeArray2){
            Animator.setLightSpeedIn(n);

        }

        c1.setCellValueFactory(new PropertyValueFactory<>("date"));
        c2.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        c3.setCellValueFactory(new PropertyValueFactory<>("status"));


        if (AdminDashBoardController.studentType.equals("CCS.")) {
            CCS_AttendanceServiceImpl ccsAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.CCS_ATTENDANCE);
            List<CCS_Attendance_DTO> ccs = ccsAttendanceService.getAll();
            ObservableList<CCS_Attendance_DTO> ccsAttendanceDTOS = FXCollections.observableArrayList(ccs);
            for (CCS_Attendance_DTO cad : ccs) {
                ccsAttendanceDTOS.add(cad);
            }
            tableView.setItems(ccsAttendanceDTOS);
        }
        if (AdminDashBoardController.studentType.equals("HND.")) {
            HND_AttendanceServiceImpl hndAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.HND_ATTENDANCE);
            List<HND_Attendance_DTO> hnd = hndAttendanceService.getAll();
            ObservableList<HND_Attendance_DTO> hndAttendanceDTOS = FXCollections.observableArrayList(hnd);
            for (HND_Attendance_DTO had : hnd) {
                hndAttendanceDTOS.add(had);
            }
            tableView.setItems(hndAttendanceDTOS);
        }
        if(AdminDashBoardController.studentType.equals("UNDERGRAD.")){
           UnderGrad_AttendanceServiceImpl underGradAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_ATTENDANCE);
            List<UNDERGRAD_Attendance_DTO> undergrad = underGradAttendanceService.getAll();
            ObservableList<UNDERGRAD_Attendance_DTO> underGradAttendanceDTOS = FXCollections.observableArrayList(undergrad);
            for(UNDERGRAD_Attendance_DTO uad: undergrad){
                underGradAttendanceDTOS.add(uad);
            }
            tableView.setItems(underGradAttendanceDTOS);

            }

        }

    public void clearOnAction(ActionEvent actionEvent) {
        TextField[] textFields = {t1, t2, t3};
        for (TextField textField : textFields) {
            textField.clear();
        }
    }


    public void updateOnAction(ActionEvent actionEvent) {
        if(AdminDashBoardController.studentType.equals("CCS.")) {
            CCS_AttendanceServiceImpl ccsAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.CCS_ATTENDANCE);
            boolean b1 = ccsAttendanceService.update(new CCS_Attendance_DTO(t1.getText(), t2.getText(), t3.getText()));
            if(b1){
                AlertSender.sendAlert("Updated!","INFORMATION.", Alert.AlertType.INFORMATION);
            }
        }
        if(AdminDashBoardController.studentType.equals("HND.")){
            HND_AttendanceServiceImpl hndAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.HND_ATTENDANCE);
            boolean b1 = hndAttendanceService.update(new HND_Attendance_DTO(t1.getText(), t2.getText(), t3.getText()));
            if(b1){
                AlertSender.sendAlert("Updated!","INFORMATION.", Alert.AlertType.INFORMATION);
            }
        }
        if (AdminDashBoardController.studentType.equals("UNDERGRAD.")){
            UnderGrad_AttendanceServiceImpl underGradAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_ATTENDANCE);
            boolean b1 = underGradAttendanceService.update(new UNDERGRAD_Attendance_DTO(t1.getText(), t2.getText(), t3.getText()));
            if(b1){
                AlertSender.sendAlert("Updated!","INFORMATION.", Alert.AlertType.INFORMATION);
            }
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        if(AdminDashBoardController.studentType.equals("CCS.")) {
            CCS_AttendanceServiceImpl ccsAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.CCS_ATTENDANCE);
            boolean b = ccsAttendanceService.delete(t2.getText());
            if(b){
                AlertSender.sendAlert("Deleted!!","INFORMATION.", Alert.AlertType.INFORMATION);
            }
        }
        if(AdminDashBoardController.studentType.equals("HND.")){
            HND_AttendanceServiceImpl hndAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.HND_ATTENDANCE);
            boolean b = hndAttendanceService.delete(t2.getText());
            if(b){
                AlertSender.sendAlert("Deleted!!","INFORMATION.", Alert.AlertType.INFORMATION);
            }
        }
        if(AdminDashBoardController.studentType.equals("UNDERGRAD.")){
            UnderGrad_AttendanceServiceImpl underGradAttendanceService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_ATTENDANCE);
            boolean b = underGradAttendanceService.delete(t2.getText());
            if(b){
                AlertSender.sendAlert("Deleted!!","INFORMATION.", Alert.AlertType.INFORMATION);
            }
        }
    }


    public void clicked(MouseEvent mouseEvent) {
        if(AdminDashBoardController.studentType.equals("CCS.")) {
            CCS_Attendance_DTO ca = (CCS_Attendance_DTO) tableView.getSelectionModel().getSelectedItem();
            if(ca!=null) {
                t1.setText(ca.getDate());
                t2.setText(ca.getStudent_id());
                t3.setText(ca.getStatus());
            }
            else {
                AlertSender.sendAlert("No records to display!","ERROR.", Alert.AlertType.ERROR);
            }
        }
        if(AdminDashBoardController.studentType.equals("HND.")) {
           HND_Attendance_DTO ha = (HND_Attendance_DTO) tableView.getSelectionModel().getSelectedItem();
           if(ha!=null){
               t1.setText(ha.getDate());
               t2.setText(ha.getStudent_id());
               t3.setText(ha.getStatus());
           }
            else{
               AlertSender.sendAlert("No records to display!","ERROR.", Alert.AlertType.ERROR);
            }
        }
        if(AdminDashBoardController.studentType.equals("UNDERGRAD.")) {
            UNDERGRAD_Attendance_DTO ua = (UNDERGRAD_Attendance_DTO) tableView.getSelectionModel().getSelectedItem();
            if(ua!=null) {
                t1.setText(ua.getDate());
                t2.setText(ua.getStudent_id());
                t3.setText(ua.getStatus());
            }
            else {
                AlertSender.sendAlert("No records to display!","ERROR.", Alert.AlertType.ERROR);
            }
        }
    }
}
