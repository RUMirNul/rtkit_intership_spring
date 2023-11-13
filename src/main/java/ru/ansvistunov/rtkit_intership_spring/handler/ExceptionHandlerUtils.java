package ru.ansvistunov.rtkit_intership_spring.handler;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Утилитарный класс для обработки исключений.
 */
public final class ExceptionHandlerUtils {
    private ExceptionHandlerUtils() {
    }

    /**
     * Создает строку с сообщением об ошибке для логирования, включая сообщение исключения и, если есть, сообщение его причины.
     *
     * @param t Исключение.
     * @return Строка с сообщением об ошибке.
     */
    public static String buildErrorMessage(Throwable t) {
        StringBuilder message =
                new StringBuilder(ExceptionUtils.getMessage(t));

        Throwable cause;
        if ((cause = t.getCause()) != null) {
            message.append(", cause: ").append(ExceptionUtils.getMessage(cause));
        }

        return message.toString();
    }
}
