package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.UnderGrad_Fees;
import com.damian.apexedu.entity.UNDERGRAD;
import com.damian.apexedu.entity.UNDERGRAD_Fees;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UnderGrad_FeesImpl_DAO implements UnderGrad_Fees {

    @Override
    public boolean add(UNDERGRAD_Fees undergradFees) {
        boolean b=false;
        try {
            b = PerformCRUD.execute("INSERT INTO undergrad_fees VALUES (?,?,?,?,?)",undergradFees.getDate(),undergradFees.getStudent_id(), undergradFees.getStudent_name(),undergradFees.getPayment_description(),undergradFees.getAmount());
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return b;
    }

    @Override
    public boolean update(UNDERGRAD_Fees undergradFees,String id) {
        boolean b=false;
        try {
            b = PerformCRUD.execute("UPDATE undergrad_fees SET date=?,student_id=?,student_name=?,payment_description=?,amount=? WHERE student_id=?",
                    undergradFees.getDate(),undergradFees.getStudent_id(), undergradFees.getStudent_name(),undergradFees.getPayment_description(),undergradFees.getAmount(),id);
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
    public Optional<UNDERGRAD_Fees> search(String s) {
        UNDERGRAD_Fees uf = null;
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM undergrad_fees WHERE student_id=?", s);
            while (rs.next()) {
                uf = new UNDERGRAD_Fees(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5));
            }
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return Optional.ofNullable(uf);
    }

    @Override
    public List<UNDERGRAD_Fees> getAll() {
        ArrayList<UNDERGRAD_Fees> uf = new ArrayList<>();
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM undergrad_fees");
            while (rs.next()){
                UNDERGRAD_Fees UF = new UNDERGRAD_Fees(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5));
                uf.add(UF);
            }
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        return uf;
    }
}
