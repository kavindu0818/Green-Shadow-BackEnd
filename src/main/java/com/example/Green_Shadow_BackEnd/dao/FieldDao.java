package com.example.Green_Shadow_BackEnd.dao;

import com.example.Green_Shadow_BackEnd.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FieldDao extends JpaRepository<FieldEntity, String>  {
}

