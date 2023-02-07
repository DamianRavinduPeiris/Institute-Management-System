package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.UNDERGRAD_AnnouncementsImpl_DAO;
import com.damian.apexedu.dto.UNDERGRAD_Announcements_DTO;
import com.damian.apexedu.entity.UNDERGRAD_Announcements;
import com.damian.apexedu.service.custom.UnderGrad_AnnouncementsService;
import com.damian.apexedu.service.util.Converter;

import java.util.ArrayList;

public class UnderGrad_AnnouncementsService_Impl implements UnderGrad_AnnouncementsService {
    @Override
    public boolean add(UNDERGRAD_Announcements_DTO underGradAnnouncementsDto) {
        UNDERGRAD_Announcements undergradAnnouncements = Converter.toUnderGradAnnouncements(underGradAnnouncementsDto);
        UNDERGRAD_AnnouncementsImpl_DAO undergradAnnouncementsImplDao = DAOFactory.getDAOObject(DAOTypes.UNDERGRAD_ANNOUNCEMENTS);
        return undergradAnnouncementsImplDao.add(undergradAnnouncements);
    }

    @Override
    public boolean delete(String description) {
        UNDERGRAD_AnnouncementsImpl_DAO undergradAnnouncementsImplDao = DAOFactory.getDAOObject(DAOTypes.UNDERGRAD_ANNOUNCEMENTS);
        return undergradAnnouncementsImplDao.delete(description);
    }

    @Override
    public ArrayList<UNDERGRAD_Announcements_DTO> getAll() {
        UNDERGRAD_AnnouncementsImpl_DAO undergradAnnouncementsImplDao = DAOFactory.getDAOObject(DAOTypes.UNDERGRAD_ANNOUNCEMENTS);
        return Converter.toUndergrad_announcements_dto_arraylist((ArrayList<UNDERGRAD_Announcements>) undergradAnnouncementsImplDao.getAll());
    }
}
