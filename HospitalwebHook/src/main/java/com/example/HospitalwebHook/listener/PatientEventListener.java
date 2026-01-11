package com.example.HospitalwebHook.listener;

import com.example.HospitalwebHook.event.PatientEvent;
import com.example.HospitalwebHook.service.WebhookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PatientEventListener {

    private final WebhookService webhookService;

    @KafkaListener(topics = "patient-events", groupId = "hospital-webhook-group")
    public void handlePatientEvent(@Payload PatientEvent event,
                                  @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                  @Header(KafkaHeaders.OFFSET) long offset,
                                  Acknowledgment acknowledgment) {
        
        try {
            log.info("Received patient event: {} from topic: {}, partition: {}, offset: {}", 
                    event.getEventType(), topic, partition, offset);

            // Process the event
            processPatientEvent(event);

            // Acknowledge the message
            acknowledgment.acknowledge();
            
            log.info("Successfully processed and acknowledged patient event: {}", event.getEventId());
            
        } catch (Exception e) {
            log.error("Error processing patient event: {}", event.getEventId(), e);
            // In a production environment, you might want to implement a retry mechanism
            // or send the message to a dead-letter queue
            throw e;
        }
    }

    @KafkaListener(topics = "appointment-events", groupId = "hospital-webhook-group")
    public void handleAppointmentEvent(@Payload PatientEvent event,
                                      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                      Acknowledgment acknowledgment) {
        
        try {
            log.info("Received appointment event: {} from topic: {}", event.getEventType(), topic);
            
            processPatientEvent(event);
            acknowledgment.acknowledge();
            
        } catch (Exception e) {
            log.error("Error processing appointment event: {}", event.getEventId(), e);
            throw e;
        }
    }

    @KafkaListener(topics = "billing-events", groupId = "hospital-webhook-group")
    public void handleBillingEvent(@Payload PatientEvent event,
                                  @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                  Acknowledgment acknowledgment) {
        
        try {
            log.info("Received billing event: {} from topic: {}", event.getEventType(), topic);
            
            processPatientEvent(event);
            acknowledgment.acknowledge();
            
        } catch (Exception e) {
            log.error("Error processing billing event: {}", event.getEventId(), e);
            throw e;
        }
    }

    private void processPatientEvent(PatientEvent event) {
        // Trigger webhook asynchronously
        webhookService.triggerWebhook(event);
        
        // Additional processing can be added here
        // For example: sending notifications, updating caches, etc.
        
        log.info("Processed patient event: {} for patient: {}", 
                event.getEventType(), event.getPatientId());
    }
}
