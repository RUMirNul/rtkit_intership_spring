package ru.ansvistunov.rtkit_intership_spring.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.ansvistunov.rtkit_intership_spring.entity.StudentEntity;
import ru.ansvistunov.rtkit_intership_spring.entity.StudyGroupEntity;
import ru.ansvistunov.rtkit_intership_spring.exception.InvalidGroupChangeException;
import ru.ansvistunov.rtkit_intership_spring.exception.NotFoundException;
import ru.ansvistunov.rtkit_intership_spring.repository.StudentRepository;
import ru.ansvistunov.rtkit_intership_spring.repository.StudyGroupRepository;
import ru.ansvistunov.rtkit_intership_spring.service.dto.*;
import ru.ansvistunov.rtkit_intership_spring.service.mapper.StudentMapper;
import ru.ansvistunov.rtkit_intership_spring.service.mapper.StudyGroupMapper;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Validated
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudyGroupService studyGroupService;
    private final StudentMapper studentMapper;


    @Transactional
    public List<StudentAndAverageGradeDto> getAverageGradeInClass(int groupNumber) {
        log.info("Получение средней оценки в группе: {}", groupNumber);

        List<StudentAndAverageGradeDto> result = studentRepository.findAverageGradeInClass(groupNumber);
        log.info("В группе: {} найдено учеников: {}", groupNumber, result.size());

        return result;
    }

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

        StudentDto foundStudentDto = studentMapper.personEntityToPersonDto(foundStudentEntity);
        log.info("Сопоставленная сущность StudentDto: {}", foundStudentDto);

        foundStudentDto.setGroupId(studyGroupDto.getId());

        StudentEntity studentEntity = studentMapper.personDtoToPersonEntity(foundStudentDto);
        log.info("Сопоставленная сущность StudentEntity: {}", studentEntity);

        StudentEntity updatedStudent = studentRepository.save(studentEntity);
        log.info("Обновленная информация о студенте: {}", updatedStudent);

        StudentDto updatedStudentDto = studentMapper.personEntityToPersonDto(updatedStudent);
        log.info("Сопоставленная сущность StudentDto: {}", updatedStudentDto);

        return updatedStudentDto;
    }
}
