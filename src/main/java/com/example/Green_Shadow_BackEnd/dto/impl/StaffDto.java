package com.example.Green_Shadow_BackEnd.dto.impl;

import com.example.Green_Shadow_BackEnd.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto implements SuperDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    private Gender gender;
    private Date joinedDate;
    private Date dob;
    private String address;
    private String contact;
    private String email;
    private Role role;
    private List<String> fieldCodes;
    private List<String> vehicleIds;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum Role {
        ADMIN, USER, SUPERVISOR
    }
}
