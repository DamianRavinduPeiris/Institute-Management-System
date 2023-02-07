package com.damian.apexedu.service.custom;


import com.damian.apexedu.dto.UNDERGRAD_DTO;
import com.damian.apexedu.service.SuperService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UNDERGRAD_Details_Service extends SuperService {
    boolean add(UNDERGRAD_DTO udto);
    boolean update(UNDERGRAD_DTO udto);
    boolean delete(String id);
    UNDERGRAD_DTO search(String id);
    ArrayList<UNDERGRAD_DTO> getAll();
}
