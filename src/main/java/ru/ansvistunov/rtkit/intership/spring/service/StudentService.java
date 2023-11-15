package ru.ansvistunov.rtkit.intership.spring.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.ansvistunov.rtkit.intership.spring.entity.StudyGroupEntity;
import ru.ansvistunov.rtkit.intership.spring.exception.InvalidGroupChangeException;
import ru.ansvistunov.rtkit.intership.spring.service.dto.StudentAndAverageGradeDto;
import ru.ansvistunov.rtkit.intership.spring.service.dto.StudentDto;
import ru.ansvistunov.rtkit.intership.spring.service.dto.StudentUpdateStudyGroupDto;
import ru.ansvistunov.rtkit.intership.spring.service.mapper.StudentMapper;
import ru.ansvistunov.rtkit.intership.spring.entity.StudentEntity;
import ru.ansvistunov.rtkit.intership.spring.exception.NotFoundException;
import ru.ansvistunov.rtkit.intership.spring.repository.StudentRepository;
import ru.ansvistunov.rtkit.intership.spring.service.dto.StudyGroupDto;
import ru.ansvistunov.rtkit.intership.spring.service.mapper.StudyGroupMapper;

import java.util.List;

/**
 * Сервис для работы со студентами.
 */
@Service
@AllArgsConstructor
@Validated
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudyGroupService studyGroupService;
    private final StudentMapper studentMapper;
    private final StudyGroupMapper studyGroupMapper;


    /**
     * Получение средней оценки в указанной группе.
     *
     * @param groupNumber Номер группы.
     * @return Список объектов {@link StudentAndAverageGradeDto}, представляющих среднюю оценку студента в группе.
     */
    @Transactional
    public List<StudentAndAverageGradeDto> getAverageGradeInClass(@Min(0) int groupNumber) {
        log.info("Получение средней оценки в группе: {}", groupNumber);

        List<StudentAndAverageGradeDto> result = studentRepository.findAverageGradeInClass(groupNumber);
        log.info("В группе: {} найдено учеников: {}", groupNumber, result.size());

        return result;
    }

    /**
     * Добавление нового студента.
     *
     * @param newStudent Новые данные о студенте.
     * @return Объект {@link StudentDto} представляющий добавленного студента.
     */
    @Transactional
    public StudentDto addStudent(@Valid StudentDto newStudent) {
        log.info("Запрос добавления нового студента: {}", newStudent);

        StudentEntity newStudentEntity = studentMapper.personDtoToPersonEntity(newStudent);
        log.info("Сопоставленный объект StudentEntity: {}", newStudentEntity);

        StudentEntity addedStudentEntity = studentRepository.save(newStudentEntity);
        log.info("Сохраненные данные студента в БД: {}", addedStudentEntity);

        StudentDto addedStudentDto = studentMapper.personEntityToPersonDto(addedStudentEntity);
        log.info("Сопоставленный объект: {}", addedStudentDto);

        return addedStudentDto;
    }

    /**
     * Обновление группы студента.
     *
     * @param studentUpdateStudyGroupDto Данные для обновления группы студента.
     * @return Обновленный объект {@link StudentDto}.
     * @throws NotFoundException           в случае, если студент с указанным идентификатором не найден.
     * @throws InvalidGroupChangeException в случае, если студент уже состоит в группе.
     */
    @Transactional
    public StudentDto updateStudyGroup(@Valid StudentUpdateStudyGroupDto studentUpdateStudyGroupDto) {
        log.info("Запрос обновления группы студента: {}", studentUpdateStudyGroupDto);

        StudentEntity foundStudentEntity = studentRepository.findById(studentUpdateStudyGroupDto.getStudentId())
                .orElseThrow(() -> new NotFoundException(String.format("Студент с id = %d не найден", studentUpdateStudyGroupDto.getStudentId())));
        log.info("По id = {}, найден студент: {}", studentUpdateStudyGroupDto.getStudentId(), foundStudentEntity);

        if (foundStudentEntity.getGroup() != null) {
            log.info("Нельзя поместить студента в группу так как он уже в группе: {}", foundStudentEntity.getGroup());
            throw new InvalidGroupChangeException("Нельзя изменить группу так как студент уже находится в группе.");
        }

        StudyGroupDto studyGroupDto = studyGroupService.getStudyGroupByGroupId(studentUpdateStudyGroupDto.getGroupId());
        log.info("Найдена группа: {}", studyGroupDto);

        StudyGroupEntity studyGroupEntity = studyGroupMapper.studyGroupDtoToStudyEntity(studyGroupDto);
        log.info("Сопоставленная сущность StudyGroupEntity: {}", studyGroupEntity);

        foundStudentEntity.setGroup(studyGroupEntity);

        StudentEntity updatedStudent = studentRepository.save(foundStudentEntity);
        log.info("Обновленная информация о студенте: {}", updatedStudent);

        StudentDto updatedStudentDto = studentMapper.personEntityToPersonDto(updatedStudent);
        log.info("Сопоставленная сущность StudentDto: {}", updatedStudentDto);

        return updatedStudentDto;
    }
}
