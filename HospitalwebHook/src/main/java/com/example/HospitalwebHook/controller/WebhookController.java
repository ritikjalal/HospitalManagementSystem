package com.example.HospitalwebHook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/webhooks")
@Slf4j
public class WebhookController {

    // In-memory storage for demo purposes
    private final Map<String, Object> receivedEvents = new ConcurrentHashMap<>();

    @PostMapping("/test")
    public ResponseEntity<String> testWebhook(@RequestBody Map<String, Object> event) {
        log.info("Received test webhook event: {}", event);
        
        String eventId = (String) event.get("eventId");
        if (eventId != null) {
            receivedEvents.put(eventId, event);
        }
        
        return ResponseEntity.ok("Webhook received successfully");
    }

    @GetMapping("/events")
    public ResponseEntity<Map<String, Object>> getReceivedEvents() {
        return ResponseEntity.ok(receivedEvents);
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<Object> getEvent(@PathVariable String eventId) {
        Object event = receivedEvents.get(eventId);
        if (event != null) {
            return ResponseEntity.ok(event);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/events")
    public ResponseEntity<String> clearEvents() {
        receivedEvents.clear();
        return ResponseEntity.ok("Events cleared");
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Webhook service is healthy");
    }
}
