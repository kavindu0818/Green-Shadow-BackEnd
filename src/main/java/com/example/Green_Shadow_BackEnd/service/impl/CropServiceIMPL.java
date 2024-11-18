package com.example.Green_Shadow_BackEnd.service.impl;

import com.example.Green_Shadow_BackEnd.customStatusCodes.SelectedAllError;
import com.example.Green_Shadow_BackEnd.dao.CropDao;
import com.example.Green_Shadow_BackEnd.dto.CropStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.CropEntityDto;
import com.example.Green_Shadow_BackEnd.entity.impl.CropEntity;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.service.CropService;
import com.example.Green_Shadow_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceIMPL implements CropService {

    @Autowired
    private CropDao cropDao;

    @Autowired
    private Mapping mapping;


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
            throw new FieldNotFoundException("Field Not Found");
        } else {
            CropEntity cropEntity = findCrop.get();
            cropEntity.setCropCode(cropDto.getCropCode());
            cropEntity.setCommonName(cropDto.getCommonName());
            cropEntity.setScientificName(cropDto.getScientificName());
            cropEntity.setImage(cropDto.getImage());
            cropEntity.setCategory(cropDto.getCategory());
            cropEntity.setSeason(cropDto.getSeason());

            // Fetch and set the FieldEntity
            // Save updated entity if needed (depending on your transactional setup)
        }
    }


    @Override
    public void deleteCrop(String cropId) {
        Optional<CropEntity> foundNote = cropDao.findById(cropId);
        if (!foundNote.isPresent()) {
            throw new FieldNotFoundException("Field not found");
        }else {
            cropDao.deleteById(cropId);
        }
    }

    @Override
    public CropEntityDto getCrop(String cropId) {
        if(cropDao.existsById(cropId)){
            var selectedCrop = cropDao.getReferenceById(cropId);
            return  mapping.toCropDTO(selectedCrop);
        }else {
//            return new SelectedAllError("2","Select not Found");
            return null;
        }
    }
    @Override
    public List<CropEntityDto> getAllCrop() {
        return mapping.asCropDTOList( cropDao.findAll());
    }




}
