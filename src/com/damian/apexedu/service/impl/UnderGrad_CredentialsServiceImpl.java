package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.impl.UnderGrad_CredentialsImpl_DAO;
import com.damian.apexedu.dto.UNDERGRAD_Fees_DTO;
import com.damian.apexedu.dto.UNDERGRAD_Student_Credentials_DTO;
import com.damian.apexedu.entity.UNDERGRAD_Student_Credentials;
import com.damian.apexedu.service.custom.UnderGrad_Credentials_Service;
import com.damian.apexedu.service.custom.UnderGrad_Fees_Service;
import com.damian.apexedu.service.util.Converter;

import java.util.List;
import java.util.Optional;

import static com.damian.apexedu.dao.DAOTypes.UNDERGRAD_CREDENTIALS;

public class UnderGrad_CredentialsServiceImpl implements UnderGrad_Credentials_Service{

    @Override
    public boolean add(UNDERGRAD_Student_Credentials_DTO underGrad_credentials) {
        UNDERGRAD_Student_Credentials undergradStudentCredentials = Converter.toUnderGradStudentCredentials(underGrad_credentials);
        UnderGrad_CredentialsImpl_DAO  underGradCredentialsImplDao=  DAOFactory.getDAOObject(UNDERGRAD_CREDENTIALS);
        boolean add = underGradCredentialsImplDao.add(undergradStudentCredentials);
        return add;
    }

    @Override
    public boolean update(UNDERGRAD_Student_Credentials_DTO underGrad_credentials) {
        UNDERGRAD_Student_Credentials undergradStudentCredentials = Converter.toUnderGradStudentCredentials(underGrad_credentials);
        UnderGrad_CredentialsImpl_DAO underGradCredentialsImplDao = DAOFactory.getDAOObject(UNDERGRAD_CREDENTIALS);
        return underGradCredentialsImplDao.update(undergradStudentCredentials,undergradStudentCredentials.getStudent_id());
    }

    @Override
    public Optional<UNDERGRAD_Student_Credentials_DTO> searchByEmail(String email) {
        UnderGrad_CredentialsImpl_DAO underGradCredentialsImplDao = DAOFactory.getDAOObject(UNDERGRAD_CREDENTIALS);
        Optional<UNDERGRAD_Student_Credentials> usc = underGradCredentialsImplDao.searchByEnail(email);
        if(usc.isPresent()){
            return Optional.of(Converter.toUnderGradStudentCredentialsDto(usc.get()));
        }
        return null;
    }

    @Override
    public UNDERGRAD_Student_Credentials_DTO search(String id) {
        UnderGrad_CredentialsImpl_DAO underGradCredentialsImplDao =DAOFactory.getDAOObject(UNDERGRAD_CREDENTIALS);
        Optional<UNDERGRAD_Student_Credentials> usc = underGradCredentialsImplDao.search(id);
        if(usc.isPresent()){
            return Converter.toUnderGradStudentCredentialsDto(usc.get());
        }
        return null;
    }
}
