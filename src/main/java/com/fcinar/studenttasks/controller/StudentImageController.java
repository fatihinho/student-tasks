package com.fcinar.studenttasks.controller;

import com.fcinar.studenttasks.dto.StudentImageDto;
import com.fcinar.studenttasks.service.StudentImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class StudentImageController {
    private final Logger logger = LoggerFactory.getLogger(StudentImageController.class);

    private final StudentImageService studentImageService;

    public StudentImageController(StudentImageService studentImageService) {
        this.studentImageService = studentImageService;
    }

    @GetMapping("/student-images/{studentId}")
    public ResponseEntity<StudentImageDto> getStudentImageByStudentId(@PathVariable UUID studentId) {
        try {
            StudentImageDto studentImage = studentImageService.getStudentImageByStudentId(studentId);
            return new ResponseEntity<>(studentImage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student-images")
    public ResponseEntity<List<StudentImageDto>> getAllStudentImages() {
        try {
            List<StudentImageDto> studentImages = studentImageService.getAllStudentImages();
            if (studentImages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(studentImages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/student-images/upload/{studentId}")
    public ResponseEntity<StudentImageDto> uploadStudentImageByStudentId(@PathVariable UUID studentId,
                                                                         @RequestParam("imageFile") MultipartFile file) {
        try {
            StudentImageDto studentImage = studentImageService.uploadStudentImageByStudentId(studentId, file);
            return new ResponseEntity<>(studentImage, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
