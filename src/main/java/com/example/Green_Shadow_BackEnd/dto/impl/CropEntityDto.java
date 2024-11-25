package com.example.Green_Shadow_BackEnd.dto.impl;

import com.example.Green_Shadow_BackEnd.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropEntityDto implements SuperDTO {
    private String cropCode;
    private String commonName;
    private String scientificName;
    private String image;
    private String category;
    private String season;
    private String field_Code; // You may want to include the field code if needed
   // If needed, you can include a related monitor log ID

}
