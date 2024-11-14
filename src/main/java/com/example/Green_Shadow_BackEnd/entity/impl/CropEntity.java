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
    @Column(columnDefinition = "LONGTEXT")
    private String image;

    private String category;

    private String season;

    @ManyToOne
    @JoinColumn(name = "field_id") // or the appropriate column name
    private FieldEntity field;


}
