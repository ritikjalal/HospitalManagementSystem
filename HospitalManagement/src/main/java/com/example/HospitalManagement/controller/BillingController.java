package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.dto.BillingCreateDto;
import com.example.HospitalManagement.dto.BillingDto;
import com.example.HospitalManagement.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bill")
public class BillingController {

    private final BillingService billingService;

    @PostMapping("/create")
    public ResponseEntity<BillingCreateDto> createBill(@RequestBody BillingDto billingDto){

        BillingCreateDto billingCreateDto=billingService.createBill(billingDto);
        return new ResponseEntity<>(billingCreateDto, HttpStatus.CREATED);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<BillingCreateDto> getBillById(@PathVariable("billId") Integer billId){
        BillingCreateDto billingCreateDto= billingService.findByBillId(billId);
        return new ResponseEntity<>(billingCreateDto,HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<BillingCreateDto>> findAllBill(){
        List<BillingCreateDto> billingCreateDtos=billingService.getAllBill();
        return ResponseEntity.ok(billingCreateDtos);
    }


}
