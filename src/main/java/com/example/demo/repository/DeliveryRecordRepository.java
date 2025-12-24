package com.example.demo.repository;

import com.example.demo.entity.DeliveryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecord, Long> {

}
