package com.damian.apexedu.service.custom;


import com.damian.apexedu.dao.custom.UnderGrad_Attendance;
import com.damian.apexedu.dto.UNDERGRAD_Attendance_DTO;
import com.damian.apexedu.service.SuperService;

import java.util.ArrayList;
import java.util.List;

public interface UnderGrad_Attendance_Service extends SuperService {
    boolean add(UNDERGRAD_Attendance_DTO udto);
    boolean update(UNDERGRAD_Attendance_DTO udto);
    boolean delete(String id);
    UNDERGRAD_Attendance_DTO search(String id);
    ArrayList<UNDERGRAD_Attendance_DTO> getAll();
}
