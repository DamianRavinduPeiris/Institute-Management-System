package com.damian.apexedu.service.custom;

import com.damian.apexedu.dto.UNDERGRAD_Announcements_DTO;
import com.damian.apexedu.service.SuperService;

import java.util.ArrayList;

public interface UnderGrad_AnnouncementsService extends SuperService {
    boolean add(UNDERGRAD_Announcements_DTO underGradAnnouncementsDto);
    boolean delete(String description);
    ArrayList<UNDERGRAD_Announcements_DTO>getAll();
}
