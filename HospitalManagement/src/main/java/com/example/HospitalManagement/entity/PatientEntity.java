package com.example.HospitalManagement.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "patient")
public class PatientEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientName;

    @Column(unique = true,nullable = false)
    private String patientCode;

    private Integer age;

    private String gender;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
