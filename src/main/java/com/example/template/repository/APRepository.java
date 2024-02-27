package com.example.template.repository;

import com.example.template.dao.AP;
import com.example.template.dao.embed.MACAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APRepository extends JpaRepository<AP, MACAddress> {

}