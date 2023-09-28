package com.example.simplelibrary.exceptions.handlers;

import com.example.simplelibrary.exceptions.handlers.dto.ErrorDto;
import com.example.simplelibrary.exceptions.handlers.dto.FieldErrorDto;
import com.example.simplelibrary.utils.ErrorMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(
                ErrorDto.builder()
                        .message(ErrorMessages.VALIDATION_ERROR)
                        .status(HttpStatus.BAD_REQUEST.name())
                        .errors(
                                ex.getBindingResult().getFieldErrors().stream()
                                        .map(fieldError -> new FieldErrorDto(fieldError.getField(), fieldError.getDefaultMessage()))
                                        .toList()
                        )
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body(
                ErrorDto.builder()
                        .message(ErrorMessages.VALIDATION_ERROR)
                        .status(HttpStatus.BAD_REQUEST.name())
                        .errors(List.of(
                                new FieldErrorDto(ex.getName(), ErrorMessages.INVALID_UUID)
                        ))
                        .build()
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.name())
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .build()
        );
    }
}
