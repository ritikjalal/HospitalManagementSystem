package com.example.HospitalwebHook.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic patientCreated() {
        return TopicBuilder.name("patient-created-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic appointmentScheduled() {
        return TopicBuilder.name("appointment-scheduled-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
