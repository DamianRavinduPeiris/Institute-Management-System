package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.Lecturer_CredentialsImpl_DAO;
import com.damian.apexedu.dto.Lecturer_Credentials_DTO;
import com.damian.apexedu.entity.Lecturer_Credentials;
import com.damian.apexedu.service.custom.Lecturer_Credentials_Service;
import com.damian.apexedu.service.util.Converter;
import com.damian.apexedu.util.AlertSender;
import javafx.scene.control.Alert;

import java.util.Optional;

public class Lecturer_CredentialsServiceImpl implements Lecturer_Credentials_Service {
    @Override
    public boolean add(Lecturer_Credentials_DTO lcd) {
        Lecturer_Credentials lecturerCredentials = Converter.toLecturerCredentials(lcd);
        Lecturer_CredentialsImpl_DAO lecturerCredentialsImplDao = DAOFactory.getDAOObject(DAOTypes.LECTURER_CREDENTIALS);
       return lecturerCredentialsImplDao.add(lecturerCredentials);
    }

    @Override
    public boolean update(Lecturer_Credentials_DTO lcd) {
        Lecturer_Credentials lecturerCredentials = Converter.toLecturerCredentials(lcd);
        Lecturer_CredentialsImpl_DAO lecturerCredentialsImplDao  = DAOFactory.getDAOObject(DAOTypes.LECTURER_CREDENTIALS);
        return  lecturerCredentialsImplDao.update(lecturerCredentials,lecturerCredentials.getLecturer_id());
    }
    @Override
    public boolean delete(String id) {
        Lecturer_CredentialsImpl_DAO lecturerCredentialsImplDao  = DAOFactory.getDAOObject(DAOTypes.LECTURER_CREDENTIALS);
        return lecturerCredentialsImplDao.delete(id);
    }

    @Override
    public Lecturer_Credentials_DTO search(String id) {
        Lecturer_CredentialsImpl_DAO lecturerCredentialsImplDao = DAOFactory.getDAOObject(DAOTypes.LECTURER_CREDENTIALS);
        Optional<Lecturer_Credentials> lc = lecturerCredentialsImplDao.search(id);
        if(lc.isPresent()){
            return   Converter.toLecturerCredentialsDto(lc.get());
        }
        return null;
    }

    @Override
    public Lecturer_Credentials_DTO searchByEmail(String email) {
       Lecturer_CredentialsImpl_DAO lcd = DAOFactory.getDAOObject(DAOTypes.LECTURER_CREDENTIALS);
        Lecturer_Credentials lecturerCredentials = lcd.searchByEmail(email);
        if(lecturerCredentials!=null){
            return  Converter.toLecturerCredentialsDto(lecturerCredentials);
        }
        return null;
    }
}
