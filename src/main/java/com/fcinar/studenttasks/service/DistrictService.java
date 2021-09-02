package com.fcinar.studenttasks.service;

import com.fcinar.studenttasks.dto.DistrictDto;
import com.fcinar.studenttasks.dto.converter.DistrictDtoConverter;
import com.fcinar.studenttasks.exception.DistrictNotFoundException;
import com.fcinar.studenttasks.model.District;
import com.fcinar.studenttasks.repository.IDistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictService {
    private final IDistrictRepository districtRepository;
    private final DistrictDtoConverter districtDtoConverter;

    public DistrictService(IDistrictRepository districtRepository,
                           DistrictDtoConverter districtDtoConverter) {
        this.districtRepository = districtRepository;
        this.districtDtoConverter = districtDtoConverter;
    }

    protected District findDistrictById(Integer id) {
        return districtRepository.findById(id)
                .orElseThrow(() -> new DistrictNotFoundException("District could not found by id: " + id));
    }

    public DistrictDto getDistrictById(Integer id) {
        District district = findDistrictById(id);
        return districtDtoConverter.convert(district);
    }

    public List<DistrictDto> getAllDistrictsByCityId(Integer cityId) {
        List<District> districts = districtRepository.findAllByCityId(cityId);
        return districts.stream().map(districtDtoConverter::convert).collect(Collectors.toList());
    }

    public List<DistrictDto> getAllDistricts() {
        List<District> districts = districtRepository.findAll();
        return districts.stream().map(districtDtoConverter::convert).collect(Collectors.toList());
    }
}
