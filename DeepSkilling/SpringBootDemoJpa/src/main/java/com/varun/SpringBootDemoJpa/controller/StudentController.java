package com.varun.SpringBootDemoJpa.controller;

import com.varun.SpringBootDemoJpa.dto.ApiResponse;
import com.varun.SpringBootDemoJpa.model.Student;
import com.varun.SpringBootDemoJpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping
    public ResponseEntity<ApiResponse<Student>> saveStudent(
            @RequestBody Student student) {

        Student savedStudent = studentService.saveStudent(student);

        ApiResponse<Student> response = new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                true,
                "Student created successfully.",
                savedStudent
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
