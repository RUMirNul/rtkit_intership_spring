package ru.ansvistunov.rtkit.intership.spring.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.ansvistunov.rtkit.intership.spring.exception.NotFoundException;
import ru.ansvistunov.rtkit.intership.spring.repository.GradeRepository;
import ru.ansvistunov.rtkit.intership.spring.service.dto.GradeDto;
import ru.ansvistunov.rtkit.intership.spring.service.mapper.GradeMapper;
import ru.ansvistunov.rtkit.intership.spring.entity.GradeEntity;
import ru.ansvistunov.rtkit.intership.spring.service.dto.UpdateGradeDto;

import java.util.List;
import java.util.Objects;

/**
 * Сервис для работы с оценками студентов.
 */
@Service
@AllArgsConstructor
@Validated
@Slf4j
public class GradeService {
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    /**
     * Обновление оценки студента.
     *
     * @param newGrade Новые данные об оценке.
     * @return Обновленный объект GradeDto.
     * @throws NotFoundException в случае, если оценка с указанным идентификатором не найдена.
     */
    @Transactional
    public GradeDto updateGrade(@Valid UpdateGradeDto newGrade) {
        log.info("Обновление оценки: {}", newGrade);

        GradeEntity gradeEntity = gradeRepository.findById(newGrade.getId())
                .orElseThrow(() -> new NotFoundException("Оценка с id = %d не найдена.".formatted(newGrade.getId())));
        log.info("Получена оценка из БД GradeEntity: {}", gradeEntity);

        gradeEntity.setGrade(newGrade.getNewGrade());
        GradeEntity updatedGrade = gradeRepository.save(gradeEntity);
        log.info("Обновленная оценка: {}", updatedGrade);

        GradeDto result = gradeMapper.gradeEntityToGradeDto(updatedGrade);
        log.info("Сопоставленный объект GradeDto: {}", result);

        return result;
    }


    /**
     * Получение списка оценок студента по его идентификатору.
     *
     * @param studentId Идентификатор студента.
     * @return Список объектов GradeDto, представляющих оценки студента.
     */
    public List<GradeDto> getGradesByStudentId(@Min(0) int studentId) {
        log.info("Получения оценок студента с id = {}", studentId);

        List<GradeEntity> studentGradeEntities = gradeRepository.getAllGradeByStudentId(studentId);

        List<GradeDto> studentGradesDto = studentGradeEntities
                .stream()
                .filter(Objects::nonNull)
                .map(gradeMapper::gradeEntityToGradeDto)
                .peek(mappedGrade -> log.info("Сопоставленный объект GradeDto: {}", mappedGrade))
                .toList();
        log.info("Список полученных оценок: {}", studentGradesDto);

        return studentGradesDto;
    }
}
