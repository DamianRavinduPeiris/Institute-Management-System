package com.damian.apexedu.service.custom;


import com.damian.apexedu.dto.HND_Attendance_DTO;
import com.damian.apexedu.service.SuperService;

import java.util.ArrayList;
import java.util.List;

public interface HND_Attendance_Service extends SuperService {
    boolean add(HND_Attendance_DTO dto);
    boolean update(HND_Attendance_DTO dto);
    boolean delete(String id);
    HND_Attendance_DTO search(String id);
    ArrayList<HND_Attendance_DTO> getAll();
}

