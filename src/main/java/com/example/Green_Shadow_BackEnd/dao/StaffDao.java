package com.example.Green_Shadow_BackEnd.dao;

import com.example.Green_Shadow_BackEnd.entity.impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDao extends JpaRepository<StaffEntity, String> {
}
