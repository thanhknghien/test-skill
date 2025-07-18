package com.thanh.user_managerment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException extends RuntimeException {
    private final Map<String, String> errors = new HashMap<>();

    public GlobalHandlerException(String message) {
        super(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerValidationException(MethodArgumentNotValidException ex){
        ex.getBindingResult().getFieldErrors().forEach(
                fieldError -> {
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
        );
        return ResponseEntity.badRequest().body(
                Map.of(
                        "status", 400,
                        "time", LocalDate.now(),
                        "message", "Validation failed!",
                        "errors", errors
                )
        );
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handlerEmailAlreadyExistsException(EmailAlreadyExistsException ex){
        return ResponseEntity.badRequest().body(
                Map.of(
                        "status", 400,
                        "time", LocalDate.now(),
                        "message", "Email already exists!",
                        "errors", ex.getMessage()
                )
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handlerUserAlreadyExistsException(UserAlreadyExistsException ex){
        return ResponseEntity.badRequest().body(
                Map.of(
                        "status", 400,
                        "time", LocalDate.now(),
                        "message", "User already exists!",
                        "errors", ex.getMessage()
                )
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handlerUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.badRequest().body(
                Map.of(
                        "status", 404,
                        "time", LocalDate.now(),
                        "message", "User not found!",
                        "errors", ex.getMessage()
                )
        );
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> handlerInvalidException(InvalidPasswordException ex){
        return ResponseEntity.badRequest().body(
                Map.of(
                        "status", 400,
                        "time", LocalDate.now(),
                        "message", "Incorrect password!",
                        "errors", ex.getMessage()
                )
        );
    }
}
