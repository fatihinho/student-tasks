package com.fcinar.studenttasks.service;

import com.fcinar.studenttasks.dto.converter.CityDtoConverter;
import com.fcinar.studenttasks.model.City;
import com.fcinar.studenttasks.model.District;
import com.fcinar.studenttasks.repository.ICityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CityServiceTest {
    private ICityRepository cityRepository;
    private CityDtoConverter cityDtoConverter;
    private CityService cityService;

    @BeforeEach
    public void setup() {
        cityRepository = mock(ICityRepository.class);
        cityDtoConverter = mock(CityDtoConverter.class);
        cityService = new CityService(cityRepository, cityDtoConverter);
    }

    @Test
    public void testGetCityById_whenCityIdExists_shouldGetCity() {
        City expected = new City(1, "ADANA", List.of());
        when(cityService.findCityById(1)).thenReturn(expected);
        City actual = cityService.findCityById(1);
        assertEquals(expected, actual);
    }
}
