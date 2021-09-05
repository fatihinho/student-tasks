package com.fcinar.studenttasks.dto.converter;

import com.fcinar.studenttasks.dto.StudentDto;
import com.fcinar.studenttasks.model.Student;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static com.fcinar.studenttasks.utils.StudentImageUtils.decompressBytes;

@Component
public class StudentDtoConverter {
    public StudentDto convert(@NotNull Student from) {
        return new StudentDto(from.getId(), from.getName(), from.getSurname(),
                from.getPhoneNumber(), from.getCity(), from.getDistrict(), from.getDescription(),
                from.getStudentImage() != null ? decompressBytes(from.getStudentImage().getByte()) : null);
    }
}
