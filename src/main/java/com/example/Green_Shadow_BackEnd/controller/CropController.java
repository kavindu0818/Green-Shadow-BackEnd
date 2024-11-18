package com.example.Green_Shadow_BackEnd.controller;
import com.example.Green_Shadow_BackEnd.dto.CropStatus;
import com.example.Green_Shadow_BackEnd.dto.FieldStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.CropEntityDto;
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

import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestParam("cropCode") String cropCode,
            @RequestParam("commonName") String commonName,
            @RequestParam("scientificName") String scientificName,
            @RequestPart("image") MultipartFile image,
            @RequestParam("category") String category,
            @RequestParam("season") String season,
            @RequestParam("field_code") String field_code // Changed to @RequestParam
    ) {
        try {
            // Convert image to Base64
            String base64ProPic1 = AppUtil.profilePicToBase64(image.getBytes());

            // Build DTO
            CropEntityDto cropEntityDto = new CropEntityDto();
            cropEntityDto.setCropCode(cropCode);
            cropEntityDto.setCommonName(commonName);
            cropEntityDto.setScientificName(scientificName);
            cropEntityDto.setImage(base64ProPic1);
            cropEntityDto.setCategory(category);
            cropEntityDto.setSeason(season);
            cropEntityDto.setFieldCode(field_code); // Set fieldId properly

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
