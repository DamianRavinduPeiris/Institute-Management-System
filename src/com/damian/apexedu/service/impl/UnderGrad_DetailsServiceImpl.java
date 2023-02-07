package com.damian.apexedu.service.impl;


import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.UnderGrad_Imple_DAO;
import com.damian.apexedu.db.DBConnection;
import com.damian.apexedu.dto.UNDERGRAD_DTO;
import com.damian.apexedu.dto.UNDERGRAD_Fees_DTO;
import com.damian.apexedu.dto.UNDERGRAD_Student_Credentials_DTO;
import com.damian.apexedu.entity.UNDERGRAD;
import com.damian.apexedu.service.ServiceFactory;
import com.damian.apexedu.service.custom.UNDERGRAD_Details_Service;
import com.damian.apexedu.service.util.Converter;
import com.damian.apexedu.util.GetAlert;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.damian.apexedu.service.ServiceTypes.UNDERGRAD_CREDENTIALS;
import static com.damian.apexedu.service.ServiceTypes.UNDERGRAD_FEES;

public class UnderGrad_DetailsServiceImpl implements UNDERGRAD_Details_Service {
    public UNDERGRAD_Fees_DTO fees;
    public UNDERGRAD_Student_Credentials_DTO credentials;

    @Override
    public boolean add(UNDERGRAD_DTO udto) {
        UNDERGRAD undergrad = Converter.toUnderGrad(udto);
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
        UnderGrad_Imple_DAO underGradImpleDao=  DAOFactory.getDAOObject(DAOTypes.UNDERGRAD);
        boolean b1 = underGradImpleDao.add(undergrad);
        if(b1){
            UnderGrad_CredentialsServiceImpl underGradCredentialsService=ServiceFactory.getServiceObject(UNDERGRAD_CREDENTIALS);
            boolean b2 = underGradCredentialsService.add(this.credentials);
            if(b2){
                UnderGrad_FeesServiceImpl underGradFeesService= ServiceFactory.getServiceObject(UNDERGRAD_FEES);
                boolean b3 = underGradFeesService.add(this.fees);
                if(b3){
                    try {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
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
                else{
                    rollback();
                }

            }else {
                rollback();
            }
        }
        else{
            rollback();
        }
        return false;

    }

    @Override
    public boolean update(UNDERGRAD_DTO udto) {
        UNDERGRAD undergrad = Converter.toUnderGrad(udto);
        UnderGrad_Imple_DAO underGradImpleDao = DAOFactory.getDAOObject(DAOTypes.UNDERGRAD);
        boolean b1 = underGradImpleDao.update(undergrad, undergrad.getStudent_id());
        if(b1){
            UnderGrad_FeesServiceImpl  underGradFeesService= ServiceFactory.getServiceObject(UNDERGRAD_FEES);
            boolean b2 = underGradFeesService.update(this.fees);
            if(b2){
                UnderGrad_CredentialsServiceImpl underGradCredentialsService = ServiceFactory.getServiceObject(UNDERGRAD_CREDENTIALS);
                boolean b3 = underGradCredentialsService.update(this.credentials);
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

                }else {
                    rollback();
                }            }
            else{
                rollback();
            }

        }
        else{
            rollback();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
       UnderGrad_Imple_DAO underGradImpleDao = DAOFactory.getDAOObject(DAOTypes.UNDERGRAD);
       return underGradImpleDao.delete(id);
    }

    @Override
    public UNDERGRAD_DTO search(String id) {
       UnderGrad_Imple_DAO underGradImplDao = DAOFactory.getDAOObject(DAOTypes.UNDERGRAD);
        Optional<UNDERGRAD> ud = underGradImplDao.search(id);
        if(ud.isPresent()){
            UNDERGRAD_DTO undergradDto = Converter.toUnderGradDto(ud.get());
            return undergradDto;

        }
        return null;


    }

    @Override
    public ArrayList<UNDERGRAD_DTO> getAll() {
        UnderGrad_Imple_DAO underGradImpleDao = DAOFactory.getDAOObject(DAOTypes.UNDERGRAD);
        List<UNDERGRAD> all = underGradImpleDao.getAll();
        return  Converter.toUndergrad_list_dto_arraylist((ArrayList<UNDERGRAD>) all);
    }

    public void setValues(UNDERGRAD_Fees_DTO fees, UNDERGRAD_Student_Credentials_DTO credentials){
        this.fees = fees;
        this.credentials = credentials;
    }
    public void rollback(){
        try {
            DBConnection.getInstance().getConnection().rollback();
        } catch (SQLException | ClassNotFoundException e) {
            GetAlert.getInstance().getAlertReference().setContentText(e.toString());
            GetAlert.getInstance().getAlertReference().setAlertType(Alert.AlertType.WARNING);
            GetAlert.getInstance().getAlertReference().show();
        }
    }
}
