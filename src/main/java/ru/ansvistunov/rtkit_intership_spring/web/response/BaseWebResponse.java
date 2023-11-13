package ru.ansvistunov.rtkit_intership_spring.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Базовый класс для HTTP-ответов ошибок.
 */
@Data
@AllArgsConstructor
public class BaseWebResponse {
    private String errorMessage;
}
