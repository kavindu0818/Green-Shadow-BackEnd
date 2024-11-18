package com.example.Green_Shadow_BackEnd.util;


import com.example.Green_Shadow_BackEnd.dto.FieldStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.*;
import com.example.Green_Shadow_BackEnd.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public FieldEntity toFieldEntity(FieldEntityDto fieldDto) {
        return modelMapper.map(fieldDto, FieldEntity.class);
    }
    public FieldEntityDto toFieldDTO(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldEntityDto.class);
    }
    public List<FieldEntityDto> asNoteDTOList(List<FieldEntity> fieldEntities) {
        return modelMapper.map(fieldEntities, new TypeToken<List<FieldEntityDto>>() {}.getType());
    }
    public CropEntity toCropEntity(CropEntityDto cropDto) {
        return modelMapper.map(cropDto, CropEntity.class);
    }

    public CropEntityDto toCropDTO(CropEntity cropEntity) {
        return modelMapper.map(cropEntity, CropEntityDto.class);
    }
    public List<CropEntityDto> asCropDTOList(List<CropEntity> cropEntities) {
        return modelMapper.map(cropEntities, new TypeToken<List<CropEntityDto>>() {}.getType());
    }

    public VehicleEntity toVehicleEntity(VehicleDto vehicleDto) {
        return modelMapper.map(vehicleDto, VehicleEntity.class);
    }

    public VehicleDto toVehicleDTO(VehicleEntity vehicleEntity) {
        return modelMapper.map(vehicleEntity, VehicleDto.class);
    }
    public List<VehicleDto> asVehicleDTOList(List<VehicleEntity> vehicleEntities) {
        return modelMapper.map(vehicleEntities, new TypeToken<List<VehicleDto>>() {}.getType());
    }

    public StaffEntity toStaffEntity(StaffDto staffDto) {
        return modelMapper.map(staffDto, StaffEntity.class);
    }

    public StaffDto toStaffDTO(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDto.class);
    }
    public List<StaffDto> asStaffDTOList(List<StaffEntity> staffEntities) {
        return modelMapper.map(staffEntities, new TypeToken<List<StaffDto>>() {}.getType());
    }

    public EquipmentEntity toEquimentEntity(EquipmentDto equipmentDto) {
        return modelMapper.map(equipmentDto, EquipmentEntity.class);
    }

    public EquipmentDto toEquimentDTO(EquipmentEntity equipmentEntity) {
        return modelMapper.map(equipmentEntity, EquipmentDto.class);
    }
    public List<EquipmentDto> asEquimentDTOList(List<EquipmentEntity> equipmentEntities) {
        return modelMapper.map(equipmentEntities, new TypeToken<List<EquipmentDto>>() {}.getType());
    }
}
