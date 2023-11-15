package ru.ansvistunov.rtkit.intership.spring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ansvistunov.rtkit.intership.spring.entity.StudyGroupEntity;
import ru.ansvistunov.rtkit.intership.spring.exception.NotFoundException;
import ru.ansvistunov.rtkit.intership.spring.repository.StudyGroupRepository;
import ru.ansvistunov.rtkit.intership.spring.service.mapper.StudyGroupMapper;
import ru.ansvistunov.rtkit.intership.spring.service.dto.StudyGroupDto;

import java.util.List;
import java.util.Objects;

/**
 * Сервис для работы с учебными группами.
 */
@Service
@AllArgsConstructor
@Slf4j
public class StudyGroupService {

    private final StudyGroupRepository studyGroupRepository;
    private final StudyGroupMapper studyGroupMapper;

    /**
     * Получение учебной группы по её уникальному идентификатору.
     *
     * @param groupId Уникальный идентификатор учебной группы.
     * @return Объект {@link StudyGroupDto}, представляющий учебную группу.
     * @throws NotFoundException в случае, если учебная группа с указанным идентификатором не найдена.
     */
    public StudyGroupDto getStudyGroupByGroupId(int groupId) {
        log.info("Получен запрос на получение учебной группы по уникальному идентификатору groupId: {}", groupId);

        StudyGroupEntity studyGroupEntity = studyGroupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException(String.format("Учебная группа с id = %d не найдена.", groupId)));
        log.info("Получена StudyGroupEntity из StudyGroupRepository: {}", studyGroupEntity);

        StudyGroupDto studyGroupDto = studyGroupMapper.studyGroupEntityToStudyGroupDto(studyGroupEntity);
        log.info("Сопоставленная сущность: {}", studyGroupDto);

        return studyGroupDto;
    }

    /**
     * Получение списка всех учебных групп.
     *
     * @return Список объектов {@link StudyGroupDto}, представляющих учебные группы.
     */
    public List<StudyGroupDto> getAllGroups() {
        log.info("Получение всех групп.");

        List<StudyGroupDto> result = studyGroupRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(studyGroupMapper::studyGroupEntityToStudyGroupDto)
                .peek(mappedStudyGroup -> log.info("Сопоставленный объект StudyGroupDto: {}", mappedStudyGroup))
                .toList();
        log.info("Список учебных групп: {}", result);

        return result;
    }
}
