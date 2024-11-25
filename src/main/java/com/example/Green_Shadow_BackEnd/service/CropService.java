package com.example.Green_Shadow_BackEnd.service;

import com.example.Green_Shadow_BackEnd.dto.impl.CropEntityDto;


import java.util.List;

public interface CropService{
    void saveCrop(CropEntityDto cropDto);

    void updateCrop(String cropId, CropEntityDto cropDto);

    void deleteCrop(String fieldId);


    CropEntityDto getCrop(String cropID);

    List<CropEntityDto> getAllCrop();
}
