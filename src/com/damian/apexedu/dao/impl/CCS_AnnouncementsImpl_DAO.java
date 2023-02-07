package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.CCS_Announcements;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CCS_AnnouncementsImpl_DAO implements CCS_Announcements {


    @Override
    public boolean add(com.damian.apexedu.entity.CCS_Announcements ccsAnnouncements) {
        boolean b = false;
        try {
            b = PerformCRUD.execute("INSERT INTO ccs_announcements VALUES(?,?,?,?)",ccsAnnouncements.getDate(),ccsAnnouncements.getLecturer_name(),ccsAnnouncements.getDescription(),ccsAnnouncements.getDue_date());
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR!", Alert.AlertType.ERROR);
        }
        return b;
    }

    @Override
    public boolean update(com.damian.apexedu.entity.CCS_Announcements ccsAnnouncements,String id) {
        return false;
    }

    @Override
    public boolean delete(String description) {

        try {
            return  PerformCRUD.execute("DELETE FROM ccs_announcements WHERE description = ?",description);
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR!", Alert.AlertType.ERROR);
        }
        return false;
    }

    @Override
    public Optional<com.damian.apexedu.entity.CCS_Announcements> search(String s) {
        return null;
    }

    @Override
    public List<com.damian.apexedu.entity.CCS_Announcements> getAll() {
        ArrayList<com.damian.apexedu.entity.CCS_Announcements> ccsAnnouncements = new ArrayList<>();
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM ccs_announcements");
            while (rs.next()){
                com.damian.apexedu.entity.CCS_Announcements ccs_announcements = new com.damian.apexedu.entity.CCS_Announcements(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                ccsAnnouncements.add(ccs_announcements);
            }
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"ERROR!", Alert.AlertType.ERROR);
        }
        return ccsAnnouncements;

    }
}

