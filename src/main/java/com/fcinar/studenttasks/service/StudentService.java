package com.fcinar.studenttasks.service;

import com.fcinar.studenttasks.dto.StudentDto;
import com.fcinar.studenttasks.dto.converter.StudentDtoConverter;
import com.fcinar.studenttasks.dto.request.CreateStudentRequest;
import com.fcinar.studenttasks.dto.request.UpdateStudentRequest;
import com.fcinar.studenttasks.exception.StudentNotFoundException;
import com.fcinar.studenttasks.model.City;
import com.fcinar.studenttasks.model.District;
import com.fcinar.studenttasks.model.Student;
import com.fcinar.studenttasks.repository.IStudentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final IStudentRepository studentRepository;
    private final StudentDtoConverter studentDtoConverter;
    private final CityService cityService;
    private final DistrictService districtService;

    public StudentService(IStudentRepository studentRepository,
                          StudentDtoConverter studentDtoConverter,
                          CityService cityService,
                          DistrictService districtService) {
        this.studentRepository = studentRepository;
        this.studentDtoConverter = studentDtoConverter;
        this.cityService = cityService;
        this.districtService = districtService;
    }

    protected Student findStudentById(UUID id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student could not found by id: " + id));
    }

    public StudentDto getStudentById(UUID id) {
        Student student = findStudentById(id);
        return studentDtoConverter.convert(student);
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(studentDtoConverter::convert).collect(Collectors.toList());
    }

    public StudentDto createStudent(@NotNull CreateStudentRequest createStudentRequest) {
        City city = cityService.findCityById(createStudentRequest.getCityId());
        District district = districtService.findDistrictById(createStudentRequest.getDistrictId());
        Student student = new Student(createStudentRequest.getName(), createStudentRequest.getSurname(),
                createStudentRequest.getPhoneNumber(), city, district, createStudentRequest.getDescription());
        return studentDtoConverter.convert(studentRepository.save(student));
    }

    public StudentDto updateStudentById(UUID id, @NotNull UpdateStudentRequest updateStudentRequest) {
        City city = cityService.findCityById(updateStudentRequest.getCityId());
        District district = districtService.findDistrictById(updateStudentRequest.getDistrictId());
        Student student = findStudentById(id);
        student.setName(updateStudentRequest.getName());
        student.setSurname(updateStudentRequest.getSurname());
        student.setPhoneNumber(updateStudentRequest.getPhoneNumber());
        student.setCity(city);
        student.setDistrict(district);
        student.setDescription(updateStudentRequest.getDescription());
        return studentDtoConverter.convert(studentRepository.save(student));
    }

    public void deleteStudentById(UUID id) {
        Student student = findStudentById(id);
        studentRepository.delete(student);
    }
}
