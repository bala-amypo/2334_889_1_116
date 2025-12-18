package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BreachReport;

@Repository
public interface BreachReportRepository extends JpaRepository<BreachReport, Long> {
}
