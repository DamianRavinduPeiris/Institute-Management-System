package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.UNDERGRAD;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UnderGrad_Imple_DAO implements UNDERGRAD {
    @Override
    public boolean add(com.damian.apexedu.entity.UNDERGRAD undergrad) {
        boolean b = false;
        try {
            b =PerformCRUD.execute("INSERT INTO undergrad_student_details VALUES (?,?,?,?,?,?)",undergrad.getProgram_id(),undergrad.getStudent_id(),undergrad.getStudent_name(),undergrad.getStudent_address(),undergrad.getStudent_email(),undergrad.getTelephone());
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return b;
    }

    @Override
    public boolean update(com.damian.apexedu.entity.UNDERGRAD undergrad, String id) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("UPDATE undergrad_student_details SET program_id=?,student_name=?,student_address=?,student_email=?,telephone=? WHERE student_id=?",undergrad.getProgram_id(),undergrad.getStudent_name(),undergrad.getStudent_address(),undergrad.getStudent_email(),undergrad.getTelephone(),id);
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return b;
    }

    @Override
    public boolean delete(String id) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("DELETE FROM undergrad_student_details WHERE student_id=?",id);
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return b;
    }

    @Override
    public Optional<com.damian.apexedu.entity.UNDERGRAD> search(String id) {
        com.damian.apexedu.entity.UNDERGRAD ud = null;
        try {

            ResultSet rs = PerformCRUD.execute("SELECT * FROM undergrad_student_details WHERE student_id=?",id);
            while (rs.next()){
                ud = new com.damian.apexedu.entity.UNDERGRAD(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
            }
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return Optional.ofNullable(ud);
    }

    @Override
    public List<com.damian.apexedu.entity.UNDERGRAD> getAll() {
        ArrayList<com.damian.apexedu.entity.UNDERGRAD >udList = new ArrayList<>();
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM undergrad_student_details");
            while (rs.next()){
                com.damian.apexedu.entity.UNDERGRAD ud = new com.damian.apexedu.entity.UNDERGRAD(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                udList.add(ud);
            }
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR!", Alert.AlertType.ERROR);
        }
        return udList;
    }
}
