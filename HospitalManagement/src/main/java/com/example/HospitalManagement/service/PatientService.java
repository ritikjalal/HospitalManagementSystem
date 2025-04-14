package com.example.HospitalManagement.service;

import com.example.HospitalManagement.dto.PatientCreateDto;
import com.example.HospitalManagement.dto.PatientDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    PatientCreateDto createPatient(PatientDto patientDto);

    List<PatientCreateDto> getAllPatient();

    void deletePatient(String patientName);

    PatientCreateDto findPatientById(String patientId);
}
