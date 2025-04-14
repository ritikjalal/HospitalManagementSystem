package com.example.HospitalManagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class WebHookService {


    private final RestTemplate restTemplate;


    public String sendWebHook(String payload, Map<String,Object> map){

       String status= restTemplate.postForObject(payload,map,String.class);
      return status;

    }




}
