package com.example.HospitalManagement.service.impl;

import com.example.HospitalManagement.dto.BillingCreateDto;
import com.example.HospitalManagement.dto.BillingDto;
import com.example.HospitalManagement.dto.PatientCreateDto;
import com.example.HospitalManagement.entity.BillingEntity;
import com.example.HospitalManagement.entity.PatientEntity;
import com.example.HospitalManagement.exception.BadRequest;
import com.example.HospitalManagement.exception.ResourceException;
import com.example.HospitalManagement.repo.BillingRepo;
import com.example.HospitalManagement.repo.PatientRepo;
import com.example.HospitalManagement.service.BillingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillingServiceImpl implements BillingService {

    private final ModelMapper modelMapper;
    private final BillingRepo billingRepo;
    private final PatientRepo patientRepo;


    @Override
    public BillingCreateDto createBill(BillingDto billingDto) {

        log.info("creating bill ");

        System.out.println("Patient_code"+ billingDto.getPatientCode());

        PatientEntity patientEntity=patientRepo.findByPatientCode(billingDto.getPatientCode()).orElseThrow(()->new ResourceException("patient with id not found "+billingDto.getPatientCode()));


        BillingEntity billingEntity=modelMapper.map(billingDto, BillingEntity.class);

        billingEntity.setPatientCode(billingDto.getPatientCode());
        billingEntity.setBillCode(billingDto.getBillCode());

        billingRepo.save(billingEntity);

        return modelMapper.map(billingEntity, BillingCreateDto.class);
    }

    @Override
    public BillingCreateDto findByBillId(Integer billId) {
        log.info("finding bill with billId:{}",billId);

        BillingEntity billingEntity=billingRepo.findByBillCode(billId)
                .orElseThrow(()->new BadRequest("Patient not found" + billId));
        return modelMapper.map(billingEntity,BillingCreateDto.class);
    }

    @Override
    public List<BillingCreateDto> getAllBill() {
        log.info("fetching all bills");

        return billingRepo.findAll().stream()
                .map(billingEntity -> modelMapper.map(billingEntity,BillingCreateDto.class))
                .collect(Collectors.toList());
    }
}
