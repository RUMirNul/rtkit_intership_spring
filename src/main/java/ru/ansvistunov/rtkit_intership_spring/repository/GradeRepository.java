package ru.ansvistunov.rtkit_intership_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ansvistunov.rtkit_intership_spring.entity.GradeEntity;

import java.util.List;

/**
 * Репозиторий для работы с оценками студентов.
 */
public interface GradeRepository extends JpaRepository<GradeEntity, Integer> {

    /**
     * Находит все оценки для указанного студента.
     *
     * @param studentId Идентификатор студента.
     * @return Список оценок для указанного студента.
     */
    List<GradeEntity> getAllGradeByStudentId(int studentId);
}
