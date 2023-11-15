package ru.ansvistunov.rtkit.intership.spring.service.mapper;

import org.mapstruct.Mapper;
import ru.ansvistunov.rtkit.intership.spring.entity.StudyGroupEntity;
import ru.ansvistunov.rtkit.intership.spring.service.dto.StudyGroupDto;

/**
 * Интерфейс маппера для преобразования между объектами StudyGroupEntity и StudyGroupDto.
 */
@Mapper(componentModel = "spring")
public interface StudyGroupMapper {

    /**
     * Метод для преобразования объекта StudyGroupEntity в объект StudyGroupDto.
     *
     * @param entity Объект StudyGroupEntity.
     * @return Преобразованный объект StudyGroupDto.
     */
    StudyGroupDto studyGroupEntityToStudyGroupDto(StudyGroupEntity entity);

    /**
     * Метод для преобразования объекта StudyGroupDto в объект StudyGroupEntity.
     *
     * @param dto Объект StudyGroupDto.
     * @return Преобразованный объект StudyGroupEntity.
     */
    StudyGroupEntity studyGroupDtoToStudyEntity(StudyGroupDto dto);

}
