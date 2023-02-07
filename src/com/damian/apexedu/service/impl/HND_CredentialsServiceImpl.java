package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.custom.HND_Credentials;
import com.damian.apexedu.dao.impl.HND_CredentialsImpl_DAO;
import com.damian.apexedu.dto.HND_Student_Credentials_DTO;
import com.damian.apexedu.entity.HND_Student_Credentials;
import com.damian.apexedu.service.custom.HND_Credentials_Service;
import com.damian.apexedu.service.util.Converter;

import java.util.Optional;

public class HND_CredentialsServiceImpl implements HND_Credentials_Service {
    @Override
    public boolean add(HND_Student_Credentials_DTO hndStudentCredentialsDto) {
        HND_Student_Credentials hndStudentCredentials = Converter.toHNDStudentCredentials(hndStudentCredentialsDto);
        HND_CredentialsImpl_DAO hndCredentialsImplDao = DAOFactory.getDAOObject(DAOTypes.HND_STUDENT_CREDENTIALS_DAO);
        boolean add = hndCredentialsImplDao.add(hndStudentCredentials);
        return add;
    }

    @Override
    public boolean update(HND_Student_Credentials_DTO hndStudentCredentialsDto) {
        HND_Student_Credentials hndStudentCredentials = Converter.toHNDStudentCredentials(hndStudentCredentialsDto);
        HND_CredentialsImpl_DAO  hndCredentialsImplDao= DAOFactory.getDAOObject(DAOTypes.HND_STUDENT_CREDENTIALS_DAO);
        boolean update = hndCredentialsImplDao.update(hndStudentCredentials, hndStudentCredentials.getStudent_id());
        return update;
    }

    @Override
    public Optional<HND_Student_Credentials_DTO> searchByEmail(String email) {
        HND_CredentialsImpl_DAO hndCredentialsImplDao = DAOFactory.getDAOObject(DAOTypes.HND_STUDENT_CREDENTIALS_DAO);
        Optional<HND_Student_Credentials> hndStudentCredentials = hndCredentialsImplDao.searchByEmail(email);
        if (hndStudentCredentials.isPresent()) {
            return Optional.of(Converter.toHND_student_credentials_dto(hndStudentCredentials.get()));
        }
        return null;
    }

    @Override
    public HND_Student_Credentials_DTO search(String id) {
       HND_CredentialsImpl_DAO hndCredentialsImplDao =  DAOFactory.getDAOObject(DAOTypes.HND_STUDENT_CREDENTIALS_DAO);
        Optional<HND_Student_Credentials> credentials = hndCredentialsImplDao.search(id);
        if(credentials.isPresent()){
            return Converter.toHND_student_credentials_dto(credentials.get());
        }
        return null;
    }
}
