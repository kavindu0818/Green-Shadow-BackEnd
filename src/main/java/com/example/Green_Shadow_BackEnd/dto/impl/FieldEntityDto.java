package com.example.Green_Shadow_BackEnd.dto.impl;

import com.example.Green_Shadow_BackEnd.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldEntityDto implements SuperDTO {

    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private double fieldSize;
    private String fieldImage;

}
