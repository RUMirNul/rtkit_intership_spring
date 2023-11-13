package ru.ansvistunov.rtkit_intership_spring.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для представления информации о студенте и его средней оценке.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о студенте и его средняя оценка.")
public class StudentAndAverageGradeDto {
    @NotNull(message = "Поле name не должно быть null.")
    @NotBlank(message = "Поле name не должно быть пустым.")
    @Schema(description = "Имя студента.")
    private String name;

    @NotNull(message = "Поле familyName не должно быть null.")
    @NotBlank(message = "Поле familyName не должно быть пустым.")
    @Schema(description = "Фамилия студента.")
    private String familyName;

    @NotNull(message = "Поле age не должно быть пустым.")
    @Schema(description = "Возраст студента.")
    private Integer age;

    @NotNull(message = "Поле groupName не должно быть пустым.")
    @Schema(description = "Название группы.")
    private Integer groupName;

    @NotNull(message = "Поле averageGrade не должно быть пустым.")
    @Schema(description = "Средняя оценка студента по всем предметам.")
    private Double averageGrade;

}
