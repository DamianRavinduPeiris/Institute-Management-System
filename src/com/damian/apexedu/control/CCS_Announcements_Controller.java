package com.damian.apexedu.control;

import animatefx.animation.JackInTheBox;
import animatefx.animation.LightSpeedIn;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.dto.CCS_Announcements_DTO;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.CCS_AnnouncementsService_Impl;
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

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CCS_Announcements_Controller implements Initializable {
    public Label label;
    public JFXTextField t1;
    public JFXTextField t2;
    public JFXTextField t3;
    public JFXTextField t4;
    public ImageView image;
    public JFXButton add;
    public JFXButton delete;
    public JFXButton clear;
    public TableView table;
    public TableColumn c1;
    public TableColumn c2;
    public TableColumn c3;
    public TableColumn c4;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Node[] nodeArray1 = {label,image,t1,t2,t3,t4};
        Node [] nodeArray2 = {add,delete,clear};

        for(Node n : nodeArray1){
            Animator.setJackInTheBox(n);
        }
        for(Node n : nodeArray2){
            Animator.setLightSpeedIn(n);
        }

        t1.setText(String.valueOf(LocalDate.now()));


        c1.setCellValueFactory(new PropertyValueFactory<>("date"));
        c2.setCellValueFactory(new PropertyValueFactory<>("lecturer_name"));
        c3.setCellValueFactory(new PropertyValueFactory<>("description"));
        c4.setCellValueFactory(new PropertyValueFactory<>("due_date"));
        loadAnnouncements();

    }

    public void addOnAction(ActionEvent actionEvent) {
        if(t1.getText()==null || t2.getText()==null || t3.getText()==null || t4.getText()==null){
            AlertSender.sendAlert("FIELDS CANNOT BE NULL!", "WARNING", Alert.AlertType.WARNING);
        }
        CCS_Announcements_DTO ca = new CCS_Announcements_DTO(String.valueOf(LocalDate.now()),t2.getText(),t3.getText(),t4.getText());
        CCS_AnnouncementsService_Impl ccsAnnouncementsService = ServiceFactory.getServiceObject(ServiceTypes.CCS_ANNOUNCEMENTS);
        boolean b = ccsAnnouncementsService.add(ca);
        if(b) {
            AlertSender.sendAlert("Added!","INFORMATION.", Alert.AlertType.INFORMATION);
          loadAnnouncements();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        CCS_Announcements_DTO ca = (CCS_Announcements_DTO) table.getSelectionModel().getSelectedItem();
        if(ca==null){
            AlertSender.sendAlert("Select a record before deleting!","ERROR.", Alert.AlertType.ERROR);
        }
        else{
           CCS_AnnouncementsService_Impl ccsAnnouncementsService = ServiceFactory.getServiceObject(ServiceTypes.CCS_ANNOUNCEMENTS);
                boolean b = ccsAnnouncementsService.delete(ca.getDescription());
                if(b) {
                   loadAnnouncements();
                    AlertSender.sendAlert("Deleted!","INFORMATION.", Alert.AlertType.INFORMATION);
                }
        }


    }

    public void clearOnAction(ActionEvent actionEvent) {
        TextField [] textFields = {t1,t2,t3,t4};
        for(TextField t : textFields){
            t.clear();
        }
    }
    public void loadAnnouncements() {
        CCS_AnnouncementsService_Impl ccsAnnouncementsService = ServiceFactory.getServiceObject(ServiceTypes.CCS_ANNOUNCEMENTS);
        ArrayList<CCS_Announcements_DTO> ccsAnnouncements = ccsAnnouncementsService.getAll();
        ObservableList<CCS_Announcements_DTO> list = FXCollections.observableArrayList();
        for (CCS_Announcements_DTO ccsAnnouncement : ccsAnnouncements) {
            list.add(ccsAnnouncement);
        }
        table.setItems(list);
        table.refresh();
    }


}
