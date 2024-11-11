package com.example.Green_Shadow_BackEnd.service.impl;

import com.example.Green_Shadow_BackEnd.dao.FieldDao;
import com.example.Green_Shadow_BackEnd.dto.impl.FieldDto;
import com.example.Green_Shadow_BackEnd.entity.impl.FieldEntity;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.service.FieldService;
import com.example.Green_Shadow_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FieldServiceIMPL implements FieldService {

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldDto fieldDto) {
        FieldEntity savedUser =
                fieldDao.save(mapping.toFieldEntity(fieldDto));
        if (savedUser == null) {
            throw new DataPersistException("Field not saved");
        }
    }



}
