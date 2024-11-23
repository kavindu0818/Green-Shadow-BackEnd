package com.example.Green_Shadow_BackEnd.controller;

import com.example.Green_Shadow_BackEnd.dto.impl.CropEntityDto;
import com.example.Green_Shadow_BackEnd.dto.impl.MonitorLogDto;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.service.MonitorLogService;
import com.example.Green_Shadow_BackEnd.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/mlog")
public class MonitoringlogController {

    @Autowired
    private MonitorLogService monitorLogService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveMonitorLog(
            @RequestParam("logCode") String logCode,
            @RequestParam("date") Date date,
            @RequestParam("observation") String observation,
            @RequestPart("observationImage") MultipartFile image,
            @RequestParam("fieldId") String fieldId,
            @RequestParam("cropId") String cropId,
            @RequestParam("staffId") String staffId // Changed to @RequestParam
    ) {
        try {
            // Convert image to Base64
            String base64ProPic1 = AppUtil.profilePicToBase64(image.getBytes());

            // Build DTO
            MonitorLogDto monitorLogDto = new MonitorLogDto();
            monitorLogDto.setLogCode(logCode);
            monitorLogDto.setDate(date);
            monitorLogDto.setObservation(observation);
            monitorLogDto.setObservationImage(base64ProPic1);
            monitorLogDto.setFieldId(fieldId);
            monitorLogDto.setCropId(cropId);
            monitorLogDto.setStaffId(staffId); // Set fieldId properly

            // Save field
            monitorLogService.saveMonitorLog(monitorLogDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{cropId}")
    public ResponseEntity<Void> updateNote(@PathVariable ("cropId") String cropId,
                                           @RequestBody CropEntityDto cropDto){
        //validations
        try {
//            if(!RegexProcess.noteIdMatcher(noteId) || updateFieldDTO == null){
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            cropService.updateCrop(cropId,cropDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{cropId}")
    public ResponseEntity<Void> deleteNote(@PathVariable ("cropId") String cropId){
        try {
//            if (!RegexProcess.noteIdMatcher(fieldId)) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            cropService.deleteCrop(cropId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{cropId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropEntityDto getSelectedCrop(@PathVariable("cropId") String cropID){
//        if (!RegexProcess.noteIdMatcher(noteId)) {
//            return new SelectedUserAndNoteErrorStatus(1,"Note ID is not valid");
//        }
        return cropService.getCrop(cropID);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropEntityDto> getALlCrops(){
        return cropService.getAllCrop();
    }


}
