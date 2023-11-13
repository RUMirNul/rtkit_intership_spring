package ru.ansvistunov.rtkit_intership_spring.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Запрос для обновления учебной группы студента.
 */
@Data
@NoArgsConstructor
@Schema(description = "Запрос для добавления пользователя в группу.")
public class StudentUpdateStudyGroupRequest {
    @NotNull(message = "Поле studentId не должно быть пустым.")
    @Schema(description = "Уникальный идентификатор студента.")
    private Integer studentId;

    @NotNull(message = "Поле groupId не должно быть пустым.")
    @Schema(description = "Уникальный идентификатор учебной группы.")
    private Integer groupId;
}
