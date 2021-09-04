package com.fcinar.studenttasks.repository;

import com.fcinar.studenttasks.model.StudentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IStudentImageRepository extends JpaRepository<StudentImage, UUID> {
    Optional<StudentImage> findByStudentId(UUID studentId);
}
