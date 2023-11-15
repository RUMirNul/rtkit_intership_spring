package ru.ansvistunov.rtkit.intership.spring.web.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Класс представляющий информацию о нарушении валидации для конкретного поля.
 */
@Getter
@RequiredArgsConstructor
public class Violation {

    /**
     * Имя поля, в котором произошло нарушение валидации.
     */
    private final String fieldName;

    /**
     * Сообщение об ошибке валидации для указанного поля.
     */
    private final String message;

}
