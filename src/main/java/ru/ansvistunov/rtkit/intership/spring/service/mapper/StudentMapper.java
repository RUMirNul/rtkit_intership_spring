package ru.ansvistunov.rtkit.intership.spring.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ansvistunov.rtkit.intership.spring.entity.StudentEntity;
import ru.ansvistunov.rtkit.intership.spring.entity.StudyGroupEntity;
import ru.ansvistunov.rtkit.intership.spring.exception.NotFoundException;
import ru.ansvistunov.rtkit.intership.spring.repository.StudyGroupRepository;
import ru.ansvistunov.rtkit.intership.spring.service.dto.StudentDto;
import ru.ansvistunov.rtkit.intership.spring.service.dto.StudentUpdateStudyGroupDto;
import ru.ansvistunov.rtkit.intership.spring.web.request.StudentAddRequest;
import ru.ansvistunov.rtkit.intership.spring.web.request.StudentUpdateStudyGroupRequest;

/**
 * Интерфейс-маппер для преобразования между сущностью StudentEntity и её DTO (StudentDto),
 * а также для преобразования запросов добавления и обновления студента в соответствующие DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class StudentMapper {
    @Autowired
    protected StudyGroupRepository studyGroupRepository;

    /**
     * Метод для преобразования запроса на добавление студента в DTO.
     *
     * @param source Запрос на добавление студента.
     * @return DTO студента.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "groupId", ignore = true)
    public abstract StudentDto addStudentRequestToPersonDto(StudentAddRequest source);

    /**
     * Метод для преобразования сущности студента в DTO.
     *
     * @param source Сущность студента.
     * @return DTO студента.
     */
    @Mapping(target = "groupId", source = "source.group.id")
    public abstract StudentDto personEntityToPersonDto(StudentEntity source);

    /**
     * Метод для преобразования DTO студента в сущность студента.
     *
     * @param source DTO студента.
     * @return Сущность студента.
     */
    @Mapping(target = "grades", ignore = true)
    @Mapping(target = "group", expression = "java(getGroupById(source.getGroupId()))")
    public abstract StudentEntity personDtoToPersonEntity(StudentDto source);

    /**
     * Метод для преобразования запроса на обновление группы студента в DTO.
     *
     * @param source Запрос на обновление группы студента.
     * @return DTO обновления группы студента.
     */
    public abstract StudentUpdateStudyGroupDto updateStudentGroupRequestToUpdateStudentGroupDto(StudentUpdateStudyGroupRequest source);

    /**
     * Получение сущности учебной группы по уникальному идетификатору.
     *
     * @param id уникальный идентификатор.
     * @return Сущность группы или null, если уникальный идентификатор = null.
     */
    protected StudyGroupEntity getGroupById(Integer id) {
        if (id == null) return null;

        return studyGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Группы с id = %d не существует".formatted(id)));
    }
}
