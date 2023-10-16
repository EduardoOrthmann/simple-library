package com.example.simplelibrary.exceptions.handlers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ErrorDto {
    @Schema(example = "Parâmetro de rota inválido", description = "Error message")
    private final String message;

    @Schema(example = "400", description = "HTTP status code")
    private final String status;

    private final List<FieldErrorDto> errors;
}
