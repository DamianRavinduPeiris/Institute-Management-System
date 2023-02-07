package com.damian.apexedu.service.custom;

import com.damian.apexedu.dto.Lecturer_Credentials_DTO;
import com.damian.apexedu.service.SuperService;
import rex.utils.S;

public interface Lecturer_Credentials_Service extends SuperService {
    boolean add(Lecturer_Credentials_DTO lcd);
    boolean update(Lecturer_Credentials_DTO lcd);

    boolean delete(String id);
    Lecturer_Credentials_DTO search(String id);

    Lecturer_Credentials_DTO searchByEmail(String email);
}
