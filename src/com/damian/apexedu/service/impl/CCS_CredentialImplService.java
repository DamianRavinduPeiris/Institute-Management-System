package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.CCS_CredentialsImpl_DAO;
import com.damian.apexedu.dao.impl.CCS_Feesimpl_DAO;
import com.damian.apexedu.dto.CCS_DTO;
import com.damian.apexedu.dto.CCS_Student_Credentials_DTO;
import com.damian.apexedu.entity.CCS_Fees;
import com.damian.apexedu.entity.CCS_Student_Credentials;
import com.damian.apexedu.service.custom.CCS_Credentials_Service;
import com.damian.apexedu.service.custom.CCS_Details_Service;
import com.damian.apexedu.service.util.Converter;

import java.util.List;
import java.util.Optional;

import static com.damian.apexedu.dao.DAOTypes.CCS_STUDENT_CREDENTIALS;

public class CCS_CredentialImplService implements CCS_Credentials_Service {


    @Override
    public boolean add(CCS_Student_Credentials_DTO ccs_student_credentials_dto) {
        CCS_Student_Credentials ccsStudentCredentials = Converter.toCCSStudentCredentials(ccs_student_credentials_dto);
        CCS_CredentialsImpl_DAO ccsCredentialsImplDao = DAOFactory.getDAOObject(CCS_STUDENT_CREDENTIALS);
        boolean b = ccsCredentialsImplDao.add(ccsStudentCredentials);
        return b;
    }

    @Override
    public boolean update(CCS_Student_Credentials_DTO ccs_student_credentials_dto,String id) {
        CCS_Student_Credentials ccsStudentCredentials = Converter.toCCSStudentCredentials(ccs_student_credentials_dto);
        CCS_CredentialsImpl_DAO ccsCredentialsImplDao = DAOFactory.getDAOObject(CCS_STUDENT_CREDENTIALS);
        boolean update = ccsCredentialsImplDao.update(ccsStudentCredentials, id);

        return update;
    }

    @Override
    public CCS_Student_Credentials_DTO search(String id) {
        CCS_CredentialsImpl_DAO ccsCredentialsImplDao  = DAOFactory.getDAOObject(CCS_STUDENT_CREDENTIALS);
        Optional<CCS_Student_Credentials> csc = ccsCredentialsImplDao.search(id);
        if(csc.isPresent()){
            return Converter.toCCS_student_credentials_dto(csc.get());
        }
        return null;

    }

    @Override
    public Optional<CCS_Student_Credentials_DTO> searchByEmail(String email) {
        CCS_CredentialsImpl_DAO ccsCredentialsImplDao= DAOFactory.getDAOObject(DAOTypes.CCS_STUDENT_CREDENTIALS);
        Optional<CCS_Student_Credentials> studentCredentials = ccsCredentialsImplDao.searchByEmail(email);
        if(studentCredentials.isPresent()){
            return  Optional.of(Converter.toCCS_student_credentials_dto((CCS_Student_Credentials) studentCredentials.get()));
        }
        return null;
    }
}
