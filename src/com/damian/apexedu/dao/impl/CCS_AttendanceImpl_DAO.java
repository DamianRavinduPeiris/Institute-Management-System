package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.CCS_Attendance;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CCS_AttendanceImpl_DAO implements CCS_Attendance {

    @Override
    public boolean add(com.damian.apexedu.entity.CCS_Attendance ccsAttendance) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("INSERT INTO ccs_student_attendance VALUES(?,?,?)",ccsAttendance.getDate(),ccsAttendance.getStudent_id(),ccsAttendance.getStatus());
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"Error", Alert.AlertType.ERROR);
        }
        return b;
    }

    @Override
    public boolean update(com.damian.apexedu.entity.CCS_Attendance ccsAttendance,String id) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("UPDATE ccs_student_attendance SET date=?,status = ? WHERE student_id = ?",ccsAttendance.getDate(),ccsAttendance.getStatus(),id);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(e.getLocalizedMessage());
            a.show();
        }
        return b;
    }

    @Override
    public boolean delete(String id) {
        boolean b = false;
        try {
            b =PerformCRUD.execute("DELETE FROM ccs_student_attendance WHERE student_id = ?",id);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(e.getLocalizedMessage());
            a.show();

        }
        return b;
    }

    @Override
    public Optional<com.damian.apexedu.entity.CCS_Attendance> search(String s) {
        return null;
    }

    @Override
    public List<com.damian.apexedu.entity.CCS_Attendance> getAll() {
        ArrayList<com.damian.apexedu.entity.CCS_Attendance> list = new ArrayList<>();
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM ccs_student_attendance ");
            while(rs.next()){
                com.damian.apexedu.entity.CCS_Attendance ca = new com.damian.apexedu.entity.CCS_Attendance(rs.getString(1),rs.getString(2),rs.getString(3));
                list.add(ca);
            }
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(e.getLocalizedMessage());
            a.show();
        }
        return list;
    }
}
