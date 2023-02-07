package com.damian.apexedu.dao.custom;

import com.damian.apexedu.dao.util.SuperDAO;
import com.damian.apexedu.entity.UNDERGRAD_Student_Credentials;

import java.util.Optional;

public interface UnderGrad_Credentials extends SuperDAO<UNDERGRAD_Student_Credentials,String> {
    Optional<UNDERGRAD_Student_Credentials> searchByEnail(String email);
}
