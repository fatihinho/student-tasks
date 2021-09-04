package com.fcinar.studenttasks.service;

import com.fcinar.studenttasks.dto.StudentImageDto;
import com.fcinar.studenttasks.dto.converter.StudentImageConverter;
import com.fcinar.studenttasks.exception.StudentImageNotFoundException;
import com.fcinar.studenttasks.model.Student;
import com.fcinar.studenttasks.model.StudentImage;
import com.fcinar.studenttasks.repository.IStudentImageRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.fcinar.studenttasks.utils.StudentImageUtils.compressBytes;
import static com.fcinar.studenttasks.utils.StudentImageUtils.decompressBytes;

@Service
public class StudentImageService {
    private final IStudentImageRepository studentImageRepository;
    private final StudentImageConverter studentImageConverter;
    private final StudentService studentService;

    public StudentImageService(IStudentImageRepository studentImageRepository,
                               StudentImageConverter studentImageConverter,
                               StudentService studentService) {
        this.studentImageRepository = studentImageRepository;
        this.studentImageConverter = studentImageConverter;
        this.studentService = studentService;
    }

    private StudentImage findStudentImageByStudentId(UUID studentId) {
        return studentImageRepository.findByStudentId(studentId)
                .orElseThrow(() -> new StudentImageNotFoundException("Student image could not found by student id: " + studentId));
    }

    public StudentImageDto getStudentImageByStudentId(UUID studentId) {
        StudentImage studentImage = findStudentImageByStudentId(studentId);
        StudentImage decompressedImage = new StudentImage(
                studentImage.getImageName(),
                studentImage.getType(),
                decompressBytes(studentImage.getByte()),
                studentImage.getStudent());
        return studentImageConverter.convert(decompressedImage);
    }

    public List<StudentImageDto> getAllStudentImages() {
        List<StudentImage> studentImages = studentImageRepository.findAll();
        return studentImages.stream().map(studentImageConverter::convert).collect(Collectors.toList());
    }

    public StudentImageDto uploadStudentImageByStudentId(UUID studentId,
                                                         @NotNull MultipartFile file) throws IOException {
        Student student = studentService.findStudentById(studentId);
        StudentImage studentImage = new StudentImage(
                file.getOriginalFilename(),
                file.getContentType(),
                compressBytes(file.getBytes()),
                student);
        return studentImageConverter.convert(studentImageRepository.save(studentImage));
    }
}
