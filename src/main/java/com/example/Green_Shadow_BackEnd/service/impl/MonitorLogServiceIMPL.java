package com.example.Green_Shadow_BackEnd.service.impl;

import com.example.Green_Shadow_BackEnd.dao.MonitoringLogDao;
import com.example.Green_Shadow_BackEnd.dto.impl.MonitorLogDto;
import com.example.Green_Shadow_BackEnd.entity.impl.CropEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.MonitorLogEntity;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.service.MonitorLogService;
import com.example.Green_Shadow_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MonitorLogServiceIMPL implements MonitorLogService {

    @Autowired
    private Mapping mapping;

    @Autowired
    private MonitoringLogDao monitoringLogDao;

    @Override
    public void saveMonitorLog(MonitorLogDto monitorLogDto) {
        MonitorLogEntity savedMonitorLog =
                monitoringLogDao.save(mapping.toMonitorLogEntity(monitorLogDto));
        if (monitorLogDto == null) {
            throw new DataPersistException("MonitorLogin not saved");
        }
    }
}
