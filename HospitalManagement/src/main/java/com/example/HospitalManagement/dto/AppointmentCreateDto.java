package com.example.HospitalManagement.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AppointmentCreateDto {

    private Long id;

    private String patientCode;

    private Integer appointmentCode;

    private String doctorCode;

    private String appointmentDate;


}
