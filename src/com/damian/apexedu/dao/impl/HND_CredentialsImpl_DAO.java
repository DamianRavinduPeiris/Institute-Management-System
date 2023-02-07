package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.HND_Credentials;
import com.damian.apexedu.entity.HND_Student_Credentials;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HND_CredentialsImpl_DAO implements HND_Credentials {

    @Override
    public boolean add(HND_Student_Credentials hndStudentCredentials) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("INSERT INTO hnd_student_credentials VALUES (?,?,?)", hndStudentCredentials.getStudent_id(), hndStudentCredentials.getUsername(), hndStudentCredentials.getPassword());
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText("HND_CREDENTIALS_DAO"+e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }
        return b;
    }

    @Override
    public boolean update(HND_Student_Credentials hndStudentCredentials,String id) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("UPDATE hnd_student_credentials SET username=?, password=? WHERE student_id=?", hndStudentCredentials.getUsername(), hndStudentCredentials.getPassword(), id);
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return b;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Optional<HND_Student_Credentials> search(String id) {
        HND_Student_Credentials hndStudentCredentials=null;
        try {
           ResultSet rs = PerformCRUD.execute("SELECT * FROM hnd_student_credentials WHERE student_id=?",id);
           while (rs.next()){
               hndStudentCredentials = new HND_Student_Credentials(rs.getString(1),rs.getString(2),rs.getString(3));
           }
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return Optional.ofNullable(hndStudentCredentials);
    }

    @Override
    public List<HND_Student_Credentials> getAll() {
        return null;
    }

    @Override
    public Optional<HND_Student_Credentials> searchByEmail(String email) {
        HND_Student_Credentials hndStudentCredentials = null;
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM hnd_student_credentials WHERE username=?",email);
            while (rs.next()){
               hndStudentCredentials = new HND_Student_Credentials(rs.getString(1),rs.getString(2),rs.getString(3));

            }
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR!", Alert.AlertType.ERROR);
        }
        return Optional.ofNullable(hndStudentCredentials);
    }
}
