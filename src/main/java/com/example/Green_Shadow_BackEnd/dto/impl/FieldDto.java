package com.example.Green_Shadow_BackEnd.dto.impl;

import com.example.Green_Shadow_BackEnd.dto.FieldStatus;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FieldDto implements FieldStatus {
    private String code;

    private String name;

    private String location;

    private Double extent;

    private String image1;
    private String image2;

    private List<CropDto> cropDto;
}
