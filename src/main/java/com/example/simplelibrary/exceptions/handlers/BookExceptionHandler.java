package com.example.simplelibrary.exceptions.handlers;

import com.example.simplelibrary.exceptions.IsbnAlreadyExistsException;
import com.example.simplelibrary.exceptions.PublicationYearTooNew;
import com.example.simplelibrary.exceptions.handlers.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler(IsbnAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleIsbnAlreadyExists(IsbnAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .build()
        );
    }

    @ExceptionHandler(PublicationYearTooNew.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handlePublicationYearTooNew(PublicationYearTooNew ex) {
        return ResponseEntity.badRequest().body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .build()
        );
    }
}
