package ru.ansvistunov.rtkit.intership.spring.web.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Класс ответа на запрос с ошибками валидации.
 */
@Getter
@RequiredArgsConstructor
public class ValidationErrorResponse {
    /**
     * Список нарушений валидации.
     */
    private final List<Violation> violations;
}