package com.example.HospitalManagement.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BillingDto {

    private String patientCode;
    private Integer billCode;
    private Integer amount;
    private String billStatus;
}
