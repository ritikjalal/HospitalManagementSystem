package com.example.HospitalManagement.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "billing")
public class BillingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer billCode;


    private String patientCode;

    private Integer amount;

    private String billStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
