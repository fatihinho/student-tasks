package com.fcinar.studenttasks.dto.converter;

import com.fcinar.studenttasks.dto.StudentImageDto;
import com.fcinar.studenttasks.model.StudentImage;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class StudentImageConverter {
    public StudentImageDto convert(@NotNull StudentImage from) {
        return new StudentImageDto(from.getId(), from.getImageName(), from.getType(), from.getByte(), from.getStudent());
    }
}
