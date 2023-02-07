package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.UnderGrad_Credentials;
import com.damian.apexedu.entity.UNDERGRAD_Student_Credentials;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UnderGrad_CredentialsImpl_DAO implements UnderGrad_Credentials {

    @Override
    public boolean add(UNDERGRAD_Student_Credentials undergradStudentCredentials) {
        boolean b=false;
        try {
            b = PerformCRUD.execute("INSERT INTO undergrad_student_credentials VALUES (?,?,?)",
                    undergradStudentCredentials.getStudent_id(),
                    undergradStudentCredentials.getUsername(),
                    undergradStudentCredentials.getPassword());
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return b;
    }

    @Override
    public boolean update(UNDERGRAD_Student_Credentials undergradStudentCredentials,String id) {
        boolean b=false;
        try {
            b = PerformCRUD.execute("UPDATE undergrad_student_credentials SET username=?,password=? WHERE student_id=?",undergradStudentCredentials.getUsername(),undergradStudentCredentials.getPassword(),id);
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
    public Optional<UNDERGRAD_Student_Credentials> search(String s) {
        UNDERGRAD_Student_Credentials undergrad_student_credentials = null;
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM undergrad_student_credentials WHERE student_id=?", s);
            while (rs.next()){
                undergrad_student_credentials = new UNDERGRAD_Student_Credentials(rs.getString(1),rs.getString(2),rs.getString(3));
            }
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return Optional.ofNullable(undergrad_student_credentials);
    }

    @Override
    public List<UNDERGRAD_Student_Credentials> getAll() {
        return null;
    }

    @Override
    public Optional<UNDERGRAD_Student_Credentials> searchByEnail(String email) {
        UNDERGRAD_Student_Credentials undergrad_student_credentials = null;
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM undergrad_student_credentials WHERE username=?", email);
            while (rs.next()){
                undergrad_student_credentials = new UNDERGRAD_Student_Credentials(rs.getString(1),rs.getString(2),rs.getString(3));

            }
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(), "Error",Alert.AlertType.ERROR);

        }
        return Optional.ofNullable(undergrad_student_credentials);

    }
}
