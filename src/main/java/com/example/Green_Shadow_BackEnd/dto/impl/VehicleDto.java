package com.example.Green_Shadow_BackEnd.dto.impl;

import com.example.Green_Shadow_BackEnd.dto.VehicleStatus;
import jakarta.persistence.Id;
import jakarta.persistence.NamedEntityGraph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDto implements VehicleStatus {

    @Id
    private String code;

    private String licensePlateNumber;

    private String category;

    private String fuelType;

    private String status;

//    private List<StaffEntity> allocatedStaffMembers;

    private String remarks;
}
