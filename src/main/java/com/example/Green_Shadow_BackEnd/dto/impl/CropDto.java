package com.example.Green_Shadow_BackEnd.dto.impl;

import com.example.Green_Shadow_BackEnd.dto.CropStatus;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CropDto implements CropStatus {

    @Id
    private String code;

    private String commonName;

    private String scientificName;

    @Lob
    private String image;

    private String category;

    private String season;

    private String fieldId;
}
