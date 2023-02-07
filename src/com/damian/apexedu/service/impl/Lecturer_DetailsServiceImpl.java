package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.Lecturer_DetailsImpl_DAO;
import com.damian.apexedu.db.DBConnection;
import com.damian.apexedu.dto.Lecturer_Credentials_DTO;
import com.damian.apexedu.dto.Lecturer_Details_DTO;
import com.damian.apexedu.entity.Lecturer_Details;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.custom.Lecturer_Details_Service;
import com.damian.apexedu.service.util.Converter;
import com.damian.apexedu.util.GetAlert;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.Optional;

public class Lecturer_DetailsServiceImpl implements Lecturer_Details_Service {
    public Lecturer_Credentials_DTO lecturer_credentials_dto;
    @Override
    public boolean add(Lecturer_Details_DTO lecturer_details_dto) {
        Lecturer_Details lecturerDetails = Converter.toLecturerDetails(lecturer_details_dto);

        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }

        Lecturer_DetailsImpl_DAO lecturerDetailsImplDao =DAOFactory.getDAOObject(DAOTypes.LECTURER_DETAILS);
        boolean b1 = lecturerDetailsImplDao.add(lecturerDetails);
        if(b1){
            Lecturer_CredentialsServiceImpl lecturerCredentialsService = ServiceFactory.getServiceObject(ServiceTypes.LECTURER_CREDENTIALS);
            boolean b2 = lecturerCredentialsService.add(this.lecturer_credentials_dto);
            if(b2){
                try {
                    DBConnection.getInstance().getConnection().commit();
                } catch (SQLException | ClassNotFoundException e) {
                    Alert a = GetAlert.getInstance().getAlertReference();
                    a.setContentText(e.getLocalizedMessage());
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.show();
                }finally {
                    try {
                        DBConnection.getInstance().getConnection().setAutoCommit(true);
                    } catch (SQLException | ClassNotFoundException e) {
                        Alert a = GetAlert.getInstance().getAlertReference();
                        a.setContentText(e.getLocalizedMessage());
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.show();
                    }
                    return true;
                }
            }else{
                try {
                    DBConnection.getInstance().getConnection().rollback();
                } catch (SQLException | ClassNotFoundException e) {
                    Alert a = GetAlert.getInstance().getAlertReference();
                    a.setContentText(e.getLocalizedMessage());
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.show();
                }
            }


        }else{
            try {
                DBConnection.getInstance().getConnection().rollback();
            } catch (SQLException | ClassNotFoundException e) {
                Alert a = GetAlert.getInstance().getAlertReference();
                a.setContentText(e.getLocalizedMessage());
                a.setAlertType(Alert.AlertType.ERROR);
                a.show();
            }
        }
        return false;

    }

    @Override
    public boolean update(Lecturer_Details_DTO lecturer_details_dto) {
        Lecturer_Details lecturerDetails = Converter.toLecturerDetails(lecturer_details_dto);
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }
        Lecturer_DetailsImpl_DAO lecturerDetailsImplDao  = DAOFactory.getDAOObject(DAOTypes.LECTURER_DETAILS);
        boolean b1 = lecturerDetailsImplDao.update(lecturerDetails, lecturerDetails.getLecturer_id());
        if(b1){
            Lecturer_CredentialsServiceImpl  lecturerCredentialsService= ServiceFactory.getServiceObject(ServiceTypes.LECTURER_CREDENTIALS);
            boolean b2 = lecturerCredentialsService.update(this.lecturer_credentials_dto);
            if(b2){
                try {
                    DBConnection.getInstance().getConnection().commit();
                } catch (SQLException | ClassNotFoundException e) {
                    Alert a = GetAlert.getInstance().getAlertReference();
                    a.setContentText(e.getLocalizedMessage());
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.show();
                }finally {
                    try {
                        DBConnection.getInstance().getConnection().setAutoCommit(true);
                    } catch (SQLException | ClassNotFoundException e) {
                        Alert a = GetAlert.getInstance().getAlertReference();
                        a.setContentText(e.getLocalizedMessage());
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.show();
                    }
                    return true;
                }

            }else{
                try {
                    DBConnection.getInstance().getConnection().rollback();
                } catch (SQLException | ClassNotFoundException e) {
                    Alert a = GetAlert.getInstance().getAlertReference();
                    a.setContentText(e.getLocalizedMessage());
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.show();
                }
            }

        }else{
            try {
                DBConnection.getInstance().getConnection().rollback();
            } catch (SQLException | ClassNotFoundException e) {
                Alert a = GetAlert.getInstance().getAlertReference();
                a.setContentText(e.getLocalizedMessage());
                a.setAlertType(Alert.AlertType.ERROR);
                a.show();
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
       Lecturer_DetailsImpl_DAO lecturerDetailsImplDao= DAOFactory.getDAOObject(DAOTypes.LECTURER_DETAILS);
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }
        boolean b1 = lecturerDetailsImplDao.delete(id);
        if(b1){
            Lecturer_CredentialsServiceImpl lecturerCredentialsService = ServiceFactory.getServiceObject(ServiceTypes.LECTURER_CREDENTIALS);
            boolean b2 = lecturerCredentialsService.delete(id);
            if(b2){
                try {
                    DBConnection.getInstance().getConnection().commit();
                } catch (SQLException | ClassNotFoundException e) {
                    Alert a = GetAlert.getInstance().getAlertReference();
                    a.setContentText(e.getLocalizedMessage());
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.show();
                }finally {
                    try {
                        DBConnection.getInstance().getConnection().setAutoCommit(true);
                    } catch (SQLException | ClassNotFoundException e) {
                        Alert a = GetAlert.getInstance().getAlertReference();
                        a.setContentText(e.getLocalizedMessage());
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.show();
                    }
                    return true;
                }
            }else{
                try {
                    DBConnection.getInstance().getConnection().rollback();
                } catch (SQLException | ClassNotFoundException e) {
                    Alert a = GetAlert.getInstance().getAlertReference();
                    a.setContentText(e.getLocalizedMessage());
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.show();
                }
            }


        }else {
            try {
                DBConnection.getInstance().getConnection().rollback();
            } catch (SQLException | ClassNotFoundException e) {
                Alert a = GetAlert.getInstance().getAlertReference();
                a.setContentText(e.getLocalizedMessage());
                a.setAlertType(Alert.AlertType.ERROR);
                a.show();
            }
        }
        return false;
    }

    @Override
    public Lecturer_Details_DTO search(String id) {
        Lecturer_DetailsImpl_DAO lecturerDetailsImplDao= DAOFactory.getDAOObject(DAOTypes.LECTURER_DETAILS);
        Optional<Lecturer_Details> ld = lecturerDetailsImplDao.search(id);
        if(ld.isPresent()){
            return Converter.toLecturerDetailsDto(ld.get());
        }
        return null;
    }



    public void setValues(Lecturer_Credentials_DTO lecturer_credentials_dto){
        this.lecturer_credentials_dto=lecturer_credentials_dto;
    }

}
