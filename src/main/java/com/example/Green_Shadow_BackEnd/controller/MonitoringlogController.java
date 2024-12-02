package com.example.Green_Shadow_BackEnd.controller;

import com.example.Green_Shadow_BackEnd.dto.MonitoringLogStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.CropEntityDto;
import com.example.Green_Shadow_BackEnd.dto.impl.MonitorLogDto;
import com.example.Green_Shadow_BackEnd.entity.impl.CropEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.FieldEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.StaffEntity;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.exception.MonitorLogfoundException;
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
@CrossOrigin(origins = "http://localhost:63342")

public class MonitoringlogController {

    @Autowired
    private MonitorLogService monitorLogService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveMonitorLog(
            @RequestParam("logCode") String logCode,
            @RequestParam("date") Date date,
            @RequestParam("observation") String observation,
            @RequestPart("observationImage") MultipartFile image,
            @RequestParam("fieldId") FieldEntity fieldCode,
            @RequestParam("cropId") CropEntity cropCode,
            @RequestParam("staffId") StaffEntity staffId

    ) {
        try {
            // Convert image to Base64
            String base64Image = AppUtil.profilePicToBase64(image.getBytes());

            // Build DTO
            MonitorLogDto monitorLogDto = new MonitorLogDto();
            monitorLogDto.setLogCode(logCode);
            monitorLogDto.setDate(date);
            monitorLogDto.setObservation(observation);
            monitorLogDto.setObservationImage(base64Image);
            monitorLogDto.setFieldId(fieldCode);
            monitorLogDto.setCropId(cropCode);
            monitorLogDto.setStaffId(staffId);

            // Save monitor log
            monitorLogService.saveMonitorLog(monitorLogDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = "/{monId}")
    public ResponseEntity<Void> updateMonitorLog(@PathVariable ("monId") String monId,
                                           @RequestBody MonitorLogDto monitorLogDto){
        //validations
        try {
//            if(!RegexProcess.noteIdMatcher(noteId) || updateFieldDTO == null){
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            monitorLogService.updateMonitorLog(monId,monitorLogDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (MonitorLogfoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{monId}")
    public ResponseEntity<Void> deleteMonitor(@PathVariable ("monId") String monId){
        try {
//            if (!RegexProcess.noteIdMatcher(fieldId)) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            monitorLogService.deleteMonitorLog(monId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (MonitorLogfoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{monId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitorLogDto getSelectedMonitor(@PathVariable("monId") String monID){
//        if (!RegexProcess.noteIdMatcher(noteId)) {
//            return new SelectedUserAndNoteErrorStatus(1,"Note ID is not valid");
//        }
        return monitorLogService.getMonitorLog(monID);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitorLogDto> getALlMonitors(){
        return monitorLogService.getAllMonitorLog();
    }


}
