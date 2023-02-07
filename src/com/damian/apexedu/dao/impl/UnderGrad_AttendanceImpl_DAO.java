package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.UnderGrad_Attendance;
import com.damian.apexedu.entity.UNDERGRAD_Attendance;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UnderGrad_AttendanceImpl_DAO implements UnderGrad_Attendance {

    @Override
    public boolean add(UNDERGRAD_Attendance undergradAttendance) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("INSERT INTO undergrad_student_attendance VALUES (?,?,?)",undergradAttendance.getDate(),undergradAttendance.getStudent_id(),undergradAttendance.getStatus());
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(e.getLocalizedMessage());
            a.show();
        }
        return b;
    }

    @Override
    public boolean update(UNDERGRAD_Attendance undergradAttendance,String id) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("UPDATE undergrad_student_attendance SET date= ?, status = ? WHERE student_id = ?",undergradAttendance.getDate(),undergradAttendance.getStatus(),id);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(e.getLocalizedMessage());
            a.show();

        }
        return b;

    }

    @Override
    public boolean delete(String s) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("DELETE FROM undergrad_student_attendance WHERE student_id = ?",s);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(e.getLocalizedMessage());
            a.show();
        }
        return b;
    }

    @Override
    public Optional<UNDERGRAD_Attendance> search(String s) {
        return null;
    }

    @Override
    public List<UNDERGRAD_Attendance> getAll() {
        ArrayList<UNDERGRAD_Attendance >undergradAttendances = new ArrayList<>();
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM undergrad_student_attendance");
            while (rs.next()){
                UNDERGRAD_Attendance ua = new UNDERGRAD_Attendance(rs.getString(1),rs.getString(2),rs.getString(3));
                undergradAttendances.add(ua);
            }
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText(e.getLocalizedMessage());
            a.show();
        }
        return undergradAttendances;
    }
}
