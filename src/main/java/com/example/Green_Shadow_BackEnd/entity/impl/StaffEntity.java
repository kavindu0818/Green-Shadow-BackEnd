package com.example.Green_Shadow_BackEnd.entity.impl;

import com.example.Green_Shadow_BackEnd.dto.impl.StaffDto;
import com.example.Green_Shadow_BackEnd.entity.SuperEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StaffEntity implements SuperEntity {
    @Id
    String id;
    String firstName;
    String lastName;
    String designation;
    @Enumerated(EnumType.STRING)
    StaffDto.Gender gender;
    Date joinedDate;
    Date dob;
    String address;
    String contact;
    String email;
    @Enumerated(EnumType.STRING)
    Role role;  // Update this to your custom enum type

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "staff_field",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "field_code")
    )
    List<FieldEntity> fields;

    @OneToMany(mappedBy = "staff")
    List<VehicleEntity> vehicleEntities;

    @OneToMany(mappedBy = "staff" ,cascade = CascadeType.ALL)
    List<EquipmentEntity> equipmentEntityList;

    @OneToOne(mappedBy = "staffEntity")
    MonitorLogEntity log;


}
