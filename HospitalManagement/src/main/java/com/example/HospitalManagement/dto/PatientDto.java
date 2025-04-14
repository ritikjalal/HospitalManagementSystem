package com.example.HospitalManagement.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {


    private String patientCode;
    private String patientName;
    private Integer age;
    private String gender;

}
