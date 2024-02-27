package com.example.template.repository;

import com.example.template.dao.VisualizationData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRecordRepository extends JpaRepository<VisualizationData, Integer> {
}