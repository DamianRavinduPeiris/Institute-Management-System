package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.CCS_Fees;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CCS_Feesimpl_DAO implements CCS_Fees {

    @Override
    public boolean add(com.damian.apexedu.entity.CCS_Fees ccsFees) {
        boolean b = false;
        try {
             b = PerformCRUD.execute("INSERT INTO ccs_Fees VALUES (?,?,?,?,?)", ccsFees.getDate(), ccsFees.getStudent_id(), ccsFees.getStudent_name(), ccsFees.getPayment_description(), ccsFees.getAmount());
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.toString());
            a.show();
        }
        return  b;
    }

    @Override
    public boolean update(com.damian.apexedu.entity.CCS_Fees ccsFees,String id  ) {
        boolean b=false;
        try {
           b =  PerformCRUD.execute("UPDATE ccs_fees SET date=?,student_name=?,payment_description=?,amount=? WHERE student_id=?", ccsFees.getDate(),  ccsFees.getStudent_name(), ccsFees.getPayment_description(), ccsFees.getAmount(), id);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.toString());
            a.show();
        }
        return b;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Optional<com.damian.apexedu.entity.CCS_Fees> search(String id) {
        com.damian.apexedu.entity.CCS_Fees ccsFees = null;
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM ccs_Fees WHERE student_id = ?", id);
            while (rs.next()){
                ccsFees = new com.damian.apexedu.entity.CCS_Fees(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5));
            }
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.toString());
            a.show();
        }
        return Optional.ofNullable(ccsFees);
    }

    @Override
    public List<com.damian.apexedu.entity.CCS_Fees> getAll() {
        ArrayList<com.damian.apexedu.entity.CCS_Fees >fees = new ArrayList<>();
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM ccs_Fees");
            while (rs.next()){
                com.damian.apexedu.entity.CCS_Fees ccsFees = new com.damian.apexedu.entity.CCS_Fees(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5));
                fees.add(ccsFees);
            }
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.toString());
            a.show();
        }
        return fees;

    }
}
