package com.example.HospitalManagement.service;

import com.example.HospitalManagement.event.PatientEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishPatientEvent(PatientEvent.EventType eventType, String patientId, Object patientData) {
        PatientEvent event = PatientEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType(eventType)
                .patientId(patientId)
                .timestamp(java.time.LocalDateTime.now())
                .metadata(java.util.Map.of("source", "hospital-management-service"))
                .build();

        if (patientData instanceof com.example.HospitalManagement.dto.PatientCreateDto) {
            com.example.HospitalManagement.dto.PatientCreateDto patient = (com.example.HospitalManagement.dto.PatientCreateDto) patientData;
            event.setPatientCode(patient.getPatientCode());
            event.setPatientName(patient.getPatientName());
            event.setAge(patient.getAge());
            event.setGender(patient.getGender());
        }

        String topicName = getTopicForEvent(eventType);
        
        try {
            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, event.getPatientId(), event);
            
            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onSuccess(SendResult<String, Object> result) {
                    log.info("Event published successfully: {} to topic: {}", event.getEventId(), topicName);
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.error("Failed to publish event: {} to topic: {}", event.getEventId(), topicName, ex);
                }
            });
        } catch (Exception e) {
            log.error("Error publishing event to Kafka", e);
        }
    }

    private String getTopicForEvent(PatientEvent.EventType eventType) {
        switch (eventType) {
            case PATIENT_CREATED:
            case PATIENT_UPDATED:
            case PATIENT_DELETED:
                return "patient-events";
            case APPOINTMENT_SCHEDULED:
            case APPOINTMENT_CANCELLED:
                return "appointment-events";
            case BILLING_GENERATED:
                return "billing-events";
            default:
                return "hospital-events";
        }
    }
}
