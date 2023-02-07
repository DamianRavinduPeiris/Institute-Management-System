package com.damian.apexedu.dao.custom;

import com.damian.apexedu.dao.util.SuperDAO;

public interface Lecturer_Credentials extends SuperDAO<com.damian.apexedu.entity.Lecturer_Credentials,String> {
    com.damian.apexedu.entity.Lecturer_Credentials searchByEmail(String email);
}
