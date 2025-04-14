package com.example.HospitalManagement.repo;

import com.example.HospitalManagement.entity.BillingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillingRepo extends JpaRepository<BillingEntity,Long> {
    Optional<BillingEntity> findByBillCode(Integer billId);
}
