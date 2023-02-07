package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.CCS_Credentials;
import com.damian.apexedu.db.DBConnection;
import com.damian.apexedu.dto.CCS_DTO;
import com.damian.apexedu.dto.CCS_Student_Credentials_DTO;
import com.damian.apexedu.entity.CCS;
import com.damian.apexedu.entity.CCS_Student_Credentials;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CCS_CredentialsImpl_DAO implements CCS_Credentials {

    @Override
    public boolean add(CCS_Student_Credentials ccsStudentCredentials) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("INSERT INTO CCS_Student_Credentials VALUES(?,?,?)",
                    ccsStudentCredentials.getStudent_id(),
                    ccsStudentCredentials.getUsername(),
                    ccsStudentCredentials.getPassword());
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(), "Error",Alert.AlertType.ERROR);

        }
        return b;

    }

    @Override
    public boolean update(CCS_Student_Credentials ccsStudentCredentials,String id) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("UPDATE CCS_Student_Credentials SET username = ?, password = ? WHERE student_id = ?",
                    ccsStudentCredentials.getUsername(),
                    ccsStudentCredentials.getPassword(),
                    ccsStudentCredentials.getStudent_id());
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(), "Error",Alert.AlertType.ERROR);

        }
        return b;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Optional<CCS_Student_Credentials> search(String id) {
        CCS_Student_Credentials ccsStudentCredentials = null;
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM CCS_Student_Credentials WHERE student_id = ?", id);
            while (rs.next()){
                ccsStudentCredentials = new CCS_Student_Credentials(rs.getString(1),rs.getString(2),rs.getString(3));
            }
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(), "Error",Alert.AlertType.ERROR);
        }
        return Optional.ofNullable(ccsStudentCredentials);
    }

    @Override
    public List<CCS_Student_Credentials> getAll() {
        return null;
    }


    @Override
    public Optional<CCS_Student_Credentials> searchByEmail(String email) {
       CCS_Student_Credentials ccs = null;
        try {
           ResultSet rs =  PerformCRUD.execute("SELECT * FROM CCS_Student_Credentials WHERE username = ?", email);
           while (rs.next()){
              ccs  = new CCS_Student_Credentials(rs.getString(1),rs.getString(2),rs.getString(3));
           }
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(), "Error",Alert.AlertType.ERROR);
        }
        return Optional.ofNullable(ccs);
    }
}
