package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.HND_FeesImpl_DAO;
import com.damian.apexedu.dto.HND_Fees_DTO;
import com.damian.apexedu.entity.HND_Fees;
import com.damian.apexedu.service.custom.HND_Fees_Service;
import com.damian.apexedu.service.util.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HND_FeesServiceImpl implements HND_Fees_Service {
    @Override
    public boolean add(HND_Fees_DTO hnd_fees_dto) {
        HND_Fees hndFees = Converter.toHNDFees(hnd_fees_dto);
        HND_FeesImpl_DAO hndFeesImplDao = DAOFactory.getDAOObject(DAOTypes.HND_FEES_DAO);
        boolean add= hndFeesImplDao.add(hndFees);
        return add;
    }

    @Override
    public boolean update(HND_Fees_DTO hnd_fees_dto) {
        HND_Fees hndFees = Converter.toHNDFees(hnd_fees_dto);
        HND_FeesImpl_DAO hndFeesImplDao = DAOFactory.getDAOObject(DAOTypes.HND_FEES_DAO);
        boolean update = hndFeesImplDao.update(hndFees, hndFees.getStudent_id());
        return update;
    }

    @Override
    public HND_Fees_DTO search(String id) {
         HND_FeesImpl_DAO hndFeesImplDao = DAOFactory.getDAOObject(DAOTypes.HND_FEES_DAO);
        Optional<HND_Fees> fees = hndFeesImplDao.search(id);
        if(fees.isPresent()){
            return Converter.toHND_fees_dto(fees.get());
        }
        return null;
    }

    @Override
    public ArrayList<HND_Fees_DTO> getAll() {
        HND_FeesImpl_DAO hndFeesImplDao = DAOFactory.getDAOObject(DAOTypes.HND_FEES_DAO);
        List<HND_Fees> hndFees = hndFeesImplDao.getAll();
        ArrayList<HND_Fees_DTO> hndFeesDtos = Converter.toHND_fees_dto_arraylist((ArrayList<HND_Fees>) hndFees);
        return hndFeesDtos;
    }
}
