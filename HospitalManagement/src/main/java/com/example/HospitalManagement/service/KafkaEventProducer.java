package com.example.HospitalManagement.service;

import com.example.HospitalManagement.event.PatientEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaEventProducer {

    private final KafkaTemplate<String, PatientEvent> kafkaTemplate;

    public void publishPatientCreated(PatientEvent event) {
        kafkaTemplate.send("patient-created-topic", event.getPatientId(), event);
        log.info("Patient created event published: {}", event.getPatientName());
    }

    public void publishAppointmentScheduled(PatientEvent event) {
        kafkaTemplate.send("appointment-scheduled-topic", event.getPatientId(), event);
        log.info("Appointment scheduled event published: {}", event.getPatientName());
    }
}
