package com.example.HospitalManagement.controller;


import com.example.HospitalManagement.dto.AppointmentCreateDto;
import com.example.HospitalManagement.dto.AppointmentDto;
import com.example.HospitalManagement.dto.BillingCreateDto;
import com.example.HospitalManagement.dto.BillingDto;
import com.example.HospitalManagement.service.AppointmentService;
import com.example.HospitalManagement.service.impl.WebHookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
@Slf4j
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final WebHookService webHookService;

    @PostMapping("/create")
    public ResponseEntity<AppointmentCreateDto> createAppointment(@RequestBody AppointmentDto appointmentDto){

       AppointmentCreateDto appointmentCreateDto=appointmentService.createAppointment(appointmentDto);

       Map<String,Object> payload=new HashMap<>();
       payload.put("appointmentCode",appointmentCreateDto.getAppointmentCode());
        payload.put("patientCode",appointmentCreateDto.getPatientCode());
        payload.put("doctorCode",appointmentCreateDto.getDoctorCode());
        payload.put("appointmentDate",appointmentCreateDto.getAppointmentDate());

        String webhookUrl="http://localhost:8081/webhook";
        String res=webHookService.sendWebHook(webhookUrl,payload);
        log.info("Appointment created",res);

        return new ResponseEntity<>(appointmentCreateDto, HttpStatus.CREATED);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentCreateDto> getAppointmentById(@PathVariable("appointmentId") Integer appointmentId){
        AppointmentCreateDto appointmentCreateDto= appointmentService.findByAppointmentId(appointmentId);
        return new ResponseEntity<>(appointmentCreateDto,HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<AppointmentCreateDto>> findAllAppointment(){
        List<AppointmentCreateDto> billingCreateDtos=appointmentService.getAllAppointment();
        return ResponseEntity.ok(billingCreateDtos);
    }





}
