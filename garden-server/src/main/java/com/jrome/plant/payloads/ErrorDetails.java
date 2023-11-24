package com.jrome.plant.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Payload class representing details of an error response needed in the GlobalExceptionHandler.class.
 */
@AllArgsConstructor
@Data
public class ErrorDetails {

    private Date timestamp;
    private String message;

}
