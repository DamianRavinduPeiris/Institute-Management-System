package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.CCS_AnnouncementsImpl_DAO;
import com.damian.apexedu.dto.CCS_Announcements_DTO;
import com.damian.apexedu.entity.CCS_Announcements;
import com.damian.apexedu.service.custom.CCS_Announcements_Service;
import com.damian.apexedu.service.util.Converter;

import java.util.ArrayList;
import java.util.List;

public class CCS_AnnouncementsService_Impl implements CCS_Announcements_Service {


    @Override
    public boolean add(CCS_Announcements_DTO ccsAnnouncementsDto) {
        CCS_Announcements ccsAnnouncements = Converter.toCCSAnnouncements(ccsAnnouncementsDto);
        CCS_AnnouncementsImpl_DAO ccsAnnouncementsImplDao = DAOFactory.getDAOObject(DAOTypes.CCS_ANNOUNCEMENTS);
        return ccsAnnouncementsImplDao.add(ccsAnnouncements);
    }

    @Override
    public boolean delete(String description) {
       CCS_AnnouncementsImpl_DAO ccsAnnouncementsImplDao  =  DAOFactory.getDAOObject(DAOTypes.CCS_ANNOUNCEMENTS);
        return   ccsAnnouncementsImplDao.delete(description);
    }

    @Override
    public ArrayList<CCS_Announcements_DTO> getAll() {
        CCS_AnnouncementsImpl_DAO ccsAnnouncementsImplDao = DAOFactory.getDAOObject(DAOTypes.CCS_ANNOUNCEMENTS);
        List<CCS_Announcements> ccsAnnouncementsImplDaoAll = ccsAnnouncementsImplDao.getAll();
        return  Converter.toCCS_announcements_dto_arraylist((ArrayList<CCS_Announcements>) ccsAnnouncementsImplDaoAll);
    }
}
