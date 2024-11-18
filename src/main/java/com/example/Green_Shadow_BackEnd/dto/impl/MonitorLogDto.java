package com.example.Green_Shadow_BackEnd.dto.impl;

import com.example.Green_Shadow_BackEnd.dto.MonitoringLogStatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitorLogDto implements MonitoringLogStatus {

   private String logCode;
   private Date date;
   private String observation;
   private String observationImage;
   private String fieldId;
   private String cropId;
   private String staffId;
}
