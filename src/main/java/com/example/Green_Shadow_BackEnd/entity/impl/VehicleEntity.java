package com.example.Green_Shadow_BackEnd.entity.impl;

import com.example.Green_Shadow_BackEnd.entity.SuperEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity implements SuperEntity {

    @Id
    private String code;

    private String licensePlateNumber;

    private String category;

    private String fuelType;

    private String status;

//    private List<StaffEntity> allocatedStaffMembers;

    private String remarks;
}
