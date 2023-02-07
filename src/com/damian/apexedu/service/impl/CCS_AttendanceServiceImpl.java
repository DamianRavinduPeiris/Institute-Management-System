package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.CCS_AttendanceImpl_DAO;
import com.damian.apexedu.dto.CCS_Attendance_DTO;
import com.damian.apexedu.entity.CCS_Attendance;
import com.damian.apexedu.service.custom.CCS_Attendance_Service;
import com.damian.apexedu.service.util.Converter;

import java.util.ArrayList;
import java.util.List;

public class CCS_AttendanceServiceImpl implements CCS_Attendance_Service {

    @Override
    public boolean add(CCS_Attendance_DTO ccsAttendanceDto) {
        CCS_Attendance ccsAttendance = Converter.toCCSAttendance(ccsAttendanceDto);
       CCS_AttendanceImpl_DAO ccsAttendanceImplDao = DAOFactory.getDAOObject(DAOTypes.CCS_ATTENDANCE);
       return  ccsAttendanceImplDao.add(ccsAttendance);
    }

    @Override
    public boolean update(CCS_Attendance_DTO ccsAttendanceDto) {
        CCS_Attendance ccsAttendance = Converter.toCCSAttendance(ccsAttendanceDto);
          CCS_AttendanceImpl_DAO ccsAttendanceImplDao = DAOFactory.getDAOObject(DAOTypes.CCS_ATTENDANCE);
      return  ccsAttendanceImplDao.update(ccsAttendance, ccsAttendanceDto.getStudent_id());
    }

    @Override
    public boolean delete(String id) {
       CCS_AttendanceImpl_DAO ccsAttendanceImplDao = DAOFactory.getDAOObject(DAOTypes.CCS_ATTENDANCE);
     return   ccsAttendanceImplDao.delete(id);
    }

    @Override
    public CCS_Attendance_DTO search(String id) {
        return null;
    }

    @Override
    public ArrayList<CCS_Attendance_DTO> getAll() {
        CCS_AttendanceImpl_DAO ccsAttendanceImplDao = DAOFactory.getDAOObject(DAOTypes.CCS_ATTENDANCE);
        List<com.damian.apexedu.entity.CCS_Attendance> ccs = ccsAttendanceImplDao.getAll();
        ArrayList<CCS_Attendance_DTO> ccsAttendanceDtos = Converter.toCCS_attendance_dto_arraylist((ArrayList<CCS_Attendance>) ccs);
        return ccsAttendanceDtos;

    }
}
