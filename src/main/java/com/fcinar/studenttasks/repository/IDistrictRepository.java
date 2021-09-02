package com.fcinar.studenttasks.repository;

import com.fcinar.studenttasks.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDistrictRepository extends JpaRepository<District, Integer> {
    List<District> findAllByCityId(Integer cityId);
}
