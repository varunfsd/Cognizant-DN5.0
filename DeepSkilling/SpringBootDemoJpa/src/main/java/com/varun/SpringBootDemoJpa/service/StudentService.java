package com.varun.SpringBootDemoJpa.service;

import com.varun.SpringBootDemoJpa.exception.StudentAlreadyExistsException;
import com.varun.SpringBootDemoJpa.model.Student;
import com.varun.SpringBootDemoJpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public Student saveStudent(Student student) {
        if (studentRepository.existsById(student.getRegNo())) {
            throw new StudentAlreadyExistsException("Student already exists");
        }
       return studentRepository.save(student);
    }
}
