package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.CCS;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CCS_Impl_DAO implements CCS {

    @Override
    public boolean add(com.damian.apexedu.entity.CCS ccs) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("INSERT INTO ccs_student_details VALUES ('" + ccs.getProgram_id() + "','" + ccs.getStudent_id() + "','" + ccs.getStudent_name() + "','" + ccs.getStudent_address() + "','" + ccs.getStudent_email() + "','" + ccs.getTelephone() + "')");
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(e.toString());
            a.show();
        }
        return b;
    }

    @Override
    public boolean update(com.damian.apexedu.entity.CCS ccs, String id) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("UPDATE ccs_student_details SET program_id=?,student_name=?,student_address=?,student_email=?,telephone=? WHERE student_id=?", ccs.getProgram_id(),ccs.getStudent_name(),ccs.getStudent_address(),ccs.getStudent_email(),ccs.getTelephone(),ccs.getStudent_id());
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(e.toString());
            a.show();

        }
        return b;
    }

    @Override
    public boolean delete(String id) {
        boolean b = false;
        try {
             b = PerformCRUD.execute("DELETE FROM ccs_student_details WHERE student_id=?", id);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.toString());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();

        }
        return b;
    }

    @Override
    public Optional<com.damian.apexedu.entity.CCS> search(String id) {
        com.damian.apexedu.entity.CCS ccs=null;
        try {
           ResultSet rs =   PerformCRUD.execute("SELECT * FROM ccs_student_details WHERE student_id=?", id);
           while (rs.next()){
            ccs = new com.damian.apexedu.entity.CCS(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
           }
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.toString());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }
        return  Optional.ofNullable(ccs);
    }

    @Override
    public List<com.damian.apexedu.entity.CCS> getAll() {
        com.damian.apexedu.entity.CCS ccs = null;
        List<com.damian.apexedu.entity.CCS> ccsList = new ArrayList<>();
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM ccs_student_details");
            while (rs.next()){
                ccs = new com.damian.apexedu.entity.CCS(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                ccsList.add(ccs);
            }
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"Error", Alert.AlertType.ERROR);
        }
        return ccsList;
    }
}
