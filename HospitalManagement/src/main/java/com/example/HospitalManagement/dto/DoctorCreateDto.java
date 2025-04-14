package com.example.HospitalManagement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DoctorCreateDto {

    private Long id;

    private String doctorName;

    private String doctorCode;

    private Integer age;

    private String gender;

    private String department;
}
