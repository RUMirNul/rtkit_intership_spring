package ru.ansvistunov.rtkit.intership.spring.service;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.ansvistunov.rtkit.intership.spring.entity.CurriculumEntity;
import ru.ansvistunov.rtkit.intership.spring.exception.NotFoundException;
import ru.ansvistunov.rtkit.intership.spring.repository.CurriculumRepository;
import ru.ansvistunov.rtkit.intership.spring.service.mapper.CurriculumMapper;
import ru.ansvistunov.rtkit.intership.spring.service.dto.CurriculumDto;

import java.util.List;
import java.util.Objects;

/**
 * Сервис для работы с учебными планами.
 */
@Service
@AllArgsConstructor
@Validated
@Slf4j
public class CurriculumService {
    private final CurriculumRepository curriculumRepository;
    private final CurriculumMapper curriculumMapper;

    /**
     * Получение учебного плана по его идентификатору.
     *
     * @param curriculumId Идентификатор учебного плана.
     * @return Объект CurriculumDto, представляющий учебный план.
     * @throws NotFoundException в случае, если учебный план с заданным идентификатором не найден.
     */
    public CurriculumDto getCurriculumById(@Min(0) int curriculumId) {
        log.info("Получение учебного плана по id = {}", curriculumId);

        CurriculumEntity foundCurriculum = curriculumRepository.findById(curriculumId)
                .orElseThrow(() -> new NotFoundException("Учебный план с id = %d не найден".formatted(curriculumId)));
        log.info("Найденный учебный план: {}", foundCurriculum);

        CurriculumDto foundCurriculumDto = curriculumMapper.curriculumEntityToCurriculumDto(foundCurriculum);
        log.info("Сопоставленный объект CurriculumDto: {}", foundCurriculumDto);

        return foundCurriculumDto;
    }

    /**
     * Получение списка учебных планов по идентификатору группы.
     *
     * @param groupId Идентификатор группы.
     * @return Список объектов CurriculumDto, представляющих учебные планы группы.
     */
    public List<CurriculumDto> getCurriculumsByGroupId(@Min(0) int groupId) {
        log.info("Получение учебных планов по id группы = {}", groupId);

        List<CurriculumEntity> foundCurriculumEntities = curriculumRepository.findAllCurriculumByGroupId(groupId);
        log.info("Найденные учебные планы группы: {}", foundCurriculumEntities);

        List<CurriculumDto> foundCurriculumsDto = foundCurriculumEntities
                .stream()
                .filter(Objects::nonNull)
                .map(curriculumMapper::curriculumEntityToCurriculumDto)
                .peek(mappedCurriculum -> log.info("Сопоставленный объект CurriculumDto: {}", mappedCurriculum))
                .toList();
        log.info("Список полученных учебных групп: {}", foundCurriculumsDto);

        return foundCurriculumsDto;
    }
}
