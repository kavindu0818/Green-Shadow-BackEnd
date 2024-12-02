package com.example.Green_Shadow_BackEnd.dto.impl;

import com.example.Green_Shadow_BackEnd.dto.SuperDTO;
import com.example.Green_Shadow_BackEnd.dto.UserStatus;
import com.example.Green_Shadow_BackEnd.entity.impl.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements UserStatus {
    private String email;
    private String password;
    Role role;
}
