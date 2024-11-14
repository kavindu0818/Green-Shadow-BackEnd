package com.example.Green_Shadow_BackEnd.service;

import com.example.Green_Shadow_BackEnd.dto.CropStatus;
import com.example.Green_Shadow_BackEnd.dto.VehicleStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.VehicleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {
    void saveVehicle(VehicleDto vehicleDto);

    void updateVehicle(String vehicleId, VehicleDto vehicleDto);

    void deleteVehicle(String vehicleId);

    VehicleStatus getVehicle(String vehicleId);

    List<VehicleDto> getAllVehicle();
}
