package ru.ansvistunov.rtkit_intership_spring.service.mapper;

import lombok.AllArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;
import ru.ansvistunov.rtkit_intership_spring.entity.CurriculumEntity;
import ru.ansvistunov.rtkit_intership_spring.entity.StudentEntity;
import ru.ansvistunov.rtkit_intership_spring.repository.CurriculumRepository;
import ru.ansvistunov.rtkit_intership_spring.repository.StudentRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Утилитарный класс для маппинга сущностей GradeEntity.
 */
@Component
@AllArgsConstructor
public class GradeMapperUtil {
    private final StudentRepository studentRepository;
    private final CurriculumRepository curriculumRepository;

    /**
     * Метод для поиска студента по идентификатору.
     *
     * @param studentId Идентификатор студента.
     * @return Сущность студента.
     */
    @StudentEntityByStudentId
    public StudentEntity findStudentByGrade(int studentId) {
        return studentRepository.getReferenceById(studentId);
    }

    /**
     * Метод для поиска учебного плана (CurriculumEntity) по идентификатору.
     *
     * @param curriculumId Идентификатор учебного плана.
     * @return Сущность учебного плана (CurriculumEntity).
     */
    @CurriculumEntityByCurriculumId
    public CurriculumEntity findCurriculumByGrade(int curriculumId) {
        return curriculumRepository.getReferenceById(curriculumId);
    }

    /**
     * Квалификатор для метода поиска студента по идентификатору.
     */
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface StudentEntityByStudentId {

    }

    /**
     * Квалификатор для метода поиска учебного плана по идентификатору.
     */
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface CurriculumEntityByCurriculumId {

    }
}
