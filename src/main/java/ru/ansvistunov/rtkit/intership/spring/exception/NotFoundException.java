package ru.ansvistunov.rtkit.intership.spring.exception;

/**
 * Исключение, выбрасываемое в случае, когда запрашиваемый ресурс не найден.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
