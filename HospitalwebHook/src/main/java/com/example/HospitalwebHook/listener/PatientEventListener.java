package com.example.HospitalwebHook.listener;

import com.example.HospitalwebHook.event.PatientEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PatientEventListener {

    @KafkaListener(topics = "patient-created-topic", groupId = "hospital-webhook-group")
    public void handlePatientEvent(PatientEvent event) {
        log.info("Received patient event: {} for patient: {}", 
                event.getEventType(), event.getPatientName());
        
        // Simple processing - just log the event
        System.out.println("🏥 Patient Event Received:");
        System.out.println("   Event Type: " + event.getEventType());
        System.out.println("   Patient ID: " + event.getPatientId());
        System.out.println("   Patient Name: " + event.getPatientName());
        System.out.println("   Patient Code: " + event.getPatientCode());
    }

    @KafkaListener(topics = "appointment-scheduled-topic", groupId = "hospital-webhook-group")
    public void handleAppointmentEvent(PatientEvent event) {
        log.info("Received appointment event: {} for patient: {}", 
                event.getEventType(), event.getPatientCode());
        
        // Simple processing - just log the event
        System.out.println("📅 Appointment Event Received:");
        System.out.println("   Event Type: " + event.getEventType());
        System.out.println("   Patient ID: " + event.getPatientId());
        System.out.println("   Patient Code: " + event.getPatientCode());
    }
}
