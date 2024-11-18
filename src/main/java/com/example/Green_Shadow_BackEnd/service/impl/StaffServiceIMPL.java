package com.example.Green_Shadow_BackEnd.service.impl;
import com.example.Green_Shadow_BackEnd.customStatusCodes.SelectedAllError;
import com.example.Green_Shadow_BackEnd.dao.StaffDao;
import com.example.Green_Shadow_BackEnd.dto.FieldStatus;
import com.example.Green_Shadow_BackEnd.dto.StaffStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.StaffDto;
import com.example.Green_Shadow_BackEnd.entity.impl.FieldEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.StaffEntity;
import com.example.Green_Shadow_BackEnd.exception.CropNotFoundException;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.service.StaffService;
import com.example.Green_Shadow_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceIMPL implements StaffService {

    @Autowired
    private StaffDao staffDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveStaff(StaffDto staffDto) {
        StaffEntity savedStaff =
                staffDao.save(mapping.toStaffEntity(staffDto));
        if (savedStaff == null) {
            throw new DataPersistException("Staff not saved");
        }
    }

    @Override
    public void updateStaff(String staffId, StaffDto staffDto) {
        // Retrieve the staff entity by ID
        Optional<StaffEntity> optionalStaff = staffDao.findById(staffId);
        if (!optionalStaff.isPresent()) {
            // Throw exception if staff entity is not found
            throw new FieldNotFoundException("Staff not found with ID: " + staffId);
        }

        // Get the existing staff entity
        StaffEntity staffEntity = optionalStaff.get();

        // Update basic fields from DTO
        staffEntity.setFirstName(staffDto.getFirstName());
        staffEntity.setLastName(staffDto.getLastName());
        staffEntity.setDesignation(staffDto.getDesignation());
        staffEntity.setGender(staffDto.getGender());
        staffEntity.setJoinedDate(staffDto.getJoinedDate());
        staffEntity.setDob(staffDto.getDob());
        staffEntity.setAddress(staffDto.getAddress());
        staffEntity.setContact(staffDto.getContact());
        staffEntity.setEmail(staffDto.getEmail());
        staffEntity.setRole(staffDto.getRole());

        // Save the updated entity
        staffDao.save(staffEntity);
    }

    @Override
    public void deleteStaff(String staffId) {
        Optional<StaffEntity> foundStaff = staffDao.findById(staffId);
        if (!foundStaff.isPresent()) {
            throw new CropNotFoundException("Field not found");
        }else {
            staffDao.deleteById(staffId);
        }

    }

    @Override
    public StaffDto getStaff(String staffId) {
        if(staffDao.existsById(staffId)){
            var selectedStaff = staffDao.getReferenceById(staffId);
            return  mapping.toStaffDTO(selectedStaff);
        }else {
//            return new SelectedAllError("2","Select not Found");
            return null;
        }
    }

    @Override
    public List<StaffDto> getAllStaff() {
        return mapping.asStaffDTOList( staffDao.findAll());
    }

}
