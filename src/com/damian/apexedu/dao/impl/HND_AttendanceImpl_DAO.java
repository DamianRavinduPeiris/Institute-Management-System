package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.HND_Attendance;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HND_AttendanceImpl_DAO implements HND_Attendance {

    @Override
    public boolean add(com.damian.apexedu.entity.HND_Attendance hndAttendance) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("INSERT INTO hnd_student_attendance VALUES (?,?,?)",hndAttendance.getDate(),hndAttendance.getStudent_id(),hndAttendance.getStatus());
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR!", Alert.AlertType.ERROR);
        }
        return b;
    }

    @Override
    public boolean update(com.damian.apexedu.entity.HND_Attendance hndAttendance,String id) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("UPDATE hnd_student_attendance SET date=? , status = ? WHERE student_id = ?",hndAttendance.getDate(),hndAttendance.getStatus(),id);
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
            b = PerformCRUD.execute("DELETE FROM hnd_student_attendance WHERE student_id = ?",id);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(e.getLocalizedMessage());
            a.show();

        }
        return b;
    }

    @Override
    public Optional<com.damian.apexedu.entity.HND_Attendance> search(String s) {
        return null;
    }

    @Override
    public List<com.damian.apexedu.entity.HND_Attendance> getAll() {
        List<com.damian.apexedu.entity.HND_Attendance >hndAttendanceList = new ArrayList<>();
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM hnd_student_attendance");
            while (rs.next()){
                com.damian.apexedu.entity.HND_Attendance hndAttendance = new com.damian.apexedu.entity.HND_Attendance(rs.getString(1),rs.getString(2),rs.getString(3));
                hndAttendanceList.add(hndAttendance);
            }

        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(e.getLocalizedMessage());
            a.show();
        }
        return hndAttendanceList;
    }
}
