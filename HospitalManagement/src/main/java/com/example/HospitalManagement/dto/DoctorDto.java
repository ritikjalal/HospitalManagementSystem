package com.example.HospitalManagement.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DoctorDto {

    private String doctorCode;

    private String doctorName;

    private Integer age;

    private String gender;

    private String department;

}
