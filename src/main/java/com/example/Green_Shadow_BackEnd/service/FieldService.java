package com.example.Green_Shadow_BackEnd.service;

import com.example.Green_Shadow_BackEnd.dto.FieldStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.FieldDto;

import java.util.List;

public interface FieldService {
    void saveField(FieldDto fieldDto);

    void updateField(String noteId, FieldDto fieldDto);

    void deleteNote(String fieldId);

    FieldStatus getField(String fieldId);

    List<FieldDto> getAllField();
}
