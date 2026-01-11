package com.example.HospitalManagement.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientEvent {
    private String eventId;
    private EventType eventType;
    private String patientId;
    private String patientCode;
    private String patientName;
    private Integer age;
    private String gender;
    private LocalDateTime timestamp;
    private Map<String, Object> metadata;
    
    public enum EventType {
        PATIENT_CREATED,
        PATIENT_UPDATED,
        PATIENT_DELETED,
        APPOINTMENT_SCHEDULED,
        APPOINTMENT_CANCELLED,
        BILLING_GENERATED
    }
}
