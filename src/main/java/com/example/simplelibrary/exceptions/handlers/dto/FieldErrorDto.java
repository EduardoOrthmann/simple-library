package com.example.simplelibrary.exceptions.handlers.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record FieldErrorDto(
        @Schema(example = "name", description = "The field that has the error")
        String field,

        @Schema(example = "Name is required", description = "The error message of the field")
        String message
) {
}
