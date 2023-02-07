package com.damian.apexedu.util;

import javafx.scene.control.Alert;

public class GetAlert {
    private static Alert a;
   private static GetAlert ga;

    private GetAlert(){
        a = new Alert(Alert.AlertType.INFORMATION);
    }


    public static GetAlert getInstance(){
        if(ga==null){
            ga = new GetAlert();
            return ga;
        }
        return ga;
    }

    public static Alert getAlertReference(){
        return a;
    }
}
