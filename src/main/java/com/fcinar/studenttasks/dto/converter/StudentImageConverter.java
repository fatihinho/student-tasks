package com.fcinar.studenttasks.dto.converter;

import com.fcinar.studenttasks.dto.StudentImageDto;
import com.fcinar.studenttasks.model.StudentImage;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import static com.fcinar.studenttasks.utils.StudentImageUtils.decompressBytes;

@Component
public class StudentImageConverter {
    public StudentImageDto convert(@NotNull StudentImage from) {
        return new StudentImageDto(from.getId(), from.getImageName(),
                from.getType(), decompressBytes(from.getByte()), from.getStudentId());
    }
}
