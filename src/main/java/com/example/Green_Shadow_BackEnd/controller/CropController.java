package com.example.Green_Shadow_BackEnd.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/crop")
public class CropController {
    @GetMapping
    public String healthTest(){
        return "Crop controller is working";
    }
}
