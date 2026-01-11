package com.example.HospitalManagement.service.impl;

import com.example.HospitalManagement.dto.AppointmentCreateDto;
import com.example.HospitalManagement.dto.AppointmentDto;
import com.example.HospitalManagement.dto.BillingCreateDto;
import com.example.HospitalManagement.entity.AppointmentEntity;
import com.example.HospitalManagement.entity.BillingEntity;
import com.example.HospitalManagement.entity.DoctorEntity;
import com.example.HospitalManagement.entity.PatientEntity;
import com.example.HospitalManagement.event.PatientEvent;
import com.example.HospitalManagement.exception.BadRequest;
import com.example.HospitalManagement.exception.ResourceException;
import com.example.HospitalManagement.repo.AppointmentRepo;
import com.example.HospitalManagement.repo.DoctorRepo;
import com.example.HospitalManagement.repo.PatientRepo;
import com.example.HospitalManagement.service.AppointmentService;
import com.example.HospitalManagement.service.KafkaEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {


    private final ModelMapper modelMapper;
    private final AppointmentRepo appointmentRepo;
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final KafkaEventProducer kafkaEventProducer;


    @Override
    public List<AppointmentCreateDto> getAllAppointment() {

        log.info("fetching all appointments");

        return appointmentRepo.findAll().stream()
                .map(appointmentEntity -> modelMapper.map(appointmentEntity,AppointmentCreateDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentCreateDto findByAppointmentId(Integer appointmentId) {

        log.info("finding appointment with billId:{}",appointmentId);

        AppointmentEntity appointmentEntity=appointmentRepo.findByAppointmentCode(appointmentId)
                .orElseThrow(()->new BadRequest("Appointment not found" + appointmentId));
        return modelMapper.map(appointmentEntity,AppointmentCreateDto.class);
    }

    @Override
    public AppointmentCreateDto createAppointment(AppointmentDto appointmentDto) {

        log.info("creating appointment ");

        patientRepo.findByPatientCode(appointmentDto.getPatientCode()).orElseThrow(()->new ResourceException("patient with id not found "+appointmentDto.getPatientCode()));
        doctorRepo.findByDoctorCode(appointmentDto.getDoctorCode()).orElseThrow(()->new ResourceException("doctor with id not found "+appointmentDto.getDoctorCode()));

        AppointmentEntity appointmentEntity=modelMapper.map(appointmentDto, AppointmentEntity.class);

        appointmentEntity.setPatientCode(appointmentDto.getPatientCode());
        appointmentEntity.setDoctorCode(appointmentDto.getDoctorCode());

        appointmentRepo.save(appointmentEntity);
        
        AppointmentCreateDto result = modelMapper.map(appointmentEntity, AppointmentCreateDto.class);
        
        // Create and publish simple Kafka event
        PatientEvent event = new PatientEvent();
        event.setPatientId(appointmentEntity.getId().toString());
        event.setPatientCode(appointmentEntity.getPatientCode());
        event.setEventType("APPOINTMENT_SCHEDULED");
        
        kafkaEventProducer.publishAppointmentScheduled(event);

        return result;
    }
}
