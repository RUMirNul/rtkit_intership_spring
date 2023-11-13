package ru.ansvistunov.rtkit_intership_spring.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@Schema(description = "Предмет группы(учебный план).")
public class CurriculumDto {
    @Schema(description = "Уникальный идентификатор.")
    private Integer id;

    @NotNull(message = "Поле subjectName не должно быть null.")
    @NotBlank(message = "Поле subjectName не должно быть пустым.")
    @Length(max = 255, message = "Максимальная длина 255 символов.")
    @Schema(description = "Название предмета.")
    private String subjectName;

    @NotNull(message = "Поле groupId не должно быть пустым.")
    @Schema(description = "Уникальный идентификатор учебной группы.")
    private Integer groupId;
}
