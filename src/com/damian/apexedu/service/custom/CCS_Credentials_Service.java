package com.damian.apexedu.service.custom;

import com.damian.apexedu.dto.CCS_Student_Credentials_DTO;
import com.damian.apexedu.service.SuperService;

import java.util.Optional;

public interface CCS_Credentials_Service extends SuperService {
 boolean add(CCS_Student_Credentials_DTO ccs_student_credentials_dto);
 boolean update(CCS_Student_Credentials_DTO ccs_student_credentials_dto,String id);

 CCS_Student_Credentials_DTO search(String id);
 Optional<CCS_Student_Credentials_DTO> searchByEmail(String id);
}
