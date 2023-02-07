package com.damian.apexedu.service.custom;

import com.damian.apexedu.dto.HND_DTO;
import com.damian.apexedu.service.SuperService;

import java.util.ArrayList;
import java.util.List;

public interface HND_Details_Service extends SuperService {
    boolean add(HND_DTO hnd_dto) ;
    boolean update(HND_DTO hnd_dto) ;
    boolean delete(String id) ;
    HND_DTO search(String id) ;
    ArrayList<HND_DTO> getAll() ;

}

