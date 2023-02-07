package com.damian.apexedu.control;

import animatefx.animation.JackInTheBox;
import animatefx.animation.LightSpeedIn;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.dto.UNDERGRAD_Announcements_DTO;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.UnderGrad_AnnouncementsService_Impl;
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

public class UNDERGRAD_Announcements_Controller implements Initializable {
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
        new JackInTheBox(label).play();
        new JackInTheBox(image).play();
        new JackInTheBox(t1).play();
        new JackInTheBox(t2).play();
        new JackInTheBox(t3).play();
        new JackInTheBox(t4).play();

        Node[] nodeArray1 = {label,image,t1,t2,t3,t4};
        for(Node n : nodeArray1){
            Animator.setJackInTheBox(n);
        }
        Node [] nodeArray2 = {add,delete,clear};
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
        UNDERGRAD_Announcements_DTO ua = new UNDERGRAD_Announcements_DTO(String.valueOf(LocalDate.now()),t2.getText(),t3.getText(),t4.getText());
        UnderGrad_AnnouncementsService_Impl underGradAnnouncementsService =ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_ANNOUNCEMENTS);
        boolean b1 = underGradAnnouncementsService.add(ua);
        if(b1){
            AlertSender.sendAlert("Added!","INFORMATION.", Alert.AlertType.INFORMATION);
            loadAnnouncements();
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        UNDERGRAD_Announcements_DTO ua = (UNDERGRAD_Announcements_DTO) table.getSelectionModel().getSelectedItem();
        System.out.println(ua.getDescription());
        if(ua==null){
            AlertSender.sendAlert("Select a row first!","ERROR!", Alert.AlertType.ERROR);
        }
        else{
           UnderGrad_AnnouncementsService_Impl underGradAnnouncementsService= ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_ANNOUNCEMENTS);
            boolean b1 = underGradAnnouncementsService.delete(ua.getDescription());
            if(b1){
                loadAnnouncements();
                AlertSender.sendAlert("Deleted!","INFORMATION.", Alert.AlertType.INFORMATION);
            }
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        TextField [] textFields = {t1,t2,t3,t4};
        for (TextField textField : textFields) {
            textField.clear();
        }
    }
    public void loadAnnouncements() {
        UnderGrad_AnnouncementsService_Impl underGradAnnouncementsService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_ANNOUNCEMENTS);
        ArrayList<UNDERGRAD_Announcements_DTO> ud = underGradAnnouncementsService.getAll();
        ObservableList<UNDERGRAD_Announcements_DTO> list = FXCollections.observableArrayList();
        for (UNDERGRAD_Announcements_DTO u: ud) {
            list.add(u);
        }
        table.setItems(list);
        table.refresh();
    }


}
