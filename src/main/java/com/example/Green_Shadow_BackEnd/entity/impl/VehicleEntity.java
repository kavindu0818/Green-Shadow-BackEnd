package com.example.Green_Shadow_BackEnd.entity.impl;
import com.example.Green_Shadow_BackEnd.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
//@Table(name = "vehicle")
public class VehicleEntity implements SuperEntity {

    @Id
    String code;
    String licensePlateNum;
    String category;
    String fuelType;
    String status;
    String remarks;

    @ManyToOne
    @JoinColumn(name = "id")
    StaffEntity staff;

}
