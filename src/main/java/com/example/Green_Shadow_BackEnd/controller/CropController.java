package com.example.Green_Shadow_BackEnd.controller;
import com.example.Green_Shadow_BackEnd.dto.CropStatus;
import com.example.Green_Shadow_BackEnd.dto.FieldStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.CropEntityDto;
import com.example.Green_Shadow_BackEnd.dto.impl.FieldEntityDto;
import com.example.Green_Shadow_BackEnd.entity.impl.FieldEntity;
import com.example.Green_Shadow_BackEnd.exception.CropNotFoundException;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.service.CropService;
import com.example.Green_Shadow_BackEnd.service.FieldService;
import com.example.Green_Shadow_BackEnd.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
@CrossOrigin(origins = "http://localhost:63342")

public class CropController {

    @Autowired
    private CropService cropService;

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestParam("cropCode") String cropCode,
            @RequestParam("commonName") String commonName,
            @RequestParam("scientificName") String scientificName,
            @RequestPart("image") MultipartFile image,
            @RequestParam("category") String category,
            @RequestParam("season") String season,
            @RequestParam("field_code") String fieldCode
    ) {
        try {
            // Convert image to Base64
            String base64ProPic1 = AppUtil.profilePicToBase64(image.getBytes());

            // Fetch FieldEntityDto using a service method
            FieldEntityDto fieldEntityDto = fieldService.findFieldByCode(fieldCode);
            if (fieldEntityDto == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // If FieldEntityDto is not found
            }

            // Build CropEntityDto
            CropEntityDto cropEntityDto = new CropEntityDto();
            cropEntityDto.setCropCode(cropCode);
            cropEntityDto.setCommonName(commonName);
            cropEntityDto.setScientificName(scientificName);
            cropEntityDto.setImage(base64ProPic1);
            cropEntityDto.setCategory(category);
            cropEntityDto.setSeason(season);
            cropEntityDto.setField_code(fieldEntityDto); // Set the resolved FieldEntityDto

            // Save crop using the service
            cropService.saveCrop(cropEntityDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{cropCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateCrop(
            @PathVariable String cropCode,
            @RequestParam String commonName,
            @RequestParam String scientificName,
            @RequestParam MultipartFile image,
            @RequestParam String category,
            @RequestParam String season,
            @RequestParam String fieldCode // Use fieldCode as a simple String
    ) {
        try {
            // Convert image file to Base64
            String base64ProPic = AppUtil.profilePicToBase64(image.getBytes());

            // Retrieve the FieldEntity from the database using the fieldCode
            FieldEntity fieldEntity = fieldService.findFieldById(fieldCode);
            if (fieldEntity == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Invalid fieldCode: " + fieldCode);
            }

            // Create a DTO with the received data
            CropEntityDto cropDto = new CropEntityDto();
            cropDto.setCropCode(cropCode);
            cropDto.setCommonName(commonName);
            cropDto.setScientificName(scientificName);
            cropDto.setImage(base64ProPic); // Save Base64 representation
            cropDto.setCategory(category);
            cropDto.setSeason(season);

            // Set the FieldEntityDto in the CropEntityDto
            FieldEntityDto fieldEntityDto = new FieldEntityDto();
            fieldEntityDto.setFieldCode(fieldCode); // Assign fieldCode to the DTO
            cropDto.setField_code(fieldEntityDto);

            // Call the service layer to update the crop
            cropService.updateCrop(cropCode, cropDto);

            return ResponseEntity.ok("Crop updated successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing the image: " + e.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating crop: " + ex.getMessage());
        }
    }



//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> saveNote(@RequestBody CropEntityDto cropEntityDto) {
//        try {
//            cropService.saveCrop(cropEntityDto);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }catch (DataPersistException e){
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @PutMapping(value = "/{cropCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> updateCrop(
//            @PathVariable String cropCode,
//            @RequestParam String commonName,
//            @RequestParam String scientificName,
//            @RequestParam MultipartFile image,
//            @RequestParam String category,
//            @RequestParam String season,
//            @RequestParam String fieldCode // Use String for fieldCode
//    ) {
//        try {
//            // Convert image file to Base64
//            String base64ProPic = AppUtil.profilePicToBase64(image.getBytes());
//
//            // Retrieve the FieldEntity from the database using the fieldCode
//            FieldEntityDto fieldEntityDto = fieldService.findFieldByCode(fieldCode);
//            if (fieldEntityDto == null) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body("Invalid fieldCode: " + fieldCode);
//            }
//
//            // Create a DTO with the received data
//            CropEntityDto cropDto = new CropEntityDto();
//            cropDto.setCropCode(cropCode);
//            cropDto.setCommonName(commonName);
//            cropDto.setScientificName(scientificName);
//            cropDto.setImage(base64ProPic); // Save Base64 representation
//            cropDto.setCategory(category);
//            cropDto.setSeason(season);
//            cropDto.setField_code(fieldEntityDto);
//
//
//            System.out.println(cropDto.getCropCode());// Set the retrieved FieldEntity
//
//            // Call the service layer to update the crop
//            cropService.updateCrop(cropCode, cropDto);
//
//            return ResponseEntity.ok("Crop updated successfully!");
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error processing the image: " + e.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Error updating crop: " + ex.getMessage());
//        }
//    }



//    @PutMapping(value = "/{cropId}")
//    public ResponseEntity<Void> updateNote(@PathVariable ("cropId") String cropId,
//                                           @RequestBody CropEntityDto cropDto){
//        //validations
//        try {
////            if(!RegexProcess.noteIdMatcher(noteId) || updateFieldDTO == null){
////                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////            }
//            cropService.updateCrop(cropId,cropDto);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }catch (FieldNotFoundException e){
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @DeleteMapping(value = "/{cropId}")
//    public ResponseEntity<Void> deleteNote(@PathVariable ("cropId") String cropId){
//        try {
////            if (!RegexProcess.noteIdMatcher(fieldId)) {
////                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////            }
//            cropService.deleteCrop(cropId);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }catch (FieldNotFoundException e){
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping(value = "/{cropId}")
    public ResponseEntity<Void> deleteCrop(@PathVariable ("cropId") String cropId){
        try {
//            if (!RegexProcess.noteIdMatcher(noteId)) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
            cropService.deleteCrop(cropId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{cropId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropStatus getSelectedCrop(@PathVariable("cropId") String cropID){
//        if (!RegexProcess.noteIdMatcher(noteId)) {
//            return new SelectedUserAndNoteErrorStatus(1,"Note ID is not valid");
//        }
        return cropService.getCrop(cropID);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CropEntityDto>> getAllCrops() {
        List<CropEntityDto> crops = cropService.getAllCrop();
        if (crops.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(crops); // 200 OK
    }

}
