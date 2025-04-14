package com.example.HospitalwebHook;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@Slf4j
public class WebHookController {


    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebHook(@RequestBody Map<String,Object> payload
                                               ){
        log.info("Payload is recevied",payload);
        return new ResponseEntity<>("Webhook processed",HttpStatus.CREATED);

    }

}
