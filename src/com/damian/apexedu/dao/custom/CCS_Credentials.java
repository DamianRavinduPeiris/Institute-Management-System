package com.damian.apexedu.dao.custom;

import com.damian.apexedu.dao.util.SuperDAO;
import com.damian.apexedu.dto.CCS_DTO;
import com.damian.apexedu.dto.CCS_Student_Credentials_DTO;
import com.damian.apexedu.entity.CCS;
import com.damian.apexedu.entity.CCS_Student_Credentials;

import java.util.Optional;

public interface CCS_Credentials extends  SuperDAO<CCS_Student_Credentials, String> {
   Optional<CCS_Student_Credentials> searchByEmail(String email);




}
