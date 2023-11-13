package ru.ansvistunov.rtkit_intership_spring.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ansvistunov.rtkit_intership_spring.entity.GradeEntity;
import ru.ansvistunov.rtkit_intership_spring.service.dto.GradeDto;
import ru.ansvistunov.rtkit_intership_spring.service.dto.UpdateGradeDto;
import ru.ansvistunov.rtkit_intership_spring.web.request.UpdateGradeRequest;

/**
 * Интерфейс-маппер для преобразования между сущностью GradeEntity и DTO (GradeDto, UpdateGradeDto).
 */
@Mapper(componentModel = "spring", uses = {GradeMapperUtil.class})
public interface GradeMapper {

    /**
     * Метод маппинга из сущности GradeEntity в DTO GradeDto.
     *
     * @param source Исходная сущность GradeEntity.
     * @return Результирующий объект DTO GradeDto.
     */
    @Mapping(target = "studentId", source = "source.student.id")
    @Mapping(target = "curriculumId", source = "source.curriculum.id")
    GradeDto gradeEntityToGradeDto(GradeEntity source);

    /**
     * Метод маппинга из DTO GradeDto в сущность GradeEntity.
     *
     * @param source Исходный объект DTO GradeDto.
     * @return Результирующая сущность GradeEntity.
     */
    @Mapping(target = "student", source = "studentId", qualifiedBy = GradeMapperUtil.StudentEntityByStudentId.class)
    @Mapping(target = "curriculum", source = "curriculumId", qualifiedBy = GradeMapperUtil.CurriculumEntityByCurriculumId.class)
    GradeEntity gradeDtoToGradeEntity(GradeDto source);

    /**
     * Метод маппинга из запроса на обновление оценки (UpdateGradeRequest) в DTO для обновления оценки (UpdateGradeDto).
     *
     * @param source Исходный запрос на обновление оценки.
     * @return Результирующий объект DTO UpdateGradeDto.
     */
    UpdateGradeDto updateGradeRequestToUpdateGradeDto(UpdateGradeRequest source);
}
