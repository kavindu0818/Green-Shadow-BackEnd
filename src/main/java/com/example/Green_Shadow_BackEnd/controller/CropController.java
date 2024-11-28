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
            @RequestParam("field_code") String fieldCode // Accept as String (ID)
    ) {
        try {
            // Convert image to Base64
            String base64ProPic1 = AppUtil.profilePicToBase64(image.getBytes());

            // Retrieve the FieldEntity based on the fieldCode
            FieldEntity fieldEntity = new FieldEntity();
            fieldEntity.setFieldCode(fieldCode);// You need a service method to fetch the FieldEntity

            if (fieldEntity == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // If FieldEntity is not found
            }

            // Build DTO
            CropEntityDto cropEntityDto = new CropEntityDto();
            cropEntityDto.setCropCode(cropCode);
            cropEntityDto.setCommonName(commonName);
            cropEntityDto.setScientificName(scientificName);
            cropEntityDto.setImage(base64ProPic1);
            cropEntityDto.setCategory(category);
            cropEntityDto.setSeason(season);
            cropEntityDto.setField_code(fieldEntity); // Set fieldEntity here

            // Save field
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

    @PutMapping(value = "/{cropCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateCrop(
            @PathVariable String cropCode,
            @RequestParam String commonName,
            @RequestParam String scientificName,
            @RequestParam MultipartFile image,
            @RequestParam String category,
            @RequestParam String season,
            @RequestParam String field_code // Use fieldCode instead of FieldEntity
    ) {
        try {
            // Convert image file to Base64
            String base64ProPic = AppUtil.profilePicToBase64(image.getBytes());

            // Retrieve the FieldEntity from the database using the fieldCode
//            FieldEntity fieldEntity = fieldService.findByFieldCode(fieldCode);
//            if (fieldEntity == null) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid fieldCode: " + fieldCode);
//            }

            FieldEntity fieldEntity = new FieldEntity();
            fieldEntity.setFieldCode(field_code);
            // Create a DTO with the received data
            CropEntityDto cropDto = new CropEntityDto();
            cropDto.setCropCode(cropCode);
            cropDto.setCommonName(commonName);
            cropDto.setScientificName(scientificName);
            cropDto.setImage(base64ProPic); // Save Base64 representation
            cropDto.setCategory(category);
            cropDto.setSeason(season);
            cropDto.setField_code(fieldEntity);

            // Call the service layer to update the crop
            cropService.updateCrop(cropCode, cropDto);

            return ResponseEntity.ok("Crop updated successfully!");
        } catch (IOException e) {
            // Handle file conversion errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing the image: " + e.getMessage());
        } catch (Exception ex) {
            // Handle any other exceptions
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating crop: " + ex.getMessage());
        }
    }




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
