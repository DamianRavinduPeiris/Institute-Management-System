package com.damian.apexedu.control;

import animatefx.animation.JackInTheBox;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.dto.CCS_Announcements_DTO;
import com.damian.apexedu.dto.HND_Announcements_DTO;
import com.damian.apexedu.dto.UNDERGRAD_Announcements_DTO;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.CCS_AnnouncementsService_Impl;
import com.damian.apexedu.service.impl.HND_AnnouncementsService_Impl;
import com.damian.apexedu.service.impl.UnderGrad_AnnouncementsService_Impl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NoticeViewerController implements Initializable {
    public Label label;
    public TableView tableView;
    public TableColumn c1;
    public TableColumn c2;
    public TableColumn c3;
    public ImageView image;
    public TableColumn c4;
    public JFXButton b1;
    public JFXTextField t1;
    public static String  links;
    public static CCS_Announcements_DTO ca;
    public static HND_Announcements_DTO ha;
    public static UNDERGRAD_Announcements_DTO ua;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node [] nodeArray = {label, image, tableView, b1, t1};
        for(Node node : nodeArray){
            Animator.setJackInTheBox(node);
        }

            c1.setCellValueFactory(new PropertyValueFactory<>("date"));
            c2.setCellValueFactory(new PropertyValueFactory<>("lecturer_name"));
            c3.setCellValueFactory(new PropertyValueFactory<>("description"));
            c4.setCellValueFactory(new PropertyValueFactory<>("due_date"));

            if(StudentLoginController.status.equals("CCS.")){
              CCS_AnnouncementsService_Impl ccsAnnouncementsService =  ServiceFactory.getServiceObject(ServiceTypes.CCS_ANNOUNCEMENTS);
                ArrayList<CCS_Announcements_DTO> ccs = ccsAnnouncementsService.getAll();

                ObservableList<CCS_Announcements_DTO> obList = FXCollections.observableArrayList();
                for(CCS_Announcements_DTO c : ccs){
                    obList.add(c);
                }
                tableView.setItems(obList);


            }
        if(StudentLoginController.status.equals("HND.")){
            HND_AnnouncementsService_Impl hndAnnouncementsService = ServiceFactory.getServiceObject(ServiceTypes.HND_ANNOUNCEMENTS);
            ArrayList<HND_Announcements_DTO> hnd = hndAnnouncementsService.getAll();

            ObservableList<HND_Announcements_DTO> obList = FXCollections.observableArrayList();
            for(HND_Announcements_DTO h : hnd){
                obList.add(h);
            }
            tableView.setItems(obList);
        }
        if(StudentLoginController.status.equals("UNDERGRAD.")){
           UnderGrad_AnnouncementsService_Impl underGradAnnouncementsService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_ANNOUNCEMENTS);
            ArrayList<UNDERGRAD_Announcements_DTO> underGrad = underGradAnnouncementsService.getAll();

            ObservableList<UNDERGRAD_Announcements_DTO> obList = FXCollections.observableArrayList();
            for(UNDERGRAD_Announcements_DTO u : underGrad){
                obList.add(u);
            }
            tableView.setItems(obList);

        }

    }

    public void extractLinks(ActionEvent actionEvent) {
        if(StudentLoginController.status.equals("CCS.")){
            ca = (CCS_Announcements_DTO)tableView.getSelectionModel().getSelectedItem();
            t1.setText(ca.getDescription());

        }
        if(StudentLoginController.status.equals("HND.")){
            ha = (HND_Announcements_DTO) tableView.getSelectionModel().getSelectedItem();
            t1.setText(ha.getDescription());
        }
        if(StudentLoginController.status.equals("UNDERGRAD.")){
            ua = (UNDERGRAD_Announcements_DTO) tableView.getSelectionModel().getSelectedItem();
            t1.setText(ua.getDescription());
        }
    }
}
