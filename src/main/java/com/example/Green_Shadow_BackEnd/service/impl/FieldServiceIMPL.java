package com.example.Green_Shadow_BackEnd.service.impl;
import com.example.Green_Shadow_BackEnd.customStatusCodes.SelectedAllError;
import com.example.Green_Shadow_BackEnd.dao.FieldDao;
import com.example.Green_Shadow_BackEnd.dto.FieldStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.FieldEntityDto;
import com.example.Green_Shadow_BackEnd.entity.impl.FieldEntity;
import com.example.Green_Shadow_BackEnd.exception.CropNotFoundException;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.service.FieldService;
import com.example.Green_Shadow_BackEnd.util.Mapping;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class FieldServiceIMPL implements FieldService {

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldEntityDto fieldDto) {
        FieldEntity fieldEntity = mapping.toFieldEntity(fieldDto);

        fieldDao.save(fieldEntity);
    }
    @Override
    public void updateField(String noteId, FieldEntityDto fieldDto) {
        Optional<FieldEntity> findNote = fieldDao.findById(noteId);
        if (!findNote.isPresent()) {
            throw new FieldNotFoundException("Field Not Found");
        }else {
            findNote.get().setFieldCode(fieldDto.getFieldCode());
            findNote.get().setFieldName(fieldDto.getFieldName());
            findNote.get().setFieldLocation(fieldDto.getFieldLocation());
            findNote.get().setFieldSize(fieldDto.getFieldSize());
            findNote.get().setFieldImage(fieldDto.getFieldImage());
        }
//        fieldDao.save(mapping.toFieldEntity(fieldDto));

    }
    @Override
    public void deleteField(String fieldId) {
        Optional<FieldEntity> foundNote = fieldDao.findById(fieldId);
        if (!foundNote.isPresent()) {
            throw new CropNotFoundException("Field not found");
        }else {
            fieldDao.deleteById(fieldId);
        }
    }
    @Override
    public FieldEntityDto getField(String fieldId) {
        if (fieldDao.existsById(fieldId)) {
            var selectedField = fieldDao.getReferenceById(fieldId);
            return mapping.toFieldDTO(selectedField);
        }
        return null;
    }
    @Override
    public List<FieldEntityDto> getAllField() {
        return mapping.asNoteDTOList( fieldDao.findAll());
    }

    @Override
    public FieldEntityDto findFieldByCode(String fieldCode) {
        FieldEntity fieldEntity = fieldDao.findById(fieldCode).orElse(null);
        if (fieldEntity == null) {
            return null;
        }
        FieldEntityDto fieldEntityDto = new FieldEntityDto();
        fieldEntityDto.setFieldCode(fieldEntity.getFieldCode());
        fieldEntityDto.setFieldName(fieldEntity.getFieldName());
        fieldEntityDto.setFieldLocation(fieldEntity.getFieldLocation());
        fieldEntityDto.setFieldSize(fieldEntity.getFieldSize());
        fieldEntityDto.setFieldImage(fieldEntity.getFieldImage());
        return fieldEntityDto;
    }

    @Override
    public FieldEntity findFieldById(String fieldId) {
        FieldEntity fieldEntity = fieldDao.findById(fieldId).orElse(null);
        if (fieldEntity == null) {
            return null;
        }
//        FieldEntityDto fieldEntityDto = new FieldEntityDto();
//        fieldEntityDto.setFieldCode(fieldEntity.getFieldCode());
//        fieldEntityDto.setFieldName(fieldEntity.getFieldName());
//        fieldEntityDto.setFieldLocation(fieldEntity.getFieldLocation());
//        fieldEntityDto.setFieldSize(fieldEntity.getFieldSize());
//        fieldEntityDto.setFieldImage(fieldEntity.getFieldImage());
        return fieldEntity;
    }
}






