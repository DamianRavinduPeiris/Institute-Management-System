package com.damian.apexedu.service.custom;

import com.damian.apexedu.dto.CCS_DTO;
import com.damian.apexedu.dto.CCS_Fees_DTO;
import com.damian.apexedu.dto.CCS_Student_Credentials_DTO;
import com.damian.apexedu.entity.CCS;
import com.damian.apexedu.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CCS_Details_Service extends SuperService{
    boolean add(CCS_DTO ccs_dto );
    boolean update(CCS_DTO ccs_dto,String id);
    boolean delete(String id);
    CCS_DTO search(String id);

    ArrayList<CCS_DTO> getAll();
}

