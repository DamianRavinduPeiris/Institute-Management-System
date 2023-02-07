package com.damian.apexedu.dao.custom;

import com.damian.apexedu.dao.util.SuperDAO;
import com.damian.apexedu.entity.HND_Student_Credentials;

import java.util.Optional;

public interface HND_Credentials extends SuperDAO<HND_Student_Credentials,String> {
    Optional<HND_Student_Credentials>   searchByEmail(String email);
}
