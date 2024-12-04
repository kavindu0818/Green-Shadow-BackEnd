package com.example.Green_Shadow_BackEnd.service;
import com.example.Green_Shadow_BackEnd.dto.StaffStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.StaffDto;
import com.example.Green_Shadow_BackEnd.entity.impl.StaffEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDto staffDto);

    void updateStaff(String staffId, StaffDto staffDto);

    void deleteStaff(String staffId);

    StaffDto getStaff(String staffId);

    List<StaffDto> getAllStaff();

    StaffEntity findStaffById(String staffId);
}

