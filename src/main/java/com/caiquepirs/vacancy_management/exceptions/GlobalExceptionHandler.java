package com.caiquepirs.vacancy_management.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        List<ErrorMessageDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors()
                .forEach(err -> {
                    String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
                    ErrorMessageDTO error = new ErrorMessageDTO(err.getField(), message);
                    dto.add(error);
                });
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerUserNotFound(UserNotFoundException e) {
        ErrorMessageDTO error = new ErrorMessageDTO("User", e.getMessage());
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                List.of(error));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerUserFound(UserFoundException e) {
        ErrorMessageDTO error = new ErrorMessageDTO("User", e.getMessage());
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                List.of(error));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerJobNotFound(JobNotFoundException e){
        ErrorMessageDTO error = new ErrorMessageDTO("Job", e.getMessage());
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                List.of(error));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ErrorResponseDTO> handlerDuplicateRecord(DuplicateRecordException e){
        ErrorMessageDTO error = new ErrorMessageDTO("Conflict", e.getMessage());
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                List.of(error));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidFormat(HttpMessageNotReadableException ex) {
        String message = "Invalid input format. Please check the data sent.";

        ErrorMessageDTO error = new ErrorMessageDTO("Input", message);
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                message,
                List.of(error));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        ErrorMessageDTO errorField = new ErrorMessageDTO("URL", "Method Not Allowed");
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                "Method not allowed for this endpoint.",
                List.of(errorField)
        );
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleInternalError(RuntimeException e) {
        ErrorMessageDTO error = new ErrorMessageDTO("Exception", e.getClass().getSimpleName());
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred",
                List.of(error));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }
}
