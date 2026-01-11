package com.example.HospitalManagement.service.impl;

import com.example.HospitalManagement.advice.GlobalException;
import com.example.HospitalManagement.dto.PatientCreateDto;
import com.example.HospitalManagement.dto.PatientDto;
import com.example.HospitalManagement.entity.PatientEntity;
import com.example.HospitalManagement.event.PatientEvent;
import com.example.HospitalManagement.exception.BadRequest;
import com.example.HospitalManagement.exception.ResourceException;
import com.example.HospitalManagement.repo.PatientRepo;
import com.example.HospitalManagement.service.KafkaEventProducer;
import com.example.HospitalManagement.service.PatientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Page;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepo patientRepo;
    private final ModelMapper modelMapper;
    private final GlobalException globalException;
    private final KafkaEventProducer kafkaEventProducer;


    @Override
    public PatientCreateDto createPatient(PatientDto patientDto) {

        log.info("creating patient:{} ",patientDto.getPatientName());

        PatientEntity patientEntity=modelMapper.map(patientDto,PatientEntity.class);
        patientEntity.setPatientCode(patientDto.getPatientCode());
        patientRepo.save(patientEntity);
        
        PatientCreateDto result = modelMapper.map(patientEntity,PatientCreateDto.class);
        
        // Create and publish simple Kafka event
        PatientEvent event = new PatientEvent();
        event.setPatientId(patientEntity.getId().toString());
        event.setPatientName(patientEntity.getPatientName());
        event.setPatientCode(patientEntity.getPatientCode());
        event.setAge(patientEntity.getAge());
        event.setGender(patientEntity.getGender());
        event.setEventType("CREATED");
        
        kafkaEventProducer.publishPatientCreated(event);
        
        return result;
    }

    @Override
    public List<PatientCreateDto> getAllPatient() {

        log.info("fetching all patient");

        return patientRepo.findAll().stream()
                .map(postEntity -> modelMapper.map(postEntity,PatientCreateDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePatient(String patientName) {

        log.info("delete entity of patient:{}",patientName);

        PatientEntity patientEntity = patientRepo.existsByPatientName(patientName)
                .orElseThrow(()->new BadRequest("Patient not found" + patientName));

        // Publish Kafka event before deletion
        PatientEvent event = new PatientEvent();
        event.setPatientId(patientEntity.getId().toString());
        event.setPatientName(patientEntity.getPatientName());
        event.setPatientCode(patientEntity.getPatientCode());
        event.setAge(patientEntity.getAge());
        event.setGender(patientEntity.getGender());
        event.setEventType("DELETED");
        
        kafkaEventProducer.publishPatientCreated(event);

        patientRepo.delete(patientEntity);
    }

    @Override
    public PatientCreateDto findPatientById(String patientId) {

        log.info("finding patient with patientId:{}",patientId);

        PatientEntity postEntity=patientRepo.findByPatientCode(patientId)
                .orElseThrow(()->new BadRequest("Patient not found" + patientId));
        return modelMapper.map(postEntity,PatientCreateDto.class);
    }


}
