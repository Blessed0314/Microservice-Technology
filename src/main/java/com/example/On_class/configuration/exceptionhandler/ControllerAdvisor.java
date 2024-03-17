package com.example.On_class.configuration.exceptionhandler;

import com.example.On_class.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.example.On_class.configuration.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(BindException ex) {
        StringBuilder errorMessage = new StringBuilder("Validation error: ");
        for (FieldError error : ex.getFieldErrors()) {
            errorMessage.append(error.getDefaultMessage()).append("; ");
        }
        errorMessage.delete(errorMessage.length() - 2, errorMessage.length());

        ExceptionResponse response = new ExceptionResponse(
                errorMessage.toString(),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(TechnologyAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleTechnologyAlreadyExistsException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
}
