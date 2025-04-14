package com.example.HospitalManagement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class BillingCreateDto {

    private Long id;
    private Integer billCode;
    private String patientCode;
    private Integer amount;
    private String billStatus;
    private LocalDateTime createdAt;

}
