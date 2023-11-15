package ru.ansvistunov.rtkit.intership.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ansvistunov.rtkit.intership.spring.web.constant.WebConstant;
import ru.ansvistunov.rtkit.intership.spring.service.StudyGroupService;
import ru.ansvistunov.rtkit.intership.spring.service.dto.StudyGroupDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(WebConstant.URL_VERSION + "/study_group")
@Slf4j
@Tag(name = "Учебные группы.", description = "Предоставляет метода для работы с учебными группами студентов.")
public class StudyGroupController {

    private final StudyGroupService studyGroupService;

    @GetMapping
    @Operation(summary = "Возвращает список всех групп в журнале.",
            description = "Возвращает список всех групп в журнале.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о студенте с изменённой группой.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = StudyGroupDto.class))))
            })
    public List<StudyGroupDto> getAllGroups() {
        log.info("Получен запрос получения всех групп.");

        return studyGroupService.getAllGroups();
    }
}
