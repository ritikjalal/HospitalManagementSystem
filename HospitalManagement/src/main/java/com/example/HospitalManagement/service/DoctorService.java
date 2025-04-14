package com.example.HospitalManagement.service;

import com.example.HospitalManagement.dto.DoctorCreateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {
    DoctorCreateDto createDoctor(DoctorCreateDto doctorCreateDto);

    DoctorCreateDto getDoctorById(String doctorId);

    List<DoctorCreateDto> getAllDoctor();

    void deleteDOctor(String doctorName);
}
