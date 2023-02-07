package com.damian.apexedu.control;

import animatefx.animation.Bounce;
import animatefx.animation.JackInTheBox;
import animatefx.animation.LightSpeedIn;
import animatefx.animation.Shake;
import com.damian.apexedu.db.DBConnection;
import com.damian.apexedu.dto.UNDERGRAD_DTO;
import com.damian.apexedu.dto.UNDERGRAD_Fees_DTO;
import com.damian.apexedu.dto.UNDERGRAD_Student_Credentials_DTO;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.impl.UnderGrad_CredentialsServiceImpl;
import com.damian.apexedu.service.impl.UnderGrad_DetailsServiceImpl;
import com.damian.apexedu.service.impl.UnderGrad_FeesServiceImpl;
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

public class UnderGradController implements Initializable,Runnable {
    public JFXComboBox cmb;
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
    public  String [] optionsArray = {"ADD A NEW STUDENT. ","VIEW DETAILS OF A STUDENT.","UPDATE AN EXISTING STUDENT.","DELETE A STUDENT."};
    public JFXTextField t7;
    public JFXTextField t6;
    public ImageView image;
    public JFXTextField t8;
    public JFXTextField t9;
    public JFXButton reportButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new JackInTheBox(image).play();


        ObservableList<String> obList = FXCollections.observableArrayList();
        ObservableList<String> obList1 = FXCollections.observableArrayList();

