package com.example.Green_Shadow_BackEnd.dto.impl;
import com.example.Green_Shadow_BackEnd.dto.EquipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDto implements EquipmentStatus {

    private String EquipmentId;
    private String name;
    private String equipmentType;
    private String status;
    private String staffId;
    private FieldEntityDto fieldId;
    private StaffDto staffDto;

}
