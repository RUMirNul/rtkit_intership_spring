package ru.ansvistunov.rtkit.intership.spring.web.response;

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
