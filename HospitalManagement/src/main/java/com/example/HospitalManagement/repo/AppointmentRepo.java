package com.example.HospitalManagement.repo;

import com.example.HospitalManagement.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepo extends JpaRepository<AppointmentEntity,Long> {
    Optional<AppointmentEntity> findByAppointmentCode(Integer appointmentId);
}
