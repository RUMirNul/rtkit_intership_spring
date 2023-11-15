package ru.ansvistunov.rtkit.intership.spring.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * Запрос для создания записи о студенте.
 */
@Data
@NoArgsConstructor
@Schema(description = "Запрос для создания записи о студенте.")
public class StudentAddRequest {
    @NotNull(message = "Поле name не должно быть null.")
    @NotBlank(message = "Поле name не должно быть пустым.")
    @Length(max = 255, message = "Максимальная длина 255 символов.")
    @Schema(description = "Имя студента.")
    private String name;

    @NotNull(message = "Поле familyName не должна быть null.")
    @NotBlank(message = "Поле familyName не должно быть пустым.")
    @Length(max = 255, message = "Максимальная длина 255 символов.")
    @Schema(description = "Фамилия студента.")
    private String familyName;

    @NotNull(message = "Поле age не должно быть пустым.")
    @Positive(message = "Поле age должно быть положительным.")
    @Max(value = 150, message = "Возраст не может быть больше 150.")
    @Schema(description = "Возраст студента.")
    private Integer age;
}
