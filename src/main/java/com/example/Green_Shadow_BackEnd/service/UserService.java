package com.example.Green_Shadow_BackEnd.service;

import com.example.Green_Shadow_BackEnd.dto.EquipmentStatus;
import com.example.Green_Shadow_BackEnd.dto.UserStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    void updateUser(String userId, UserDto userDto);

    void deleteUser(String userId);

    UserStatus getUser(String userID);

    List<UserDto> getAllUser();
}
