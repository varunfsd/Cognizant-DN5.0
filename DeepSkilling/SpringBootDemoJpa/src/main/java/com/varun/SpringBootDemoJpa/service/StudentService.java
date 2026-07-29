package com.varun.SpringBootDemoJpa.service;

import com.varun.SpringBootDemoJpa.dto.UpdateStudentRequest;
import com.varun.SpringBootDemoJpa.exception.ResourceNotFoundException;
import com.varun.SpringBootDemoJpa.exception.StudentAlreadyExistsException;
import com.varun.SpringBootDemoJpa.model.Student;
import com.varun.SpringBootDemoJpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        if (studentRepository.existsById(student.getRegNo())) {
            throw new StudentAlreadyExistsException("Student already exists");
        }
       return studentRepository.save(student);
    }

    public List<Student> getStudents() {
        List<Student> students=studentRepository.findAll();
        if(students.isEmpty()){
            throw new ResourceNotFoundException("No students found.");
        }
        return students;
    }

    public Student getStudentById(int regNo) {
        return studentRepository.findById(regNo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with registration number: " + regNo));
    }


    public Student updateStudent(int regNo, UpdateStudentRequest request) {
        Student existingStudent = studentRepository.findById(regNo)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with Reg No: " + regNo));
        if(request.getFullName()!=null){
            existingStudent.setFullName(request.getFullName());
        }
        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(int regNo) {
        Student existingStudent=studentRepository.findById(regNo).orElseThrow(()->
                new ResourceNotFoundException("Student not found with registration number: " + regNo));
        studentRepository.delete(existingStudent);
    }
}
