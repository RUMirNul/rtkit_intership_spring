package ru.ansvistunov.rtkit_intership_spring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ansvistunov.rtkit_intership_spring.entity.StudyGroupEntity;
import ru.ansvistunov.rtkit_intership_spring.exception.NotFoundException;
import ru.ansvistunov.rtkit_intership_spring.repository.StudyGroupRepository;
import ru.ansvistunov.rtkit_intership_spring.service.dto.StudyGroupDto;
import ru.ansvistunov.rtkit_intership_spring.service.mapper.StudyGroupMapper;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class StudyGroupService {

    private final StudyGroupRepository studyGroupRepository;
    private final StudyGroupMapper studyGroupMapper;

    public StudyGroupDto getStudyGroupByGroupId(int groupId) {
        log.info("Получен запрос на получение учебной группы по уникальному идентификатору groupId: {}", groupId);

        StudyGroupEntity studyGroupEntity = studyGroupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException(String.format("Учебная группа с id = %d не найдена.", groupId)));
        log.info("Получена StudyGroupEntity из StudyGroupRepository: {}", studyGroupEntity);

        StudyGroupDto studyGroupDto = studyGroupMapper.studyGroupEntityToStudyGroupDto(studyGroupEntity);
        log.info("Сопоставленная сущность: {}", studyGroupDto);

        return studyGroupDto;
    }

    public List<StudyGroupDto> getAllGroups() {
        log.info("Получение всех групп.");

        List<StudyGroupDto> result = studyGroupRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(studyGroupMapper::studyGroupEntityToStudyGroupDto)
                .peek(mappedStudyGroup -> log.info("Сопоставленный объект: {}", mappedStudyGroup))
                .toList();

        return result;
    }
}
