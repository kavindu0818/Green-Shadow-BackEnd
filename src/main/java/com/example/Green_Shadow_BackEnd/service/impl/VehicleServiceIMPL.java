package com.example.Green_Shadow_BackEnd.service.impl;
import com.example.Green_Shadow_BackEnd.customStatusCodes.SelectedAllError;
import com.example.Green_Shadow_BackEnd.dao.VehicleDao;
import com.example.Green_Shadow_BackEnd.dto.CropStatus;
import com.example.Green_Shadow_BackEnd.dto.VehicleStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.VehicleDto;
import com.example.Green_Shadow_BackEnd.entity.impl.FieldEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.VehicleEntity;
import com.example.Green_Shadow_BackEnd.exception.CropNotFoundException;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.service.VehicleService;
import com.example.Green_Shadow_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceIMPL implements VehicleService {

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveVehicle(VehicleDto vehicleDto) {
        VehicleEntity savedVehicle =
                vehicleDao.save(mapping.toVehicleEntity(vehicleDto));
        if (savedVehicle == null) {
            throw new DataPersistException("Vedhicle not saved");
        }

    }

    @Override
    public void updateVehicle(String vehicleId, VehicleDto vehicleDto) {
        Optional<VehicleEntity> findVehicle = vehicleDao.findById(vehicleId);
        if (!findVehicle.isPresent()) {
            throw new FieldNotFoundException("Vehicle Not Found");
        }else {
            findVehicle.get().setCode(vehicleDto.getCode());
            findVehicle.get().setLicensePlateNumber(vehicleDto.getLicensePlateNumber());
            findVehicle.get().setCategory(vehicleDto.getCategory());
            findVehicle.get().setFuelType(vehicleDto.getFuelType());
            findVehicle.get().setStatus(vehicleDto.getStatus());
            findVehicle.get().setRemarks(vehicleDto.getRemarks());
        }

        vehicleDao.save(mapping.toVehicleEntity(vehicleDto));
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        Optional<VehicleEntity> foundVehicle = vehicleDao.findById(vehicleId);
        if (!foundVehicle.isPresent()) {
            throw new CropNotFoundException("Vehicle not found");
        }else {
            vehicleDao.deleteById(vehicleId);
        }
    }

    @Override
    public VehicleStatus getVehicle(String vehicleId) {
        if(vehicleDao.existsById(vehicleId)){
            var selectedVehicle = vehicleDao.getReferenceById(vehicleId);
            return mapping.toVehicleDTO( selectedVehicle);
        }else {
            return new SelectedAllError("2","Select not Found");
        }
    }

    @Override
    public List<VehicleDto> getAllVehicle() {
        return mapping.asVehicleDTOList( vehicleDao.findAll());
    }

}
