package com.example.HospitalManagement.controller;


import com.example.HospitalManagement.dto.DoctorCreateDto;
import com.example.HospitalManagement.dto.PatientCreateDto;
import com.example.HospitalManagement.dto.PatientDto;
import com.example.HospitalManagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/create")
    public ResponseEntity<DoctorCreateDto> createDoctor(@RequestBody DoctorCreateDto doctorCreateDto){

        DoctorCreateDto doctor=doctorService.createDoctor(doctorCreateDto);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorCreateDto> getDoctorById(@PathVariable("doctorId") String doctorId){
        DoctorCreateDto doctor= doctorService.getDoctorById(doctorId);
        return new ResponseEntity<>(doctor,HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<DoctorCreateDto>> findAllDoctor(){
        List<DoctorCreateDto> doctorCreateDtoList=doctorService.getAllDoctor();
        return ResponseEntity.ok(doctorCreateDtoList);
    }

    @DeleteMapping("/{doctorName}")
    public ResponseEntity<String> deleteDoctorByID(@PathVariable("doctorName") String doctorName){
        doctorService.deleteDOctor(doctorName);
        return ResponseEntity.ok("Doctor with name '" + doctorName + "' deleted successfully.");

    }


}
