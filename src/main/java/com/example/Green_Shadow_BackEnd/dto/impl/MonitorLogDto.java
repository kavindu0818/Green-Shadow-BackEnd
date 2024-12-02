package com.example.Green_Shadow_BackEnd.dto.impl;

import com.example.Green_Shadow_BackEnd.dto.MonitoringLogStatus;
import com.example.Green_Shadow_BackEnd.dto.SuperDTO;
import com.example.Green_Shadow_BackEnd.entity.impl.CropEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.FieldEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.StaffEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitorLogDto implements SuperDTO {

   private String logCode;
   private Date date;
   private String observation;
   private String observationImage;
   private FieldEntity fieldId;
   private CropEntity cropId;
   private StaffEntity staffId;
}
