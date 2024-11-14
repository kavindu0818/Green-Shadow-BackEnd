package com.example.Green_Shadow_BackEnd.entity.impl;

import com.example.Green_Shadow_BackEnd.entity.SuperEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity implements SuperEntity {
    @Id
    private String id; // Unique code for each staff member
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Designation designation;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Date joinedDate;
    private Date dob;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ElementCollection
    private List<String> fieldList; // Allocated fields, if not, add "N/A"

    @ElementCollection
    private List<String> vehicleList; // Allocated vehicles, if not, add "N/A"

    // Enum for Gender
    public enum Gender {
        MALE, FEMALE, OTHER
    }

    // Enum for Designation
    public enum Designation {
        MANAGER, ENGINEER, TECHNICIAN, SUPPORT, OTHER
    }

    // Enum for Role
    public enum Role {
        ADMIN, USER, SUPERVISOR, GUEST, OTHER
    }
}
