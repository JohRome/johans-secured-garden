package com.jrome.plant.exceptions;

import com.jrome.plant.payloads.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchPlantException.class) ResponseEntity<ErrorDetails> throwPlantNotFoundException(
            NoSuchPlantException e) {

        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
