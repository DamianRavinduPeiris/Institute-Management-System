package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.HND;
import com.damian.apexedu.dao.custom.HND_Announcements;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HND_Impl_DAO implements HND {

    @Override
    public boolean add(com.damian.apexedu.entity.HND hnd) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("INSERT INTO hnd_student_details VALUES(?,?,?,?,?,?)", hnd.getProgram_id(), hnd.getStudent_id(), hnd.getStudent_name(), hnd.getStudent_address(), hnd.getStudent_email(), hnd.getTelephone());
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText("HND_IMPL_DAO"+e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }
        return b;
    }

    @Override
    public boolean update(com.damian.apexedu.entity.HND hnd,String id) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("UPDATE hnd_student_details SET program_id=?,student_name=?,student_address=?,student_email=?,telephone=? WHERE student_id=?",hnd.getProgram_id(),hnd.getStudent_name(),hnd.getStudent_address(),hnd.getStudent_email(),hnd.getTelephone(),id);
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
            b =PerformCRUD.execute("DELETE FROM hnd_student_details WHERE student_id=?",id);
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return b;
    }

    @Override
    public Optional<com.damian.apexedu.entity.HND > search(String id) {
        com.damian.apexedu.entity.HND hnd= null;
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM hnd_student_details WHERE student_id=?",id);
            while (rs.next()){
                hnd = new com.damian.apexedu.entity.HND(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
            }
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return Optional.ofNullable(hnd);
    }

    @Override
    public List<com.damian.apexedu.entity.HND> getAll() {
        ArrayList<com.damian.apexedu.entity.HND> hndList= new ArrayList<>();
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM hnd_student_details");
            while (rs.next()){
                com.damian.apexedu.entity.HND hnd = new com.damian.apexedu.entity.HND(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                hndList.add(hnd);
            }
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR!", Alert.AlertType.ERROR);
        }
        return hndList;
    }
}
