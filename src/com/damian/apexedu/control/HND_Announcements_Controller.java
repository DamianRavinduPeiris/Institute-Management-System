package com.damian.apexedu.control;

import animatefx.animation.JackInTheBox;
import animatefx.animation.LightSpeedIn;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.dto.HND_Announcements_DTO;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.HND_AnnouncementsService_Impl;

import com.damian.apexedu.util.AlertSender;

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

public class HND_Announcements_Controller implements Initializable {
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
        Node[] nodeArray1  = {label,image,t1,t2,t3,t4};
        for(Node n : nodeArray1){
            Animator.setJackInTheBox(n);
        }

        Node[] nodeArray2 = {add,delete,clear};
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
        HND_Announcements_DTO ha = new HND_Announcements_DTO(String.valueOf(LocalDate.now()),t2.getText(),t3.getText(),t4.getText());
        if(t1.getText()==null || t2.getText()==null || t3.getText()==null || t4.getText()==null){
            AlertSender.sendAlert("Fields cannot be empty!","ERROR!", Alert.AlertType.ERROR);
        }
        HND_AnnouncementsService_Impl hndAnnouncementsService = ServiceFactory.getServiceObject(ServiceTypes.HND_ANNOUNCEMENTS);
        boolean b1 = hndAnnouncementsService.add(ha);
        if(b1){
           loadAnnouncements();
            AlertSender.sendAlert("Added!","INFORMATION.", Alert.AlertType.INFORMATION);

        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        HND_Announcements_DTO ha = (HND_Announcements_DTO) table.getSelectionModel().getSelectedItem();
        if(ha==null){
            AlertSender.sendAlert("Select a record before deleting!","ERROR.", Alert.AlertType.ERROR);
        }
        else{
            HND_AnnouncementsService_Impl hndAnnouncementsService= ServiceFactory.getServiceObject(ServiceTypes.HND_ANNOUNCEMENTS);
            boolean b1 = hndAnnouncementsService.delete(ha.getDescription());
            if(b1){
                loadAnnouncements();
                AlertSender.sendAlert("Announcement deleted successfully!","INFORMATION!", Alert.AlertType.INFORMATION);
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
        HND_AnnouncementsService_Impl hndAnnouncementsService = ServiceFactory.getServiceObject(ServiceTypes.HND_ANNOUNCEMENTS);
        ArrayList<HND_Announcements_DTO> all = hndAnnouncementsService.getAll();
        ObservableList<HND_Announcements_DTO>observableList = FXCollections.observableArrayList();
        for(HND_Announcements_DTO hnd_announcements : all){
            observableList.add(hnd_announcements);
        }
        table.setItems(observableList);
    }


}
