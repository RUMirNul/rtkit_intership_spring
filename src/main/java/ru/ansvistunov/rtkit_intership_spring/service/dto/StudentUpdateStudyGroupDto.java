package ru.ansvistunov.rtkit_intership_spring.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentUpdateStudyGroupDto {
    @NotNull(message = "Поле studentId не должно быть пустым.")
    private Integer studentId;

    @NotNull(message = "Поле groupId не должно быть пустым.")
    private Integer groupId;
}
