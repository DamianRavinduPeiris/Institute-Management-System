package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.HND_AttendanceImpl_DAO;
import com.damian.apexedu.dto.HND_Attendance_DTO;
import com.damian.apexedu.dto.HND_Fees_DTO;
import com.damian.apexedu.entity.HND_Attendance;
import com.damian.apexedu.service.custom.HND_Attendance_Service;
import com.damian.apexedu.service.custom.HND_Fees_Service;
import com.damian.apexedu.service.util.Converter;

import java.util.ArrayList;
import java.util.List;

public class HND_AttendanceServiceImpl implements HND_Attendance_Service {

    @Override
    public boolean add(HND_Attendance_DTO dto) {
        HND_Attendance hndAttendance = Converter.toHNDAttendance(dto);
        HND_AttendanceImpl_DAO hndAttendanceImplDao = DAOFactory.getDAOObject(DAOTypes.HND_ATTENDANCE);
        return  hndAttendanceImplDao.add(hndAttendance);
    }

    @Override
    public boolean update(HND_Attendance_DTO dto) {
        HND_Attendance hndAttendance = Converter.toHNDAttendance(dto);
        HND_AttendanceImpl_DAO hndAttendanceImplDao = DAOFactory.getDAOObject(DAOTypes.HND_ATTENDANCE);
        return hndAttendanceImplDao.update(hndAttendance, hndAttendance.getStudent_id());
    }

    @Override
    public boolean delete(String id) {
        HND_AttendanceImpl_DAO hndAttendanceImplDao = DAOFactory.getDAOObject(DAOTypes.HND_ATTENDANCE);
        return hndAttendanceImplDao.delete(id);
    }

    @Override
    public HND_Attendance_DTO search(String id) {
        return null;
    }

    @Override
    public ArrayList<HND_Attendance_DTO> getAll() {
        HND_AttendanceImpl_DAO hndAttendanceImplDao = DAOFactory.getDAOObject(DAOTypes.HND_ATTENDANCE);
        List<HND_Attendance> hnd = hndAttendanceImplDao.getAll();
        ArrayList<HND_Attendance_DTO> hndAttendanceDtos = Converter.toHND_attendance_dto_arraylist((ArrayList<HND_Attendance>) hnd);
        return hndAttendanceDtos;
    }
}
