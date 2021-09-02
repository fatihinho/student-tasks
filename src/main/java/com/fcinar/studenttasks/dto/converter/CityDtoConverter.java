package com.fcinar.studenttasks.dto.converter;

import com.fcinar.studenttasks.dto.CityDto;
import com.fcinar.studenttasks.model.City;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class CityDtoConverter {
    public CityDto convert(@NotNull City from) {
        return new CityDto(from.getId(), from.getName(), from.getDistricts());
    }
}
