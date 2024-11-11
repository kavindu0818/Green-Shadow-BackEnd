package com.example.Green_Shadow_BackEnd.entity.impl;
import com.example.Green_Shadow_BackEnd.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field")
public class FieldEntity implements SuperEntity {

    @Id
    private String code;

    private String name;

    private String location;

    private Double extent;

//    @Lob
    private String image1;

//    @Lob
    private String image2;

    @OneToMany(mappedBy = "field") // or similar
    private Set<CropEntity> crops;




}
