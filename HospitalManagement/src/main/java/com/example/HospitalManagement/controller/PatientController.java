package com.example.HospitalManagement.controller;


import com.example.HospitalManagement.dto.PatientCreateDto;
import com.example.HospitalManagement.dto.PatientDto;
import com.example.HospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;


    //create patient
    @PostMapping("/create")
    public ResponseEntity<PatientCreateDto> createPatient(@RequestBody PatientDto patientDto){

        PatientCreateDto patientCreateDto=patientService.createPatient(patientDto);
        return new ResponseEntity<>(patientCreateDto, HttpStatus.CREATED);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<PatientCreateDto> getPatientById(@PathVariable("patientId") String patientId){
        PatientCreateDto patientCreateDto= patientService.findPatientById(patientId);
        return new ResponseEntity<>(patientCreateDto,HttpStatus.CREATED);
    }


    @GetMapping("/patient")
    public ResponseEntity<List<PatientCreateDto>> findAllPatient(){
       List<PatientCreateDto> patientCreateDtoList=patientService.getAllPatient();
       return ResponseEntity.ok(patientCreateDtoList);
    }

    @DeleteMapping("/{patientName}")
    public ResponseEntity<String> deleteDoctorByID(@PathVariable("patientName") String patientName){
        patientService.deletePatient(patientName);
        return ResponseEntity.ok("Doctor with name '" + patientName + "' deleted successfully.");

    }
}
