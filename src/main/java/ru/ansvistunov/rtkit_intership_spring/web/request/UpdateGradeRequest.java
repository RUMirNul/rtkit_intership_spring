package ru.ansvistunov.rtkit_intership_spring.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * Запрос для обновления оценки.
 */
@Data
@NoArgsConstructor
@Schema(description = "Запрос обновления оценки.")
public class UpdateGradeRequest {
    @NotNull(message = "Поле id не может быть пустым.")
    @Schema(description = "Уникальный идентификатор оценки.")
    private Integer id;

    @NotNull(message = "Поле newGrade не может быть пустым.")
    @Range(min = 1, max = 5, message = "Оценка должна находится в диапазоне от 1 до 5.")
    @Schema(description = "Новая оценка.")
    private Integer newGrade;
}
