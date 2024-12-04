package com.example.Green_Shadow_BackEnd.service;

import com.example.Green_Shadow_BackEnd.dto.FieldStatus;

import com.example.Green_Shadow_BackEnd.dto.impl.FieldEntityDto;
import com.example.Green_Shadow_BackEnd.entity.impl.FieldEntity;

import java.util.List;

public interface FieldService {
    void saveField(FieldEntityDto fieldDto);

    void updateField(String noteId, FieldEntityDto fieldDto);

    void deleteField(String fieldId);

    FieldEntityDto getField(String fieldId);

    List<FieldEntityDto> getAllField();

    FieldEntityDto findFieldByCode(String fieldCode);

    FieldEntity findFieldById(String fieldId);
}
