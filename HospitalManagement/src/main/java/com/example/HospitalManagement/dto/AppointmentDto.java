package com.example.HospitalManagement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AppointmentDto {


    private String patientCode;

    private Integer appointmentCode;

    private String doctorCode;

    private String appointmentDate;
}
