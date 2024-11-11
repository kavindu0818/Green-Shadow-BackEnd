package com.example.Green_Shadow_BackEnd.util;


import com.example.Green_Shadow_BackEnd.dto.impl.FieldDto;
import com.example.Green_Shadow_BackEnd.entity.impl.FieldEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public FieldEntity toFieldEntity(FieldDto fieldDto) {
        return modelMapper.map(fieldDto, FieldEntity.class);
    }
    public FieldDto toFieldDTO(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDto.class);
    }
}
