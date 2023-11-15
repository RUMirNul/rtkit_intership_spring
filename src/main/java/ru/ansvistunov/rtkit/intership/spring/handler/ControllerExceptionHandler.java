package ru.ansvistunov.rtkit.intership.spring.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.ansvistunov.rtkit.intership.spring.exception.InvalidGroupChangeException;
import ru.ansvistunov.rtkit.intership.spring.web.response.BaseWebResponse;
import ru.ansvistunov.rtkit.intership.spring.web.response.ValidationErrorResponse;
import ru.ansvistunov.rtkit.intership.spring.web.response.Violation;
import ru.ansvistunov.rtkit.intership.spring.exception.NotFoundException;

import java.util.List;

/**
 * Глобальный обработчик исключений для контроллеров. Обрабатывает исключения и возвращает соответствующий HTTP-статус и тело ответа.
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    /**
     * Обработчик исключения NotFoundException. Возвращает ответ с HTTP-статусом NOT_FOUND (404).
     *
     * @param e Исключение NotFoundException.
     * @return ResponseEntity с телом ответа в виде BaseWebResponse.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseWebResponse> handleNotFoundExceptionException(@NonNull final NotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseWebResponse(createErrorMessage(e)));
    }

    /**
     * Обработчик исключения InvalidGroupChangeException. Возвращает ответ с HTTP-статусом BAD_REQUEST (400).
     *
     * @param e Исключение InvalidGroupChangeException.
     * @return ResponseEntity с телом ответа в виде BaseWebResponse.
     */
    @ExceptionHandler(InvalidGroupChangeException.class)
    public ResponseEntity<BaseWebResponse> handleInvalidGroupChangeException(@NonNull final InvalidGroupChangeException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseWebResponse(createErrorMessage(e)));
    }

    /**
     * Обработчик исключения ConstraintViolationException. Возвращает ответ с HTTP-статусом BAD_REQUEST (400).
     *
     * @param e Исключение ConstraintViolationException.
     * @return ResponseEntity с телом ответа в виде ValidationErrorResponse, содержащего список нарушений валидации.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> handleConstraintViolationException(@NonNull final ConstraintViolationException e) {
        log.error(e.getMessage());
        List<Violation> violations = e.getConstraintViolations()
                .stream()
                .map(violation -> new Violation(violation.getPropertyPath().toString(), violation.getMessage()))
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorResponse(violations));
    }

    /**
     * Обработчик исключения MethodArgumentNotValidException. Возвращает ответ с HTTP-статусом BAD_REQUEST (400).
     *
     * @param e Исключение MethodArgumentNotValidException.
     * @return ResponseEntity с телом ответа в виде ValidationErrorResponse, содержащего список нарушений валидации.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(@NonNull final MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        List<Violation> violations = e.getBindingResult().getFieldErrors()
                .stream()
                .map(violation -> new Violation(violation.getField(), violation.getDefaultMessage()))
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorResponse(violations));
    }


    /**
     * Создает строку с сообщением об ошибке для логирования.
     *
     * @param exception Исключение.
     * @return Строка с сообщением об ошибке.
     */
    private String createErrorMessage(Exception exception) {
        final String message = exception.getMessage();
        log.error("Ошибка: ", exception);
        return message;
    }
}
