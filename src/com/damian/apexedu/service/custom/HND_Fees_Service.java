package com.damian.apexedu.service.custom;

import com.damian.apexedu.dto.HND_DTO;
import com.damian.apexedu.dto.HND_Fees_DTO;
import com.damian.apexedu.service.SuperService;

import java.util.ArrayList;
import java.util.List;

public interface HND_Fees_Service extends SuperService {
    boolean add(HND_Fees_DTO hnd_fees_dto);
    boolean update(HND_Fees_DTO hnd_fees_dto);
    HND_Fees_DTO search(String id);
    ArrayList<HND_Fees_DTO> getAll();

}
