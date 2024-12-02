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
    private List<String> cropCodes; // List of crop codes associated with the field
    private List<String> staffCodes; // List of staff codes (if you want to transfer staff info)
    private List<String> equipmentCodes; // List of equipment codes (if you want to transfer equipment info)
    private List<String> monitorLogId; // Optional: Monitor Log ID if needed

}
