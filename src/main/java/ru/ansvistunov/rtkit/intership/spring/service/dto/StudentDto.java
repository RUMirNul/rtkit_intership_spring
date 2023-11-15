package ru.ansvistunov.rtkit.intership.spring.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * DTO для представления информации о студенте.
 */
@Data
@NoArgsConstructor
@Schema(description = "Информация о студенте.")
public class StudentDto {
    @Schema(description = "Уникальный идентификатор студента.")
    private Integer id;

    @NotNull(message = "Поле name не должно быть null.")
    @NotBlank(message = "Поле name не должно быть пустым.")
    @Length(max = 255, message = "Максимальная длина 255 символов.")
    @Schema(description = "Имя студента.")
    private String name;

    @NotNull(message = "Поле familyName не должно быть null.")
    @NotBlank(message = "Поле familyName не должно быть пустым.")
    @Length(max = 255, message = "Максимальная длина 255 символов.")
    @Schema(description = "Фамилия студента.")
    private String familyName;

    @NotNull(message = "Поле age не должно быть пустым.")
    @Schema(description = "Возраст студента.")
    private Integer age;
    @Schema(description = "Уникальный идентификатор группы.")
    private Integer groupId;
}
