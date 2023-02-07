package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.HND_Impl_DAO;
import com.damian.apexedu.db.DBConnection;
import com.damian.apexedu.dto.HND_DTO;
import com.damian.apexedu.dto.HND_Fees_DTO;
import com.damian.apexedu.dto.HND_Student_Credentials_DTO;
import com.damian.apexedu.entity.HND;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.ServiceTypes;
import com.damian.apexedu.service.custom.HND_Details_Service;
import com.damian.apexedu.service.util.Converter;
import com.damian.apexedu.util.GetAlert;
import com.mongodb.DB;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HND_DetailsServiceImpl implements HND_Details_Service {
    public HND_Fees_DTO hnd_fees_dto;
    public HND_Student_Credentials_DTO hnd_student_credentials_dto;

    @Override
    public boolean add(HND_DTO hnd_dto) {
        HND hnd = Converter.toHND(hnd_dto);
        HND_Impl_DAO hndImplDao = DAOFactory.getDAOObject(DAOTypes.HND_DETAILS_DAO);

        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            Alert a = GetAlert.getInstance().getAlertReference();
            a.setContentText(e.getLocalizedMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }

        boolean b1 = hndImplDao.add(hnd);
        if(b1){
            HND_CredentialsServiceImpl hndCredentialsService  = ServiceFactory.getServiceObject(ServiceTypes.HND_CREDENTIALS);
            boolean b2 = hndCredentialsService.add(this.hnd_student_credentials_dto);
            if(b2){
                HND_FeesServiceImpl hndFeesService  = ServiceFactory.getServiceObject(ServiceTypes.HND_FEES);
                boolean b3 = hndFeesService.add(this.hnd_fees_dto);
                if(b3){
                    try {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
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
                    }

                }
                else{
                    try {
                        DBConnection.getInstance().getConnection().rollback();
                    } catch (SQLException | ClassNotFoundException e) {
                        Alert a = GetAlert.getInstance().getAlertReference();
                        a.setContentText(e.getLocalizedMessage());
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.show();
                    }
                }

            }
            else {
                try {
                    DBConnection.getInstance().getConnection().rollback();
                } catch (SQLException | ClassNotFoundException e) {
                    Alert a = GetAlert.getInstance().getAlertReference();
                    a.setContentText(e.getLocalizedMessage());
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.show();
                }
            }
        }
        else{
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
    public boolean update(HND_DTO hnd_dto) {
        HND hnd = Converter.toHND(hnd_dto);
        HND_Impl_DAO hndImplDao = DAOFactory.getDAOObject(DAOTypes.HND_DETAILS_DAO);
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean b1 = hndImplDao.update(hnd, hnd.getStudent_id());
        if(b1){
            HND_CredentialsServiceImpl hndCredentialsService = ServiceFactory.getServiceObject(ServiceTypes.HND_CREDENTIALS);
            boolean b2 = hndCredentialsService.update(this.hnd_student_credentials_dto);
            if(b2){
                HND_FeesServiceImpl hndFeesService = ServiceFactory.getServiceObject(ServiceTypes.HND_FEES);
                boolean b3 = hndFeesService.update(this.hnd_fees_dto);
                if(b3){
                    try {
                        DBConnection.getInstance().getConnection().commit();
                    } catch (SQLException | ClassNotFoundException e) {
                        GetAlert.getInstance().getAlertReference().setContentText(e.toString());
                        GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
                        GetAlert.getInstance().getAlertReference().show();

                    }finally {
                        try {
                            DBConnection.getInstance().getConnection().setAutoCommit(true);
                        } catch (SQLException | ClassNotFoundException e) {
                            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
                            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
                            GetAlert.getInstance().getAlertReference().show();
                        }
                        return true;
                    }

                }
                else {
                    try {
                        DBConnection.getInstance().getConnection().rollback();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

            }else {
                try {
                    DBConnection.getInstance().getConnection().rollback();
                } catch (SQLException | ClassNotFoundException e) {
                    GetAlert.getInstance().getAlertReference().setContentText(e.toString());
                    GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
                    GetAlert.getInstance().getAlertReference().show();
                }
            }

        }else {
            try {
                DBConnection.getInstance().getConnection().rollback();
            } catch (SQLException | ClassNotFoundException e) {
                GetAlert.getInstance().getAlertReference().setContentText(e.toString());
                GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
                GetAlert.getInstance().getAlertReference().show();
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        HND_Impl_DAO hndImplDao = DAOFactory.getDAOObject(DAOTypes.HND_DETAILS_DAO);
        boolean delete = hndImplDao.delete(id);
        return delete;
    }

    @Override
    public HND_DTO search(String id) {
       HND_Impl_DAO hndImplDao= DAOFactory.getDAOObject(DAOTypes.HND_DETAILS_DAO);
        Optional<HND> hnd = hndImplDao.search(id);
        if(hnd.isPresent()){
            HND_DTO hndDto = Converter.toHND_dto(hnd.get());
            return hndDto;

        }
        return null;
    }

    @Override
    public ArrayList<HND_DTO> getAll() {
        HND_Impl_DAO hndImplDao=DAOFactory.getDAOObject(DAOTypes.HND_DETAILS_DAO);
        List<HND> all = hndImplDao.getAll();
        return  Converter.toHND_list_dto_arraylist((ArrayList<HND>) all);
    }

    public void setValues(HND_Fees_DTO hnd_fees_dto, HND_Student_Credentials_DTO hnd_student_credentials_dto) {
        this.hnd_fees_dto = hnd_fees_dto;
        this.hnd_student_credentials_dto = hnd_student_credentials_dto;
    }
}
