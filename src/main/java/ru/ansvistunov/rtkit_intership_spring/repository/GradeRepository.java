package ru.ansvistunov.rtkit_intership_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ansvistunov.rtkit_intership_spring.entity.GradeEntity;

import java.util.List;

public interface GradeRepository extends JpaRepository<GradeEntity, Integer> {
    List<GradeEntity> getAllGradeByStudentId(int studentId);
}
