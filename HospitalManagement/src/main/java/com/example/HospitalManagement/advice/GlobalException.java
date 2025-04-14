package com.example.HospitalManagement.advice;

import com.example.HospitalManagement.exception.BadRequest;
import com.example.HospitalManagement.exception.ResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<ApiError> handleException(ResourceException resourceException){

        ApiError apierror=new ApiError(resourceException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apierror,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequest badRequestException){

        ApiError apierror=new ApiError(badRequestException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apierror,HttpStatus.BAD_REQUEST);

    }

}
