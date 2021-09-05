package com.fcinar.studenttasks.repository;

import com.fcinar.studenttasks.model.StudentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IStudentImageRepository extends JpaRepository<StudentImage, UUID> {
    Optional<StudentImage> findByStudentId(UUID studentId);

    List<StudentImage> findAllByStudentId(UUID studentId);

    @Transactional
    void deleteAllByStudentId(UUID studentId);
}
