package com.damian.apexedu.control;

import com.damian.apexedu.animations.Animator;
import com.damian.apexedu.db.DBConnection;
import com.damian.apexedu.dto.HND_DTO;
import com.damian.apexedu.dto.HND_Fees_DTO;
import com.damian.apexedu.dto.HND_Student_Credentials_DTO;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.HND_CredentialsServiceImpl;
import com.damian.apexedu.service.impl.HND_DetailsServiceImpl;
import com.damian.apexedu.service.impl.HND_FeesServiceImpl;
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

import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HNDController implements Initializable,Runnable {
    public JFXComboBox cmb1;
    public JFXTextField t1;
    public JFXTextField t2;
    public JFXTextField t3;
    public JFXTextField t4;
    public JFXTextField t5;

    public JFXButton clear;
    public JFXButton add;
    public JFXButton view;
    public JFXButton update;
    public JFXButton delete;
    public JFXComboBox cmb;
    public  String [] optionsArray = {"ADD A NEW STUDENT. ","VIEW DETAILS OF A STUDENT.","UPDATE AN EXISTING STUDENT.","DELETE A STUDENT."};
    public JFXTextField t6;
    public JFXTextField t7;
    public ImageView image;
    public JFXTextField t8;
    public JFXTextField t9;
    public JFXButton reportButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Animator.setJackInTheBox(image);

        ObservableList<String> obList = FXCollections.observableArrayList();
        ObservableList<String> obList1 = FXCollections.observableArrayList();
        for(String  s : optionsArray){
            obList.add(s);
        }
        cmb.setItems(obList);
        obList1.add("P002");
        cmb1.setItems(obList1);
        if(StudentDashBoardController.status.equals("HND.")){
            update.setVisible(true);
            cmb.setVisible(false);
            add.setVisible(false);
            view.setVisible(false);
            delete.setVisible(false);
            t1.setText(StudentLoginController.studentID);
            t1OnAction(new ActionEvent());
        }
        else{
            cmb.setVisible(true);
            Animator.setBounce(cmb);
            add.setVisible(false);
            view.setVisible(false);
            update.setVisible(false);
            delete.setVisible(false);
        }

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
                boolean c3 = Validator.check(t8.getText(), Patterns.USERNAME);
                if(c3){
                    HND_DTO hnd = new HND_DTO(String.valueOf(cmb1.getValue()),t1.getText(),t2.getText(),t3.getText(),t4.getText(),Integer.valueOf(t5.getText()));
                    HND_Fees_DTO hf = new HND_Fees_DTO(String.valueOf(LocalDate.now()), t1.getText(),t2.getText(),t6.getText(),Double.parseDouble(t7.getText()));
                    HND_Student_Credentials_DTO hsc = new HND_Student_Credentials_DTO(t1.getText(),t8.getText(),t9.getText());
                    
                    HND_DetailsServiceImpl hndDetailsService = ServiceFactory.getServiceObject(ServiceTypes.HND_DETAILS);
                    hndDetailsService.setValues(hf,hsc);
                    boolean b1 = hndDetailsService.add(hnd);
                    if(b1){
                        AlertSender.sendAlert("Added!","INFORMATION.", Alert.AlertType.INFORMATION);
                        run();

                    }


                }
                else{
                    showErrors(t8,"INVALID USERNAME!");
                    t8.setFocusColor(Paint.valueOf("RED"));
                }

            }
            else{
                showErrors(t5,"INVALID TELEPHONE NUMBER!");
                t5.setFocusColor(Paint.valueOf("RED"));
            }
        }
        else{
            showErrors(t4,"INVALID EMAIL!");
            t4.setFocusColor(Paint.valueOf("RED"));
        }

    }
    public void showErrors(TextField textField,String msg){
        Animator.setShake(textField);
        textField.requestFocus();
        textField.clear();
        textField.setPromptText(msg);
    }

    public void viewOnAction(ActionEvent actionEvent) {
        HND_DetailsServiceImpl hndDetailsService = ServiceFactory.getServiceObject(ServiceTypes.HND_DETAILS);
        HND_DTO hnd = hndDetailsService.search(t1.getText());

        HND_CredentialsServiceImpl hndCredentialsService = ServiceFactory.getServiceObject(ServiceTypes.HND_CREDENTIALS);
        HND_Student_Credentials_DTO credentials = hndCredentialsService.search(t1.getText());

        HND_FeesServiceImpl hndFeesService = ServiceFactory.getServiceObject(ServiceTypes.HND_FEES);
        HND_Fees_DTO fees = hndFeesService.search(t1.getText());
        if(hnd!=null && credentials!=null && fees!=null){
            t2.setText(hnd.getStudent_name());
            t3.setText(hnd.getStudent_address());
            t4.setText(hnd.getStudent_email());
            t5.setText(String.valueOf(hnd.getTelephone()));
            t6.setText(fees.getPayment_description());
            t7.setText(String.valueOf(fees.getAmount()));
            t8.setText(credentials.getUsername());
            t9.setText(credentials.getPassword());
        }
        else{
            AlertSender.sendAlert("Student not found!","ERROR.", Alert.AlertType.ERROR);;
        }


    }

    public void updateOnAction(ActionEvent actionEvent) {
        HND_DTO hnd = new HND_DTO(String.valueOf(cmb1.getValue()),t1.getText(),t2.getText(),t3.getText(),t4.getText(),Integer.valueOf(t5.getText()));
        HND_Fees_DTO hf = new HND_Fees_DTO(String.valueOf(LocalDate.now()), t1.getText(),t2.getText(),t6.getText(),Double.parseDouble(t7.getText()));
        HND_Student_Credentials_DTO hsc = new HND_Student_Credentials_DTO(t1.getText(),t8.getText(),t9.getText());

        HND_DetailsServiceImpl hndDetailsService = ServiceFactory.getServiceObject(ServiceTypes.HND_DETAILS);
        hndDetailsService.setValues(hf,hsc);
        boolean b1 = hndDetailsService.update(hnd);
        if(b1){
            AlertSender.sendAlert("Updated!","INFORMATION.", Alert.AlertType.INFORMATION);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
     HND_DetailsServiceImpl hndDetailsService =  ServiceFactory.getServiceObject(ServiceTypes.HND_DETAILS);
        boolean b1 = hndDetailsService.delete(t1.getText());
        if(b1){
            AlertSender.sendAlert("Deleted!","INFORMATION.", Alert.AlertType.INFORMATION);
        }
    }

    public void t1OnAction(ActionEvent actionEvent) {
        viewOnAction(actionEvent);
    }

    public void reportOnAction(ActionEvent actionEvent) {
        InputStream inputStream= this.getClass().getResourceAsStream
                ("/com/damian/apexedu/reports/HND_STUDENT_DETAILS.jrxml");

        try {
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
