package com.example.Green_Shadow_BackEnd.service;

import com.example.Green_Shadow_BackEnd.dto.CropStatus;
import com.example.Green_Shadow_BackEnd.dto.FieldStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.CropDto;
import com.example.Green_Shadow_BackEnd.dto.impl.FieldDto;

import java.util.List;

public interface CropService{
    void saveCrop(CropDto cropDto);

    void updateCrop(String cropId, CropDto cropDto);

    void deleteCrop(String fieldId);


    CropStatus getCrop(String cropID);

    List<CropDto> getAllCrop();
}
