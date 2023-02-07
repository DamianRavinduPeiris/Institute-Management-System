package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.Lecturer_Details;
import com.damian.apexedu.util.GetAlert;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Lecturer_DetailsImpl_DAO implements Lecturer_Details {

    @Override
    public boolean add(com.damian.apexedu.entity.Lecturer_Details lecturerDetails) {
        boolean  b = false;
        try {
            b = PerformCRUD.execute("INSERT INTO lecturer_details VALUES (?,?,?,?,?,?,?)",lecturerDetails.getLecturer_id(),lecturerDetails.getProgram_id(),lecturerDetails.getLecturer_name(),lecturerDetails.getLecturer_address(),lecturerDetails.getLecturer_email(),lecturerDetails.getLecturer_telephone(),lecturerDetails.getBasic_salary());
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }
        return b;
    }

    @Override
    public boolean update(com.damian.apexedu.entity.Lecturer_Details lecturerDetails,String id) {
        boolean b = false;
        try {
           b =  PerformCRUD.execute("UPDATE lecturer_details SET lecturer_id=?,program_id=?,lecturer_name=?,lecturer_address=?,lecturer_email=?,lecturer_telephone=?,basic_salary=? WHERE lecturer_id=?",lecturerDetails.getLecturer_id(),lecturerDetails.getProgram_id(),lecturerDetails.getLecturer_name(),lecturerDetails.getLecturer_address(),lecturerDetails.getLecturer_email(),lecturerDetails.getLecturer_telephone(),lecturerDetails.getBasic_salary(),id);
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
            b = PerformCRUD.execute("DELETE FROM lecturer_details WHERE lecturer_id=?",id);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }
        return b;
    }

    @Override
    public Optional<com.damian.apexedu.entity.Lecturer_Details> search(String id) {
        com.damian.apexedu.entity.Lecturer_Details lecturerDetails = null;
        try {
         ResultSet rs =PerformCRUD.execute("SELECT * FROM lecturer_details WHERE lecturer_id=?",id);
         while (rs.next()){
             lecturerDetails = new com.damian.apexedu.entity.Lecturer_Details(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getDouble(7));
         }

        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }
        return Optional.ofNullable(lecturerDetails);

    }

    @Override
    public List<com.damian.apexedu.entity.Lecturer_Details> getAll() {
        return null;
    }
}
