package ru.ansvistunov.rtkit_intership_spring.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ansvistunov.rtkit_intership_spring.entity.StudentEntity;
import ru.ansvistunov.rtkit_intership_spring.service.dto.StudentDto;
import ru.ansvistunov.rtkit_intership_spring.service.dto.StudentUpdateStudyGroupDto;
import ru.ansvistunov.rtkit_intership_spring.web.request.StudentAddRequest;
import ru.ansvistunov.rtkit_intership_spring.web.request.StudentUpdateStudyGroupRequest;

/**
 * Интерфейс-маппер для преобразования между сущностью StudentEntity и её DTO (StudentDto),
 * а также для преобразования запросов добавления и обновления студента в соответствующие DTO.
 */
@Mapper(componentModel = "spring", uses = {StudentMapperUtil.class})
public interface StudentMapper {

    /**
     * Метод для преобразования запроса на добавление студента в DTO.
     *
     * @param source Запрос на добавление студента.
     * @return DTO студента.
     */
    StudentDto addStudentRequestToPersonDto(StudentAddRequest source);

    /**
     * Метод для преобразования сущности студента в DTO.
     *
     * @param source Сущность студента.
     * @return DTO студента.
     */
    @Mapping(target = "groupId", source = "source.group.id")
    StudentDto personEntityToPersonDto(StudentEntity source);

    /**
     * Метод для преобразования DTO студента в сущность студента.
     *
     * @param source DTO студента.
     * @return Сущность студента.
     */
    @Mapping(target = "group", source = "groupId", qualifiedBy = StudentMapperUtil.StudyGroupEntityByGroupId.class)
    StudentEntity personDtoToPersonEntity(StudentDto source);

    /**
     * Метод для преобразования запроса на обновление группы студента в DTO.
     *
     * @param source Запрос на обновление группы студента.
     * @return DTO обновления группы студента.
     */
    StudentUpdateStudyGroupDto studentUpdateStudyGroupRequestToStudentUpdateStudyGroupDto(StudentUpdateStudyGroupRequest source);
}
