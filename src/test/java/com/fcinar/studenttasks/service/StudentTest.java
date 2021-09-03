package com.fcinar.studenttasks.service;

import com.fcinar.studenttasks.dto.StudentDto;
import com.fcinar.studenttasks.dto.converter.StudentDtoConverter;
import com.fcinar.studenttasks.model.City;
import com.fcinar.studenttasks.model.District;
import com.fcinar.studenttasks.model.Student;
import com.fcinar.studenttasks.repository.IStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentTest {
    private IStudentRepository studentRepository;
    private StudentService studentService;
    private CityService cityService;
    private DistrictService districtService;
    private StudentDtoConverter studentDtoConverter;

    @BeforeEach
    public void setup() {
        studentRepository = mock(IStudentRepository.class);
        studentDtoConverter = mock(StudentDtoConverter.class);
        studentService = new StudentService(
                studentRepository, studentDtoConverter, cityService, districtService);
    }

    @Test
    public void testFindStudentById_whenStudentIdExists_shouldFindStudent() {
        UUID id = UUID.randomUUID();
        City city = new City(1, "ADANA");
        District district = new District(8, "KOZAN", city);
        Student expected = new Student(id, "name", "surname",
                "phone-number", city, district, "description");
        when(studentRepository.findById(id)).thenReturn(Optional.of(expected));
        Student actual = studentService.findStudentById(id);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetStudentById_whenStudentIdExists_shouldGetStudentDto() {
        UUID id = UUID.randomUUID();
        City city = new City(1, "ADANA");
        District district = new District(8, "KOZAN", city);
        Student student = new Student(id, "name", "surname",
                "phone-number", city, district, "description");
        StudentDto expected = new StudentDto(id, "name", "surname",
                "phone-number", city, district, "description");
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        when(studentDtoConverter.convert(student)).thenReturn(expected);
        StudentDto actual = studentService.getStudentById(id);
        assertEquals(expected, actual);
    }
}
