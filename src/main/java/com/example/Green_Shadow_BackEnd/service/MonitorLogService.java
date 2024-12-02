package com.example.Green_Shadow_BackEnd.service;

import com.example.Green_Shadow_BackEnd.dto.MonitoringLogStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.CropEntityDto;
import com.example.Green_Shadow_BackEnd.dto.impl.MonitorLogDto;

import java.util.List;

public interface MonitorLogService {
    void saveMonitorLog(MonitorLogDto monitorLogDto);

    void updateMonitorLog(String monId, MonitorLogDto monitorLogDto);

    void deleteMonitorLog(String monId);

    MonitorLogDto getMonitorLog(String monID);

    List<MonitorLogDto> getAllMonitorLog();
}
