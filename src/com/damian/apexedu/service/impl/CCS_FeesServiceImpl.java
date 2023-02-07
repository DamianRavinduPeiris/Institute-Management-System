package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.CCS_Feesimpl_DAO;
import com.damian.apexedu.dao.impl.CCS_Impl_DAO;
import com.damian.apexedu.dto.CCS_Fees_DTO;
import com.damian.apexedu.entity.CCS_Fees;
import com.damian.apexedu.service.custom.CCS_Fees_Service;
import com.damian.apexedu.service.util.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CCS_FeesServiceImpl implements CCS_Fees_Service {
    @Override
    public boolean add(CCS_Fees_DTO ccs_fees_dto) {
        CCS_Fees ccsFees = Converter.toCCSFees(ccs_fees_dto);
        CCS_Feesimpl_DAO ccsFeesimplDao = DAOFactory.getDAOObject(DAOTypes.CCS_FEES);
        boolean b = ccsFeesimplDao.add(ccsFees);
        return b;


    }

    @Override
    public boolean update(CCS_Fees_DTO ccs_fees_dto,String id) {
        CCS_Fees ccsFees = Converter.toCCSFees(ccs_fees_dto);
        System.out.println(ccsFees.getPayment_description());
        CCS_Feesimpl_DAO ccsFeesimplDao = DAOFactory.getDAOObject(DAOTypes.CCS_FEES);
        boolean b = ccsFeesimplDao.update(ccsFees, id);
        return b;
    }

    @Override
    public CCS_Fees_DTO search(String id) {
        CCS_Feesimpl_DAO ccsFeesimplDao =  DAOFactory.getDAOObject(DAOTypes.CCS_FEES);
        Optional<CCS_Fees> fees = ccsFeesimplDao.search(id);
        if(fees.isPresent()){
            CCS_Fees_DTO ccsFeesDto = Converter.toCCS_fees_dto((CCS_Fees) fees.get());
            return ccsFeesDto;
        }
        return null;
    }

    @Override
    public ArrayList<CCS_Fees_DTO> getAllFees() {
        CCS_Feesimpl_DAO ccsFeesimplDao = DAOFactory.getDAOObject(DAOTypes.CCS_FEES);
        List<CCS_Fees> fees = ccsFeesimplDao.getAll();
        ArrayList<CCS_Fees_DTO> ccsFeesDtos = Converter.toCCS_fees_dto_arraylist((ArrayList<CCS_Fees>) fees);
        return ccsFeesDtos;

    }
}
