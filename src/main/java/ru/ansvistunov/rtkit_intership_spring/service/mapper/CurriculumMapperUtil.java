package ru.ansvistunov.rtkit_intership_spring.service.mapper;

import lombok.AllArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;
import ru.ansvistunov.rtkit_intership_spring.entity.StudyGroupEntity;
import ru.ansvistunov.rtkit_intership_spring.repository.StudyGroupRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@AllArgsConstructor
public class CurriculumMapperUtil {
    private final StudyGroupRepository studyGroupRepository;

    @StudyGroupEntityByGroupId
    public StudyGroupEntity findStudyGroupByStudent(int groupId) {
        return studyGroupRepository.getReferenceById(groupId);
    }


    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface StudyGroupEntityByGroupId{

    }
}
