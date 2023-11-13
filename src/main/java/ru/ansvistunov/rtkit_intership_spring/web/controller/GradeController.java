package ru.ansvistunov.rtkit_intership_spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ansvistunov.rtkit_intership_spring.service.GradeService;
import ru.ansvistunov.rtkit_intership_spring.service.dto.GradeDto;
import ru.ansvistunov.rtkit_intership_spring.service.dto.UpdateGradeDto;
import ru.ansvistunov.rtkit_intership_spring.service.mapper.GradeMapper;
import ru.ansvistunov.rtkit_intership_spring.web.constant.WebConstant;
import ru.ansvistunov.rtkit_intership_spring.web.request.UpdateGradeRequest;

import java.util.List;

@RestController
@RequestMapping(WebConstant.URL_VERSION + "/grade")
@Validated
@AllArgsConstructor
@Slf4j
@Tag(name = "Оценки.", description = "Предоставляет методы работы с оценками учеников.")
public class GradeController {
    private final GradeService gradeService;
    private final GradeMapper gradeMapper;

    @PutMapping
    @Operation(summary = "Обновление оценки.",
            description = "Обновляет оценку по ID.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Список учеников и их средняя оценка.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = GradeDto.class)))
            })
    public GradeDto updateGrade(@Valid @RequestBody UpdateGradeRequest request) {
        log.info("Запрос обновления оценки: {}", request);

        UpdateGradeDto newGradeDto = gradeMapper.updateGradeRequestToUpdateGradeDto(request);
        log.info("Сопоставленный объект UpdateGradeDto: {}", newGradeDto);

        return gradeService.updateGrade(newGradeDto);
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Получение оценки студента.",
            description = "Возвращает все оценки студента по его ID.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Список учеников и их средняя оценка.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = GradeDto.class))))
            })
    public List<GradeDto> getStudentGradesByStudentId(@PathVariable @Min(0) int studentId) {
        log.info("Запрос получения всех оценок ученика с id = {}", studentId);

        return gradeService.getGradesByStudentId(studentId);
    }
}
