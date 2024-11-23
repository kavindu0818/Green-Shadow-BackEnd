package com.example.Green_Shadow_BackEnd.service.impl;


import com.example.Green_Shadow_BackEnd.customStatusCodes.SelectedAllError;
import com.example.Green_Shadow_BackEnd.dao.EquipmentDao;
import com.example.Green_Shadow_BackEnd.dto.EquipmentStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.CropEntityDto;
import com.example.Green_Shadow_BackEnd.dto.impl.EquipmentDto;
import com.example.Green_Shadow_BackEnd.entity.impl.EquipmentEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.FieldEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.StaffEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.VehicleEntity;
import com.example.Green_Shadow_BackEnd.exception.CropNotFoundException;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.service.EquipmentService;
import com.example.Green_Shadow_BackEnd.util.Mapping;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EqipmentServiceIMPL implements EquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveEquiment(EquipmentDto equipmentDto) {
        EquipmentEntity savedEquiment =
                equipmentDao.save(mapping.toEquimentEntity(equipmentDto));
        if (savedEquiment == null) {
            throw new DataPersistException("Field not saved");
        }
    }

    @Override
    public void updateCrop(String equId, EquipmentDto equipmentDto) {
        Optional<EquipmentEntity> findEquiment = equipmentDao.findById(equId);
        if (!findEquiment.isPresent()) {
            throw new FieldNotFoundException("Equiment Not Found");
        }else {
            findEquiment.get().setEquipmentId(equipmentDto.getEquipmentId());
            findEquiment.get().setName(equipmentDto.getName());
            findEquiment.get().setEquipmentType(equipmentDto.getEquipmentType());
            findEquiment.get().setStatus(equipmentDto.getStatus());
//            findEquiment.get().setStaff(equipmentDto.getStaffId());
//            findEquiment.get().setField(equipmentDto.getFieldId());
//            findVehicle.get().setSt(vehicleDto.getStaffId());
        }

        equipmentDao.save(mapping.toEquimentEntity(equipmentDto));
    }

    @Override
    public void deleteCrop(String equId) {
        Optional<EquipmentEntity> findEquiment = equipmentDao.findById(equId);
        if (!findEquiment.isPresent()) {
            throw new CropNotFoundException("Field not found");
        }else {
            equipmentDao.deleteById(equId);
        }
    }

    @Override
    public EquipmentStatus getEquiment(String equID) {
        if(equipmentDao.existsById(equID)){
            var selectedEquiment = equipmentDao.getReferenceById(equID);
            return mapping.toEquimentDTO(selectedEquiment);
        }else {
            return new SelectedAllError("2","Select not Found");
        }
    }

    @Override
    public List<EquipmentDto> getAllEquiment() {
        return mapping.asEquimentDTOList(equipmentDao.findAll());
    }


}
