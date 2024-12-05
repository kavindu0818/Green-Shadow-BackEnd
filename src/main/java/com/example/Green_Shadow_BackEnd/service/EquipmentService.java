package com.example.Green_Shadow_BackEnd.service;

import com.example.Green_Shadow_BackEnd.dto.EquipmentStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.CropEntityDto;
import com.example.Green_Shadow_BackEnd.dto.impl.EquipmentDto;
import com.example.Green_Shadow_BackEnd.entity.impl.EquipmentEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.FieldEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.StaffEntity;

import java.util.List;

public interface EquipmentService {
    void saveEquiment(EquipmentDto equipmentDto);

    void updateCrop(String equId, EquipmentDto equipmentDto);



    EquipmentStatus getEquiment(String equID);

    List<EquipmentDto> getAllEquiment();

    void deleteEqu(String equId);
}
