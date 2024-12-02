package com.example.Green_Shadow_BackEnd.controller;

import com.example.Green_Shadow_BackEnd.dto.FieldStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.FieldEntityDto;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.service.FieldService;
import com.example.Green_Shadow_BackEnd.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/field")
@CrossOrigin(origins = "http://localhost:63342")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestParam("fieldCode") String fieldCode,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocation") String fieldLocation,
            @RequestParam("fieldSize") Double fieldSize,
            @RequestPart("fieldImage") MultipartFile fieldImage1
//            @RequestParam("cropEntityList") String cropEntityList,
//            @RequestParam("staffEntityList") String staffEntityList,
//            @RequestParam("equipmentEntityList") String equipmentEntityList



            ) {
        try {
            // Convert images to Base64
            String base64ProPic1 = AppUtil.profilePicToBase64(fieldImage1.getBytes());


            // Build DTO
            FieldEntityDto buildFieldDto = new FieldEntityDto();
            buildFieldDto.setFieldCode(fieldCode);
            buildFieldDto.setFieldName(fieldName);
            buildFieldDto.setFieldLocation(fieldLocation);
            buildFieldDto.setFieldSize(fieldSize);
            buildFieldDto.setFieldImage(base64ProPic1);
//            buildFieldDto.setCropCodes(cropEntityList);
//            buildFieldDto.setStaffCodes(staffEntityList);
//            buildFieldDto.setEquipmentCodes(equipmentEntityList);
//            buildFieldDto.setImage2(base64ProPic2);

            // Save field
            fieldService.saveField(buildFieldDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = "/{fieldId}")
    public ResponseEntity<Void> updateNote(@PathVariable ("fieldId") String fieldId,
                                           @RequestBody FieldEntityDto updateFieldDTO){
        //validations
        try {
//            if(!RegexProcess.noteIdMatcher(noteId) || updateFieldDTO == null){
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            fieldService.updateField(fieldId,updateFieldDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{fieldId}")
    public ResponseEntity<Void> deleteField(@PathVariable ("fieldId") String fieldId){
        try {
//            if (!RegexProcess.noteIdMatcher(fieldId)) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            fieldService.deleteField(fieldId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{fieldID}",produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldEntityDto getSelectedNote(@PathVariable("fieldID") String fieldId){
//        if (!RegexProcess.noteIdMatcher(noteId)) {
//            return new SelectedUserAndNoteErrorStatus(1,"Note ID is not valid");
//        }
        return fieldService.getField(fieldId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldEntityDto> getALlNotes(){
        return fieldService.getAllField();
    }
}
