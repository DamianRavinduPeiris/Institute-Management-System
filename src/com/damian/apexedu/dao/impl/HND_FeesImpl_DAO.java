package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.HND_Fees;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HND_FeesImpl_DAO implements HND_Fees {

    @Override
    public boolean add(com.damian.apexedu.entity.HND_Fees hndFees) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("INSERT INTO hnd_fees VALUES (?,?,?,?,?)", hndFees.getDate(), hndFees.getStudent_id(), hndFees.getStudent_name(), hndFees.getPayment_description(), hndFees.getAmount());
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText("HND_FEESIMPL_DAO"+e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }
        return b;
    }

    @Override
    public boolean update(com.damian.apexedu.entity.HND_Fees hndFees,String  id) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("UPDATE hnd_fees SET date=?, student_name=?, payment_description=?, amount=? WHERE student_id=?", hndFees.getDate(),  hndFees.getStudent_name(), hndFees.getPayment_description(), hndFees.getAmount(), id);
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
    public Optional<com.damian.apexedu.entity.HND_Fees> search(String id) {
        com.damian.apexedu.entity.HND_Fees hndFees = null;
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM hnd_fees WHERE student_id=?",id);
            while (rs.next()){
                hndFees = new com.damian.apexedu.entity.HND_Fees(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5));
            }
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return Optional.ofNullable(hndFees);
    }

    @Override
    public List<com.damian.apexedu.entity.HND_Fees> getAll() {
        ArrayList<com.damian.apexedu.entity.HND_Fees >hndFees   = new ArrayList<>();
        try {
           ResultSet rs =  PerformCRUD.execute("SELECT * FROM hnd_fees");
            while (rs.next()) {
                com.damian.apexedu.entity.HND_Fees hndFees1 = new com.damian.apexedu.entity.HND_Fees(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5));
                        hndFees.add(hndFees1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return hndFees;
    }
}
