package com.damian.apexedu.control;


import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.db.DBConnection;
import com.damian.apexedu.dto.CCS_DTO;
import com.damian.apexedu.dto.CCS_Fees_DTO;
import com.damian.apexedu.dto.CCS_Student_Credentials_DTO;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.CCS_CredentialImplService;
import com.damian.apexedu.service.impl.CCS_DetailsServiceImpl;
import com.damian.apexedu.service.impl.CCS_FeesServiceImpl;
import com.damian.apexedu.smtp.EmailToStudent;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ResourceBundle;

public class CCSController implements Initializable,Runnable {


    public JFXTextField t1;
    public JFXTextField t2;
    public JFXTextField t3;
    public JFXTextField t4;
    public JFXTextField t5;
    public JFXTextField t6;
    public JFXComboBox cmb;
    public JFXButton add;
    public JFXButton view;
    public JFXButton update;
    public JFXButton delete;
    public String[] optionsArray = {"ADD A NEW STUDENT. ", "VIEW DETAILS OF A STUDENT.", "UPDATE AN EXISTING STUDENT.", "DELETE A STUDENT."};
    public JFXComboBox cmb1;
    public ImageView image;
    public JFXTextField t7;
    public JFXTextField t8;
    public JFXTextField t9;
    public JFXButton reportButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Animator.setJackInTheBox(image);
        if (StudentDashBoardController.status.equals("CCS.")) {
            update.setVisible(true);
            cmb.setVisible(false);
            add.setVisible(false);
            view.setVisible(false);
            delete.setVisible(false);
            t1.setText(StudentLoginController.studentID);
            t1OnAction(new ActionEvent());
        } else {
            Animator.setBounce(cmb);
            add.setVisible(false);
            view.setVisible(false);
            update.setVisible(false);
            delete.setVisible(false);
        }

        ObservableList<String> obList = FXCollections.observableArrayList();
        ObservableList<String> obList1 = FXCollections.observableArrayList();

