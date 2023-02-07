package com.damian.apexedu.service.custom;


import com.damian.apexedu.dto.CCS_Announcements_DTO;
import com.damian.apexedu.entity.CCS_Announcements;
import com.damian.apexedu.service.SuperService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface CCS_Announcements_Service extends SuperService {
    boolean add(CCS_Announcements_DTO ccsAnnouncementsDto);
    boolean delete(String description);
   ArrayList<CCS_Announcements_DTO> getAll();
}

