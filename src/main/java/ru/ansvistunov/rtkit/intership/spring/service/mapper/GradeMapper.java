package ru.ansvistunov.rtkit.intership.spring.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ansvistunov.rtkit.intership.spring.entity.GradeEntity;
import ru.ansvistunov.rtkit.intership.spring.repository.CurriculumRepository;
import ru.ansvistunov.rtkit.intership.spring.repository.StudentRepository;
import ru.ansvistunov.rtkit.intership.spring.service.dto.GradeDto;
import ru.ansvistunov.rtkit.intership.spring.service.dto.UpdateGradeDto;
import ru.ansvistunov.rtkit.intership.spring.web.request.UpdateGradeRequest;

/**
 * Интерфейс-маппер для преобразования между сущностью GradeEntity и DTO (GradeDto, UpdateGradeDto).
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class GradeMapper {
    @Autowired
    protected StudentRepository studentRepository;
    protected CurriculumRepository curriculumRepository;

    /**
     * Метод маппинга из сущности GradeEntity в DTO GradeDto.
     *
     * @param source Исходная сущность GradeEntity.
     * @return Результирующий объект DTO GradeDto.
     */
    @Mapping(target = "studentId", source = "source.student.id")
    @Mapping(target = "curriculumId", source = "source.curriculum.id")
    public abstract GradeDto gradeEntityToGradeDto(GradeEntity source);

    /**
     * Метод маппинга из DTO GradeDto в сущность GradeEntity.
     *
     * @param source Исходный объект DTO GradeDto.
     * @return Результирующая сущность GradeEntity.
     */
    @Mapping(target = "student", expression = "java(studentRepository.getReferenceById(source.getStudentId()))")
    @Mapping(target = "curriculum", expression = "java(curriculumRepository.getReferenceById(source.getCurriculumId()))")
    public abstract GradeEntity gradeDtoToGradeEntity(GradeDto source);

    /**
     * Метод маппинга из запроса на обновление оценки (UpdateGradeRequest) в DTO для обновления оценки (UpdateGradeDto).
     *
     * @param source Исходный запрос на обновление оценки.
     * @return Результирующий объект DTO UpdateGradeDto.
     */
    public abstract UpdateGradeDto updateGradeRequestToUpdateGradeDto(UpdateGradeRequest source);
}
