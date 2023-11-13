package ru.ansvistunov.rtkit_intership_spring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ansvistunov.rtkit_intership_spring.entity.CurriculumEntity;
import ru.ansvistunov.rtkit_intership_spring.exception.NotFoundException;
import ru.ansvistunov.rtkit_intership_spring.repository.CurriculumRepository;
import ru.ansvistunov.rtkit_intership_spring.service.dto.CurriculumDto;
import ru.ansvistunov.rtkit_intership_spring.service.mapper.CurriculumMapper;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class CurriculumService {
    private final CurriculumRepository curriculumRepository;
    private final CurriculumMapper curriculumMapper;


    public CurriculumDto getCurriculumById(int curriculumId) {
        log.info("Получение учебного плана по id = {}", curriculumId);

        CurriculumEntity foundCurriculum = curriculumRepository.findById(curriculumId)
                .orElseThrow(() -> new NotFoundException("Учебный план с id = %d не найден".formatted(curriculumId)));
        log.info("Найденный учебный план: {}", foundCurriculum);

        CurriculumDto foundCurriculumDto = curriculumMapper.curriculumEntityToCurriculumDto(foundCurriculum);
        log.info("Сопоставленный объект CurriculumDto: {}", foundCurriculumDto);

        return foundCurriculumDto;
    }

    public List<CurriculumDto> getCurriculumsByGroupId(int groupId) {
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
