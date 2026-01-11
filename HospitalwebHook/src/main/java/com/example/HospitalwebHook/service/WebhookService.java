package com.example.HospitalwebHook.service;

import com.example.HospitalwebHook.event.PatientEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${webhook.patient.created.url:}")
    private String patientCreatedWebhookUrl;

    @Value("${webhook.patient.deleted.url:}")
    private String patientDeletedWebhookUrl;

    @Value("${webhook.appointment.scheduled.url:}")
    private String appointmentScheduledWebhookUrl;

    @Async
    public CompletableFuture<Void> triggerWebhook(PatientEvent event) {
        try {
            String webhookUrl = getWebhookUrlForEvent(event.getEventType());
            
            if (webhookUrl != null && !webhookUrl.isEmpty()) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("X-Event-Type", event.getEventType().name());
                
                HttpEntity<PatientEvent> request = new HttpEntity<>(event, headers);
                
                log.info("Triggering webhook for event: {} to URL: {}", event.getEventType(), webhookUrl);
                
                String response = restTemplate.postForObject(webhookUrl, request, String.class);
                log.info("Webhook response: {}", response);
            } else {
                log.warn("No webhook URL configured for event type: {}", event.getEventType());
            }
        } catch (Exception e) {
            log.error("Failed to trigger webhook for event: {}", event.getEventType(), e);
        }
        
        return CompletableFuture.completedFuture(null);
    }

    private String getWebhookUrlForEvent(PatientEvent.EventType eventType) {
        switch (eventType) {
            case PATIENT_CREATED:
                return patientCreatedWebhookUrl;
            case PATIENT_DELETED:
                return patientDeletedWebhookUrl;
            case APPOINTMENT_SCHEDULED:
                return appointmentScheduledWebhookUrl;
            default:
                return null;
        }
    }
}
