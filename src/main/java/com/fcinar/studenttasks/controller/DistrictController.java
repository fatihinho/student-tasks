package com.fcinar.studenttasks.controller;

import com.fcinar.studenttasks.dto.DistrictDto;
import com.fcinar.studenttasks.service.DistrictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DistrictController {
    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/districts")
    public ResponseEntity<List<DistrictDto>> getAllDistricts() {
        try {
            List<DistrictDto> districts = districtService.getAllDistricts();
            if (districts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(districts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/districts/code/{cityId}")
    public ResponseEntity<List<DistrictDto>> getAllDistrictsByCityId(@PathVariable Integer cityId) {
        try {
            List<DistrictDto> districts = districtService.getAllDistrictsByCityId(cityId);
            if (districts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(districts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}