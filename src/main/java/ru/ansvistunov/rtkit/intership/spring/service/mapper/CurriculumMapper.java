package ru.ansvistunov.rtkit.intership.spring.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ansvistunov.rtkit.intership.spring.entity.CurriculumEntity;
import ru.ansvistunov.rtkit.intership.spring.service.dto.CurriculumDto;

/**
 * Маппер для преобразования между сущностью CurriculumEntity и DTO CurriculumDto.
 */
@Mapper(componentModel = "spring", uses = {CurriculumMapperUtil.class})
public interface CurriculumMapper {

    /**
     * Преобразование из CurriculumEntity в CurriculumDto.
     *
     * @param source Исходная сущность CurriculumEntity.
     * @return DTO CurriculumDto.
     */
    @Mapping(target = "groupId", source = "source.group.id")
    CurriculumDto curriculumEntityToCurriculumDto(CurriculumEntity source);

    /**
     * Преобразование из CurriculumDto в CurriculumEntity.
     *
     * @param source Исходное DTO CurriculumDto.
     * @return Сущность CurriculumEntity.
     */
    @Mapping(target = "group", source = "groupId", qualifiedBy = CurriculumMapperUtil.StudyGroupEntityByGroupId.class)
    CurriculumEntity CurriculumDtoToCurriculumEntity(CurriculumDto source);
}
