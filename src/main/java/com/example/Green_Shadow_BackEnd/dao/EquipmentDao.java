package com.example.Green_Shadow_BackEnd.dao;

import com.example.Green_Shadow_BackEnd.entity.impl.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface EquipmentDao extends JpaRepository<EquipmentEntity, String> {
}
