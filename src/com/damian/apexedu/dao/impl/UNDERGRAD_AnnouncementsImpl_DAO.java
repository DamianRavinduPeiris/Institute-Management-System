package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.UNDERGRAD_Announcements;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UNDERGRAD_AnnouncementsImpl_DAO implements UNDERGRAD_Announcements {
    @Override
    public boolean add(com.damian.apexedu.entity.UNDERGRAD_Announcements undergradAnnouncements) {
        boolean b   = false;
        try {
            b = PerformCRUD.execute("INSERT INTO undergrad_announcements VALUES (?,?,?,?)",undergradAnnouncements.getDate(),undergradAnnouncements.getLecturer_name(),undergradAnnouncements.getDescription(),undergradAnnouncements.getDue_date());
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"EROOR!", Alert.AlertType.ERROR);
        }
        return  b;
    }

    @Override
    public boolean update(com.damian.apexedu.entity.UNDERGRAD_Announcements undergradAnnouncements, String s) {
        return false;
    }

    @Override
    public boolean delete(String description) {
        boolean b   = false;
        try {
            b =PerformCRUD.execute("DELETE FROM undergrad_announcements WHERE description = ?",description);
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"EROOR!", Alert.AlertType.ERROR);
        }
        return  b;
    }

    @Override
    public Optional<com.damian.apexedu.entity.UNDERGRAD_Announcements> search(String s) {
        return Optional.empty();
    }

    @Override
    public List<com.damian.apexedu.entity.UNDERGRAD_Announcements> getAll() {
        ArrayList<com.damian.apexedu.entity.UNDERGRAD_Announcements> undergradAnnouncements = new ArrayList<>();
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM undergrad_announcements");
            while (rs.next()){
                undergradAnnouncements.add(new com.damian.apexedu.entity.UNDERGRAD_Announcements(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"EROOR!", Alert.AlertType.ERROR);
        }
        return undergradAnnouncements;
    }
}
