package ru.ansvistunov.rtkit.intership.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import ru.ansvistunov.rtkit.intership.spring.service.dto.StudentDto;
import ru.ansvistunov.rtkit.intership.spring.service.mapper.StudentMapper;
import ru.ansvistunov.rtkit.intership.spring.service.StudentService;
import ru.ansvistunov.rtkit.intership.spring.service.dto.StudentAndAverageGradeDto;
import ru.ansvistunov.rtkit.intership.spring.service.dto.StudentUpdateStudyGroupDto;
import ru.ansvistunov.rtkit.intership.spring.web.constant.WebConstant;
import ru.ansvistunov.rtkit.intership.spring.web.request.StudentAddRequest;
import ru.ansvistunov.rtkit.intership.spring.web.request.StudentUpdateStudyGroupRequest;

import java.util.List;

@RestController
@RequestMapping(WebConstant.URL_VERSION + "/student")
@Validated
@AllArgsConstructor
@Slf4j
@Tag(name = "Студенты.", description = "Предоставляет методы работы с информацией о студентах и их оценках.")
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping("/average_grade/{groupNumber}")
    @Operation(summary = "Получение средней оценки каждого ученика в классе.",
            description = "Возвращает список всех учеников с их средней оценкой из заданного класса.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Список учеников и их средняя оценка.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = StudentAndAverageGradeDto.class))))
            })
    public List<StudentAndAverageGradeDto> getAverageGradeInClass(
            @PathVariable @Min(0) @Parameter(description = "Название группы") int groupNumber) {
        log.info("Запрос получения средней оценки в группе: {}", groupNumber);
        return studentService.getAverageGradeInClass(groupNumber);
    }

    @PostMapping
    @Operation(summary = "Добавление студента.",
            description = "Добавление студента в журнал.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о добавленном студенте.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = StudentDto.class)))
            })
    public StudentDto addStudent(@Valid @RequestBody StudentAddRequest request) {
        log.info("Запрос на добавление нового студента: {}", request);

        StudentDto studentDto = studentMapper.addStudentRequestToPersonDto(request);
        log.info("Сопоставленный объект: {}", studentDto);

        return studentService.addStudent(studentDto);
    }

    @PutMapping("/study_group")
    @Operation(summary = "Добавление студента в группу.",
            description = "Добавляет студента в группу по ID студента и ID группы. " +
                    "Важно: добавляет студента в группу, только если он не состоит ни в какой группе!",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о студенте с изменённой группой.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = StudentDto.class)))
            })
    public StudentDto updateStudyGroup(@Valid @RequestBody StudentUpdateStudyGroupRequest request) {
        log.info("Запрос обновления группы студента: {}", request);

        StudentUpdateStudyGroupDto mappedRequest = studentMapper.updateStudentGroupRequestToUpdateStudentGroupDto(request);
        log.info("Сопоставленный объект StudentUpdateGradeDto: {}", mappedRequest);

        return studentService.updateStudyGroup(mappedRequest);
    }
}
