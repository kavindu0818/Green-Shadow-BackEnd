package com.example.Green_Shadow_BackEnd.entity.impl;

import com.example.Green_Shadow_BackEnd.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "monitorLog")
public class MonitorLogEntity implements SuperEntity {

    @Id
    String logCode;

    Date date;
    String observation;

    @Column(columnDefinition = "LONGTEXT")
    String observationImage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "field_code", referencedColumnName = "fieldCode")
    FieldEntity fieldEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crop_code", referencedColumnName = "cropCode")
    CropEntity cropEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    StaffEntity staffEntity;

}
