package com.damian.apexedu.service.impl;

import com.damian.apexedu.dao.DAOFactory;
import com.damian.apexedu.dao.DAOTypes;
import com.damian.apexedu.dao.impl.UnderGrad_AttendanceImpl_DAO;
import com.damian.apexedu.dto.UNDERGRAD_Attendance_DTO;
import com.damian.apexedu.entity.UNDERGRAD_Attendance;
import com.damian.apexedu.service.custom.UnderGrad_Attendance_Service;
import com.damian.apexedu.service.util.Converter;

import java.util.ArrayList;
import java.util.List;



public class UnderGrad_AttendanceServiceImpl implements UnderGrad_Attendance_Service {

    @Override
    public boolean add(UNDERGRAD_Attendance_DTO udto) {
        UNDERGRAD_Attendance undergradAttendance = Converter.toUnderGradAttendance(udto);
        UnderGrad_AttendanceImpl_DAO underGradAttendanceImplDao = DAOFactory.getDAOObject(DAOTypes.UNDERGRAD_ATTENDANCE);
        return underGradAttendanceImplDao.add(undergradAttendance);
    }

    @Override
    public boolean update(UNDERGRAD_Attendance_DTO udto) {
        UNDERGRAD_Attendance undergradAttendance = Converter.toUnderGradAttendance(udto);
        UnderGrad_AttendanceImpl_DAO underGradAttendanceImplDao = DAOFactory.getDAOObject(DAOTypes.UNDERGRAD_ATTENDANCE);
        return underGradAttendanceImplDao.update(undergradAttendance, undergradAttendance.getStudent_id());
    }

    @Override
    public boolean delete(String id) {
        UnderGrad_AttendanceImpl_DAO underGradAttendanceImplDao = DAOFactory.getDAOObject(DAOTypes.UNDERGRAD_ATTENDANCE);
        return underGradAttendanceImplDao.delete(id);
    }

    @Override
    public UNDERGRAD_Attendance_DTO search(String id) {
        return null;
    }

    @Override
    public ArrayList<UNDERGRAD_Attendance_DTO> getAll() {
        UnderGrad_AttendanceImpl_DAO underGradAttendanceImplDao = DAOFactory.getDAOObject(DAOTypes.UNDERGRAD_ATTENDANCE);
        List<UNDERGRAD_Attendance> undergrad = underGradAttendanceImplDao.getAll();
        ArrayList<UNDERGRAD_Attendance_DTO> undergradAttendanceDtos = Converter.undergradAttendanceDtos((ArrayList<UNDERGRAD_Attendance>) undergrad);
        return undergradAttendanceDtos;
    }
}
