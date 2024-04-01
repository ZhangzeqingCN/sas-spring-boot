package com.example.template.repository;

import com.example.template.dao.Control;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Control, Integer> {
}