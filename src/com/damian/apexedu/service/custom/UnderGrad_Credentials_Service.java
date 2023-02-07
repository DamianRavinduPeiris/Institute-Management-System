package com.damian.apexedu.service.custom;


import com.damian.apexedu.dao.custom.UnderGrad_Credentials;
import com.damian.apexedu.dto.UNDERGRAD_Student_Credentials_DTO;
import com.damian.apexedu.entity.UNDERGRAD_Student_Credentials;
import com.damian.apexedu.service.SuperService;

import java.util.Optional;

public interface UnderGrad_Credentials_Service extends SuperService {
    boolean add(UNDERGRAD_Student_Credentials_DTO underGrad_credentials);
    boolean update(UNDERGRAD_Student_Credentials_DTO underGrad_credentials);

    Optional<UNDERGRAD_Student_Credentials_DTO>searchByEmail(String email);
    UNDERGRAD_Student_Credentials_DTO search(String id);
}
