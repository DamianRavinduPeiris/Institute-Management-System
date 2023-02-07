package com.damian.apexedu.util;

import javafx.scene.control.Alert;

public class AlertSender {
    public static void sendAlert(String message,String header, Alert.AlertType alertType){
        Alert a = GetAlert.getInstance().getAlertReference();
        a.setAlertType(alertType);
        a.setHeaderText(header);
        a.setContentText(message);
        a.show();
    }
}
