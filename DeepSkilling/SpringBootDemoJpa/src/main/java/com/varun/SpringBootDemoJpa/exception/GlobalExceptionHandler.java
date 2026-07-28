package com.varun.SpringBootDemoJpa.exception;

import com.varun.SpringBootDemoJpa.dto.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles StudentAlreadyExistsException
    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleStudentAlreadyExistsException(
            StudentAlreadyExistsException ex) {

        ApiResponse<Object> response = new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                false,
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDuplicateKey(
            DataIntegrityViolationException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Student already exists.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal Server Error");
    }
}