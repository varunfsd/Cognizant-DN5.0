package com.varun.SpringBootDemoJpa.exception;

public class StudentAlreadyExistsException extends RuntimeException {

    public StudentAlreadyExistsException() {
        super("Student already exists.");
    }

    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}