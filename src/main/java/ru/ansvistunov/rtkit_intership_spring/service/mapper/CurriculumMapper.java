package ru.ansvistunov.rtkit_intership_spring.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ansvistunov.rtkit_intership_spring.entity.CurriculumEntity;
import ru.ansvistunov.rtkit_intership_spring.service.dto.CurriculumDto;

@Mapper(componentModel = "spring", uses = {CurriculumMapperUtil.class})
public interface CurriculumMapper {

    @Mapping(target = "groupId", source = "source.group.id")
    CurriculumDto curriculumEntityToCurriculumDto(CurriculumEntity source);

    @Mapping(target = "group", source = "groupId", qualifiedBy = CurriculumMapperUtil.StudyGroupEntityByGroupId.class)
    CurriculumEntity CurriculumDtoToCurriculumEntity(CurriculumDto source);
}
