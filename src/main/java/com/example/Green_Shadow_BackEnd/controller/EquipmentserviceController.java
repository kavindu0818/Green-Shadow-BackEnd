package com.example.Green_Shadow_BackEnd.controller;

import com.example.Green_Shadow_BackEnd.dao.EquipmentDao;
import com.example.Green_Shadow_BackEnd.dto.EquipmentStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.CropEntityDto;
import com.example.Green_Shadow_BackEnd.dto.impl.EquipmentDto;
import com.example.Green_Shadow_BackEnd.dto.impl.VehicleDto;
import com.example.Green_Shadow_BackEnd.entity.impl.EquipmentEntity;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.EquipmentNotFoundException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equ")
@CrossOrigin(origins = "http://localhost:63342")
public class EquipmentserviceController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(
            @RequestParam("EquipmentId") String equipmentId,
            @RequestParam("name") String name,
            @RequestParam("equipmentType") String equipmentType,
            @RequestPart("status") String status,
            @RequestParam("staffId") String staffId,
            @RequestParam("fieldId") String field

    ) {
        try {

            // Build DTO
            EquipmentDto equipmentDto = new EquipmentDto();
            equipmentDto.setEquipmentId(equipmentId);
            equipmentDto.setName(name);
            equipmentDto.setEquipmentType(equipmentType);
            equipmentDto.setStatus(status);
            equipmentDto.setStaffId(staffId);
            equipmentDto.setFieldId(field);

            System.out.println(field + "mmmmmmmmmmmmmmmmmmmmm");
            // Save field
            equipmentService.saveEquiment(equipmentDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{equId}")
    public ResponseEntity<Void> updateNote(@PathVariable ("equId") String equId,
                                           @RequestBody EquipmentDto equipmentDto){
        //validations
        try {
//            if(!RegexProcess.noteIdMatcher(noteId) || updateFieldDTO == null){
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            equipmentService.updateCrop(equId,equipmentDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{equId}")
    public ResponseEntity<Void> deleteNote(@PathVariable ("equId") String equId){
        try {
//            if (!RegexProcess.noteIdMatcher(fieldId)) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            equipmentService.deleteCrop(equId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{equId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentStatus getSelectedCrop(@PathVariable("equId") String equID){
//        if (!RegexProcess.noteIdMatcher(noteId)) {
//            return new SelectedUserAndNoteErrorStatus(1,"Note ID is not valid");
//        }
        return equipmentService.getEquiment(equID);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDto> getALlEqu(){
        return equipmentService.getAllEquiment();
    }


}
