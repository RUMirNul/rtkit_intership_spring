package ru.ansvistunov.rtkit_intership_spring.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.ansvistunov.rtkit_intership_spring.exception.InvalidGroupChangeException;
import ru.ansvistunov.rtkit_intership_spring.exception.NotFoundException;
import ru.ansvistunov.rtkit_intership_spring.web.response.BaseWebResponse;
import ru.ansvistunov.rtkit_intership_spring.web.response.ValidationErrorResponse;
import ru.ansvistunov.rtkit_intership_spring.web.response.Violation;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseWebResponse> handleNotFoundExceptionException(@NonNull final NotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseWebResponse(createErrorMessage(e)));
    }

    @ExceptionHandler(InvalidGroupChangeException.class)
    public ResponseEntity<BaseWebResponse> handleInvalidGroupChangeException(@NonNull final InvalidGroupChangeException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseWebResponse(createErrorMessage(e)));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> handleConstraintViolationException(@NonNull final ConstraintViolationException e) {
        log.error(e.getMessage());
        List<Violation> violations = e.getConstraintViolations()
                .stream()
                .map(violation -> new Violation(violation.getPropertyPath().toString(), violation.getMessage()))
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorResponse(violations));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(@NonNull final MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        List<Violation> violations = e.getBindingResult().getFieldErrors()
                .stream()
                .map(violation -> new Violation(violation.getField(), violation.getDefaultMessage()))
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorResponse(violations));
    }


    private String createErrorMessage(Exception exception) {
        final String message = exception.getMessage();
        log.error(ExceptionHandlerUtils.buildErrorMessage(exception));
        return message;
    }
}
