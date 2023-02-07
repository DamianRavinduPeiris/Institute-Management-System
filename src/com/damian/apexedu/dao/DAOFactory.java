package com.damian.apexedu.dao;

import com.damian.apexedu.dao.impl.*;
import com.damian.apexedu.util.GetAlert;
import javafx.scene.control.Alert;

public class DAOFactory <T>{
    public static <T> T getDAOObject(DAOTypes daoTypes){
        switch (daoTypes){
            case CCS_IMPL:
                return (T) new CCS_Impl_DAO();
            case CCS_FEES:
                return (T) new CCS_Feesimpl_DAO();
            case CCS_STUDENT_CREDENTIALS:
                return (T) new CCS_CredentialsImpl_DAO();
            case HND_DETAILS_DAO:
                return (T) new HND_Impl_DAO();
             case HND_FEES_DAO:
                return (T) new HND_FeesImpl_DAO();
            case HND_STUDENT_CREDENTIALS_DAO:
                return (T) new HND_CredentialsImpl_DAO();
            case UNDERGRAD:
                return (T) new UnderGrad_Imple_DAO();
            case UNDERGRAD_FEES:
                return (T) new UnderGrad_FeesImpl_DAO();
            case UNDERGRAD_CREDENTIALS:
                return (T) new UnderGrad_CredentialsImpl_DAO();
            case LECTURER_DETAILS:
                return (T) new Lecturer_DetailsImpl_DAO();
            case LECTURER_CREDENTIALS:
                return (T) new Lecturer_CredentialsImpl_DAO();
            case CCS_ATTENDANCE:
                return (T) new CCS_AttendanceImpl_DAO();
            case HND_ATTENDANCE:
                return (T) new HND_AttendanceImpl_DAO();
            case UNDERGRAD_ATTENDANCE:
                return (T) new UnderGrad_AttendanceImpl_DAO();
            case CCS_ANNOUNCEMENTS:
                return (T) new CCS_AnnouncementsImpl_DAO();
            case HND_ANNOUNCEMENTS:
                return (T) new HND_AnnouncementsImpl_DAO();
            case UNDERGRAD_ANNOUNCEMENTS:
                return (T) new UNDERGRAD_AnnouncementsImpl_DAO();
            default:
                Alert a = GetAlert.getInstance().getAlertReference();
                a.setContentText("DAO not found!");
                a.setAlertType(Alert.AlertType.WARNING);
                a.show();
        }
        return null;
    }
}
