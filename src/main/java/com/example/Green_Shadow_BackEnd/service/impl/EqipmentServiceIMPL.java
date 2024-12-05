package com.example.Green_Shadow_BackEnd.service.impl;


import com.example.Green_Shadow_BackEnd.customStatusCodes.SelectedAllError;
import com.example.Green_Shadow_BackEnd.dao.EquipmentDao;
import com.example.Green_Shadow_BackEnd.dao.FieldDao;
import com.example.Green_Shadow_BackEnd.dao.StaffDao;
import com.example.Green_Shadow_BackEnd.dto.EquipmentStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.CropEntityDto;
import com.example.Green_Shadow_BackEnd.dto.impl.EquipmentDto;
import com.example.Green_Shadow_BackEnd.dto.impl.FieldEntityDto;
import com.example.Green_Shadow_BackEnd.entity.impl.*;
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
import java.util.stream.Collectors;

@Service
@Transactional
public class EqipmentServiceIMPL implements EquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping mapping;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private FieldDao fieldDao;
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
//          findEquiment.get().setStaff(equipmentDto.getStaffId());
//            findEquiment.get().setField(equipmentDto.getFieldId());

        }

        equipmentDao.save(mapping.toEquimentEntity(equipmentDto));
    }


    @Override
    public EquipmentStatus getEquiment(String equID) {
        if (equipmentDao.existsById(equID)) {
            var selectedCrop = equipmentDao.getReferenceById(equID);

            // Map the selected crop to CropEntityDto
            EquipmentDto equipmentDto = mapping.toEquimentDTO(selectedCrop);

            // Map the associated FieldEntity and set it in CropEntityDto
            FieldEntityDto fieldDTO = mapping.toFieldDTO(selectedCrop.getField());
            equipmentDto.setFieldId(fieldDTO);

            return equipmentDto;
        } else {
            // Handle the case where the crop ID is not found
            return null;
        }
    }

    @Override
    public List<EquipmentDto> getAllEquiment() {
            List<EquipmentEntity> all = equipmentDao.findAll();

            List<EquipmentDto> collect = all.stream().map(equipmentEntity -> {
                EquipmentDto equipmentDto = mapping.toEquimentDTO(equipmentEntity);

                // Null check for field
                if (equipmentEntity.getField() != null) {
                    FieldEntityDto fieldDTO = mapping.toFieldDTO(equipmentEntity.getField());
                    equipmentDto.setFieldId(fieldDTO);
                } else {
                    equipmentDto.setFieldId(null); // Explicitly set to null
                }

                return equipmentDto;
            }).collect(Collectors.toList());

            return collect;
        }

    @Override
    public void deleteEqu(String equId) {
        equipmentDao.deleteById(equId);
    }
//        Optional<EquipmentEntity> findEquiment = equipmentDao.findById(equId);
//        if (!findEquiment.isPresent()) {
//            throw new CropNotFoundException("Field not found");
//        }else {
//            equipmentDao.deleteById(equId);
//        }
//    }
    }






