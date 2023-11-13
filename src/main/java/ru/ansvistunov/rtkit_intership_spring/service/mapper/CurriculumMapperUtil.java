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

/**
 * Утилитарный класс для маппинга между StudyGroupEntity и groupId.
 */
@Component
@AllArgsConstructor
public class CurriculumMapperUtil {
    private final StudyGroupRepository studyGroupRepository;

    /**
     * Метод для поиска учебной группы по идентификатору.
     *
     * @param groupId Идентификатор учебной группы.
     * @return Сущность StudyGroupEntity.
     */
    @StudyGroupEntityByGroupId
    public StudyGroupEntity findStudyGroupByStudent(int groupId) {
        return studyGroupRepository.getReferenceById(groupId);
    }

    /**
     * Квалификатор для указания MapStruct на использование данного метода при маппинге.
     */
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface StudyGroupEntityByGroupId{

    }
}
