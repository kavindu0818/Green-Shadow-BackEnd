package com.example.Green_Shadow_BackEnd.controller;

import com.example.Green_Shadow_BackEnd.dto.CropStatus;
import com.example.Green_Shadow_BackEnd.dto.VehicleStatus;

import com.example.Green_Shadow_BackEnd.dto.impl.VehicleDto;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.exception.VehicleNotFoundException;
import com.example.Green_Shadow_BackEnd.service.VehicleService;
import com.example.Green_Shadow_BackEnd.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/veh")
@CrossOrigin(origins = "http://localhost:63342")

public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(
            @RequestParam("code") String code,
            @RequestParam("licensePlateNum") String licensePlateNum,
            @RequestParam("category") String category,
            @RequestParam("fuelType") String fuelType, // Changed to @RequestParam for consistency
            @RequestParam("status") String status,
            @RequestParam("remarks") String remarks,
            @RequestParam("staffId") String staffId // Ensure this maps correctly
    ) {
        try {
            // Build DTO
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setCode(code);
            vehicleDto.setLicensePlateNum(licensePlateNum);
            vehicleDto.setCategory(category);
            vehicleDto.setFuelType(fuelType);
            vehicleDto.setStatus(status);
            vehicleDto.setRemarks(remarks);
            vehicleDto.setStaffId(staffId);

            // Save field
            vehicleService.saveVehicle(vehicleDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = "/{vehicleId}")
    public ResponseEntity<Void> updateVehicle(@PathVariable ("vehicleId") String vehicleId,
                                           @RequestBody VehicleDto vehicleDto){
        //validations
        try {
//            if(!RegexProcess.noteIdMatcher(noteId) || updateFieldDTO == null){
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            vehicleService.updateVehicle(vehicleId,vehicleDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (VehicleNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable ("vehicleId") String vehicleId){
        try {
//            if (!RegexProcess.noteIdMatcher(fieldId)) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            vehicleService.deleteVehicle(vehicleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (VehicleNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{vehicleId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleStatus getSelectedVehicle(@PathVariable("vehicleId") String vehicleId){
//        if (!RegexProcess.noteIdMatcher(noteId)) {
//            return new SelectedUserAndNoteErrorStatus(1,"Note ID is not valid");
//        }
        return vehicleService.getVehicle(vehicleId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDto> getALlCrops(){
        return vehicleService.getAllVehicle();
    }
}
