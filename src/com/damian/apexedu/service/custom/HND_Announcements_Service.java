package com.damian.apexedu.service.custom;

import com.damian.apexedu.dto.HND_Announcements_DTO;
import com.damian.apexedu.service.SuperService;

import java.util.ArrayList;
import java.util.List;

public interface HND_Announcements_Service extends SuperService {
    boolean add(HND_Announcements_DTO hndAnnouncementsDto);
    boolean delete(String description);
    ArrayList<HND_Announcements_DTO> getAll();

}
