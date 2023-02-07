package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.Lecturer_Credentials;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Lecturer_CredentialsImpl_DAO implements Lecturer_Credentials {

    @Override
    public boolean add(com.damian.apexedu.entity.Lecturer_Credentials lecturerCredentials) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("INSERT INTO lecturer_credentials VALUES (?,?,?)",lecturerCredentials.getLecturer_id(),lecturerCredentials.getUsername(),lecturerCredentials.getPassword());
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }
        return b;
    }

    @Override
    public boolean update(com.damian.apexedu.entity.Lecturer_Credentials lecturerCredentials,String id) {
       boolean b = false;
        try {
            b = PerformCRUD.execute("UPDATE lecturer_credentials SET lecturer_id=?,username=?,password=? WHERE lecturer_id=?",lecturerCredentials.getLecturer_id(),lecturerCredentials.getUsername(),lecturerCredentials.getPassword(),id);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }
        return b;
    }

    @Override
    public boolean delete(String id) {
        boolean b = false;
        try {
            b =PerformCRUD.execute("DELETE FROM lecturer_credentials WHERE lecturer_id=?",id);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
            return false;
        }
        return b;
    }

    @Override
    public Optional<com.damian.apexedu.entity.Lecturer_Credentials> search(String id) {
        com.damian.apexedu.entity.Lecturer_Credentials lc = null;
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM lecturer_credentials WHERE lecturer_id=?",id);
            while (rs.next()){
                lc = new com.damian.apexedu.entity.Lecturer_Credentials(rs.getString(1),rs.getString(2),rs.getString(3));
            }
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }
        return Optional.ofNullable(lc);
    }

    @Override
    public List<com.damian.apexedu.entity.Lecturer_Credentials> getAll() {
        return null;
    }

    @Override
    public com.damian.apexedu.entity.Lecturer_Credentials searchByEmail(String email) {
        com.damian.apexedu.entity.Lecturer_Credentials lc = null;
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM lecturer_credentials WHERE username=?",email);
            while (rs.next()){
                lc = new com.damian.apexedu.entity.Lecturer_Credentials(rs.getString(1),rs.getString(2),rs.getString(3));
            }
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR!", Alert.AlertType.ERROR);
        }
        return lc;
    }
}
