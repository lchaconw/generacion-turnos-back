package com.reservas;

import com.reservas.exceptions.CustomBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<?> handleCustomBadRequestException(CustomBadRequestException ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("error", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}