        for(String s : optionsArray) {
            obList.add(s);
        }
        cmb.setItems(obList);
        obList1.add("P001");
        cmb1.setItems(obList1);

    }

    public void cmbOnAction(ActionEvent actionEvent) {
        if (cmb.getValue().equals(optionsArray[0])) {
            add.setVisible(true);
          Animator.setLightSpeedIn(add);
        } else {
            add.setVisible(false);
        }
        if (cmb.getValue().equals(optionsArray[1])) {
            view.setVisible(true);
            Animator.setLightSpeedIn(view);
        } else {
            view.setVisible(false);
        }
        if (cmb.getValue().equals(optionsArray[2])) {
            update.setVisible(true);
            Animator.setLightSpeedIn(update);
        } else {
            update.setVisible(false);
        }
        if (cmb.getValue().equals(optionsArray[3])) {
            delete.setVisible(true);
            Animator.setLightSpeedIn(delete);
        } else {
            delete.setVisible(false);
        }
    }

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        boolean b = Validator.check(t4.getText(), Patterns.EMAIL);
        if (b) {
            boolean b2 = Validator.check(t5.getText(), Patterns.TELEPHONE);
            if (b2) {
                boolean b3 = Validator.check(t8.getText(), Patterns.USERNAME);
                if (b3) {
                    CCS_DTO ccs = new CCS_DTO(String.valueOf(cmb1.getValue()), t1.getText(), t2.getText(), t3.getText(), t4.getText(), Integer.valueOf(t5.getText()));
                    CCS_Fees_DTO cf = new CCS_Fees_DTO(String.valueOf(LocalDate.now()), t1.getText(), t2.getText(), t6.getText(), Double.parseDouble(t7.getText()));
                    CCS_Student_Credentials_DTO sc = new CCS_Student_Credentials_DTO(t1.getText(), t8.getText(), t9.getText());

                    CCS_DetailsServiceImpl c = (CCS_DetailsServiceImpl) ServiceFactory.getServiceObject(ServiceTypes.CCS_DETAILS);
                    c.setValues(sc,cf);
                    boolean add = c.add(ccs);
                    if(add){
                        AlertSender.sendAlert("Added!","INFORMATION.", Alert.AlertType.INFORMATION);
                        run();
                    }

                } else {
                    t8.setFocusColor(Paint.valueOf("RED"));
                    showErrors(t8,"INVALID USERNAME!");
                }
            } else {
                t5.setFocusColor(Paint.valueOf("RED"));
                showErrors(t5,"INVALID TELEPHONE NUMBER!");
            }
        } else {
            t4.setFocusColor(Paint.valueOf("RED"));
            showErrors(t4,"INVALID EMAIL!");
        }
    }
    public void showErrors(TextField textField,String msg){
        Animator.setShake(textField);
        textField.requestFocus();
        textField.clear();
        textField.setPromptText(msg);
    }



    public void clearOnAction(ActionEvent actionEvent) {
        TextField [] textFields = {t1,t2,t3,t4,t5,t6,t7,t8,t9};
        for(TextField t : textFields){
            t.clear();
        }
        t4.setPromptText("STUDENT EMAIL.");
        t5.setPromptText("STUDENT CONTACT NUMBER.");
        t8.setPromptText("ENTER USERNAME. (MUST BE YOUR EMAIL!)");
    }

    public void viewOnAction(ActionEvent actionEvent) {
        t1OnAction(actionEvent);
    }

    public void t1OnAction(ActionEvent actionEvent) {

        CCS_DetailsServiceImpl ccsDetailsService  = ServiceFactory.getServiceObject(ServiceTypes.CCS_DETAILS);
        CCS_DTO details= ccsDetailsService.search(t1.getText());

        CCS_CredentialImplService ccsCredentialsService = ServiceFactory.getServiceObject(ServiceTypes.CCS_CREDENTIALS);
        CCS_Student_Credentials_DTO credentials = ccsCredentialsService.search(t1.getText());

        CCS_FeesServiceImpl  ccsFeesService =ServiceFactory.getServiceObject(ServiceTypes.CCS_FEES);
        CCS_Fees_DTO fees = ccsFeesService.search(t1.getText());

        if(details!=null && credentials!=null && fees!=null){
            t2.setText(details.getStudent_name());
            t3.setText(details.getStudent_address());
            t4.setText(details.getStudent_email());
            t5.setText(String.valueOf(details.getTelephone()));
            t6.setText(fees.getPayment_description());
            t7.setText(String.valueOf(fees.getAmount()));
            t8.setText(credentials.getUsername());
            t9.setText(credentials.getPassword());
        }
        else{
            AlertSender.sendAlert("Student not found!","ERROR.", Alert.AlertType.ERROR);
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
        CCS_DTO ccs = new CCS_DTO(String.valueOf(cmb1.getValue()), t1.getText(), t2.getText(), t3.getText(), t4.getText(), Integer.valueOf(t5.getText()));
        CCS_Fees_DTO cf = new CCS_Fees_DTO(String.valueOf(LocalDate.now()), t1.getText(), t2.getText(), t6.getText(), Double.parseDouble(t7.getText()));
        CCS_Student_Credentials_DTO sc = new CCS_Student_Credentials_DTO(t1.getText(), t8.getText(), t9.getText());

        CCS_DetailsServiceImpl ccsDetails = ServiceFactory.getServiceObject(ServiceTypes.CCS_DETAILS);
        ccsDetails.setValues(sc,cf);
        boolean update = ccsDetails.update(ccs, t1.getText());
        if(update){
            AlertSender.sendAlert("Updated!!","INFORMATION.", Alert.AlertType.INFORMATION);
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        CCS_DetailsServiceImpl ccsDetails = ServiceFactory.getServiceObject(ServiceTypes.CCS_DETAILS);
        boolean b = ccsDetails.delete(t1.getText());
        if(b){
            AlertSender.sendAlert("Deleted!","INFORMATION.", Alert.AlertType.INFORMATION);
        }


    }

    public void reportOnAction(ActionEvent actionEvent) {

        try {
            InputStream inputStream = this.getClass().getResourceAsStream
                    ("/com/damian/apexedu/reports/CCS_STUDENT_DETAILS.jrxml");

            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert("An error occurred while generating report!","ERROR.", Alert.AlertType.ERROR);
        }
    }

    public void sendMail() {
        EmailToStudent.sendMail(t4.getText(),t2.getText(),t1.getText(),t8.getText(),t9.getText());
    }

    @Override
    public void run() {
        sendMail();

    }
}