package com.damian.apexedu.service.custom;

import com.damian.apexedu.dto.CCS_Fees_DTO;
import com.damian.apexedu.entity.CCS_Fees;
import com.damian.apexedu.service.SuperService;

import java.util.ArrayList;

public interface CCS_Fees_Service extends SuperService {
    boolean add(CCS_Fees_DTO ccs_fees_dto);
    boolean update(CCS_Fees_DTO ccs_fees_dto,String id);
    CCS_Fees_DTO search(String id);

    ArrayList<CCS_Fees_DTO>getAllFees();
}
