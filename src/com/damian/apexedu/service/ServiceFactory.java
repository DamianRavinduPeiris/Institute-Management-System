package com.damian.apexedu.service;

import com.damian.apexedu.service.impl.*;
import com.damian.apexedu.util.GetAlert;
import javafx.scene.control.Alert;

public class ServiceFactory <T>{
    public static <T> T getServiceObject(ServiceTypes serviceTypes) {
        switch (serviceTypes) {
            case CCS_DETAILS:
                return (T) new CCS_DetailsServiceImpl();
            case CCS_CREDENTIALS:
                    return (T) new CCS_CredentialImplService();
            case CCS_FEES:
                return (T) new CCS_FeesServiceImpl();
            case HND_DETAILS:
                return (T) new HND_DetailsServiceImpl();
            case HND_CREDENTIALS:
                return (T) new HND_CredentialsServiceImpl();
            case HND_FEES:
                    return (T) new HND_FeesServiceImpl();
            case UNDERGRAD:
                return (T) new UnderGrad_DetailsServiceImpl();
            case UNDERGRAD_CREDENTIALS:
                return (T) new UnderGrad_CredentialsServiceImpl();
            case UNDERGRAD_FEES:
                return (T) new UnderGrad_FeesServiceImpl();
            case LECTURER_DETAILS:
                return (T) new Lecturer_DetailsServiceImpl();
            case LECTURER_CREDENTIALS:
                return (T) new Lecturer_CredentialsServiceImpl();
            case CCS_ATTENDANCE:
                return (T) new CCS_AttendanceServiceImpl();
             case HND_ATTENDANCE:
                return (T) new HND_AttendanceServiceImpl();
            case UNDERGRAD_ATTENDANCE:
                return (T) new UnderGrad_AttendanceServiceImpl();
            case CCS_ANNOUNCEMENTS:
                return (T) new CCS_AnnouncementsService_Impl();
             case HND_ANNOUNCEMENTS:
                return (T) new HND_AnnouncementsService_Impl();
             case UNDERGRAD_ANNOUNCEMENTS:
                return (T) new UnderGrad_AnnouncementsService_Impl();
                default:
                Alert a = GetAlert.getInstance().getAlertReference();
                a.setContentText("Service not found!");
                a.setAlertType(Alert.AlertType.WARNING);
                a.show();

        }

        return null;
    }

}
