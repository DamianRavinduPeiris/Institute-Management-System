package com.damian.apexedu.dao.impl;

import com.damian.apexedu.dao.custom.HND_Announcements;
import com.damian.apexedu.util.AlertSender;
import com.damian.apexedu.util.PerformCRUD;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HND_AnnouncementsImpl_DAO implements HND_Announcements {

    @Override
    public boolean add(com.damian.apexedu.entity.HND_Announcements hndAnnouncements) {
       boolean b =false;
        try {
            b = PerformCRUD.execute("INSERT INTO hnd_announcements VALUES(?,?,?,?)",hndAnnouncements.getDate(),hndAnnouncements.getLecturer_name(),hndAnnouncements.getDescription(),hndAnnouncements.getDue_date());
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"EROOR!", Alert.AlertType.ERROR);
        }
        return b;
    }

    @Override
    public boolean update(com.damian.apexedu.entity.HND_Announcements hndAnnouncements,String id) {
        return false;
    }

    @Override
    public boolean delete(String d) {
        boolean b =false;
        try {
            b = PerformCRUD.execute("DELETE FROM hnd_announcements WHERE description=?",d);
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"EROOR!", Alert.AlertType.ERROR);
        }
        return b;
    }

    @Override
    public Optional<com.damian.apexedu.entity.HND_Announcements> search(String s) {
        return null;
    }

    @Override
    public List<com.damian.apexedu.entity.HND_Announcements> getAll() {
        ArrayList<com.damian.apexedu.entity.HND_Announcements> hndAnnouncements = new ArrayList<>();
        try {
            ResultSet rs = PerformCRUD.execute("SELECT * FROM hnd_announcements");
            while (rs.next()){
                com.damian.apexedu.entity.HND_Announcements hndAnnouncements1 = new com.damian.apexedu.entity.HND_Announcements(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                hndAnnouncements.add(hndAnnouncements1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"EROOR!", Alert.AlertType.ERROR);
        }
        return hndAnnouncements;
    }
}
