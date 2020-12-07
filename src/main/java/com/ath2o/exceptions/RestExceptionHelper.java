package com.ath2o.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHelper.class);

    @ExceptionHandler(value = { VehicleSearchSpecificException.class})
    protected ResponseEntity handleSearchVehicleException(VehicleSearchSpecificException vex) {
        LOGGER.error("Vehicle Search Specific Exception: ",vex.toString());
        return new ResponseEntity(vex.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class})
    protected ResponseEntity handleException(RuntimeException ex, WebRequest request) {
        LOGGER.error("General Exception: ",ex.getMessage());
        return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
