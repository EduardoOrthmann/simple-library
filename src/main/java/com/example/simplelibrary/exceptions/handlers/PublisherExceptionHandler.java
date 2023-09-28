package com.example.simplelibrary.exceptions.handlers;

import com.example.simplelibrary.exceptions.PublisherNameAlreadyExists;
import com.example.simplelibrary.exceptions.handlers.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PublisherExceptionHandler {

    @ExceptionHandler(PublisherNameAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handlePublisherNameAlreadyExists(PublisherNameAlreadyExists ex) {
        return ResponseEntity.badRequest().body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .build()
        );
    }
}
