package com.example.HospitalManagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer appointmentCode;

    private String patientCode;

    private String doctorCode;

    private String appointmentDate;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
