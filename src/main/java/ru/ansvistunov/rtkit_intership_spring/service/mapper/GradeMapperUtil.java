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

@Component
@AllArgsConstructor
public class GradeMapperUtil {
    private final StudentRepository studentRepository;
    private final CurriculumRepository curriculumRepository;

    @StudentEntityByStudentId
    public StudentEntity findStudentByGrade(int studentId) {
        return studentRepository.getReferenceById(studentId);
    }

    @CurriculumEntityByCurriculumId
    public CurriculumEntity findCurriculumByGrade(int curriculumId) {
        return curriculumRepository.getReferenceById(curriculumId);
    }




    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface StudentEntityByStudentId {

    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface CurriculumEntityByCurriculumId {

    }
}
