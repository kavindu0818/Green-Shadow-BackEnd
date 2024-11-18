package com.example.Green_Shadow_BackEnd.dao;

import com.example.Green_Shadow_BackEnd.entity.impl.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface EquipmentDao extends JpaRepository<EquipmentEntity, String> {
}
