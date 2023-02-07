package com.damian.apexedu.util;

import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static Pattern usernamePattern = Pattern.compile("^([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)");
    private static Pattern emailPattern = Pattern.compile("^([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)");
    private  static Pattern telephonePattern = Pattern.compile("^(075|077|078|011|091|038|036)([0-9]{7})$");

    public static boolean check(String txt,Patterns patterns){
        switch (patterns){
            case USERNAME:
                boolean b1 = validate(usernamePattern, txt);
                return b1;
            case EMAIL:
                boolean b2 = validate(emailPattern, txt);
                return b2;
            case TELEPHONE:
                boolean b3 = validate(telephonePattern, txt);
                return b3;

            default:
                    GetAlert.getInstance().getAlertReference().setContentText("VALIDATION FAILED");
                    GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
                    GetAlert.getInstance().getAlertReference().show();
        }


    return false;
    }
    public static boolean validate(Pattern pattern,String txt){
        Matcher matcher = pattern.matcher(txt);
        if(matcher.matches()){
            return true;
        }
        return false;
    }
}
