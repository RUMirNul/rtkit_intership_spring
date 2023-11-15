package ru.ansvistunov.rtkit.intership.spring.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ansvistunov.rtkit.intership.spring.entity.CurriculumEntity;
import ru.ansvistunov.rtkit.intership.spring.repository.StudyGroupRepository;
import ru.ansvistunov.rtkit.intership.spring.service.dto.CurriculumDto;

/**
 * Маппер для преобразования между сущностью CurriculumEntity и DTO CurriculumDto.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class CurriculumMapper {
    @Autowired
    protected StudyGroupRepository studyGroupRepository;

    /**
     * Преобразование из CurriculumEntity в CurriculumDto.
     *
     * @param source Исходная сущность CurriculumEntity.
     * @return DTO CurriculumDto.
     */
    @Mapping(target = "groupId", source = "source.group.id")
    public abstract CurriculumDto curriculumEntityToCurriculumDto(CurriculumEntity source);

    /**
     * Преобразование из CurriculumDto в CurriculumEntity.
     *
     * @param source Исходное DTO CurriculumDto.
     * @return Сущность CurriculumEntity.
     */
    @Mapping(target = "group", expression = "java(studyGroupRepository.getReferenceById(source.getGroupId()))")
    public abstract CurriculumEntity CurriculumDtoToCurriculumEntity(CurriculumDto source);
}
