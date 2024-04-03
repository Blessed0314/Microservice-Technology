package com.example.on_class.configuration.exceptionhandler;

import com.example.on_class.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.on_class.domain.exception.RepeatCapacitiesInListException;
import com.example.on_class.domain.exception.RepeatTechnologiesInListException;
import com.example.on_class.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.example.on_class.configuration.Constants;
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
    public ResponseEntity<ExceptionResponse> handleValidationException(BindException bindException) {
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : bindException.getFieldErrors()) {
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

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(RepeatTechnologiesInListException.class)
    public ResponseEntity<ExceptionResponse> handleRepeatTechnologiesInListException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                Constants.REPEAT_TECHNOLOGY_IN_LIST_EXCEPTION_MESSAGE, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(RepeatCapacitiesInListException.class)
    public ResponseEntity<ExceptionResponse> handleRepeatCapacitiesInListException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                Constants.REPEAT_CAPACITY_IN_LIST_EXCEPTION_MESSAGE, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
}
