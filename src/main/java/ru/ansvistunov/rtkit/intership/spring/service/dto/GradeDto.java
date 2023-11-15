package ru.ansvistunov.rtkit.intership.spring.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * DTO для представления предмета оценки студента за предмет.
 */
@Data
@NoArgsConstructor
@Schema(description = "Оценка ученика за предмет.")
public class GradeDto {
    @Schema(description = "Уникальный идентификатор оценки.")
    private Integer id;

    @NotNull(message = "Поле grade не должно быть пустым.")
    @Range(min = 1, max = 5, message = "Оценка должна находится в диапазоне от 1 до 5.")
    @Schema(description = "Оценка за предмет.")
    private Integer grade;

    @NotNull(message = "Поле studentId не должно быть пустым.")
    @Schema(description = "Уникальны идентификатор студента.")
    private Integer studentId;

    @NotNull(message = "Поле curriculumId не должно быть пустым.")
    @Schema(description = "Уникальный идентификатор предмета.")
    private Integer curriculumId;
}
