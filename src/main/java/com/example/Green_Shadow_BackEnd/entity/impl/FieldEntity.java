package com.example.Green_Shadow_BackEnd.entity.impl;

import com.example.Green_Shadow_BackEnd.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FieldEntity implements SuperEntity {
    @Id
    String fieldCode;
    String fieldName;
    String fieldLocation;
    double  fieldSize;
    @Column(columnDefinition = "LONGTEXT")
    String fieldImage;
    @OneToMany(mappedBy = "fieldEntity")
    List<CropEntity> cropEntityList;

    @ManyToMany(mappedBy = "fields", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<StaffEntity> staffEntityList;


    @OneToMany(mappedBy = "field")
    List<EquipmentEntity> equipmentEntityList;

    @OneToOne(mappedBy = "fieldEntity" )
    MonitorLogEntity log;
}