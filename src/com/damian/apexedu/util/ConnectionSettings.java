package com.damian.apexedu.util;

import com.damian.apexedu.db.DBConnection;
import com.mongodb.DB;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionSettings {
   public static Connection connection;
    public static void  setAutoCommit(boolean commitStatus){
        try {
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(commitStatus);
        } catch (SQLException | ClassNotFoundException e) {
           AlertSender.sendAlert(e.getLocalizedMessage(),"Error", Alert.AlertType.ERROR);
        }
    }
    public static void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"Error", Alert.AlertType.ERROR);
        };
    }
    public static void commit(){
        try {
            connection.commit();
        } catch (SQLException e) {
            AlertSender.sendAlert(e.getLocalizedMessage(),"Error", Alert.AlertType.ERROR);
        }
    }
}
