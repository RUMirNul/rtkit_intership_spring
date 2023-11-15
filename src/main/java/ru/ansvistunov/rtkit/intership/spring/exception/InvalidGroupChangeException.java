package ru.ansvistunov.rtkit.intership.spring.exception;

/**
 * Исключение, выбрасываемое в случае недопустимой попытки изменения группы ученика.
 */
public class InvalidGroupChangeException extends RuntimeException {
    public InvalidGroupChangeException(String message) {
        super(message);
    }
}
