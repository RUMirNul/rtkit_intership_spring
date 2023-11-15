package ru.ansvistunov.rtkit.intership.spring.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * DTO для обновления оценки за предмет.
 */
@Data
@NoArgsConstructor
public class UpdateGradeDto {
    @NotNull(message = "Поле id не должно быть пустым.")
    private Integer id;

    @NotNull(message = "Поле newGrade не должно быть пустым.")
    @Range(min = 1, max = 5, message = "Оценка должна находится в диапазоне от 1 до 5.")
    private Integer newGrade;
}
