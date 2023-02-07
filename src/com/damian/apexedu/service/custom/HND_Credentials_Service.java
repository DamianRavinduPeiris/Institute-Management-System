package com.damian.apexedu.service.custom;

import com.damian.apexedu.dto.HND_Student_Credentials_DTO;
import com.damian.apexedu.service.SuperService;
import java.util.Optional;

public interface HND_Credentials_Service extends SuperService {
    boolean add(HND_Student_Credentials_DTO hndStudentCredentialsDto);
    boolean update(HND_Student_Credentials_DTO hndStudentCredentialsDto);

    Optional<HND_Student_Credentials_DTO >searchByEmail(String email);
    HND_Student_Credentials_DTO search(String id);
}
