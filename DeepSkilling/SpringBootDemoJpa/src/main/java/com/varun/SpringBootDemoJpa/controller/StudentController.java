package com.varun.SpringBootDemoJpa.controller;

import com.varun.SpringBootDemoJpa.dto.ApiResponse;
import com.varun.SpringBootDemoJpa.model.Student;
import com.varun.SpringBootDemoJpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>>  getStudents(){
        List<Student> retrievedStudents= studentService.getStudents();
        ApiResponse<List<Student>> response = new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                true,
                "Students retrieved successfully.",
                retrievedStudents
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{regNo}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable int regNo){
        ApiResponse<Student> response=new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                true,
                "Student retrieved successfully.",
                studentService.getStudentById(regNo)
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
