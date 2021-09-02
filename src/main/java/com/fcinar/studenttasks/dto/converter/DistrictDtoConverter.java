package com.fcinar.studenttasks.dto.converter;

import com.fcinar.studenttasks.dto.DistrictDto;
import com.fcinar.studenttasks.model.District;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class DistrictDtoConverter {
    public DistrictDto convert(@NotNull District from) {
        return new DistrictDto(from.getId(), from.getName(), from.getCity());
    }
}
