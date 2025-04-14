package com.example.HospitalManagement.dto;


import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientCreateDto {

    private Long id;
    private String patientName;
    private String patientCode;
    private Integer age;
    private String gender;
    private LocalDateTime createdAt;
}
