package com.example.HospitalManagement.exception;

public class BadRequest extends RuntimeException{
    public BadRequest(String mssg) {
        super(mssg);
    }
}
