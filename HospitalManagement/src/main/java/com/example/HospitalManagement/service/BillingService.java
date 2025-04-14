package com.example.HospitalManagement.service;

import com.example.HospitalManagement.dto.BillingCreateDto;
import com.example.HospitalManagement.dto.BillingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillingService {
    BillingCreateDto createBill(BillingDto billingDto);

    BillingCreateDto findByBillId(Integer billId);

    List<BillingCreateDto> getAllBill();
}
