package ru.ansvistunov.rtkit.intership.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ansvistunov.rtkit.intership.spring.service.CurriculumService;
import ru.ansvistunov.rtkit.intership.spring.service.dto.CurriculumDto;
import ru.ansvistunov.rtkit.intership.spring.web.constant.WebConstant;

import java.util.List;

/**
 * Контроллер для работы с учебными планами групп.
 */
@RestController
@RequestMapping(WebConstant.URL_VERSION + "/curriculum")
@Validated
@AllArgsConstructor
@Slf4j
@Tag(name = "Учебные планы.", description = "Предоставляет методы работы с учебными планами групп.")
public class CurriculumController {

    private final CurriculumService curriculumService;

    @GetMapping("/{curriculumId}")
    @Operation(summary = "Получение предмета.",
            description = "Получение информации о предмете по его ID.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о студенте с изменённой группой.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CurriculumDto.class)))
            })
    public CurriculumDto getCurriculumById(@PathVariable @Min(0) int curriculumId) {
        log.info("Запрос на получение учебного палана по id = {}", curriculumId);

        return curriculumService.getCurriculumById(curriculumId);
    }

    @GetMapping("/group/{groupId}")
    @Operation(summary = "Получение предметов группы.",
            description = "Получение информации о всех предметах группы по ID группы.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Список учеников и их средняя оценка.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CurriculumDto.class))))
            })
    public List<CurriculumDto> getCurriculumListByGroupId(@PathVariable @Min(0) int groupId) {
        log.info("Запрос получения учебного плана по id группы = {}", groupId);

        return curriculumService.getCurriculumsByGroupId(groupId);
    }
}
