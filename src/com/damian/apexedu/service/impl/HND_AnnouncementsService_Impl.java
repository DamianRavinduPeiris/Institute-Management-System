package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.HND_AnnouncementsImpl_DAO;
import com.damian.apexedu.dto.HND_Announcements_DTO;
import com.damian.apexedu.entity.HND_Announcements;
import com.damian.apexedu.service.custom.HND_Announcements_Service;
import com.damian.apexedu.service.util.Converter;

import java.util.ArrayList;
import java.util.List;

public class HND_AnnouncementsService_Impl implements HND_Announcements_Service {
    @Override
    public boolean add(HND_Announcements_DTO hndAnnouncementsDto) {
        HND_Announcements hndAnnouncements = Converter.toHNDAnnouncements(hndAnnouncementsDto);
        HND_AnnouncementsImpl_DAO hndAnnouncementsImplDao = DAOFactory.getDAOObject(DAOTypes.HND_ANNOUNCEMENTS);
        return hndAnnouncementsImplDao.add(hndAnnouncements);
    }

    @Override
    public boolean delete(String description) {
        HND_AnnouncementsImpl_DAO hndAnnouncementsImplDao = DAOFactory.getDAOObject(DAOTypes.HND_ANNOUNCEMENTS);
        return hndAnnouncementsImplDao.delete(description);
    }

    @Override
    public ArrayList<HND_Announcements_DTO> getAll() {
       HND_AnnouncementsImpl_DAO hndAnnouncementsImplDao =  DAOFactory.getDAOObject(DAOTypes.HND_ANNOUNCEMENTS);
        List<HND_Announcements> all = hndAnnouncementsImplDao.getAll();
        return  Converter.toHND_announcements_dto_arraylist((ArrayList<HND_Announcements>) all);
    }
}