        for(String  s : optionsArray){
            obList.add(s);
        }
        cmb.setItems(obList);
        obList1.add("P003");
        cmb1.setItems(obList1);
        if(StudentDashBoardController.status.equals("UNDERGRAD.")){
            update.setVisible(true);
            cmb.setVisible(false);
            add.setVisible(false);
            view.setVisible(false);
            delete.setVisible(false);
        }
        else{
            new Bounce(cmb).play();
            add.setVisible(false);
            view.setVisible(false);
            update.setVisible(false);
            delete.setVisible(false);
        }

    }

    public void cmbOnAction(ActionEvent actionEvent) {
        if(cmb.getValue().equals(optionsArray[0])){
            add.setVisible(true);
            new LightSpeedIn(add).play();
        }
        else{
            add.setVisible(false);
        }
        if(cmb.getValue().equals(optionsArray[1])){
            view.setVisible(true);
            new LightSpeedIn(view).play();
        }
        else{
            view.setVisible(false);
        }
        if(cmb.getValue().equals(optionsArray[2])){
            update.setVisible(true);
            new LightSpeedIn(update).play();
        }
        else{
            update.setVisible(false);
        }
        if(cmb.getValue().equals(optionsArray[3])){
            delete.setVisible(true);
            new LightSpeedIn(delete).play();
        }
        else{
            delete.setVisible(false);
        }
    }

    public void t1OnAction(ActionEvent actionEvent) {
        viewOnAction(actionEvent);
    }

    public void clearOnAction(ActionEvent actionEvent) {
        t1.clear();
        t2.clear();
        t3.clear();
        t4.clear();
        t4.setPromptText("STUDENT EMAIL.");
        t5.clear();
        t5.setPromptText("STUDENT CONTACT NUMBER.");
        t6.clear();
        t7.clear();
        t8.clear();
        t8.setPromptText("ENTER USERNAME. (MUST BE YOUR EMAIL!)");
        t9.clear();
    }

    public void addOnAction(ActionEvent actionEvent) {
        boolean c1 = Validator.check(t4.getText(), Patterns.EMAIL);
        if(c1){
            boolean c2 = Validator.check(t5.getText(), Patterns.TELEPHONE);
            if(c2){
                boolean c3 = Validator.check(t8.getText(), Patterns.USERNAME);
                if(c3){
                    UNDERGRAD_DTO ud = new UNDERGRAD_DTO(String.valueOf(cmb1.getValue()),t1.getText(),t2.getText(),t3.getText(),t4.getText(),Integer.parseInt(t5.getText()));
                    UNDERGRAD_Fees_DTO uf = new UNDERGRAD_Fees_DTO(String.valueOf(LocalDate.now()),t1.getText(),t2.getText(),t6.getText(),Double.parseDouble(t7.getText()));
                    UNDERGRAD_Student_Credentials_DTO usc = new UNDERGRAD_Student_Credentials_DTO(t1.getText(),t8.getText(),t9.getText());

                    UnderGrad_DetailsServiceImpl undergrad =  ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD);
                    undergrad.setValues(uf,usc);
                    boolean b1 = undergrad.add(ud);
                    if(b1){
                        AlertSender.sendAlert("Added!","INFORMATION.", Alert.AlertType.INFORMATION);
                        run();
                    }

                }
                else{
                    new Shake(t8).play();
                    focusAndShow(t8,"INVALID USERNAME!");
                    t8.setFocusColor(Paint.valueOf("RED"));
                }
            }
            else{
                new Shake(t5).play();
                focusAndShow(t5,"INVALID CONTACT NUMBER!");
                t5.setFocusColor(Paint.valueOf("RED"));

            }
        }
        else{
            new Shake(t4).play();
            focusAndShow(t4,"INVALID EMAIL!");
            t4.setFocusColor(Paint.valueOf("RED"));

        }
    }
    public void focusAndShow(TextField textField,String msg){
        textField.requestFocus();
        textField.clear();
        textField.setPromptText(msg);
    }


    public void viewOnAction(ActionEvent actionEvent) {
    UnderGrad_DetailsServiceImpl underGradDetailsService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD);
        UNDERGRAD_DTO ud = underGradDetailsService.search(t1.getText());
        UnderGrad_CredentialsServiceImpl underGradCredentialsService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_CREDENTIALS);
        UNDERGRAD_Student_Credentials_DTO usc = underGradCredentialsService.search(t1.getText());

        UnderGrad_FeesServiceImpl underGradFeesService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD_FEES);
        UNDERGRAD_Fees_DTO uf = underGradFeesService.search(t1.getText());
        if(uf!=null && ud!=null && usc!=null){
            t2.setText(ud.getStudent_name());
            t3.setText(ud.getStudent_address());
            t4.setText(ud.getStudent_email());
            t5.setText(String.valueOf(ud.getTelephone()));
            t6.setText(uf.getPayment_description());
            t7.setText(String.valueOf(uf.getAmount()));
            t8.setText(usc.getUsername());
            t9.setText(usc.getPassword());

        }else{
            AlertSender.sendAlert("Student not found!","ERROR!.", Alert.AlertType.ERROR);
    }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        UnderGrad_DetailsServiceImpl underGradDetailsService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD);
        boolean b1 = underGradDetailsService.delete(t1.getText());
        if(b1){
            AlertSender.sendAlert("Deleted!","INFORMATION.", Alert.AlertType.INFORMATION);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        UNDERGRAD_DTO ud = new UNDERGRAD_DTO(String.valueOf(cmb1.getValue()),t1.getText(),t2.getText(),t3.getText(),t4.getText(),Integer.parseInt(t5.getText()));
        UNDERGRAD_Fees_DTO uf = new UNDERGRAD_Fees_DTO(String.valueOf(LocalDate.now()),t1.getText(),t2.getText(),t6.getText(),Double.parseDouble(t7.getText()));
        UNDERGRAD_Student_Credentials_DTO usc = new UNDERGRAD_Student_Credentials_DTO(t1.getText(),t8.getText(),t9.getText());

        UnderGrad_DetailsServiceImpl underGradDetailsService = ServiceFactory.getServiceObject(ServiceTypes.UNDERGRAD);
        underGradDetailsService.setValues(uf,usc);
        boolean b = underGradDetailsService.update(ud);
        if(b){
            AlertSender.sendAlert("Updated!","INFORMATION.", Alert.AlertType.INFORMATION);
        }
    }

    public void reportOnAction(ActionEvent actionEvent) {
        try {
            InputStream inputStream= this.getClass().getResourceAsStream
                    ("/com/damian/apexedu/reports/UNDERGRAD_STUDENT_DETAILS.jrxml");

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
