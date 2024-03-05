package com.example.webflux.exception;

import com.example.webflux.dto.InputFailedValidationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException ex){
        System.out.println("To ControllerAdvice InputValidationHandler.class");
        var response = new InputFailedValidationResponse();
        response.setErrorCode(ex.getErrorCode());
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }


}
