package com.damian.apexedu.control;

import animatefx.animation.JackInTheBox;
import animatefx.animation.Shake;
import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.dto.CCS_Fees_DTO;
import com.damian.apexedu.dto.HND_Fees_DTO;
import com.damian.apexedu.dto.UNDERGRAD_Fees_DTO;

import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.CCS_FeesServiceImpl;
import com.damian.apexedu.service.impl.HND_FeesServiceImpl;
import com.damian.apexedu.service.impl.UnderGrad_FeesServiceImpl;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.Navigator;
import com.damian.apexedu.util.Routes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentsViewerController implements Initializable {
    public Label label;
    public TableView tableView;
    public TableColumn c1;
    public TableColumn c2;
    public TableColumn c3;
    public TableColumn c4;
    public TableColumn c5;
    public ImageView image;
    public JFXTextField t1;
    public JFXTextField t2;
    public JFXButton paymentButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        c1.setCellValueFactory(new PropertyValueFactory<>("date"));
        c2.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        c3.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        c4.setCellValueFactory(new PropertyValueFactory<>("payment_description"));
        c5.setCellValueFactory(new PropertyValueFactory<>("amount"));

        Node [] nodeArray = {label,image,t1,t2,paymentButton};
        for(Node n : nodeArray){
            Animator.setJackInTheBox(n);
        }
         Animator.setShake(tableView);

        if(StudentLoginController.status.equals("CCS.")){
            CCS_FeesServiceImpl ccsFeesService = ServiceFactory.getServiceObject(ServiceTypes.CCS_FEES);
            ArrayList<CCS_Fees_DTO> ccs = ccsFeesService.getAllFees();

            ObservableList<CCS_Fees_DTO> obList = FXCollections.observableArrayList();
            for(CCS_Fees_DTO cf : ccs){
                obList.add(cf);
            }
            tableView.setItems(obList);
        }
        if(StudentLoginController.status.equals("HND.")){
          HND_FeesServiceImpl hndFeesService = ServiceFactory.getServiceObject(ServiceTypes.HND_FEES);
            ArrayList<HND_Fees_DTO> hnd = hndFeesService.getAll();
            ObservableList<HND_Fees_DTO> obList = FXCollections.observableArrayList();
            for(HND_Fees_DTO hf : hnd){
                obList.add(hf);
            }
            tableView.setItems(obList);
        }
        if(StudentLoginController.status.equals("UNDERGRAD.")) {
           UnderGrad_FeesServiceImpl underGradFeesService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_FEES);
            ArrayList<UNDERGRAD_Fees_DTO> underGrad = underGradFeesService.getAll();
            ObservableList<UNDERGRAD_Fees_DTO> obList = FXCollections.observableArrayList();
            for (UNDERGRAD_Fees_DTO uf : underGrad) {
                obList.add(uf);
            }
            tableView.setItems(obList);

        }
    }

    public void addOnAction(ActionEvent actionEvent) {
        if(StudentLoginController.status.equals("CCS.")){
            CCS_Fees_DTO cf = new CCS_Fees_DTO(String.valueOf(LocalDate.now()),StudentLoginController.studentID,StudentLoginController.studentName,t1.getText(),Double.parseDouble(t2.getText()));
           CCS_FeesServiceImpl ccsFeesService = ServiceFactory.getServiceObject(ServiceTypes.CCS_FEES);

            boolean b1 = ccsFeesService.add(cf);
            if(b1){
                try {
                    Navigator.navigate(StudentDashBoardController.staticAP, Routes.FEES);
                } catch (IOException e) {
                    AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR!", Alert.AlertType.ERROR);
                }
                AlertSender.sendAlert("Added!","INFORMATION.", Alert.AlertType.INFORMATION);
            }


        }
        if(StudentLoginController.status.equals("HND.")){
            HND_Fees_DTO hf = new HND_Fees_DTO(String.valueOf(LocalDate.now()),StudentLoginController.studentID,StudentLoginController.studentName,t1.getText(),Double.parseDouble(t2.getText()));

            HND_FeesServiceImpl hndFeesService = ServiceFactory.getServiceObject(ServiceTypes.HND_FEES);
            boolean b1 = hndFeesService.add(hf);
            if(b1){
                try {
                    Navigator.navigate(StudentDashBoardController.staticAP, Routes.FEES);
                } catch (IOException e) {
                    AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR!", Alert.AlertType.ERROR);
                }
                AlertSender.sendAlert("Added!","INFORMATION.", Alert.AlertType.INFORMATION);
            }



        }
        if(StudentLoginController.status.equals("UNDERGRAD.")){
            UNDERGRAD_Fees_DTO uf = new UNDERGRAD_Fees_DTO(String.valueOf(LocalDate.now()),StudentLoginController.studentID,StudentLoginController.studentName,t1.getText(),Double.parseDouble(t2.getText()));

            UnderGrad_FeesServiceImpl underGradFeesService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_FEES);
            boolean b1 = underGradFeesService.add(uf);
            if(b1){
                try {
                    Navigator.navigate(StudentDashBoardController.staticAP, Routes.FEES);
                } catch (IOException e) {
                    AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR!", Alert.AlertType.ERROR);
                }
                AlertSender.sendAlert("Added!","INFORMATION.", Alert.AlertType.INFORMATION);
            }
        }
    }
}
