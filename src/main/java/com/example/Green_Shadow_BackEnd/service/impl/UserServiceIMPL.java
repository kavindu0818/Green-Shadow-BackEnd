package com.example.Green_Shadow_BackEnd.service.impl;

import com.example.Green_Shadow_BackEnd.customStatusCodes.SelectedAllError;
import com.example.Green_Shadow_BackEnd.dao.UserDao;
import com.example.Green_Shadow_BackEnd.dto.EquipmentStatus;
import com.example.Green_Shadow_BackEnd.dto.UserStatus;
import com.example.Green_Shadow_BackEnd.dto.impl.UserDto;
import com.example.Green_Shadow_BackEnd.entity.impl.EquipmentEntity;
import com.example.Green_Shadow_BackEnd.entity.impl.UserEntity;
import com.example.Green_Shadow_BackEnd.exception.CropNotFoundException;
import com.example.Green_Shadow_BackEnd.exception.DataPersistException;
import com.example.Green_Shadow_BackEnd.exception.FieldNotFoundException;
import com.example.Green_Shadow_BackEnd.service.UserService;
import com.example.Green_Shadow_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private Mapping mapping;
    @Override
    public void saveUser(UserDto userDto) {
        UserEntity savedUser =
                userDao.save(mapping.toUserEntity(userDto));
        if (savedUser == null) {
            throw new DataPersistException("User not saved");
        }
    }

    @Override
    public void updateUser(String userId, UserDto userDto) {
        Optional<UserEntity> findUser = userDao.findById(userId);
        if (!findUser.isPresent()) {
            throw new FieldNotFoundException("User Not Found");
        } else {
            findUser.get().setEmail(userDto.getEmail());
            findUser.get().setPassword(userDto.getPassword());
            findUser.get().setRole(userDto.getRole());
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> findUser = userDao.findById(userId);
        if (!findUser.isPresent()) {
            throw new CropNotFoundException("User not found");
        }else {
            userDao.deleteById(userId);
        }
    }

    @Override
    public UserStatus getUser(String userID) {
        if(userDao.existsById(userID)){
            var selectedUser = userDao.getReferenceById(userID);
            return mapping.toUserDTO(selectedUser);
        }else {
            return (UserStatus) new SelectedAllError("2","Select not Found");
        }
    }

    @Override
    public List<UserDto> getAllUser() {
        return mapping.asUserDTOList(userDao.findAll());
    }
}
