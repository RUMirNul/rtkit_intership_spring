package ru.ansvistunov.rtkit_intership_spring.exception;

/**
 * Исключение, выбрасываемое в случае недопустимой попытки изменения группы ученика.
 */
public class InvalidGroupChangeException extends RuntimeException {
    public InvalidGroupChangeException(String message) {
        super(message);
    }
}
