package com.jrome.plant.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
//@Getter
public class NoSuchPlantException extends RuntimeException {

//    HttpStatus status;
    public NoSuchPlantException(String message) {
        super(message);
    }
//    public NoSuchPlantException(String message, HttpStatus status) {
//        super(message);
//        this.status = status;
//    }
}
