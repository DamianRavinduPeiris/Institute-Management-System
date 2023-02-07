package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.UnderGrad_FeesImpl_DAO;
import com.damian.apexedu.dto.UNDERGRAD_Fees_DTO;
import com.damian.apexedu.entity.UNDERGRAD_Fees;
import com.damian.apexedu.service.custom.UnderGrad_Fees_Service;
import com.damian.apexedu.service.util.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UnderGrad_FeesServiceImpl implements UnderGrad_Fees_Service {
    @Override
    public boolean add(UNDERGRAD_Fees_DTO dto) {
        UNDERGRAD_Fees undergradFees = Converter.toUnderGradFees(dto);
        UnderGrad_FeesImpl_DAO underGradFeesImplDao =  DAOFactory.getDAOObject(DAOTypes.UNDERGRAD_FEES);
        boolean add = underGradFeesImplDao.add(undergradFees);
        return add;
    }

    @Override
    public boolean update(UNDERGRAD_Fees_DTO dto) {
        UNDERGRAD_Fees undergradFees = Converter.toUnderGradFees(dto);
       UnderGrad_FeesImpl_DAO underGradFeesImplDao =  DAOFactory.getDAOObject(DAOTypes.UNDERGRAD_FEES);
        return  underGradFeesImplDao.update(undergradFees,undergradFees.getStudent_id());

    }

    @Override
    public UNDERGRAD_Fees_DTO search(String id) {
        UnderGrad_FeesImpl_DAO underGradFeesImplDao = DAOFactory.getDAOObject(DAOTypes.UNDERGRAD_FEES);
        Optional<UNDERGRAD_Fees> uf = underGradFeesImplDao.search(id);
        if(uf.isPresent()){
            UNDERGRAD_Fees_DTO undergradFeesDto = Converter.toUnderGradFeesDto(uf.get());
            return undergradFeesDto;
        }
        return null;
    }

    @Override
    public ArrayList<UNDERGRAD_Fees_DTO> getAll() {
        UnderGrad_FeesImpl_DAO underGradFeesImplDao =DAOFactory.getDAOObject(DAOTypes.UNDERGRAD_FEES);
        List<UNDERGRAD_Fees> underGradFees = underGradFeesImplDao.getAll();
        return  Converter.toUndergrad_fees_dto_arraylist((ArrayList<UNDERGRAD_Fees>) underGradFees);
    }
}
