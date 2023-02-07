package com.damian.apexedu.service.custom;

import com.damian.apexedu.dto.UNDERGRAD_Fees_DTO;
import com.damian.apexedu.service.SuperService;

import java.util.List;
import java.util.Optional;

public interface UnderGrad_Fees_Service extends SuperService {
    boolean add(UNDERGRAD_Fees_DTO dto) ;
    boolean update(UNDERGRAD_Fees_DTO dto) ;
   UNDERGRAD_Fees_DTO search(String id) ;
    List<UNDERGRAD_Fees_DTO> getAll() ;
}
