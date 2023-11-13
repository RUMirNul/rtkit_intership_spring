package ru.ansvistunov.rtkit_intership_spring.exception;

public class InvalidGroupChangeException extends RuntimeException{
    public InvalidGroupChangeException(String message) {
        super(message);
    }
}
