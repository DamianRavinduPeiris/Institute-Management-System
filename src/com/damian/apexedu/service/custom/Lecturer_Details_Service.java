package com.damian.apexedu.service.custom;


import com.damian.apexedu.dto.Lecturer_Details_DTO;
import com.damian.apexedu.service.SuperService;

public interface Lecturer_Details_Service extends SuperService {
    boolean add(Lecturer_Details_DTO lecturer_details_dto);
    boolean update(Lecturer_Details_DTO lecturer_details_dto);
    boolean delete(String id);
    Lecturer_Details_DTO search(String id);

}
