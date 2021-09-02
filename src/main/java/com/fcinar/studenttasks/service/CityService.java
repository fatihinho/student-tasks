package com.fcinar.studenttasks.service;

import com.fcinar.studenttasks.dto.CityDto;
import com.fcinar.studenttasks.dto.converter.CityDtoConverter;
import com.fcinar.studenttasks.exception.CityNotFoundException;
import com.fcinar.studenttasks.model.City;
import com.fcinar.studenttasks.repository.ICityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final ICityRepository cityRepository;
    private final CityDtoConverter cityDtoConverter;

    public CityService(ICityRepository cityRepository,
                       CityDtoConverter cityDtoConverter) {
        this.cityRepository = cityRepository;
        this.cityDtoConverter = cityDtoConverter;
    }

    protected City findCityById(Integer id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City could not found by id: " + id));
    }

    public CityDto getCityById(Integer id) {
        City city = findCityById(id);
        return cityDtoConverter.convert(city);
    }

    public List<CityDto> getAllCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream().map(cityDtoConverter::convert).collect(Collectors.toList());
    }
}
