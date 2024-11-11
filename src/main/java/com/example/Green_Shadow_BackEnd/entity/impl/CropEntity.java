package com.example.Green_Shadow_BackEnd.entity.impl;

import com.example.Green_Shadow_BackEnd.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "crop")
public class CropEntity implements SuperEntity {

    @Id
    private String code;

    private String commonName;

    private String scientificName;

    @Lob
    private String image;

    private String category;

    private String season;

    @ManyToOne
    @JoinColumn(name = "fieldId",nullable = false)
    private FieldEntity field;

}
