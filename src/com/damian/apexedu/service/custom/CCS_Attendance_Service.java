package com.damian.apexedu.service.custom;


import com.damian.apexedu.dto.CCS_Announcements_DTO;
import com.damian.apexedu.dto.CCS_Attendance_DTO;
import com.damian.apexedu.entity.CCS_Attendance;
import com.damian.apexedu.service.SuperService;

import java.util.ArrayList;
import java.util.List;

public interface CCS_Attendance_Service extends SuperService {
 boolean add(CCS_Attendance_DTO ccsAttendanceDto);
 boolean update(CCS_Attendance_DTO ccsAttendanceDto);

 boolean delete(String id);
 CCS_Attendance_DTO search(String id);
 ArrayList<CCS_Attendance_DTO> getAll();
}

