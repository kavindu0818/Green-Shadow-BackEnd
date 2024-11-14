package com.example.Green_Shadow_BackEnd.customStatusCodes;

import com.example.Green_Shadow_BackEnd.dto.*;

public class SelectedAllError implements CropStatus, EquipmentStatus, FieldStatus, MonitoringLogStatus, StaffStatus,VehicleStatus {
    private String statusCode;
    private String statusMessage;

    public SelectedAllError(String number, String selectNotFound) {
    }
}
