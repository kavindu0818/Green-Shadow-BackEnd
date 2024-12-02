package com.example.Green_Shadow_BackEnd.service.impl;

import com.example.Green_Shadow_BackEnd.dao.MonitoringLogDao;
import com.example.Green_Shadow_BackEnd.dto.MonitoringLogStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.MonitorLogDto;
import com.example.Green_Shadow_BackEnd.entity.impl.CropEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.EquipmentEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.MonitorLogEntity;
import com.example.Green_Shadow_BackEnd.exception.CropNotFoundException;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.exception.MonitorLogfoundException;
import com.example.Green_Shadow_BackEnd.service.MonitorLogService;
import com.example.Green_Shadow_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void updateMonitorLog(String monId, MonitorLogDto monitorLogDto) {
        Optional<MonitorLogEntity> findEquiment = monitoringLogDao.findById(monId);
        if (!findEquiment.isPresent()) {
            throw new FieldNotFoundException("Equiment Not Found");
        }else {
            findEquiment.get().setLogCode(monitorLogDto.getLogCode());
            findEquiment.get().setDate(monitorLogDto.getDate());
            findEquiment.get().setObservation(monitorLogDto.getObservation());
            findEquiment.get().setObservationImage(monitorLogDto.getObservationImage());
            findEquiment.get().setFieldEntity(monitorLogDto.getFieldId());
            findEquiment.get().setCropEntity(monitorLogDto.getCropId());
            findEquiment.get().setStaffEntity(monitorLogDto.getStaffId());

        }

        monitoringLogDao.save(mapping.toMonitorLogEntity(monitorLogDto));

    }

    @Override
    public void deleteMonitorLog(String monId) {
        Optional<MonitorLogEntity> findEquiment = monitoringLogDao.findById(monId);
        if (!findEquiment.isPresent()) {
            throw new MonitorLogfoundException("Field not found");
        }else {
            monitoringLogDao.deleteById(monId);
        }

    }

    @Override
    public MonitorLogDto getMonitorLog(String monID) {
        if (monitoringLogDao.existsById(monID)) {
            var selectedField = monitoringLogDao.getReferenceById(monID);
            return mapping.toMonitorLogDTO(selectedField);
        }
        return null;
    }


    @Override
    public List<MonitorLogDto> getAllMonitorLog() {
        return mapping.asMonitorLogDTOList( monitoringLogDao.findAll());
    }
}
