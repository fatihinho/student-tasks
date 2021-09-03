package com.fcinar.studenttasks.controller;

import com.fcinar.studenttasks.dto.StudentDto;
import com.fcinar.studenttasks.dto.request.CreateStudentRequest;
import com.fcinar.studenttasks.dto.request.UpdateStudentRequest;
import com.fcinar.studenttasks.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        try {
            List<StudentDto> students = studentService.getAllStudents();
            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable UUID id) {
        try {
            StudentDto student = studentService.getStudentById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/students/create")
    public ResponseEntity<StudentDto> createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        try {
            StudentDto student = studentService.createStudent(createStudentRequest);
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/students/update/{id}")
    public ResponseEntity<StudentDto> updateStudentById(@PathVariable UUID id,
                                                        @RequestBody UpdateStudentRequest updateStudentRequest) {
        try {
            StudentDto student = studentService.updateStudentById(id, updateStudentRequest);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity<HttpStatus> deleteStudentById(@PathVariable UUID id) {
        try {
            studentService.deleteStudentById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
