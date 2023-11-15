package ru.ansvistunov.rtkit.intership.spring.service.mapper;

import lombok.AllArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;
import ru.ansvistunov.rtkit.intership.spring.entity.StudyGroupEntity;
import ru.ansvistunov.rtkit.intership.spring.repository.StudyGroupRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Утилитарный класс для маппера студента (StudentMapper).
 * Содержит методы для поиска учебной группы студента по идентификатору и соответствующий квалификатор.
 */
@Component
@AllArgsConstructor
public class StudentMapperUtil {

    private final StudyGroupRepository studyGroupRepository;

    /**
     * Метод для поиска учебной группы студента по идентификатору.
     *
     * @param groupId Идентификатор учебной группы.
     * @return Найденная учебная группа.
     */
    @StudyGroupEntityByGroupId
    public StudyGroupEntity findStudyGroupByStudent(int groupId) {
        return studyGroupRepository.getReferenceById(groupId);
    }

    /**
     * Квалификатор для использования метода findStudyGroupByStudent в маппере.
     */
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface StudyGroupEntityByGroupId {

    }
}