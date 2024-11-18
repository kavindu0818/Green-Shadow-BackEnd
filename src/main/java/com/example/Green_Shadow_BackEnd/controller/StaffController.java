package com.example.Green_Shadow_BackEnd.controller;

import com.example.Green_Shadow_BackEnd.dto.FieldStatus;
import com.example.Green_Shadow_BackEnd.dto.StaffStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.FieldEntityDto;
import com.example.Green_Shadow_BackEnd.dto.impl.StaffDto;
import com.example.Green_Shadow_BackEnd.entity.impl.Role;
import com.example.Green_Shadow_BackEnd.entity.impl.StaffEntity;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.exception.StaffNotFoundException;
import com.example.Green_Shadow_BackEnd.service.StaffService;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(
            @RequestParam("id") String id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("designation") String designation, // Enum as String
            @RequestParam("gender") String gender, // Enum as String
            @RequestParam("joinedDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date joinedDate,
            @RequestParam("dob") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dob,
            @RequestParam("address") String address,
            @RequestParam("contact") String contactNo,
            @RequestParam("email") String email,
            @RequestParam("role") String role // Enum as String

    ) {
        try {
            // Construct full address
//            String address = String.join(", ", addressLine1, addressLine2, addressLine3, addressLine4, addressLine5);

            // Convert input strings to enums
            StaffDto.Gender genderEnum = StaffDto.Gender.valueOf(gender.toUpperCase());
            StaffDto.Role roleEnum = StaffDto.Role.valueOf(role.toUpperCase());

            // Create StaffDto
            StaffDto staffDto = new StaffDto();
            staffDto.setId(id);
            staffDto.setFirstName(firstName);
            staffDto.setLastName(lastName);
            staffDto.setDesignation(designation);
            staffDto.setGender(genderEnum);
            staffDto.setJoinedDate(joinedDate);
            staffDto.setDob(dob);
            staffDto.setAddress(address);
            staffDto.setContact(contactNo);
            staffDto.setEmail(email);
            staffDto.setRole(roleEnum);
//            staffDto.setFieldCodes(fieldList);
//            staffDto.setVehicleIds(vehicleList);

            // Call the service layer to save the staff
            staffService.saveStaff(staffDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Invalid enums
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{staffId}")
    public ResponseEntity<Void> updateNote(@PathVariable ("staffId") String staffId,
                                           @RequestBody StaffDto staffDto){
        //validations
        try {
//            if(!RegexProcess.noteIdMatcher(noteId) || updateFieldDTO == null){
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            staffService.updateStaff(staffId,staffDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{staffId}")
    public ResponseEntity<Void> deleteField(@PathVariable ("staffId") String staffId){
        try {
//            if (!RegexProcess.noteIdMatcher(fieldId)) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            staffService.deleteStaff(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{staffId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffDto getSelectedNote(@PathVariable("staffId") String staffId){
//        if (!RegexProcess.noteIdMatcher(noteId)) {
//            return new SelectedUserAndNoteErrorStatus(1,"Note ID is not valid");
//        }
        return staffService.getStaff(staffId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDto> getALlStaff(){
        return staffService.getAllStaff();
    }
}





