package ru.ansvistunov.rtkit_intership_spring.service.mapper;

import org.mapstruct.Mapper;
import ru.ansvistunov.rtkit_intership_spring.service.dto.StudyGroupDto;
import ru.ansvistunov.rtkit_intership_spring.entity.StudyGroupEntity;

@Mapper(componentModel = "spring")
public interface StudyGroupMapper {

    StudyGroupDto studyGroupEntityToStudyGroupDto(StudyGroupEntity entity);

    StudyGroupEntity studyGroupDtoToStudyEntity(StudyGroupDto dto);

}
