package com.example.Green_Shadow_BackEnd.service.impl;

import com.example.Green_Shadow_BackEnd.dao.CropDao;
import com.example.Green_Shadow_BackEnd.dto.CropStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.CropEntityDto;
import com.example.Green_Shadow_BackEnd.dto.impl.FieldEntityDto;
import com.example.Green_Shadow_BackEnd.entity.impl.CropEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.FieldEntity;
import com.example.Green_Shadow_BackEnd.exception.CropNotFoundException;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.service.CropService;
import com.example.Green_Shadow_BackEnd.service.FieldService;
import com.example.Green_Shadow_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CropServiceIMPL implements CropService {

    @Autowired
    private CropDao cropDao;

    @Autowired
    private Mapping mapping;

    @Autowired
    private FieldService fieldService;


    @Override
    public void saveCrop(CropEntityDto cropDto) {
        CropEntity savedUser =
                cropDao.save(mapping.toCropEntity(cropDto));
        if (savedUser == null) {
            throw new DataPersistException("Field not saved");
        }
    }
    // Inject FieldRepository

    @Override
    public void updateCrop(String cropId, CropEntityDto cropDto) {
        Optional<CropEntity> findCrop = cropDao.findById(cropId);
        if (!findCrop.isPresent()) {
            throw new FieldNotFoundException("Crop Not Found");
        }

        CropEntity cropEntity = findCrop.get();

        // Map DTO fields to Entity fields
        cropEntity.setCropCode(cropDto.getCropCode());
        cropEntity.setCommonName(cropDto.getCommonName());
        cropEntity.setImage(cropDto.getImage());
        cropEntity.setScientificName(cropDto.getScientificName());
        cropEntity.setCategory(cropDto.getCategory());
        cropEntity.setSeason(cropDto.getSeason());

        // Convert FieldEntityDto to FieldEntity
        FieldEntity fieldEntity = fieldService.findFieldById(cropDto.getField_code().getFieldCode());
        if (fieldEntity == null) {
            throw new FieldNotFoundException("Field Not Found for Code: " + cropDto.getField_code().getFieldCode());
        }

        // Set the FieldEntity in CropEntity
        cropEntity.setFieldEntity(fieldEntity);

        // Save the updated crop entity
        cropDao.save(cropEntity);
    }




    @Override
    public void deleteCrop(String cropId) {
        Optional<CropEntity> foundNote = cropDao.findById(cropId);
        if (!foundNote.isPresent()) {
            throw new CropNotFoundException("Crop not found");
        }else {
            cropDao.deleteById(cropId);
        }
    }

    public CropEntityDto getCrop(String cropId) {
        if (cropDao.existsById(cropId)) {
            var selectedCrop = cropDao.getReferenceById(cropId);

            // Map the selected crop to CropEntityDto
            CropEntityDto cropDTO = mapping.toCropDTO(selectedCrop);

            // Map the associated FieldEntity and set it in CropEntityDto
            FieldEntityDto fieldDTO = mapping.toFieldDTO(selectedCrop.getFieldEntity());
            cropDTO.setField_code(fieldDTO);

            return cropDTO;
        } else {
            // Handle the case where the crop ID is not found
            return null;
        }
    }

    @Override
    public List<CropEntityDto> getAllCrop() {
        List<CropEntity> all = cropDao.findAll();

        List<CropEntityDto> collect = all.stream().map(cropEntity -> {
            FieldEntityDto fieldDTO = mapping.toFieldDTO(cropEntity.getFieldEntity());
            CropEntityDto cropDTO = mapping.toCropDTO(cropEntity);
            cropDTO.setField_code(fieldDTO);
            return cropDTO;
        }).collect(Collectors.toList());

        return collect;
    }




}
