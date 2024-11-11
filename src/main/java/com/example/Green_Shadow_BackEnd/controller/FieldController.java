package com.example.Green_Shadow_BackEnd.controller;


import com.example.Green_Shadow_BackEnd.dto.impl.FieldDto;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.service.FieldService;
import com.example.Green_Shadow_BackEnd.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping ("api/v1/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveUser(
            @RequestPart("code") String code,
            @RequestPart ("name") String name,
            @RequestPart ("location") String location,
            @RequestPart ("extent") Double extent,
            @RequestPart ("image1") MultipartFile image1,
            @RequestPart ("image2") MultipartFile image2
    ) {
        // profilePic ----> Base64
        String base64ProPic1 = "";
        String base64ProPic2 = "";
        try {
            byte [] bytesProPic1 = image1.getBytes();
            base64ProPic1 = AppUtil.profilePicToBase64(bytesProPic1);

            byte [] bytesProPic2 = image2.getBytes();
            base64ProPic2 = AppUtil.profilePicToBase64(bytesProPic2);
            //UserId generate

            //Build the Object
            FieldDto buildFieldDto = new FieldDto();
            buildFieldDto.setCode(code);
            buildFieldDto.setName(name);
            buildFieldDto.setLocation(location);
            buildFieldDto.setExtent(extent);
            buildFieldDto.setImage1(base64ProPic1);
            buildFieldDto.setImage2(base64ProPic2);
            fieldService.saveField(buildFieldDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
