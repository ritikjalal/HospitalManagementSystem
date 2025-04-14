package com.example.HospitalManagement.repo;

import com.example.HospitalManagement.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<PatientEntity,Long> {
    Optional<PatientEntity> existsByPatientName(String patientName);

    Optional<PatientEntity> findByPatientCode(String patientCode);

}
