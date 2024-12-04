package com.example.Green_Shadow_BackEnd.dao;

import com.example.Green_Shadow_BackEnd.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity,String> {
}
