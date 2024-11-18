package com.example.Green_Shadow_BackEnd.entity.impl;

import com.example.Green_Shadow_BackEnd.dto.EquipmentStatus;
import com.example.Green_Shadow_BackEnd.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class EquipmentEntity implements SuperEntity {

    @Id
   private String EquipmentId;
   private String name;
   private String equipmentType;
//    EquipmentStatus equipmentType;

   private String status;

    @ManyToOne
    @JoinColumn(name = "id")
    StaffEntity staff;

    @ManyToOne
    @JoinColumn(name = "field_code")
    FieldEntity field;

}
