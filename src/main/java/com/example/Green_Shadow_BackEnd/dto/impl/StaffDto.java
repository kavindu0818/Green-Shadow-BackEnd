package com.example.Green_Shadow_BackEnd.dto.impl;
import com.example.Green_Shadow_BackEnd.dto.StaffStatus;
import com.example.Green_Shadow_BackEnd.entity.impl.StaffEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDto implements StaffStatus {

    private String id; // Unique code for each staff member
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private StaffEntity.Designation designation;

    @Enumerated(EnumType.STRING)
    private StaffEntity.Gender gender;

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
    private StaffEntity.Role role;

    @ElementCollection
    private List<String> fieldList; // Allocated fields, if not, add "N/A"

    @ElementCollection
    private List<String> vehicleList; // Allocated vehicles, if not, add "N/A"

    // Enum for Gender
    public enum Gender {
        MALE, FEMALE, OTHER
    }
    public enum Designation {
        MANAGER, ENGINEER, TECHNICIAN, SUPPORT, OTHER
    }
    public enum Role {
        ADMIN, USER, SUPERVISOR, GUEST, OTHER
    }
}
