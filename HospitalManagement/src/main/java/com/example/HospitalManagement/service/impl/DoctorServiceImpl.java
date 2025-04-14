package com.example.HospitalManagement.service.impl;


import com.example.HospitalManagement.dto.DoctorCreateDto;
import com.example.HospitalManagement.entity.DoctorEntity;
import com.example.HospitalManagement.exception.ResourceException;
import com.example.HospitalManagement.repo.DoctorRepo;
import com.example.HospitalManagement.service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepo doctorRepo;
    private final ModelMapper modelMapper;


    @Override
    public DoctorCreateDto createDoctor(DoctorCreateDto doctorCreateDto) {

        log.info("creating doctor:{} ",doctorCreateDto.getDoctorName());

        DoctorEntity doctorEntity=modelMapper.map(doctorCreateDto,DoctorEntity.class);
        doctorEntity.setDoctorCode(doctorCreateDto.getDoctorCode());
        doctorEntity=doctorRepo.save(doctorEntity);

        return modelMapper.map(doctorEntity, DoctorCreateDto.class);
    }


    @Override
    public DoctorCreateDto getDoctorById(String doctorId) {

        log.info("finding doctor with doctorId:{}",doctorId);

        DoctorEntity doctorEntity=doctorRepo.findByDoctorCode(doctorId)
                .orElseThrow(()->new ResourceException("Doctor not found" + doctorId));
        return modelMapper.map(doctorEntity,DoctorCreateDto.class);
    }

    @Override
    public List<DoctorCreateDto> getAllDoctor() {

        log.info("fetching all doctors");

        return doctorRepo.findAll().stream()
                .map(doctorEntity -> modelMapper.map(doctorEntity,DoctorCreateDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteDOctor(String doctorName) {

        log.info("deleting entity of doctor :{}",doctorName);

        DoctorEntity doctor = doctorRepo.existsByDoctorName(doctorName)
                .orElseThrow(() -> new ResourceException("Doctor not found with name: " + doctorName));

        doctorRepo.delete(doctor);

    }
}
