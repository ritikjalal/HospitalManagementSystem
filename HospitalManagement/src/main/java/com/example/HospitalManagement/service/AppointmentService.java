package com.example.HospitalManagement.service;

import com.example.HospitalManagement.dto.AppointmentCreateDto;
import com.example.HospitalManagement.dto.AppointmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {
    List<AppointmentCreateDto> getAllAppointment();

    AppointmentCreateDto findByAppointmentId(Integer appointmentId);

    AppointmentCreateDto createAppointment(AppointmentDto appointmentDto);
}
