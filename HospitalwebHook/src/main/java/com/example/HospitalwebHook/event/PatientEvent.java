package com.example.HospitalwebHook.event;

import lombok.Data;

@Data
public class PatientEvent {
    private String patientId;
    private String patientName;
    private String patientCode;
    private Integer age;
    private String gender;
    private String eventType; // "CREATED", "DELETED", "APPOINTMENT_SCHEDULED"
}
