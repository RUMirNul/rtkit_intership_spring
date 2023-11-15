package ru.ansvistunov.rtkit.intership.spring.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для представления учебной группы.
 */
@Data
@NoArgsConstructor
@Schema(description = "Учебная группа.")
public class StudyGroupDto {
    @Schema(description = "Уникальный идентификатор группы.")
    private Integer id;

    @NotNull(message = "Поле groupName не должно быть пустым.")
    @Schema(description = "Название группы.")
    private Integer groupName;
}
